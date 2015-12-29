package com.dsj.bookstore.book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

import com.dsj.bookstore.book.domain.Book;
import com.dsj.bookstore.book.service.BookService;


public class BookServlet extends BaseServlet {
	private BookService bookService=new BookService();
	
	
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid=request.getParameter("bid");
		request.setAttribute("bookInf",bookService.findByBid(bid) );
		return "f:/jsps/book/desc.jsp";
	}
	
	/**
	 * 查询所有图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("bookList",bookService.findAll());
		return "f:/jsps/book/list.jsp";
	}
	
	/**
	 * 按分类查询图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid=request.getParameter("cid");
		request.setAttribute("bookList",bookService.findByCategory(cid));
		return "f:/jsps/book/list.jsp";
	}
	
	
}
