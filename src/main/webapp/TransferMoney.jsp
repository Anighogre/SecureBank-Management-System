<%@page import="com.ani.bean.CustomerBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer Money</title>
<link rel="stylesheet" href="style.css" />
<style>
.payment-box {
    width: 400px;
    margin: 40px auto;
    background: #ffffff;
    padding: 25px;
    border-radius: 10px;
    box-shadow: 0px 3px 10px rgba(0,0,0,0.15);
    text-align: center;
    font-family: Arial, sans-serif;
}

.payment-box h2 {
    margin-bottom: 20px;
    color: #333;
}

.payment-box label {
    display: block;
    text-align: left;
    margin: 10px 0 5px;
    color: #444;
    font-weight: 600;
}

.payment-box input[type="text"],
.payment-box input[type="number"] {
    width: 100%;
    padding: 10px;
    font-size: 15px;
    border: 1px solid #ccc;
    border-radius: 6px;
    outline: none;
    transition: 0.2s;
}

.payment-box input[type="text"]:focus,
.payment-box input[type="number"]:focus {
    border-color: #007bff;
}

.payment-box button {
    width: 100%;
    padding: 12px;
    background: #007bff;
    color: white;
    border: none;
    border-radius: 6px;
    font-size: 16px;
    cursor: pointer;
    margin-top: 15px;
    transition: 0.3s;
}

.payment-box button:hover {
    background: #0056b3;
}
</style>
</head>
<body>
		<%
			CustomerBean user = (CustomerBean)session.getAttribute("CustomerBean");
			if (user == null) {
			    response.sendRedirect("CustomerLogin.html");
			    return;
			}
		%>
		
<header class="navbar">
    <div class="container">
        <h1 class="logo">💳 SecureBank</h1>
        <nav>
            <ul>
                <li><a href="Dashboard.jsp">Dashboard</a></li>
                <li><a href="Transaction.jsp">Transactions</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="SendMoney.jsp">Send Money</a></li>
                <li><a href="ApplyLoan.jsp">Apply Loan</a></li>
                <li><a href="logout" class="logout-btn">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>

<div class="payment-box">
<h2>Make a Payment</h2>

<form action="TransferServlet" method="post">
    <label>Sender Account:</label>
    <input type="text" value="<%= user.getAccount_no() %>" readonly />

    <label>Receiver Account:</label>
    <input type="text" name="receiverAccount" placeholder="Receiver Account No" required />

    <label>Amount:</label>
    <input type="number" step="0.01" name="amount" required />

    <button type="submit">Transfer</button>
</form>
</div>

		
</body>
</html>