package com.ani.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.time.LocalDate;

import com.ani.bean.CustomerBean;
import com.ani.connection.DBConnect;

public class RegisterDAO 
{
	public int userRegister(CustomerBean bean)
	{
		int rowCount = 0;
		
		try
		{
			Connection con = DBConnect.connect();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO BANK_USERS (FULL_NAME, EMAIL, USERNAME, PASSWORD, PHONE_NUMBER, ACCOUNT_NUMBER, BALANCE, CREATED_AT) VALUES(?,?,?,?,?,?,?,SYSDATE)");
			pstmt.setString(1, bean.getFull_name());			
			pstmt.setString(2, bean.getEmail());			
			pstmt.setString(3, bean.getUsername());			
			pstmt.setString(4, bean.getPassword());			
			pstmt.setString(5, bean.getPhone_no());			
			pstmt.setString(6, bean.getAccount_no());
			pstmt.setDouble(7, 15000.0);
			
			rowCount = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rowCount;
	}
}
