<%@page import="com.ani.bean.CustomerBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile - SecureBank</title>
    <link rel="stylesheet" href="style.css" />
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head class="bg-gray-100 font-sans">
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

	<%
		CustomerBean user = (CustomerBean)session.getAttribute("CustomerBean");
	%>

<div class="max-w-3xl mx-auto mt-10 bg-white shadow-2xl rounded-xl p-8">
        <h2 class="text-3xl font-semibold text-blue-900 mb-6 border-b-2 pb-2">✏️ Edit Your Profile</h2>

        <form action="UpdateProfileServlet" method="post" class="grid grid-cols-2 gap-6 text-lg">
            <div>
                <label class="font-semibold text-gray-700">Full Name:</label>
                <input type="text" name="fullName" value="<%= user.getFull_name() %>"
                       class="w-full p-2 border rounded-lg focus:ring-2 focus:ring-blue-600">
            </div>

            <div>
                <label class="font-semibold text-gray-700">Email:</label>
                <input type="email" name="email" value="<%= user.getEmail() %>"
                       class="w-full p-2 border rounded-lg focus:ring-2 focus:ring-blue-600">
            </div>

            <div>
                <label class="font-semibold text-gray-700">Username:</label>
                <input type="text" name="username" value="<%= user.getUsername() %>"
                       class="w-full p-2 border rounded-lg focus:ring-2 focus:ring-blue-600">
            </div>

            <div>
                <label class="font-semibold text-gray-700">Password:</label>
                <input type="password" name="password" placeholder="Enter new password"
                       class="w-full p-2 border rounded-lg focus:ring-2 focus:ring-blue-600">
                <p class="text-sm text-gray-500 mt-1">Leave blank to keep your current password.</p>
            </div>

            <div>
                <label class="font-semibold text-gray-700">Phone Number:</label>
                <input type="text" name="phoneNumber" value="<%= user.getPhone_no() != null ? user.getPhone_no(): "" %>"
                       class="w-full p-2 border rounded-lg focus:ring-2 focus:ring-blue-600">
            </div>

            <div>
                <label class="font-semibold text-gray-700">Account Number:</label>
                <input type="text" value="<%= user.getAccount_no() %>" readonly
                       class="w-full p-2 border rounded-lg bg-gray-100 text-gray-600 cursor-not-allowed">
            </div>

            <div>
                <label class="font-semibold text-gray-700">Balance:</label>
                <input type="text" value="₹ <%= user.getBalance() %>" readonly
                       class="w-full p-2 border rounded-lg bg-gray-100 text-green-700 cursor-not-allowed">
            </div>

            <div class="col-span-2 text-center mt-6">
                <button type="submit" class="bg-blue-800 text-white px-8 py-2 rounded-lg hover:bg-blue-700 transition">
                    💾 Save Changes
                </button>
                <a href="profile.jsp" class="ml-4 text-blue-800 font-semibold hover:underline">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>