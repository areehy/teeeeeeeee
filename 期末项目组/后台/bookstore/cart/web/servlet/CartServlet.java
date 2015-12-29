package com.dsj.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

import com.dsj.bookstore.book.domain.Book;
import com.dsj.bookstore.book.service.BookService;
import com.dsj.bookstore.cart.domain.Cart;
import com.dsj.bookstore.cart.domain.CartItem;

public class CartServlet extends BaseServlet {
	
	/**
	 * 添加购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.得到车
		 * 2.得到图书和数量
		 */
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		/*
		 * 1.先得到图书的bid通过bid查询数据库得到Book
		 * 2.表单中直接得到数量
		 * 3.将条目添加到购物车
		 */
		String bid=request.getParameter("bid");
		Book book=new BookService().findByBid(bid);
		int count=Integer.parseInt(request.getParameter("count"));
		CartItem cartItem=new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		cart.add(cartItem);
		return "f:/jsps/cart/list.jsp";
	}
	
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.得到车
		 * 2.清空购物车
		 */
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/jsps/cart/list.jsp";
	}
	
	/**
	 * 删除购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.得到车
		 * 2.得到要删除的id
		 */
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		String bid=request.getParameter("bid");
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
	}
}


