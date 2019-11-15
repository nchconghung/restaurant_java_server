package server.Models.Response;

public class TableResp{
    public String _id;
    public String department;
    public int status;
    public String name;
    
    public TableResp() {
    	
    }
    
    public TableResp(String _id, String department, int status, String name) {
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
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