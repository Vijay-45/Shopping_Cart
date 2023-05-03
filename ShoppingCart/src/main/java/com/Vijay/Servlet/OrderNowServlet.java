package com.Vijay.Servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.text.SimpleDateFormat;

import com.Vijay.Connection.DbConnection;
import com.Vijay.Dao.OrderDao;
import com.Vijay.Model.Cart;
import com.Vijay.Model.Order;
import com.Vijay.Model.User;

public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderNowServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Date date = new Date();

			User auth = (User) request.getSession().getAttribute("auth");
			if (auth != null) {
				String productId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if (productQuantity <= 0) {
					productQuantity = 1;
				}

				Order order = new Order();
				order.setId(Integer.parseInt(productId));
				order.setUid(auth.getId());
				order.setQuantity(productQuantity);
				order.setDate(format.format(date));

				OrderDao orderDao = new OrderDao(DbConnection.getConnection());
				boolean result = orderDao.insertOrder(order);
				if (result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c : cart_list) {
							if (c.getId() == Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
						
					}

					response.sendRedirect("Orders.jsp");
				} else {
					out.print("Order Failed");
				}

			} else {
				response.sendRedirect("Login.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
