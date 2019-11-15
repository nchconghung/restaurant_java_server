package server.Models.Temporary;

public class SessionAddTemporary {
	private String _id;
	private String table;
	private SessionTemporary session;
	
	public SessionAddTemporary() {
		
	}
	
	

	public String get_id() {
		return _id;
	}



	public void set_id(String _id) {
		this._id = _id;
	}



	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public SessionTemporary getSession() {
		return session;
	}

	public void setSession(SessionTemporary session) {
		this.session = session;
	}
	
	
}
