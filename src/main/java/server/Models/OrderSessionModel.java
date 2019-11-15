package server.Models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class OrderSessionModel {
	@Field(value="creator")
	private String creator;
	
	@Field(value="_id")
	private String id;
	
	@Field(value="sub_total")
	private int subTotal;
	
	@Field(value="created_at")
	private int createAt;
	
	@Field(value="detail")
	private List<BillModel> detail;
	
	public OrderSessionModel() {
		
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	public int getCreateAt() {
		return createAt;
	}

	public void setCreateAt(int createAt) {
		this.createAt = createAt;
	}

	public List<BillModel> getDetail() {
		return detail;
	}

	public void setDetail(List<BillModel> detail) {
		this.detail = detail;
	}
	
	
}
