package server.Models.Response;

import server.Models.DepartmentModel;

public class TableWebResponse {
	 public String _id;
	    public DepartmentModel department;
	    public int status;
	    public String name;
	    
	    public TableWebResponse() {
	    	
	    }
	    
	    public TableWebResponse(String _id, DepartmentModel department, int status, String name) {
	        this._id = _id;
	        this.department = department;
	        this.status = status;
	        this.name = name;
	    }

	    public String get_id() {
	        return _id;
	    }

	    public void set_id(String _id) {
	        this._id = _id;
	    }

	    public DepartmentModel getDepartment() {
	        return department;
	    }

	    public void setDepartment(DepartmentModel department) {
	        this.department = department;
	    }

	    public int getStatus() {
	        return status;
	    }

	    public void setStatus(int status) {
	        this.status = status;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
}
