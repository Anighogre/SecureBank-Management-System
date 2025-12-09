package com.ani.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ani.bean.TransactionBean;
import com.ani.connection.DBConnect;

public class AllTransactionDAO 
{
	ArrayList<TransactionBean> al = new ArrayList<TransactionBean>();
	
	public ArrayList<TransactionBean> viewAllTransactions(String senderAcc)
	{
		try
		{
			Connection con = DBConnect.connect();
			PreparedStatement pstmt = con.prepareStatement("SELECT TXN_DATE, DESCRIPTION, TXN_TYPE, AMOUNT, STATUS FROM BANK_TRANSACTIONS WHERE SENDER_ACCOUNT = ? ORDER BY TXN_DATE DESC");
			pstmt.setString(1, senderAcc);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				TransactionBean bean = new TransactionBean();
				bean.setTxn_date(rs.getDate("TXN_DATE"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setTxn_type(rs.getString("TXN_TYPE"));
				bean.setAmount(rs.getDouble("AMOUNT"));
				bean.setStatus(rs.getString("STATUS"));
				
				al.add(bean);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return al;
	}
}
