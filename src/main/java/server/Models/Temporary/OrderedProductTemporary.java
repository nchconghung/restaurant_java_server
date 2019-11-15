package server.Models.Temporary;

public class OrderedProductTemporary {
	private String product;
	private int quantity;
	private String note;
	
	public OrderedProductTemporary() {
		
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
