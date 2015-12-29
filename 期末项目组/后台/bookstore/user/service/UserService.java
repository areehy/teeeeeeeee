package com.dsj.bookstore.user.service;


import com.dsj.bookstore.user.dao.UserDao;
import com.dsj.bookstore.user.domain.User;

/**
 * User业务层
 * @author Administrator
 *
 */
public class UserService {
	private UserDao userDao=new UserDao();
	
	/**
	 * 注册功能
	 * @param user
	 * @throws UserException 
	 */
	public void regist(User form) throws UserException{
		//校验用户名
		User user=userDao.findByUsername(form.getUsername());
		if(user!=null) throw new UserException("用户名已经被注册！");
		//校验邮箱
		user=userDao.findByEmail(form.getEmail());
		if(user!=null) throw new UserException("邮箱已经被注册！");
		//插入用户到数据库
		userDao.add(form);
	}
	
	/**
	 * 激活功能
	 * @throws UserException 
	 */
	public void active(String code) throws UserException {
		/*
		 * 1.使用code查询数据库，得到User
		 */
		User user=userDao.findByCode(code);
		/*
		 * 2.如果user不存在，说明激活码错误
		 */
		if(user==null) throw new UserException("激活码无效！");
		/*
		 * 3.校验用户的状态是否为未激活状态，如果已激活，说明是二次激活，抛出异常
		 */
		if(user.isState()) throw new UserException("您已经激活过了！");
		/*
		 * 4.修改用户的状态
		 */
		userDao.updateState(user.getUid(), true);
	
	}
	
	/**
	 * 登录方法
	 * @param form
	 * @return
	 * @throws UserException 
	 */
	public User login(User form) throws UserException{
		/*
		 * 1.使用username查询，得到User
		 * 2.如果user为null,抛出异常
		 * 3.比较form和user的密码，若不同，抛出异常
		 * 4.查看用户状态，若为false，抛出异常
		 * 5.返回user
		 */
		User user=userDao.findByUsername(form.getUsername());
		if(user==null) throw new UserException("用户名不存在！");
		if(!user.getPassword().equals(form.getPassword()))
			throw new UserException("密码错误！");
		if(!user.isState()) throw new UserException("邮箱尚未激活！");
		return user;
		
	}
}
