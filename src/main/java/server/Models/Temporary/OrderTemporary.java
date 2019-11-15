package server.Models.Temporary;

import java.util.List;

public class OrderTemporary {
	private String table;
	private String department;
	private List<SessionTemporary> sessions;
	
	public OrderTemporary() {
		
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<SessionTemporary> getSessions() {
		return sessions;
	}

	public void setSessions(List<SessionTemporary> sessions) {
		this.sessions = sessions;
	}
	
}
