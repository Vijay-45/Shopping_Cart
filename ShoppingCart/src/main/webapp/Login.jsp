<%@page import="com.Vijay.Model.*" %>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	User auth = (User)request.getSession().getAttribute("auth"); 
	if(auth!=null){
		request.setAttribute("auth", auth);
		response.sendRedirect("Index.jsp");
	}
	ArrayList<Cart> cart_list =(ArrayList<Cart>)session.getAttribute("cart-list");

	if(cart_list !=null){
		
		request.setAttribute("cart_list", cart_list);
	}

	%>
<!DOCTYPE html>
<html>
<head>
<title>Shopping Cart Login Page</title>
<%@ include file="Include-Files/Header.jsp" %>
</head>
<body>
<%@ include file="Include-Files/Nav-Bar.jsp" %>
     <div class="Container">
         <div class="card w-50 mx-auto my-5">
               <div class="card-header text-center">User Login</div>
               <div class="card-body">
                    <form action="user-login" method="post">
                        <div class="form-group">
                             <label>Email Address</label>
                             <input type="email" class="form-control" name="Login-email" placeholder="Enter your email" required>
                        </div>
                        <div class="form-group">
                             <label>Password</label>
                             <input type="password" class="form-control" name="Login-password" placeholder="*******" required>
                        </div>
                        <div class="text-center"> 
                             <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>
               </div>
         </div>
     </div>
<%@ include file="Include-Files/Footer.jsp" %>
</body>
</html>