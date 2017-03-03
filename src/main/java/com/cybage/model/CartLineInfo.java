package com.cybage.model;

public class CartLineInfo {
	
	private ProductInfo productInfo;
	private int quantity;
	
	

	public CartLineInfo() 
	{
	 this.quantity=0;
	}

	public Object getProductInfo()
	{
		return productInfo;
	}

	public void setQuantity(int i)
	{
	this.quantity = i;
		
	}

	public void setProductInfo(ProductInfo productInfo) 
	{
	this.productInfo= productInfo;	
	}

	public int getQuantity() {
		return quantity;
	}

 
	public double getAmount() {
		
	return this.productInfo.getPrice() * this.quantity;	}

}
