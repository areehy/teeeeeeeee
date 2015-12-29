package com.dsj.bookstore.book.web.servlet.admin;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import com.dsj.bookstore.book.domain.Book;
import com.dsj.bookstore.book.service.BookService;
import com.dsj.bookstore.category.domain.Category;
import com.dsj.bookstore.category.service.CategoryService;

public class AdminBookServlet extends BaseServlet {
	private BookService bookService=new BookService();
	private CategoryService categoryService=new CategoryService();
	
	/**
	 * 修改功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book=CommonUtils.toBean(request.getParameterMap(), Book.class);
		Category category=CommonUtils.toBean(request.getParameterMap(), Category.class);
		book.setCategory(category);
		bookService.edit(book);
		return findAll(request, response);
	}
	/**
	 * 删除功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid=request.getParameter("bid");
		bookService.delete(bid);
		return findAll(request, response);
	}
	/**
	 * 添加之前
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 *查询所有分类，保存到request中，转发到add.jsp
		 *add.jsp中所有分类使用下拉列表显示在表单中 
		 */
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/add.jsp";
	}
	/**
	 * 加载图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid=request.getParameter("bid");
		request.setAttribute("book", bookService.findByBid(bid));
		//还需要获取所有分类
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/desc.jsp";
	}
	/**
	 * 查看所有图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAll());
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	
}
