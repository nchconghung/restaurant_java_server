package server.Models.Temporary;

import java.util.List;

public class SessionTemporary {
	private String creator;
	private int created_at;
	private List<OrderedProductTemporary> detail;
	
	public SessionTemporary() {
		
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getCreated_at() {
		return created_at;
	}

	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}

	public List<OrderedProductTemporary> getDetail() {
		return detail;
	}

	public void setDetail(List<OrderedProductTemporary> detail) {
		this.detail = detail;
	}
	
}
