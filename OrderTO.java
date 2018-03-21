package com.pizzashop.order.transferobjects;

public class OrderTO {
	
	private String itemName;
	private Integer orderTime;
	private String formattedOrderDate;
	
	
	public OrderTO() {}
	public OrderTO(String itemName, Integer orderTime) {
		this.itemName = itemName;
		this.orderTime = orderTime;
		
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Integer orderTime) {
		this.orderTime = orderTime;
	}
	public String getFormattedOrderDate() {
		return formattedOrderDate;
	}
	public void setFormattedOrderDate(String formattedOrderDate) {
		this.formattedOrderDate = formattedOrderDate;
	}

}
