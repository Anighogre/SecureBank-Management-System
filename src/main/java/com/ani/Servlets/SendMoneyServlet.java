package com.ani.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ani.DAO.SendMoneyDAO;
import com.ani.bean.CustomerBean;

@WebServlet("/sendMoney")
public class SendMoneyServlet extends HttpServlet 
{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		SendMoneyDAO dao = new SendMoneyDAO();
		String receiver = req.getParameter("receiver");
        String description = req.getParameter("description");
        double amount = Double.parseDouble(req.getParameter("amount"));
        
        res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
        
        HttpSession session = req.getSession();
        CustomerBean bean = (CustomerBean)session.getAttribute("CustomerBean");
        
        int rowCount = dao.senMoney(amount, bean.getAccount_no(), receiver, description);
        
        if(rowCount>0)
        {
        		req.setAttribute("msg", "₹" + amount +" sent successfully to " + receiver + "!");
        		req.getRequestDispatcher("SendMoney.jsp").forward(req, res);
        } else {
        		pw.print("<center><h2>Money not Transfer!!</h2></center>");
			req.getRequestDispatcher("SendMoney.jsp").include(req, res);
        }
        
	}
}
