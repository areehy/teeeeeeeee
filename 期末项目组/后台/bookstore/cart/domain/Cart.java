package com.dsj.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 购物车类
 * @author Administrator
 *
 */
public class Cart {
	private Map<String,CartItem> map=new LinkedHashMap<String, CartItem>();
	
	/**
	 * 计算合计
	 * @return
	 */
	public double getTotal(){
		BigDecimal total=new BigDecimal("0");
		for(CartItem cartItem:map.values()){
			BigDecimal subtotal=new BigDecimal(cartItem.getSubtotal()+"");
			total=total.add(subtotal);
		}
		return total.doubleValue();
	}
	
	/**
	 * 添加条目到购物车中
	 * @param caetItem
	 */
	public void add(CartItem cartItem){
		if(map.containsKey(cartItem.getBook().getBid())){	//判断原来车中是否存在该条目
			CartItem _cartItem=map.get(cartItem.getBook().getBid());	//返回原条目
			_cartItem.setCount(cartItem.getCount()+_cartItem.getCount());  //设置老条目的数量为，其自己数量加上新条目的数量
			map.put(cartItem.getBook().getBid(), _cartItem);
		}else{
			map.put(cartItem.getBook().getBid(), cartItem);
		}
	}
	
	/**
	 * 清空购物车
	 */
	public void clear(){
		map.clear();
	}
	
	/**
	 * 删除购物车中物品
	 * @param bid
	 */
	public void delete(String bid){
		map.remove(bid);
	}
	
	/**
	 * 我的购物车
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		
		return map.values();
	}
}
