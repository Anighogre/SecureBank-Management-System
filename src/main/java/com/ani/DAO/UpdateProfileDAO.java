package com.ani.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ani.bean.CustomerBean;
import com.ani.connection.DBConnect;

public class UpdateProfileDAO 
{
	public int updateProfile(CustomerBean bean)
	{
		int rowCount = 0;
		
		try
		{
			
			Connection con = DBConnect.connect();
			
			String sql;
            if (bean.getPassword() != null && !bean.getPassword().trim().isEmpty()) {
                sql = "UPDATE BANK_USERS SET FULL_NAME=?, EMAIL=?, USERNAME=?, PASSWORD=?, PHONE_NUMBER=? WHERE USER_ID=?";
            } else {
                sql = "UPDATE BANK_USERS SET FULL_NAME=?, EMAIL=?, USERNAME=?, PHONE_NUMBER=? WHERE USER_ID=?";
            }
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bean.getFull_name());
            pstmt.setString(2, bean.getEmail());
            pstmt.setString(3, bean.getUsername());
            
            if(bean.getPassword() != null && !bean.getPassword().trim().isEmpty())
            {
            		pstmt.setString(4, bean.getPassword());
            		pstmt.setString(5, bean.getPhone_no());
            		pstmt.setInt(6, bean.getUser_id());
            } else {
            		pstmt.setString(4, bean.getPhone_no());
            		pstmt.setInt(5, bean.getUser_id());
            }
            
            		rowCount = pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rowCount;
	}
}
