<%@page import="com.ani.bean.TransactionBean"%>
<%@ page import="java.util.*, java.text.DecimalFormat" %>
<%@page import="com.ani.helper.AccountNumberGenerator"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.ani.bean.CustomerBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SecureBank | Dashboard</title>
<link rel="stylesheet" href="style.css" />
<style>
/* =========================Dashboard started=======================*/

.dashboard {
    padding: 40px 0;
    background: #f5f5f5;
}

.dashboard .container {
    width: 90%;
    margin: auto;
}

/* Welcome Box */
.welcome-box {
    background: #007bff;
    padding: 25px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.welcome-box h2 {
    margin: 0 0 10px;
    font-size: 28px;
    color: white;
}

.welcome-box p {
    font-size: 20px;
    color: white;
}

/*========================================DashBoard complete==============================*/

/* ============================================Summary Section =================================*/
.summary {
    display: flex;
    gap: 20px;
    margin-top: 25px;
}

/* Cards */
.card {
    flex: 1;
    background: #ffffff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
    text-align: center;
}

/* Balance Card Highlight */
.balance-card {
    border-left: 4px solid #007bff;
}

.card h3 {
    margin: 0 0 10px;
    font-size: 18px;
    color: #333;
}

.card p {
    font-size: 20px;
    font-weight: bold;
    color: #444;
    margin: 10px 0 0;
}

/* Transfer Button */
.btn-transfer {
    display: inline-block;
    margin-top: 12px;
    padding: 8px 15px;
    background: #007bff;
    color: #fff;
    text-decoration: none;
    border-radius: 6px;
    transition: 0.3s ease;
}

.btn-transfer:hover {
    background: #0056b3;
}


/*===============================================================================*/

.transactions {
    margin: 20px auto;
    max-width: 1200px;
    background: #ffffff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.1);
}

.transactions h3 {
    margin-bottom: 15px;
    font-size: 22px;
    text-align: center;
    color: #333;
}

.transactions table {
    width: 100%;
    border-collapse: collapse;
    font-size: 16px;
}

.transactions th, 
.transactions td {
    padding: 12px 10px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

.transactions th {
    background: #f2f2f2;
    font-weight: bold;
    color: #444;
}

.transactions tr:hover {
    background: #fafafa;
}

/* Status Badges */
.status {
    padding: 6px 12px;
    border-radius: 20px;
    font-size: 14px;
    font-weight: 600;
    color: white;
}

.status.completed {
    background: #28a745;   /* Green */
}

.status.pending {
    background: #ffc107;   /* Yellow */
    color: #000;
}


	
</style>
</head>
<body>

	<header class="navbar">
    <div class="container">
        <h1 class="logo">💳 SecureBank</h1>
        <nav>
            <ul>
                <li><a href="Dashboard.jsp" class="active">Dashboard</a></li>
                <li><a href="Transaction">Transactions</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="SendMoney.jsp">Send Money</a></li>
                <li><a href="ApplyLoan.jsp">Apply Loan</a></li>
                <li><a href="logout" class="logout-btn">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>
	
	<center>
		<h1>
			<%
				CustomerBean bean = (CustomerBean)session.getAttribute("CustomerBean");
				if (bean == null) {
			        response.sendRedirect("CustomerLogin.html");
			        return;
			    }
				
				 AccountNumberGenerator dao = new AccountNumberGenerator();
				 double balance = dao.getBalance(bean.getAccount_no());
				 List<TransactionBean> txns = dao.getRecentTransactions(bean.getAccount_no());
			%>
				
		<section class="dashboard">
    			<div class="container">
    			<%
    String msg = request.getParameter("msg");
    if (msg != null) {
%>
    <div style="width: 50%; margin: 10px auto; padding: 12px;
                background: #d4edda; color: #155724; border-left: 5px solid #28a745;
                border-radius: 5px; font-size: 16px;">
        <%= msg %>
    </div>
<%
    }
%>
				<div class="welcome-box">
		            <h2>Welcome back, <%= bean.getFull_name() %> 👋</h2>
		            <p>Account Number: <b><%= bean.getAccount_no() %></b></p>
	        		</div>
	        		
	        	<div class="summary">
	            <div class="card balance-card">
	                <h3>Current Balance</h3>
	                <p>₹ <%= new DecimalFormat("#,##0.00").format(bean.getBalance()) %></p>
	            </div>
	            <div class="card">
	                <h3>Make a Transfer</h3>
	                <a class="btn-transfer" href="TransferMoney.jsp">Transfer Money</a>
	            </div>
        		</div>
	        		
	      <div class="transactions">
            <h3>Recent Transactions</h3>
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Description</th>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                <% if (txns != null && !txns.isEmpty()) {
                    for (TransactionBean t : txns) { %>
                        <tr>
                            <td><%= t.getTxn_date() %></td>
                            <td><%= t.getDescription() %></td>
                            <td><%= t.getTxn_type() %></td>
                            <td>₹ <%= new DecimalFormat("#,##0.00").format(t.getAmount()) %></td>
                            <td>
                                <% if ("SUCCESS".equalsIgnoreCase(t.getStatus())) { %>
                                    <span class="status completed">Completed</span>
                                <% } else { %>
                                    <span class="status pending"><%= t.getStatus() %></span>
                                <% } %>
                            </td>
                        </tr>
                <%  }
                } else { %>
                    <tr><td colspan="5" style="text-align:center;">No recent transactions found.</td></tr>
                <% } %>
                </tbody>
            </table>
        </div>
	        		
	        </div>
	    </section>
			
		</h1>
</body>
</html>