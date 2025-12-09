package com.ani.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ani.connection.DBConnect;

public class TransactionDAO 
{
	// Transfer Amount Method
    public boolean transferAmount(String senderAccount, String receiverAccount, double amount) {

        String getBalanceSQL = "SELECT BALANCE FROM BANK_USERS WHERE ACCOUNT_NUMBER = ?";
        String debitSQL = "UPDATE BANK_USERS SET BALANCE = BALANCE - ? WHERE ACCOUNT_NUMBER = ?";
        String creditSQL = "UPDATE BANK_USERS SET BALANCE = BALANCE + ? WHERE ACCOUNT_NUMBER = ?";

        String insertCreditTxnSQL = "INSERT INTO BANK_TRANSACTIONS (SENDER_ACCOUNT, RECEIVER_ACCOUNT, AMOUNT, TXN_TYPE, DESCRIPTION, STATUS, TXN_DATE) VALUES (?, ?, ?, ?, ?, ?, SYSDATE)";
        
        String insertDebitTxnSQL = "INSERT INTO BANK_TRANSACTIONS(SENDER_ACCOUNT, RECEIVER_ACCOUNT, AMOUNT, TXN_TYPE, DESCRIPTION, STATUS, TXN_DATE)VALUES (?, ?, ?, ?, ?, ?, SYSDATE)"; 

        Connection conn = null;

        try {
            conn = DBConnect.connect();
            conn.setAutoCommit(false);

            // 1️⃣ Check Sender Balance
            double senderBalance = 0;

            try (PreparedStatement balStmt = conn.prepareStatement(getBalanceSQL)) {
                balStmt.setString(1, senderAccount);
                ResultSet rs = balStmt.executeQuery();

                if (rs.next()) {
                    senderBalance = rs.getDouble("BALANCE");
                } else {
                    return false; // Sender not found
                }
            }

            // Insufficient funds
            if (senderBalance < amount) {
                insertFailedTransaction(conn, senderAccount, receiverAccount, amount, "INSUFFICIENT BALANCE");
                conn.rollback();
                return false;
            }

            // 2️⃣ Perform Debit & Credit
            try (
                    PreparedStatement debitStmt = conn.prepareStatement(debitSQL);
                    PreparedStatement creditStmt = conn.prepareStatement(creditSQL);
                    PreparedStatement txncreditStmt = conn.prepareStatement(insertCreditTxnSQL);
            		 PreparedStatement txndebitStmt = conn.prepareStatement(insertDebitTxnSQL);
            ) {

                // Debit sender
                debitStmt.setDouble(1, amount);
                debitStmt.setString(2, senderAccount);
                int debited = debitStmt.executeUpdate();

                // Credit receiver
                creditStmt.setDouble(1, amount);
                creditStmt.setString(2, receiverAccount);
                int credited = creditStmt.executeUpdate();

                if (debited > 0 && credited > 0) {

                    // Insert transaction success
                	txncreditStmt.setString(1, senderAccount);
                	txncreditStmt.setString(2, receiverAccount);
                	txncreditStmt.setDouble(3, amount);
                	txncreditStmt.setString(4, "Credit");
                	txncreditStmt.setString(5, "Money sent from " + senderAccount + " to " + receiverAccount);
                	txncreditStmt.setString(6, "SUCCESS");
                    txncreditStmt.executeUpdate();

                    txndebitStmt.setString(1, receiverAccount);
                    txndebitStmt.setString(2, senderAccount);
                    txndebitStmt.setDouble(3, amount);
                    txndebitStmt.setString(4, "Debit");
                	txndebitStmt.setString(5, "Money sent from " + senderAccount + " to " + receiverAccount);
                	txndebitStmt.setString(6, "SUCCESS");
                	txndebitStmt.executeUpdate();
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            try { conn.rollback(); } catch (Exception ex) {}
        }

        return false;
    }


    // Failed Transaction Logger
    private void insertFailedTransaction(Connection conn, String sender, String receiver, double amount, String reason)
            throws SQLException {

        String sql = "INSERT INTO BANK_TRANSACTIONS "
                + "(SENDER_ACCOUNT, RECEIVER_ACCOUNT, AMOUNT, TXN_TYPE, DESCRIPTION, STATUS, TXN_DATE) "
                + "VALUES (?, ?, ?, 'TRANSFER', ?, 'FAILED')";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sender);
            ps.setString(2, receiver);
            ps.setDouble(3, amount);
            ps.setString(4, "Transaction Failed: " + reason);
            ps.executeUpdate();
        }
    }
}
