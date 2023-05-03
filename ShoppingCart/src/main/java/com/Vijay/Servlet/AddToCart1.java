package com.Vijay.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.Vijay.Model.Cart;

/**
 * Servlet implementation class AddToCart1
 */
public class AddToCart1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			ArrayList<Cart> cartList = new ArrayList<Cart>();
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			Cart cn = new Cart();
			
			cn.setId(id);
			cn.setQuantity(1);
			
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list==null) {
				cartList.add(cn);
				session.setAttribute("cart-list", cartList);
			    response.sendRedirect("Index.jsp");
			}
			else {
				cartList = cart_list;
				boolean exist = false;
				for(Cart c:cart_list) {
					if(c.getId()==id) {
						exist = true;
						out.println("<h1 style='font:Arial;color:rgb(100,100,100);'>Item Already Exists<a href='Cart.jsp';display:block;>Go to the Cart</a></h1>");
					}
					
				}
				if(!exist) {
					cartList.add(cn);
					response.sendRedirect("Index.jsp");
				}
				
				for(Cart c:cart_list) {
					out.println(c.getId());
				}
			}
		}
	}

}
