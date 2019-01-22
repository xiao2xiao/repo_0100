package com.neno.filter;

/**
 * @Author: root
 * @Date: 2018/12/16 16:22
 */
//public class AccessFilter extends ZuulFilter {
//    private final Logger logger = LoggerFactory.getLogger(AccessFilter.class);
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        logger.info("send {} request to {}", request.getMethod(), request.getRequestURI());
//        Object accessToken = request.getParameter("accessToken");
//        if (accessToken == null) {
//            logger.info("accessToken is empty.....");
//            /**
//             * 使zuul过滤该请求，不对其进行路由
//             */
//            ctx.setSendZuulResponse(false);
//            /**
//             * 返回错误的状态码
//             */
//            ctx.setResponseStatusCode(401);
//            return null;
//        }
//        logger.info("accessToken ok............");
//        return null;
//    }
//}
