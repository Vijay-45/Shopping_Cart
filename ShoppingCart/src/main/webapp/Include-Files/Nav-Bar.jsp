<nav class="navbar navbar-expand-lg navbar-light bg-light">

	<a class="navbar-brand" href="Index.jsp">E-Commerce Shopping Cart</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item active"><a class="nav-link" href="Index.jsp">Home</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="Cart.jsp">Cart<span class="badge badge-danger px-1">${cart_list.size()}</span></a>
			</li>
			<%
			if(auth!=null){%>
				<li class="nav-item">
		          <a class="nav-link" href="Orders.jsp">Orders</a>
		        </li>
		        <li class="nav-item"><a class="nav-link" href="log-out">Logout</a>
				</li>
    	    <% }else{ %>
            	 <li class="nav-item"><a class="nav-link" href="Login.jsp">Login</a>
     			 </li>
             <% }%>
		</ul>
	</div>
</nav>