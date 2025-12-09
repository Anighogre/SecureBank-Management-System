<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ani.bean.CustomerBean"%>
<%@page import="com.ani.bean.TransactionBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transactions</title>
<link rel="stylesheet" href="style.css" />
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
                <li><a href="Transaction.jsp" class="active">Transactions</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="SendMoney.jsp">Send Money</a></li>
                <li><a href="ApplyLoan.jsp">Apply Loan</a></li>
                <li><a href="logout" class="logout-btn">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>

		<%
		  	CustomerBean bean =  (CustomerBean)session.getAttribute("CustomerBean");
			ArrayList<TransactionBean> al = (ArrayList<TransactionBean>)session.getAttribute("TransactionList");
			
		%>

<div class="container">
    <h2>Your Transactions</h2>

    <table>
        <tr>
            <th>Transaction ID</th>
            <th>Date</th>
            <th>Description</th>
            <th>Type</th>
            <th>Amount (₹)</th>
            <th>Status</th>
        </tr>
        
        <%
        			Iterator<TransactionBean> i =  al.iterator();
        			while(i.hasNext())
        			{
        				TransactionBean tb = i.next();
        	%>
        		
        		<tr>
                 <td><%= tb.getTxn_id() %></td>
                 <td><%= tb.getTxn_date() %></td>
           	     <td><%= tb.getDescription() %></td>
                 <td><%= tb.getTxn_type() %></td>
			    <td><%= tb.getAmount() %></td>
                 <td class="<%= "SUCCESS".equalsIgnoreCase(tb.getStatus()) ? "status-success" : "status-failed" %>">
                 <%= tb.getStatus() %>
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