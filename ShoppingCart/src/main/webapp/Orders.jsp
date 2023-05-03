<%@page import="com.Vijay.Dao.OrderDao"%>
<%@page import="com.Vijay.Connection.DbConnection"%>
<%@page import="com.Vijay.Model.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%
	DecimalFormat dcf = new DecimalFormat("#.##");
	request.setAttribute("dcf", dcf);
	
	User auth = (User)request.getSession().getAttribute("auth"); 
	List<Order> orders = null;
	if(auth!=null){
		request.setAttribute("auth", auth);
		orders = new OrderDao(DbConnection.getConnection()).userOrders(auth.getId());
		
	}
	else{
		response.sendRedirect("Login.jsp");
	}
	ArrayList<Cart> cart_list =(ArrayList<Cart>)session.getAttribute("cart-list");

	if(cart_list !=null){
		
		request.setAttribute("cart_list", cart_list);
	}

	%>
<!DOCTYPE html>
<html>
<head>
<title>Orders Page</title>
<%@ include file="Include-Files/Header.jsp" %>
</head>
<body>
<%@ include file="Include-Files/Nav-Bar.jsp" %>

<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%
			if(orders!=null){
				for(Order order:orders){%>
				<tr>
					<td><%=order.getDate() %></td>
					<td><%=order.getName()%></td>
					<td><%=order.getCategory() %></td>
					<td><%=order.getQuantity() %></td>
					<td><%=dcf.format(order.getPrice()) %></td>
					<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%= order.getOrderId()%>">Cancel</a></td>
				</tr>
				<%}
			}
			%>
			</tbody>
		</table>
	</div>

<%@ include file="Include-Files/Footer.jsp" %>
</body>
</html>