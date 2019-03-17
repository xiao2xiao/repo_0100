package com.neno.model;

import java.util.Date;

/**
 * @Author: root
 * @Date: 2019/3/17 20:54
 */
public class ProductInfo {
    private long id;
    private String name;
    private double price;
    private String specification;
    private Date modifiedTime;
    private long shopId;

    public ProductInfo() {
    }

    public ProductInfo(long id, String name, double price, String specification, Date modifiedTime, long shopId) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.specification = specification;
        this.modifiedTime = modifiedTime;
        this.shopId = shopId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "ProductInfo [id=" + id + ", name=" + name + ", price=" + price + ", specification=" + specification
                + ", modifiedTime=" + modifiedTime + ", shopId=" + shopId + "]";
    }
}
