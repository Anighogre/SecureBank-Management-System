package com.ani.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ani.bean.TransactionBean;
import com.ani.connection.DBConnect;

public class AccountNumberGenerator 
{
	private static long nextAccountNo = 1000000000L + (long)(Math.random() * 9000000000L);; // starting number

    // Thread-safe method
    public static synchronized String generateAccountNo() {
        long current = nextAccountNo;  // get current
        nextAccountNo++;               // increment for next user
        return current + "SBI";        // append SBI
    }
    
    
    
    public double getBalance(String accountNumber) {
	    double balance = 0;
	    try {
	    	Connection conn = DBConnect.connect();
	    
	         PreparedStatement ps = conn.prepareStatement("SELECT BALANCE FROM BANK_USERS WHERE ACCOUNT_NUMBER = ?");
	        ps.setString(1, accountNumber);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            balance = rs.getDouble("BALANCE");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return balance;
	}
    
    
    
    public List<TransactionBean> getRecentTransactions(String accountNumber) {
	    List<TransactionBean> list = new ArrayList<>();
	    String sql = "SELECT * FROM BANK_TRANSACTIONS WHERE SENDER_ACCOUNT = ? OR RECEIVER_ACCOUNT = ? ORDER BY TXN_DATE DESC FETCH FIRST 5 ROWS ONLY";
	    try {
	    			Connection con = DBConnect.connect();
	         PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, accountNumber);
	        ps.setString(2, accountNumber);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            TransactionBean t = new TransactionBean();
//	            t.setTxn_id();;
	            t.setSenderAccount(rs.getString("SENDER_ACCOUNT"));;
	            t.setReceiverAccount(rs.getString("RECEIVER_ACCOUNT"));
	            t.setAmount(rs.getDouble("AMOUNT"));
	            t.setTxn_type(rs.getString("TXN_TYPE"));
	            t.setDescription(rs.getString("DESCRIPTION"));
	            t.setStatus(rs.getString("STATUS"));
	            t.setTxn_date(rs.getDate("TXN_DATE"));;
	            list.add(t);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

}
