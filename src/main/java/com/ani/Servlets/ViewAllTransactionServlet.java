package com.ani.Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ani.DAO.AllTransactionDAO;
import com.ani.bean.CustomerBean;
import com.ani.bean.TransactionBean;

@WebServlet("/Transaction")
public class ViewAllTransactionServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		CustomerBean bean =(CustomerBean) session.getAttribute("CustomerBean");
		ArrayList<TransactionBean> viewtransaction = new AllTransactionDAO().viewAllTransactions(bean.getAccount_no());
		session.setAttribute("TransactionList", viewtransaction);
		req.getRequestDispatcher("Transaction.jsp").forward(req, res);
	}
}
