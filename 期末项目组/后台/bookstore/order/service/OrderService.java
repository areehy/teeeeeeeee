package com.dsj.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;

import com.dsj.bookstore.order.dao.OrderDao;
import com.dsj.bookstore.order.domain.Order;

public class OrderService {
	private OrderDao orderDao=new OrderDao();
	
	/**
	 * 支付方法
	 * @param oid
	 */
	public void pay(String oid){
		/*
		 * 1.获取订单状态
		 * 	 ->如果状态为1，那么执行下面代码
		 * 	 ->如果状态不为1，那么结束
		 */
		int state=orderDao.getStateByOid(oid);
		if(state==1){
			//修改订单
			orderDao.updateState(oid, 2);
		}
	}
	/**
	 * 添加订单
	 * @param order
	 */
	public void add(Order order){
		try{
			//开启事物
			JdbcUtils.beginTransaction();
			orderDao.addOrder(order);	//插入订单
			orderDao.addOrderItemList(order.getOrderItemList());	//插入订单中的所有条目
			//提交事务
			JdbcUtils.commitTransaction();
		}catch(Exception e){			
			try {
				//回滚事务
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {

			}
			throw new RuntimeException(e);
		}
	}

	/**
	 * 我的订单
	 * @param uid
	 * @return
	 */
	public List<Order> myOrders(String uid) {
		return orderDao.fingByUid(uid);
	}
	
	/**
	 * 加载订单
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		return orderDao.load(oid);
	}
	
	/**
	 * 确认收获
	 * @param oid
	 * @throws OrderException
	 */
	public void confirm(String oid) throws OrderException{
		/*
		 * 1.检验订单状态，如果不是3，则抛出异常
		 */
		int state=orderDao.getStateByOid(oid);  //	获取订单状态
		if(state!=3) throw new OrderException("订单确认失败！");
		/*
		 * 2.修改订单状态为4，表示交易成功！
		 */
		orderDao.updateState(oid, 4);
	}
}
