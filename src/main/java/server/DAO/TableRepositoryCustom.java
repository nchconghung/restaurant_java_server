package server.DAO;

import server.Models.TableModel;

public interface TableRepositoryCustom {
	public boolean updateStatus(String id, int status);
	TableModel create(String name, String department);
	TableModel update(String name, String department, String id);
}