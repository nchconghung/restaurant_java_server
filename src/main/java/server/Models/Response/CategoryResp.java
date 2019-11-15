package server.Models.Response;

public class CategoryResp {
	private String _id;
	private int created_at;
	private int products;
	private String image;
	private String name;
	
	public CategoryResp() {
		
	}
	
	public CategoryResp(String _id, int created_at, int products, String image, String name) {
		this._id = _id;
		this.created_at = created_at;
		this.products = products;
		this.image = image;
		this.name = name;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getCreated_at() {
		return created_at;
	}

	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}

	public int getProducts() {
		return products;
	}

	public void setProducts(int products) {
		this.products = products;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
