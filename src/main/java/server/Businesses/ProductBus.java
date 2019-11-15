package server.Businesses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import server.DAO.ProductRepository;
import server.Models.ProductModel;
import server.Models.TableModel;
import server.Models.Response.CategoryResp;
import server.Models.Response.ProductResp;
import server.Models.Response.ProductTempResp;

@Service
public class ProductBus {
	
	@Autowired 
	ProductRepository productRepository;
	
	@Autowired
	CategoryBus categoryBus;
	
	public List<ProductResp> getList(Map<String,String[]> params){
		int page = Integer.parseInt(params.get("pg_page")[0]);
		int size = Integer.parseInt(params.get("pg_size")[0]);
		
		List<ProductResp> result = new ArrayList<>();
		Page<ProductModel> data = null;
		Pageable pageable;
		
		if (params.get("search_category") != null) {
			String category = params.get("search_category")[0];
			ObjectId categoryId = new ObjectId(category);
			pageable = PageRequest.of(page,size, Sort.by("name").ascending());
			data = productRepository.findByCategory(categoryId, pageable);
		} else if (params.get("search_name") != null) {
			String name = params.get("search_name")[0];
			pageable = PageRequest.of(page,size, Sort.by("category").ascending());
			data = productRepository.findByName(name, pageable);
			System.out.println(data.toList().size());
		} else {
			
			pageable = PageRequest.of(page,size, Sort.by("name").ascending());
			data = productRepository.findAll(pageable);
		}
		
		for (ProductModel entity : data.toList()) {
			CategoryResp category = categoryBus.getCategoryRespById(entity.getCategory());
			
			ProductResp productResp = new ProductResp(entity.getId(),category,
													  entity.getCreatedAt(),"type",
													  "unit",entity.getDiscountPercent(),
													  entity.getPrice(),entity.getImage(),
													  entity.getDesc(),entity.getName());
			result.add(productResp);
		}
		return result;
	}

	public ProductTempResp getProductTempResp(String productId) {
		
		
		ProductModel data = productRepository.findById(productId).get();
		
		if (data != null) {
			ProductTempResp result = new ProductTempResp(data.getId(),data.getCreatedAt(),
														 "type","unit",
														 data.getDiscountPercent(),data.getPrice(),
														 data.getImage(),data.getDesc(),data.getName());
			return result;
		}
		
		return null;
	}

	public int getPriceByProductId(String productId) {
		ProductModel data = productRepository.findById(productId).get();
		
		if(data != null) {
			return data.getPrice();
		}
		
		return 0;
	}
}
