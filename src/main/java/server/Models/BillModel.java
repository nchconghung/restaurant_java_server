package server.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class BillModel {
	@Field(value="price")
	private int price;
	
	@Id
	private String id;
	
	@Field(value="product")
	private String product;
	
	@Field(value="note")
	private String note;
	
	@Field(value="quantity")
	private int quantity;
	
	public BillModel() {
		
	}

	public BillModel(int price, String product, String note, int quantity) {
		this.price = price;
		this.product = product;
		this.note = note;
		this.quantity = quantity;
	}



	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
