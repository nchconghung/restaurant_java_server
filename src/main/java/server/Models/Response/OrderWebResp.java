package server.Models.Response;

import java.util.List;

public class OrderWebResp {
	private String _id;
	private List<BillResp> bills;
	private UserResp creator;
	private TableResp table;
	private int created_at;
	private int billed_at;
	private int status;
	private List<OrderSessionResp> sessions;
	private DepartmentResp department;
	private int total;
	private int discount_percent;
	private String discount_code;
	private String id;
	
	public OrderWebResp() {
		
	}

	public OrderWebResp(String _id, List<BillResp> bills, TableResp table, int created_at, int billed_at,
			int status, List<OrderSessionResp> sessions, DepartmentResp department, int total, int discount_percent,
			String discount_code, String id,UserResp user) {
		super();
		this._id = _id;
		this.bills = bills;
		this.table = table;
		this.created_at = created_at;
		this.billed_at = billed_at;
		this.status = status;
		this.sessions = sessions;
		this.department = department;
		this.total = total;
		this.discount_percent = discount_percent;
		this.discount_code = discount_code;
		this.id = id;
		this.creator = user;
	}
	
	public UserResp getCreator() {
		return creator;
	}

	public void setCreator(UserResp creator) {
		this.creator = creator;
	}

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public List<BillResp> getBills() {
		return bills;
	}

	public void setBills(List<BillResp> bills) {
		this.bills = bills;
	}

	public TableResp getTable() {
		return table;
	}

	public void setTable(TableResp table) {
		this.table = table;
	}

	public int getCreated_at() {
		return created_at;
	}

	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}

	public int getBilled_at() {
		return billed_at;
	}

	public void setBilled_at(int billed_at) {
		this.billed_at = billed_at;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<OrderSessionResp> getSessions() {
		return sessions;
	}

	public void setSessions(List<OrderSessionResp> sessions) {
		this.sessions = sessions;
	}

	public DepartmentResp getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentResp department) {
		this.department = department;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getDiscount_percent() {
		return discount_percent;
	}

	public void setDiscount_percent(int discount_percent) {
		this.discount_percent = discount_percent;
	}

	public String getDiscount_code() {
		return discount_code;
	}

	public void setDiscount_code(String discount_code) {
		this.discount_code = discount_code;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}
	
}
