package com.ani.Servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ani.DAO.TransactionDAO;
import com.ani.bean.CustomerBean;

@WebServlet("/TransferServlet")
public class TransactionServlet extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		HttpSession session = req.getSession();
		CustomerBean bean = (CustomerBean)session.getAttribute("CustomerBean");
		
		if (bean == null) {
            res.sendRedirect("CustomerLogin.html");
            return;
        }
		
		String receiverAccount = req.getParameter("receiverAccount");
        double amount = Double.parseDouble(req.getParameter("amount"));
        
        TransactionDAO dao = new TransactionDAO();
        
        boolean ifSuccess = dao.transferAmount(bean.getAccount_no(), receiverAccount, amount);
        
        if (ifSuccess) {
            res.sendRedirect("Dashboard.jsp?msg=Transfer Successful");
        } else {
            res.sendRedirect("TransferMoney.jsp?error=Transfer Failed");
        }
	}
}
