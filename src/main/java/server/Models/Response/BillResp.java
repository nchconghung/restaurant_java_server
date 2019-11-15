package server.Models.Response;

public class BillResp {
	private int price;
	private ProductTempResp product;
	private String _id;
	private String note;
	private int quantity;
	
	public BillResp() {
		
	}

	public BillResp(int price, ProductTempResp product, String _id, String note, int quantity) {
		super();
		this.price = price;
		this.product = product;
		this._id = _id;
		this.note = note;
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ProductTempResp getProduct() {
		return product;
	}

	public void setProduct(ProductTempResp product) {
		this.product = product;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
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
