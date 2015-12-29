package com.dsj.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

import com.dsj.bookstore.book.domain.Book;
import com.dsj.bookstore.order.domain.Order;
import com.dsj.bookstore.order.domain.OrderItem;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 添加订单
	 * 
	 * @param order
	 */
	public void addOrder(Order order) {
		try {
			String sql = "insert into orders values(?,?,?,?,?,?)";
			/*
			 * 处理util的Date转换成sql的Timestamp
			 */
			Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
			Object[] params = { order.getOid(), timestamp, order.getTotal(),
					order.getState(), order.getOwner().getUid(),
					order.getAddress() };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 插入订单条目
	 * 
	 * @param OrderItemList
	 */
	public void addOrderItemList(List<OrderItem> OrderItemList) {
		try {
			/**
			 * QueryRunner类的batch(String sql,Object[][] parpams)
			 * 其中parpams是多个一维数组！
			 */
			String sql = "insert into orderitem values(?,?,?,?,?)";
			/*
			 * 把orderItemLIst转化成二位数组 把一个OrderItem对象转换成一个一维数组
			 */
			Object[][] params = new Object[OrderItemList.size()][];
			// 循环遍历orderItemList,使用每个orderItem对象为parpams中每一个一维数组赋值
			for (int i = 0; i < OrderItemList.size(); i++) {
				OrderItem item = OrderItemList.get(i);
				params[i] = new Object[] { item.getIid(), item.getCount(),
						item.getSubtotal(), item.getOrder().getOid(),
						item.getBook().getBid() };
			}
			qr.batch(sql, params); // 执行批处理
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按uid查询订单
	 * 
	 * @param uid
	 * @return
	 */
	public List<Order> fingByUid(String uid) {
		/*
		 * 1.通过uid查询出当前用户的所有List<Order> 2.循环遍历每个Order，为其加载它的所有OrderItem 3.返回订单列表
		 */
		try {
			// 得到当前用户的所有订单
			String sql = "select * from orders where uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(
					Order.class), uid);
			// 循环遍历每个Order，为其加载它自己所有的订单条目
			for (Order order : orderList) {
				loadOrderItems(order); // 为Order对象加载它的所有订单条目
			}
			// 返回订单列表
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 加载指定订单的所有的订单条目
	 * 
	 * @param order
	 */
	private void loadOrderItems(Order order) {
		try {
			/*
			 * 查询两张表:orderitem和book
			 */
			String sql = "select * from orderitem i,book b where i.bid=b.bid and oid=?";
			//因为一行结果集对应的不再是一个javaBean，所以不能再使用BeanListHandler,而是MapListHandler
			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
			/*
			 * mapList十多个Map，每个map对应一行结果集
			 * 我们需要使用Map生成两个对象，OrderItem和Book，然后再建立两者的关系(把Book设置给OrderItem)
			 * 循环遍历每个Map，使用map生成两个对象，然后建立关系(最终结果是OrderItem)，把OrderItem保存起来
			 */
				List<OrderItem> orderItemList=toOrderItemList(mapList);
				order.setOrderItemList(orderItemList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 把maplist中每个Map转换成两个对象并建立关系
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		for(Map<String,Object> map:mapList){
			OrderItem item=toOrderItem(map);
			orderItemList.add(item);
		}
		return orderItemList;
	}
	
	/**
	 * 把一个Map转换成一个OrderItem对象
	 * @param map
	 * @return
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem=CommonUtils.toBean(map, OrderItem.class);
		Book book=CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	/**
	 * 加载订单
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		try{
			String sql = "select * from orders where oid=?";
			Order order=qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			loadOrderItems(order);
			return order;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 通过oid查询订单状态
	 * @param oid
	 * @return
	 */
	public int getStateByOid(String oid){
		try{
		String sql="select state from orders where oid=?";	
		 return (Integer)qr.query(sql,new ScalarHandler(),oid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 修改订单状态
	 * @param oid
	 * @param state
	 * @return
	 */
	public void updateState(String oid,int state){
		try{
		String sql="update orders set state=? where oid=?";	
		 qr.update(sql,state,oid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
