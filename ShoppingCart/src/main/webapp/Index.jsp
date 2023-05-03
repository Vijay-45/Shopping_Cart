<%@page import="com.Vijay.Connection.DbConnection"%>
<%@page import="com.Vijay.Model.*"%>
<%@page import="com.Vijay.Dao.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pd = new ProductDao(DbConnection.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

if (cart_list != null) {

	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to ShoppingCart</title>
<%@ include file="Include-Files/Header.jsp"%>
</head>
<body>
	<%@ include file="Include-Files/Nav-Bar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product product : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img class="card-img-top"
						src="Product-Images/<%=product.getImage()%>" alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=product.getName()%></h5>
						<h6 class="price"><%=product.getPrice()%></h6>
						<h6 class="category"><%=product.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a href="AddToCart1?id=<%=product.getId()%>" class="btn btn-dark">Add
								to Cart</a> <a href="order-now?quantity=1&id=<%=product.getId() %> " class="btn btn-primary">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>
		</div>
	</div>

	<%@ include file="Include-Files/Footer.jsp"%>
</body>
</html>