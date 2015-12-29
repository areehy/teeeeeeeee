package com.dsj.bookstore.order.domain;

import java.util.Date;
import java.util.List;

import com.dsj.bookstore.user.domain.User;

/**
 * 订单类
 * @author Administrator
 *
 */
public class Order {
	private String oid;
	private Date ordertime;
	private double total;
	private int state;	//订单状态有四种，1.未付款 2.已付款但未发货 3.已发货但未确认收获 4.交易成功
	private User owner;
	private String address;
	private List<OrderItem> orderItemList;
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
