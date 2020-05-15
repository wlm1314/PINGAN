package com.pingan.bank.libs.myapplication;

import java.io.Serializable;
import java.util.List;

public class Orders implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7068535321155918872L;
	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Orders [orders=" + orders + "]";
	}
}
