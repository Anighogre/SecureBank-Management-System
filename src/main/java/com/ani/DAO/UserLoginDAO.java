package com.ani.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ani.bean.CustomerBean;
import com.ani.connection.DBConnect;

public class UserLoginDAO 
{
	public CustomerBean checkLogin(String username, String pwd)
	{
		CustomerBean bean = null;
		
		try
		{
			Connection con = DBConnect.connect();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM BANK_USERS WHERE USERNAME=? AND PASSWORD=?");
			pstmt.setString(2, pwd);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				bean = new CustomerBean();
				bean.setUser_id(rs.getInt(1));
				bean.setFull_name(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setUsername(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setPhone_no(rs.getString(6));
				bean.setAccount_no(rs.getString(7));
				bean.setBalance(rs.getDouble(8));
				bean.setDate(rs.getDate(9));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return bean;
	}
}
