package com.ani.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ani.DAO.LoanDAO;
import com.ani.bean.LoanBean;

@WebServlet("/ApplyLoanServlet")
public class ApplyLoanServlet extends HttpServlet {

		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
		{
			int customerId = Integer.parseInt(req.getParameter("customerId"));
	        String loanType = req.getParameter("loanType");
	        double amount = Double.parseDouble(req.getParameter("amount"));
	        int duration = Integer.parseInt(req.getParameter("duration"));
	        
	        LoanBean bean = new LoanBean();
	        bean.setCustomerId(customerId);
	        bean.setLoanType(loanType);
	        bean.setAmount(amount);
	        bean.setDuration(duration);
	        
	        LoanDAO dao = new LoanDAO();
	        boolean success = dao.applyLoan(bean);
	        
	        if(success)
	        {
//	        		res.sendRedirect("ApplyLoan.jsp");
	        		req.setAttribute("msg", "Loan Applied Successfully!");
	        		req.getRequestDispatcher("ApplyLoan.jsp").forward(req, res);
	        } else {
//	        		res.sendRedirect("ApplyLoan.jsp");
	        		req.setAttribute("msg", "Something went wrong!");
	        		req.getRequestDispatcher("ApplyLoan.jsp").forward(req, res);
	        }
	        
	        
		}
}
