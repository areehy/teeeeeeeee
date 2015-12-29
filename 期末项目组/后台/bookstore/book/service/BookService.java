package com.dsj.bookstore.book.service;

import java.util.List;

import com.dsj.bookstore.book.dao.BookDao;
import com.dsj.bookstore.book.domain.Book;

public class BookService {
	private BookDao bookDao=new BookDao();
	
	public List<Book> findAll(){
		return bookDao.findAll();
	}

	public List<Book> findByCategory(String cid) {
		return bookDao.findByCategory(cid);
	}

	public Book findByBid(String bid) {
		return bookDao.findByBid(bid);
	}

	/**
	 * 添加图书
	 * @param book
	 */
	public void add(Book book) {
		bookDao.add(book);	
	}
	
	public void delete(String bid){
		bookDao.delete(bid);
	}

	public void edit(Book book) {
		bookDao.edit(book);
		
	}
}
