package com.neno.controller;

import com.alibaba.fastjson.JSONObject;
import com.neno.hystrix.command.GetProductInfoCommand;
import com.neno.hystrix.command.GetProductInfosCommand;
import com.neno.hystrix.command.GetShopNameCommand;
import com.neno.model.ProductInfo;
import com.neno.service.ProductService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixObservableCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;

import java.util.Arrays;

/**
 * @Author: root
 * @Date: 2019/3/17 20:49
 */
@Controller
public class CacheController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/change/getProductInfo")
    @ResponseBody
    public String changeProductInfo(Long productId) {
        ProductInfo productInfo = productService.getProductById(productId);
        LOGGER.info(JSONObject.toJSONString(productInfo));
        return "success";
    }


    /**
     * 通过继承HystrixCommand<T>方法实现,查询一条数据
     */
    @RequestMapping("/getProductInfo")
    @ResponseBody
    public String getProductInfo(Long productId) {
        HystrixCommand<ProductInfo> productInfoHystrixCommand = new GetProductInfoCommand(productId, restTemplate);
        ProductInfo productInfo = productInfoHystrixCommand.execute();

        Long shopId = productInfo.getShopId();
        HystrixCommand<String> shopNameHystrixCommand = new GetShopNameCommand(shopId);
        String shopName = shopNameHystrixCommand.execute();
        productInfo.setShopName(shopName);
        LOGGER.info(productInfo.toString());
        return "success";
    }

    /**
     * 通过继承HystrixObservableCommand<T>方法实现
     */
    @RequestMapping("/getProductInfos")
    @ResponseBody
    public String getProductInfos(String productIds) {
        String[] p = productIds.split(",");
        long[] pp = Arrays.stream(p).mapToLong(Long::valueOf).toArray();
        HystrixObservableCommand<ProductInfo> hystrixObservableCommand = new GetProductInfosCommand(pp, restTemplate);
        Observable<ProductInfo> observable = hystrixObservableCommand.observe();
        observable.subscribe(new Observer<ProductInfo>() {
            @Override
            public void onCompleted() {
                LOGGER.info("接受完了数据。。。。。。。。。。。。。。。。");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(ProductInfo productInfo) {
                LOGGER.info(productInfo.toString());
            }
        });
        return "success";
    }

    /**
     * 通过继承HystrixCommand<T>方法实现
     * 通过走request cache实现
     */
    @RequestMapping("/getProductInfosFromCache")
    @ResponseBody
    public String getProductInfosFromCache(String productIds) {
        long[] pp = Arrays.stream(productIds.split(",")).mapToLong(Long::valueOf).toArray();
        HystrixCommand<ProductInfo> infoHystrixCommand = null;
        for (Long productId : pp) {
            infoHystrixCommand = new GetProductInfoCommand(productId, restTemplate);
            ProductInfo productInfo = infoHystrixCommand.execute();
            LOGGER.info(productInfo.toString());
            LOGGER.info("是否从缓存中获取：" + infoHystrixCommand.isResponseFromCache());
        }
        return "success";
    }
}
