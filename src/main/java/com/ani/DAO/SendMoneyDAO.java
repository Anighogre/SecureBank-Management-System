package com.ani.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ani.connection.DBConnect;

public class SendMoneyDAO 
{
    public int senMoney(double amount, String sender, String receiver, String description)
    {
        int rowCount = 0;

        try
        {
            Connection con = DBConnect.connect();
            con.setAutoCommit(false);

            // 1️⃣ Fetch sender balance
            PreparedStatement pstmt = con.prepareStatement("SELECT BALANCE FROM BANK_USERS WHERE ACCOUNT_NUMBER=?");
            pstmt.setString(1, sender);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
                double senderBal = rs.getDouble("BALANCE");

                if(senderBal < amount)
                {
                    throw new RuntimeException("Insufficient balance!");
                } 
                else 
                {
                    // 2️⃣ Fetch receiver details
                    PreparedStatement pstmt1 = con.prepareStatement("SELECT ACCOUNT_NUMBER, FULL_NAME, BALANCE FROM BANK_USERS WHERE ACCOUNT_NUMBER=? OR PHONE_NUMBER=?");
                    pstmt1.setString(1, receiver);
                    pstmt1.setString(2, receiver);

                    ResultSet rs1 = pstmt1.executeQuery();

                    if(!rs1.next())
                    {
                        throw new RuntimeException("Receiver not found!");
                    } 
                    else 
                    {

                    		String receiverAcc = rs1.getString("ACCOUNT_NUMBER");
                        String receiverName = rs1.getString("FULL_NAME");
                        double receiverBalance = rs1.getDouble("BALANCE");

                        // Update sender balance
                        PreparedStatement pstmt2 = con.prepareStatement("UPDATE BANK_USERS SET BALANCE=? WHERE ACCOUNT_NUMBER=?");
                        pstmt2.setDouble(1, senderBal - amount);
                        pstmt2.setString(2, sender);
                        pstmt2.executeUpdate();

                        // Update receiver balance
                        pstmt2.setDouble(1, receiverBalance + amount);
                        pstmt2.setString(2, receiverAcc);
                        rowCount = pstmt2.executeUpdate();

                        // 5️⃣ Insert transaction record
                        PreparedStatement psTxn = con.prepareStatement("INSERT INTO BANK_TRANSACTIONS (SENDER_ACCOUNT, RECEIVER_ACCOUNT, AMOUNT, TXN_TYPE, DESCRIPTION, STATUS, TXN_DATE) VALUES (?, ?, ?, ?, ?, ?, SYSDATE)");
                        psTxn.setString(1, sender);
                        psTxn.setString(2, receiverAcc);
                        psTxn.setDouble(3, amount);
                        psTxn.setString(4, "TRANSFER");
                        psTxn.setString(5, (description != null && !description.isEmpty()) ? description : "Money transfer");
                        psTxn.setString(6, "SUCCESS");
                        rowCount = psTxn.executeUpdate();

                        //  Commit / rollback
                        if(rowCount > 0)
                            con.commit();
                        else
                            con.rollback();
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return rowCount;
    }
}
