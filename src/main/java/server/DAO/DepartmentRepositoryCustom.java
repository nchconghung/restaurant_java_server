package server.DAO;

import server.Models.DepartmentModel;

public interface DepartmentRepositoryCustom {
	public DepartmentModel create(String name);
	public DepartmentModel update(String name,String id);
}
