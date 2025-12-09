<%@page import="java.util.Iterator"%>
<%@page import="com.ani.bean.LoanBean"%>
<%@page import="java.util.List"%>
<%@page import="com.ani.DAO.LoanDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loans</title>
<link rel="stylesheet" href="style.css">

<style>
	h2 {
	            color: #0078d7;
	            text-align: center;
	        }
	table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
            border-radius: 3px;
        }
        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #0078d7;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .status-success {
            color: green;
            font-weight: bold;
        }
        .status-failed {
            color: red;
            font-weight: bold;
        }
        a.back {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            background: #0078d7;
            color: white;
            padding: 10px 15px;
            border-radius: 6px;
        }
        a.back:hover {
            background: #005fa3;
        }
</style>
</head>
<body>
	<header class="navbar">
    <div class="container">
        <h1 class="logo">💳 SecureBank</h1>
        <nav>
            <ul>
                <li><a href="Dashboard.jsp">Dashboard</a></li>
                <li><a href="Transaction">Transactions</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="SendMoney.jsp">Send Money</a></li>
                <li><a href="ApplyLoan.jsp" class="active">Apply Loan</a></li>
                <li><a href="logout" class="logout-btn">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>

	<%
			LoanDAO dao = new LoanDAO();
			List<LoanBean> list = (List<LoanBean>)dao.getAllLoan();
		
	%>
		
		<div class="container">
    <h2>All Loans</h2>

    <table>
        <tr>
            <th>Loan ID</th>
            <th>Customer ID</th>
            <th>Loan Type</th>
            <th>Loan Amount (₹)</th>
            <th>Duration</th>
            <th>Status</th>
        </tr>
        		
	<%		
			Iterator<LoanBean> i =  list.iterator();
			
			while(i.hasNext())
			{
				LoanBean bean = i.next();
	%>
	<tr>
                 <td><%= bean.getLoanId() %></td>
                 <td><%= bean.getCustomerId() %></td>
           	     <td><%= bean.getLoanType() %></td>
                 <td><%= bean.getAmount() %></td>
			    <td><%= bean.getDuration() %></td>
			    <td class="<%= "SUCCESS".equalsIgnoreCase(bean.getStatus()) ? "status-success" : "status-Pending" %>">
                 <%= bean.getStatus() %>
                </td>
           </tr>
        	<% 
        			}
        	%>
        	</table>
        	
        	<a style="text-decoration: none;" href="Dashboard.jsp" class="back">⬅ Back to Dashboard</a>
        	</div>
</body>
</html>