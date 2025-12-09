<%@page import="com.ani.bean.CustomerBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<link rel="stylesheet" href="style.css">
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

		<%
			CustomerBean bean =  (CustomerBean)session.getAttribute("CustomerBean");
		%>

	<header class="navbar">
    <div class="container">
        <h1 class="logo">💳 SecureBank</h1>
        <nav>
            <ul>
                <li><a href="Dashboard.jsp">Dashboard</a></li>
                <li><a href="Transaction">Transactions</a></li>
                <li><a href="profile.jsp" class="active">Profile</a></li>
                <li><a href="SendMoney.jsp">Send Money</a></li>
                <li><a href="ApplyLoan.jsp">Apply Loan</a></li>
                <li><a href="logout" class="logout-btn">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>

		<div class="max-w-3xl mx-auto mt-10 bg-white shadow-2xl rounded-xl p-8">
        <h2 class="text-3xl font-semibold text-blue-900 mb-6 border-b-2 pb-2">👤 Your Profile</h2>
        <div class="grid grid-cols-2 gap-6 text-lg">
        
            <div>
                <p class="font-semibold text-gray-700">Full Name:</p>
                <p class="text-gray-800"><%= bean.getFull_name() %></p>
            </div>
            </div>
            
            <div>
                <p class="font-semibold text-gray-700">Email:</p>
                <p class="text-gray-800"><%= bean.getEmail() %></p>
            </div>
            
            <div>
                <p class="font-semibold text-gray-700">Username:</p>
                <p class="text-gray-800"><%= bean.getUsername() %></p>
            </div>
            
            <div>
                <p class="font-semibold text-gray-700">Account Number:</p>
                <p class="text-gray-800"><%= bean.getAccount_no() %></p>
            </div>
            
            <div>
                <p class="font-semibold text-gray-700">Balance:</p>
                <p class="text-green-700 font-bold">₹ <%= bean.getBalance() %></p>
            </div>
            
            <div>
                <p class="font-semibold text-gray-700">Phone Number:</p>
                <p class="text-gray-800">
                    <%= bean.getPhone_no() %>
                </p>
            </div>
      	</div>
      	
      	<div class="mt-8 text-center">
            <a href="Dashboard.jsp" class="bg-blue-800 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition">
                🔙 Back to Dashboard
            </a>
            &nbsp;
            <a href="editProfile.jsp" class="bg-blue-800 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition">
               Edit
            </a>
        </div>
        <br>
	
</body>
</html>