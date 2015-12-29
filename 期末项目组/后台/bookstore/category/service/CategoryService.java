package com.dsj.bookstore.category.service;

import java.util.List;

import com.dsj.bookstore.book.dao.BookDao;
import com.dsj.bookstore.category.dao.CategoryDao;
import com.dsj.bookstore.category.domain.Category;
import com.dsj.bookstore.category.web.servlet.admin.CategoryException;

public class CategoryService {
	private CategoryDao categoryDao=new CategoryDao();
	private BookDao bookDao=new BookDao();
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/**
	 * 添加分类
	 * @param category
	 */
	public void add(Category category) {
		categoryDao.add(category);
		
	}
	
	/**
	 * 删除分类
	 * @param cid
	 * @throws CategoryException 
	 */
	public void delete(String cid) throws CategoryException {
		//获取该分类的图书数
		int count=bookDao.getCountByCid(cid);
		//判断，如果图书数大于0，不能删除，抛出异常
		if(count>0) throw new CategoryException("该分类下还有图书，不能删除！");
		//删除
		categoryDao.delete(cid);
	}

	/**
	 * 加载
	 * @param cid
	 * @return
	 */
	public Category load(String cid) {
		return categoryDao.load(cid);
	}

	/**
	 * 修改
	 * @param category
	 */
	public void edit(Category category) {
		categoryDao.edit(category);
		
	}
}
