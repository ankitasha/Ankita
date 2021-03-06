package com.cybage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Order_Details")
public class OrderDetails implements Serializable
{
 private static final long serialVersionUID = 7550745928843183535L;
 
 private String id;
 private Order order;
 private Product product;
 
 private int quantity;
 private double price;
 private double amount;

 
 
 @Id
 @Column(name = "ID", length = 50, nullable = false)
 public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}

/*@Column(name="Order_Id",length=50, nullable=false)
*/
@ManyToOne(fetch= FetchType.LAZY)
@JoinColumn(name="Order_Id", nullable=false ,  
foreignKey = @ForeignKey(name="Order_Details_Ord_FK"))
Order getOrder() {
	return order;
}
public void setOrder(Order order) {
	this.order = order;
}

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="Product_Id", nullable= false,
foreignKey = @ForeignKey(name="Order_Details_Prod_FK"))
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}


@Column(name = "Quantity", length = 50, nullable = false)
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

@Column(name = "Price", length = 50, nullable = false)
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}

@Column(name = "Amount", length = 50, nullable = false)
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
 
 

}
