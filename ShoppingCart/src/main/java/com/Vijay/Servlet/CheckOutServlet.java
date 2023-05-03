package com.Vijay.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.Vijay.Connection.DbConnection;
import com.Vijay.Dao.OrderDao;
import com.Vijay.Model.Cart;
import com.Vijay.Model.Order;
import com.Vijay.Model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
//cart-check-out
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Date date = new Date();
			
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");
			
			if(cart_list!=null && auth!=null) {
				
				for(Cart c:cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(format.format(date));
					
					OrderDao orderDao = new OrderDao(DbConnection.getConnection());
					boolean result = orderDao.insertOrder(order);
					if(!result) break;
				}
				
				cart_list.clear();
				response.sendRedirect("Orders.jsp");
			}else {
				if(auth==null) response.sendRedirect("Login.jsp");
				response.sendRedirect("Cart.jsp");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
