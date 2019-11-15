package server.Models.Response;

public class ProductResp {
	private String _id;
	private CategoryResp category;
	private int created_at;
	private String type;
	private String unit;
	private int discount_percent;
	private int price;
	private String image;
	private String desc;
	private String name;
	
	public ProductResp() {
		
	}
	
	public ProductResp(String _id, CategoryResp category, int created_at, String type, String unit,
			int discount_percent, int price, String image, String desc, String name) {
		super();
		this._id = _id;
		this.category = category;
		this.created_at = created_at;
		this.type = type;
		this.unit = unit;
		this.discount_percent = discount_percent;
		this.price = price;
		this.image = image;
		this.desc = desc;
		this.name = name;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public CategoryResp getCategory() {
		return category;
	}

	public void setCategory(CategoryResp category) {
		this.category = category;
	}

	public int getCreated_at() {
		return created_at;
	}

	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getDiscount_percent() {
		return discount_percent;
	}

	public void setDiscount_percent(int discount_percent) {
		this.discount_percent = discount_percent;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
