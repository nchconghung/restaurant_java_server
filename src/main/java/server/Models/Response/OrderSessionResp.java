package server.Models.Response;

import java.util.List;

public class OrderSessionResp {
	private String _id;
	private int sub_total;
	private int created_at;
	private UserResp creator;
	private List<BillResp> detail;
	
	public OrderSessionResp() {
		
	}
	
	public OrderSessionResp(String _id, int sub_total, int created_at, UserResp creator, List<BillResp> detail) {
		super();
		this._id = _id;
		this.sub_total = sub_total;
		this.created_at = created_at;
		this.creator = creator;
		this.detail = detail;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getSub_total() {
		return sub_total;
	}

	public void setSub_total(int sub_total) {
		this.sub_total = sub_total;
	}

	public int getCreated_at() {
		return created_at;
	}

	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}

	public UserResp getCreator() {
		return creator;
	}

	public void setCreator(UserResp creator) {
		this.creator = creator;
	}

	public List<BillResp> getDetail() {
		return detail;
	}

	public void setDetail(List<BillResp> detail) {
		this.detail = detail;
	}
	
	
}
