package com.ani.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ani.DAO.UserLoginDAO;
import com.ani.bean.CustomerBean;

@WebServlet("/clog")
public class CustomerLoginServlet extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String username = req.getParameter("uname");
		String password = req.getParameter("upass");
		
		UserLoginDAO dao = new UserLoginDAO();
		CustomerBean bean = dao.checkLogin(username, password);
		
		if(bean==null)
		{
			req.setAttribute("msg", "Invalid Login credentials!!");
			req.getRequestDispatcher("CustomerReg.jsp").forward(req, res);
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("CustomerBean", bean);
			req.getRequestDispatcher("Dashboard.jsp").forward(req, res);
		}
		
		
	}
}
