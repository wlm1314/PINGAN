package com.pingan.bank.libs.myapplication;

import java.io.Serializable;

/**
 * Order
 * 
 * @author ZHAOYONG
 * 
 */
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4133141497038361084L;
	private String order;
	private String orderSign;
	private String appkey;
	private String sno;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderSign() {
		return orderSign;
	}

	public void setOrderSign(String orderSign) {
		this.orderSign = orderSign;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	@Override
	public String toString() {
		return "Order [order=" + order + ", orderSign=" + orderSign
				+ ", appkey=" + appkey + "]";
	}
}
