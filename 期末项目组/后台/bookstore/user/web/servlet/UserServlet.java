package com.dsj.bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

import com.dsj.bookstore.cart.domain.Cart;
import com.dsj.bookstore.user.domain.User;
import com.dsj.bookstore.user.service.UserException;
import com.dsj.bookstore.user.service.UserService;
/**
 * User表述层
 * @author Administrator
 *
 */
public class UserServlet extends BaseServlet {
	private UserService userService=new UserService();
	
	/**
	 * 退出方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		return "r:/index.jsp";
	}
	
	/**
	 * 登录方法
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.封装表单数据到form中
		 * 2.输入检验
		 * 3.调用service完成激活
		 * 	  ->保存错误信息，form到request,转发到login.jsp
		 * 4.保存用户信息到session中，然后重定向到index.jsp
		 */
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user=userService.login(form);
			request.getSession().setAttribute("session_user", user);
			/*
			 * 给用户添加一辆购物车，即向session中保存一个Cart对象
			 */
			request.getSession().setAttribute("cart", new Cart());
			return "r:/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form",form);
			return "f:/jsps/user/login.jsp";
		}

	}

	/**
	 * 激活功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取参数激活码
		 * 2.调用service方法完成激活
		 * 	  ->保存异常信息到request域，转发到msg.jsp
		 * 	  ->保存成功信息到request域，转发到msg.jsp
		 */
		String code=request.getParameter("code");
		try {
			userService.active(code);
			request.setAttribute("msg","恭喜您已经激活成功！请马上登录！");		
		} catch (UserException e) {
			request.setAttribute("msg",e.getMessage());
		}
		return "f:jsps/msg.jsp";
	}
	/**
	 * 注册功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.封装表单数据到form对象中
		 * 2.补全：uid，code
		 * 3.输入检验
		 * 	->保存错误信息，form到request域，转发到regist.jsp中
		 * 4.调用service方法完成注册
		 * 	->保存错误信息，form到request域，转发到regist.jsp中
		 * 5.发邮件
		 * 6.保存成功信息，转发到msg.jsp
		 */
		//封装
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		//补全
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
		/*
		 * 输入校验
		 * 1.创建一个map用来封装错误信息，其中key为表单字段名称，值为错误信息
		 */
		Map<String,String> errors=new HashMap<String,String>();
		/*
		 * 2.获取form中的username，password，email进行校验
		 */
		String username=form.getUsername();
		if(username==null||username.trim().isEmpty()){
			errors.put("username", "用户名不能为空！");
		}else if(username.length()<3||username.length()>10){
			errors.put("username", "用户名长度必须在3~10之间！");
		}
		String password=form.getPassword();
		if(password==null||password.trim().isEmpty()){
			errors.put("password", "密码不能为空！");
		}else if(password.length()<3||password.length()>10){
			errors.put("password", "密码长度必须在3~10之间！");
		}
		String email=form.getEmail();
		if(email==null||email.trim().isEmpty()){
			errors.put("email", "邮箱不能为空！");
		}else if(!email.matches("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$")){
			errors.put("email", "邮箱格式错误！");
		}
		/*
		 * 3.判断是否存在错误信息
		 */
		if(errors.size()>0){
			/*
			 * 1.保存错误信息
			 * 2.保存表单数据
			 * 3.转发到regist.jsp
			 */
			request.setAttribute("errors",errors);
			request.setAttribute("form",form);
			return "f:/jsps/user/regist.jsp";
		}
		try{
			userService.regist(form);
			/*
			 * 执行到这里说明执行成功，没有抛出异常
			 */
		}catch(UserException e){
			/*
			 * 1.保存错误信息
			 * 2.保存form
			 * 3.转发到regist.jsp
			 */
			request.setAttribute("msg",e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		//发邮件
		/*
		 * 1.准备配置文件
		 * 获取配置文件内容
		 */
		Properties props=new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String host=props.getProperty("host");	//获取服务器主机
		String uname=props.getProperty("uname");
		String pwd=props.getProperty("pwd");
		String from=props.getProperty("from");
		String to=form.getEmail();
		String subject=props.getProperty("subject");
		String content=props.getProperty("content");
		content=MessageFormat.format(content, form.getCode());	//替换占位符
		Session session=MailUtils.createSession(host, uname, pwd);
		Mail mail=new Mail(from, to, subject, content);	//创建邮件对象
		try{
			MailUtils.send(session, mail);	//发邮件
		}catch(MessagingException e){
			
		}
		 /* 1.保存成功信息
		 * 2.转发到msg.jsp
		 */
		request.setAttribute("msg", "恭喜，注册成功！请到邮箱激活！");
		return "f:/jsps/msg.jsp";
	}
	
	
	
}
