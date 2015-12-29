package demo.dao;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.print.attribute.standard.PresentationDirection;

import demo.bean.Book;
import demo.util.conn;

public class BookDAO {
	/**
	 * @param args
	 *具体查找一个对象getmodel(String bookno)、更新一条书本信息update(Book book)、通过书本类型找所有相关书本getlist(String type)
	 */
	
	public Book getmodel(String bookno)  //具体查找一个对象
	{
		Book book=new Book();
		Connection con=conn.getcon();
		//sql执行器对象
	     PreparedStatement ps=null;
	     //结果集对象
	     ResultSet rs=null;
	try {
		String sql="select * from booktable where BookNo="+bookno;
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next())
		{
			book.setNo(rs.getString("BookNo"));
			book.setClientname(rs.getString("ClientName"));
			book.setName(rs.getString("BookName"));
			book.setNum(rs.getInt("BookNum"));
			book.setStyle(rs.getString("BookStyle"));
			book.setCourse(rs.getString("BookCourse"));
		}
		return book;
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
		  }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	public boolean update(Book book)   //更新一条书本信息
	{
		
		Connection con=conn.getcon();
		PreparedStatement ps=null;
		String sql="update booktable set ClientName=?,BookName=?,BookNum=?," +
				"BookStyle=?,BookCourse=? where BookNo=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1,book.getClientname());
			ps.setString(2, book.getName());
			ps.setInt(3, book.getNum());
			ps.setString(4, book.getStyle());
			ps.setString(5, book.getCourse());
			ps.setString(6, book.getNo());
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
   		  }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
    public  ArrayList<Book> getlist(String type)//通过书本类型找所有相关书本	
    {
          Book book =new Book();
          ArrayList<Book> list=new ArrayList<Book>();
          Connection con=conn.getcon();
          PreparedStatement ps=null;
          ResultSet rs=null;
          try {
			String sql="select * from booktable where BookStyle=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, type);
			rs=ps.executeQuery();
			while(rs.next())
			{
				book.setNo(rs.getString("BookNo"));
				book.setClientname(rs.getString("ClientName"));
				book.setName(rs.getString("BookName"));
				book.setNum(rs.getInt("BookNum"));
				book.setStyle(rs.getString("BookStyle"));
				book.setCourse(rs.getString("BookCourse"));
				list.add(book);
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
        		  }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	  }
        	 
      }
    public boolean delete(String no)
    {
    	Connection con=conn.getcon();
        PreparedStatement ps=null;
        String sql="delete from booktable where BookNo=?";
        try {
			ps=con.prepareStatement(sql);
			ps.setString(1, no);
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