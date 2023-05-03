package com.Vijay.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Vijay.Model.*;

public class UserDao {
	private Connection con;
	private String Query;
	private PreparedStatement ps;
	private ResultSet rs;
	public UserDao(Connection con) {
		
		this.con = con;
	}
	
	
	public User userLogin(String email,String password) {
		User user = null;
		try {
			Query = "select * from users where email=? and password=?";
			ps = this.con.prepareStatement(Query);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return user;
	}
}
