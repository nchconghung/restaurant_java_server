package server.Businesses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.DAO.CategoryRepository;
import server.Models.CategoryModel;
import server.Models.TableModel;
import server.Models.Response.CategoryResp;

@Service 
public class CategoryBus {
	
	@Autowired 
	private CategoryRepository categoryRepository;
	
	@Autowired 
	ImageBus imageBus;
	
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
	
	public List<CategoryModel> getListForWeb(Map<String,String[]> params){
		List<CategoryModel> list;
		int page = Integer.parseInt(params.get("pg_page").toString()),
			size = Integer.parseInt(params.get("pg_size").toString());
		Pageable pageable = PageRequest.of(page,size,Sort.by("name").ascending());
		
		list = categoryRepository.findAll(pageable).toList();
		return list;
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

	public CategoryModel create(String imgUrl,String name) {
		CategoryModel data = categoryRepository.create(imgUrl,name);
		return data;
	}
	
	public CategoryModel update(String imgUrl,String name,String id) {
		CategoryModel data = categoryRepository.update(imgUrl,name,id);
		return data;
	}

	public boolean delete(String id) {
		try {
			categoryRepository.deleteById(id);
			return true;
		} catch (Exception x) {
			return false;
		}
	}
}
