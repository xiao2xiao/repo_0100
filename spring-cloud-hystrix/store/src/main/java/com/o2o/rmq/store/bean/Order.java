package com.o2o.rmq.store.bean;

import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Double price;
	private String remark;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(Long id, Double price, String remark) {
		super();
		this.id = id;
		this.price = price;
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", price=" + price + ", remark=" + remark + "]";
	}
}
