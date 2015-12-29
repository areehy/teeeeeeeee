package com.dsj.bookstore.cart.domain;

import java.math.BigDecimal;

import com.dsj.bookstore.book.domain.Book;

/**
 * 购物车条目类
 * @author Administrator
 *
 */
public class CartItem {
	private Book book;	//商品
	private int count;		//数量
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	} 
	/**
	 * 小计方法
	 * 处理了二进制误差问题
	 * @return
	 */
	public double getSubtotal(){	//小计
		BigDecimal d1=new BigDecimal(book.getPrice()+"");
		BigDecimal d2=new BigDecimal(count+"");
		return d1.multiply(d2).doubleValue();
	}

}
