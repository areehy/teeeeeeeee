package demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import demo.bean.Order;
import demo.bean.User;
import demo.util.conn;

public class OrderDAO {

	/**
	 * @param args
	 */
	public ArrayList<Order> getlist(User user)
	 {
		 ArrayList<Order> list=new ArrayList<Order>();
		 Order order=new Order();
		 Connection con=conn.getcon();
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 String sql="select * from ordertable where ClientName=?";
		 try {
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getName());
			rs=ps.executeQuery();
			while(rs.next())
			{
				order.setNo(rs.getInt("OrderNo"));
				order.setClientname(rs.getString("ClientName"));
				order.setName(rs.getString("OrderName"));
				order.setAdd(rs.getString("OrderAdd"));
				order.setPost(rs.getString("OrderPost"));
				order.setPhone(rs.getString("OrderPhone"));
				order.setPayway(rs.getString("OrderWay"));
				order.setRemark(rs.getString("OrderRemark"));
				order.setTime(rs.getString("OrderTime"));
				order.setMark(rs.getString("OrderDeal"));
                list.add(order);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		 finally
		 {
			 try {
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
				}
				if(con!=null)
				{
					con.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		 }
	 }
}
