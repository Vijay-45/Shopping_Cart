package com.Vijay.Servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.Vijay.Connection.DbConnection;
import com.Vijay.Dao.UserDao;
import com.Vijay.Model.User;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.sendRedirect("Login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			String email = request.getParameter("Login-email");
			String password = request.getParameter("Login-password");
			
			
			UserDao uDao = new UserDao(DbConnection.getConnection());
			
			User user=uDao.userLogin(email, password);
			if(user!=null) {
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("Index.jsp");
			}
			else {
				out.println("User-Login-Failed");
			}
 		}
		catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
		}
	}

}
