package com.dsj.bookstore.category.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsj.bookstore.category.domain.Category;
import com.dsj.bookstore.category.service.CategoryService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
	private CategoryService categoryService=new CategoryService();

	/**
	 * 修改分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category= CommonUtils.toBean(request.getParameterMap(), Category.class);  
		categoryService.edit(category);
		return findAll(request, response);
	}
	/**
	 * 修改准备
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid=request.getParameter("cid");
		request.setAttribute("category",categoryService.load(cid));
		return "f:/adminjsps/admin/category/mod.jsp";
	}
	/**
	 * 删除图书分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid=request.getParameter("cid");
		try{
		categoryService.delete(cid);
		return findAll(request, response);
		}catch(CategoryException e){
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/msg.jsp";
		}
		
	}
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.封装表单数据
		 * 2.补全：uid
		 * 3.调用service方法完成添加功能
		 * 4.调用findAll()
		 */
		Category category=new Category();
		String cname=request.getParameter("cname");
		category.setCname(cname);
		category.setCid(CommonUtils.uuid());
		categoryService.add(category);
		return findAll(request, response);
	}
	/**
	 * 获得所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 调用service方法，得到所有分类
		 * 保存到request域，转发到adminjsps/left.jsp
		 */
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/category/list.jsp";
	}
	
	
}
