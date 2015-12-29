package com.dsj.bookstore.order.domain;

import com.dsj.bookstore.book.domain.Book;

/**
 * 订单条目类
 * @author Administrator
 *
 */
public class OrderItem {
	private String iid;
	private int count;
	private double subtotal;
	private Order order;
	private Book book;	//所要购买的图书
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
}
