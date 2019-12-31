package server.DAO;

import server.Models.CategoryModel;

public interface CategoryRepositoryCustom{
	public CategoryModel create(String img,String name);
	public CategoryModel update(String img,String name,String id);
}
