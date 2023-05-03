package com.Vijay.Model;

public class Order extends Product {
	private int orderId;
	private int uid;
	private int quantity;
	private String date;
	
	
	public Order(){
		
	}
	
	

	public Order(int id, String name, String category, double price, String image, int uid, int quantity, String date) {
		super(id, name, category, price, image);
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}


	public Order(int id, String name, String category, double price, String image, int orderId, int uid, int quantity,
			String date) {
		super(id, name, category, price, image);
		this.orderId = orderId;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String data) {
		this.date = data;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", uid=" + uid + ", quantity=" + quantity + ", date=" + date + "]";
	}
	
	
}
