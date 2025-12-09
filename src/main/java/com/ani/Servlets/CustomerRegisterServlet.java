package com.ani.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Authenticator.RequestorType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ani.DAO.RegisterDAO;
import com.ani.bean.CustomerBean;
import com.ani.helper.AccountNumberGenerator;

@WebServlet("/creg")
public class CustomerRegisterServlet extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		CustomerBean bean = new CustomerBean();
		
		bean.setFull_name(req.getParameter("fname"));
		bean.setEmail(req.getParameter("mail"));
		bean.setUsername(req.getParameter("uname"));
		String pwd = req.getParameter("pwd");
		String cpwd = req.getParameter("cpwd");
		
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		if(pwd.equals(cpwd))
		{
			bean.setPassword(req.getParameter("pwd"));
		} else {
			req.getRequestDispatcher("CustomerReg.html").forward(req, res);
		}
		bean.setPhone_no(req.getParameter("phone"));
		String accountNo = AccountNumberGenerator.generateAccountNo();
		bean.setAccount_no(accountNo);
				
		String check = req.getParameter("check");
		
		if("yes".equals(check)) {
			int rowCount = new RegisterDAO().userRegister(bean);
			if(rowCount>0)
			{
				req.setAttribute("msg", "New User Register Successfully");
				req.getRequestDispatcher("CustomerReg.jsp").forward(req, res);
			} else {
				pw.print("Invalid Credentials!!<br>");
				req.getRequestDispatcher("CustomerReg.html").forward(req, res);
			}
		}
		
		
		
		
	}
}
