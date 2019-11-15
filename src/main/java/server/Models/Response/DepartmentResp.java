package server.Models.Response;

public class DepartmentResp {
	private String _id;
	private int tables;
	private String address;
	private String name;
	
	public DepartmentResp() {
		
	}
	
	public DepartmentResp(String _id, int tables, String address, String name) {
		this._id = _id;
		this.tables = tables;
		this.address = address;
		this.name = name;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getTables() {
		return tables;
	}

	public void setTables(int tables) {
		this.tables = tables;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
