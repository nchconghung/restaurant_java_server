package server.Businesses;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.DAO.CategoryRepository;
import server.Models.CategoryModel;
import server.Models.Response.CategoryResp;

@Service 
public class CategoryBus {
	
	@Autowired 
	private CategoryRepository categoryRepository;
	
	public List<CategoryResp> getList(){
		List<CategoryModel> list;
		List<CategoryResp> result = new ArrayList<>();
		
		list = categoryRepository.findAll();
		
		for (CategoryModel entity : list) {
			CategoryResp categoryObject = new CategoryResp(entity.getId(),
														   entity.getCreatedAt(),
														   entity.getProducts(),
														   entity.getImage(),
														   entity.getName());
			result.add(categoryObject);
		}
		
		return result;
	}
	
	public CategoryResp getCategoryRespById(String id) {
//		ObjectId categoryId = new ObjectId(id);
		CategoryModel data = categoryRepository.findById(id).get();
		CategoryResp categoryResp = new CategoryResp(data.getId(),
				data.getCreatedAt(),
				data.getProducts(),
				data.getImage(),
				data.getName());
		
		return categoryResp;
	}
}
