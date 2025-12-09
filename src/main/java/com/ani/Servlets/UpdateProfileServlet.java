package com.ani.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ani.DAO.UpdateProfileDAO;
import com.ani.bean.CustomerBean;

import oracle.net.aso.b;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		HttpSession session = req.getSession();
		CustomerBean bean = (CustomerBean)session.getAttribute("CustomerBean");
		
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		if(bean==null)
		{
			res.sendRedirect("CustomerLogin.html");
			return;
		}
		
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String phoneNo = req.getParameter("phoneNumber");
		
		int rowCount = new UpdateProfileDAO().updateProfile(bean);
		
		if(rowCount>0)
		{
			bean.setFull_name(fullName);
			bean.setEmail(email);
			bean.setUsername(userName);
			bean.setPhone_no(phoneNo);
			session.setAttribute("CustomerBean", bean);
			
			pw.println("<center><h3>Data Updated Successfully!</h3></center>");
			req.getRequestDispatcher("profile.jsp").include(req, res);
		} else {
			pw.println("<center><h3>Data NOT Updated!!</h3></center>");
			req.getRequestDispatcher("editeProfile.jsp").include(req, res);
		}
	}
}
