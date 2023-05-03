package com.Vijay.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Vijay.Model.Cart;
import com.Vijay.Model.Product;

public class ProductDao {
	private Connection con;
	private String Query;
	private PreparedStatement ps;
	private ResultSet rs;
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<>();
		
		try {
			Query = "SELECT * FROM PRODUCTS";
			ps = con.prepareStatement(Query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Product row =new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setPrice(rs.getDouble("price"));
				row.setCategory(rs.getString("category"));
				row.setImage(rs.getString("image"));
				
				
				products.add(row);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public List<Cart> GetCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<>();
		try {
			if(cartList.size()>0) {
				for(Cart c:cartList) {
					Query = "select * from products where id=?";
					ps = this.con.prepareStatement(Query);
					ps.setInt(1, c.getId());
					rs = ps.executeQuery();
					while(rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setPrice(rs.getDouble("price")*c.getQuantity());
						row.setCategory(rs.getString("category"));
						row.setQuantity(c.getQuantity());
						
						products.add(row);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return products; 
		
	}
	public double getTotal(ArrayList<Cart> cartList) {
		double total=0;
		try {
			if(cartList.size()>0) {
				for(Cart c:cartList) {
					Query = "SELECT * FROM PRODUCTS WHERE ID=?";
					ps=this.con.prepareStatement(Query);
					ps.setInt(1, c.getId());
					
					rs=ps.executeQuery();
					
					while(rs.next()) {
						total+=rs.getDouble("price")*c.getQuantity();
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return total;
		
	}
	public Product getSingleProduct(int id) {
		Product rowProduct=null;
		try {
			Query = "select * from products where id=?";
			ps=this.con.prepareStatement(Query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				rowProduct=new Product();
				rowProduct.setId(rs.getInt("id"));
				rowProduct.setName(rs.getString("name"));
				rowProduct.setCategory(rs.getString("category"));
				rowProduct.setPrice(rs.getDouble("price"));
				rowProduct.setImage(rs.getString("image"));;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return rowProduct;
	}
}
