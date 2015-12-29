package demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import demo.bean.User;
import demo.util.conn;

public class UserDAO {

	/**
	 * @param args
	 * 包括判断用户登陆的方法
	 */
	//用户名密码 登陆
			public boolean Exit(User user)
			{
				Connection con=conn.getcon();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String sql="select * from clienttable where ClientName=? and ClientCode=?";
				try {
					ps=con.prepareStatement(sql);
					ps.setString(1, user.getName());
					ps.setString(2, user.getCode());
					rs=ps.executeQuery();
					if(rs.next())
					{
						return true;
					}
					else
					{
						return false;
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return false;
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
		//添加用户
		public boolean Add(User user)
		{
			Connection con=conn.getcon();
			PreparedStatement ps=null;
		    String sql="insert into clienttable(ClientName,ClientCode,ClientRName,ClientPhone,ClientAdd,ClientMail,ClientPost)"
			 +"values(?,?,?,?,?,?,?)";
		   try {
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getCode());
			ps.setString(3, user.getTruename());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getAdd());
			ps.setString(6, user.getMail());
			ps.setString(7,user.getPost());
			int a=ps.executeUpdate();
			if(a>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		   finally
		   {
			   try {
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
