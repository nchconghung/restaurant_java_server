package server.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "products")
public class ProductModel {
	@Id
	private String id;
	
	@Field(value="category")
	private String category;
	
	@Field(value="updated_at")
	private int updatedAt;
	
	@Field(value="created_at")
	private int createdAt;
	
	@Field(value="is_active")
	private int isActive;
	
	@Field(value="is_deleted")
	private int isDeleted;
	
	@Field(value="data")
	private List<IngredientModel> data;
	
	@Field(value="discount_percent")
	private int discountPercent;
	
	@Field(value="price")
	private int price;
	
	@Field(value="image")
	private String image;
	
	@Field(value="desc")
	private String desc;
	
	@Field(value="name")
	@TextIndexed
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(int updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<IngredientModel> getData() {
		return data;
	}

	public void setData(List<IngredientModel> data) {
		this.data = data;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
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
