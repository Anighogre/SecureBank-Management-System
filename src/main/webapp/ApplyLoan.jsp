<%@page import="com.ani.bean.CustomerBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apply Loan</title>

<link rel="stylesheet" href="style.css">

<style>

.loan-box {
    width: 380px;
    margin: 40px auto;
    padding: 25px;
    border-radius: 12px;
    background: #ffffff;
    box-shadow: 0px 4px 18px rgba(0,0,0,0.15);
    font-family: Arial, sans-serif;
}

.loan-box h3 {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
}

/* Labels */
.loan-box label {
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
    color: #444;
}

/* Input Fields */
.loan-box input, 
.loan-box select {
    width: 100%;
    padding: 10px;
    border-radius: 6px;
    border: 1px solid #aaa;
    margin-bottom: 15px;
    font-size: 15px;
    transition: 0.3s ease;
}

.loan-box input:focus, 
.loan-box select:focus {
    border-color: #0066ff;
    box-shadow: 0 0 6px rgba(0,102,255,0.3);
}

/* Button */
.loan-box .btn {
    width: 100%;
    padding: 12px;
    background: #0066ff;
    color: #fff;
    font-size: 16px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: 0.3s ease;
}


.loan-box .btn:hover {
    background: #004fcc;
}

/* Message styling */
.loan-box .msg {
    text-align: center;
    margin-top: 15px;
    font-weight: bold;
    color: green;
}

.loan-list {
    margin-top: 30px;
    padding: 20px 0;
}

.loan-btn {
    display: inline-block;
    background: #0077ff;
    color: white;
    padding: 12px 30px;
    font-size: 18px;
    font-weight: bold;
    text-decoration: none;
    border-radius: 8px;
    transition: 0.3s ease;
    box-shadow: 0 4px 10px rgba(0, 119, 255, 0.3);
    margin-bottom: -50px;
    margin-top: -30px;
}

.loan-btn:hover {
    background: #005fcc;
    transform: scale(1.05);
    box-shadow: 0 6px 15px rgba(0, 119, 255, 0.4);
}

.loan-btn:active {
    transform: scale(0.97);
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
			CustomerBean user =  (CustomerBean)session.getAttribute("CustomerBean");
			String data = (String)request.getAttribute("msg");
		%>

	<div class="loan-box">
    <h3>Apply for Loan</h3>

    <form action="ApplyLoanServlet" method="post">
        <input type="hidden" name="customerId" value="<%= user.getUser_id() %>">

        <!-- Loan Amount -->
        <label>Loan Amount (₹)</label>
        <input type="number" name="amount" class="form-control" required style="width:100%; padding:8px; margin-bottom:15px;">

        <!-- Loan Type -->
        <label>Loan Type</label>
        <select name="loanType" class="form-control" required style="width:100%; padding:8px; margin-bottom:15px;">
            <option value="">-- Select Loan Type --</option>
            <option>Home Loan</option>
            <option>Personal Loan</option>
            <option>Education Loan</option>
            <option>Vehicle Loan</option>
            <option>Business Loan</option>
        </select>

        <!-- Loan Duration -->
        <label>Duration (Months)</label>
        <input type="number" name="duration" required class="form-control" style="width:100%; padding:8px; margin-bottom:15px;">

        <button class="btn">Submit Application</button>
    </form>
    <p class="msg"><%=data %></p>
</div>

	<div class="loan-list">
    <center>
        <a class="loan-btn" href="ShowAllLoans.jsp">Show All Loans</a>
	</div>

			
</body>
</html>