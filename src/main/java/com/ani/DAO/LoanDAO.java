package com.ani.DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ani.bean.LoanBean;
import com.ani.connection.DBConnect;

public class LoanDAO 
{
	public boolean applyLoan(LoanBean bean)
	{
		boolean f = false;
		
		try
		{
			Connection con = DBConnect.connect();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO loans (CUSTOMER_ID, LOAN_TYPE, AMOUNT, DURATION, STATUS,APPLY_DATE) VALUES (?, ?, ?, ?,  'Pending', SYSDATE)");
			pstmt.setInt(1, bean.getCustomerId());
			pstmt.setString(2, bean.getLoanType());
			pstmt.setDouble(3, bean.getAmount());
			pstmt.setInt(4, bean.getDuration());
			
			int rowCount = pstmt.executeUpdate();
			
			if(rowCount == 1)
			{
				f = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return f;
	}
	
	public List<LoanBean> getAllLoan()
	{
		List<LoanBean> list = new ArrayList<>();
		
		
		try
		{
			Connection con = DBConnect.connect();
			String sql = "SELECT * FROM loans ORDER BY loan_id DESC";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				LoanBean bean = new LoanBean();
				bean.setLoanId(rs.getInt(1));
				bean.setCustomerId(rs.getInt(2));
				bean.setLoanType(rs.getString(3));
				bean.setAmount(rs.getDouble(4));
				bean.setDuration(rs.getInt(5));
				bean.setStatus(rs.getString(6));
				bean.setApplyDate(rs.getDate(7));
				
				list.add(bean);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return list;
		
	}
}
