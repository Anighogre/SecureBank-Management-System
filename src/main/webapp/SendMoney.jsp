<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Send Money</title>
<link rel="stylesheet" href="style.css" />
<style>
	/* Send Money Container */
.Moneycontainer {
    width: 400px;
    margin: 40px auto;
    background: #ffffff;
    padding: 25px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.Moneycontainer h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
}

/* Labels */
.Moneycontainer label {
    font-size: 15px;
    color: #444;
    font-weight: 500;
}

/* Input fields */
.Moneycontainer input[type="text"],
.Moneycontainer input[type="number"] {
    width: 100%;
    padding: 10px;
    margin-top: 6px;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 15px;
    outline: none;
    transition: 0.2s ease;
}

.Moneycontainer input[type="text"]:focus,
.Moneycontainer input[type="number"]:focus {
    border-color: #007bff;
}

/* Submit Button */
.Moneycontainer input[type="submit"] {
    width: 100%;
    margin-top: 10px;
    padding: 10px;
    background: #007bff;
    border: none;
    color: #fff;
    border-radius: 6px;
    font-size: 16px;
    cursor: pointer;
    transition: 0.3s ease;
}

.Moneycontainer input[type="submit"]:hover {
    background: #0056b3;
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
                <li><a href="Transaction.jsp">Transactions</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="SendMoney.jsp" class="active">Send Money</a></li>
                <li><a href="ApplyLoan.jsp">Apply Loan</a></li>
                <li><a href="logout" class="logout-btn">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>

	<div class="Moneycontainer">
	    <h2>Send Money</h2>
	
	    <form action="sendMoney" method="post">
	        <label>Recipient Account Number or Mobile:</label><br>
	        <input type="text" name="receiver" required placeholder="Enter account or mobile number"><br><br>
	
	        <label>Amount (₹):</label><br>
	        <input type="number" name="amount" required min="1" step="0.01"><br><br>
	
	        <label>Description:</label><br><br>
	        <input type="text" name="description" placeholder="Purpose (optional)"><br><br>
	
	        <input type="submit" value="Send Money">
	    </form>
	    <br>
	    <%
	   		String data = (String)request.getAttribute("msg");
	    %>
	    <p style="color: green; font-weight: bold; text-align:center;"><%= data %></p>
	    <a style="text-decoration: none;" href="Dashboard.jsp" class="back">⬅ Back to Dashboard</a>
    </div>
</body>
</html>