package server.DAO;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import server.Models.CategoryModel;
//import server.Models.CategoryModel;
import server.Models.ProductModel;

public class ProductRepositoryImpl implements ProductRepositoryCustom{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public ProductModel create(String imgUrl, String name, int price, String category, String describe) {
		ProductModel pd = new ProductModel();
	
		pd.setName(name);
		pd.setImage(imgUrl);
		pd.setPrice(price);
		pd.setCategory(category);
		pd.setIsActive(1);
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		
		pd.setCreatedAt(current);
		pd.setUpdatedAt(current);
		
		ProductModel create = mongoTemplate.insert(pd);
		return create;
	}

	@Override
	public ProductModel update(String imgUrl, String name, int price, String category, String describe,String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		
		Update update = new Update();
		
		if (!imgUrl.isEmpty()) {
			update.set("image",imgUrl);
		}
		
		if (!name.isEmpty()) {
			update.set("name",name);
		}
		
		if (price >= 0) {
			update.set("price",price);
		}
		
		if (!category.isEmpty()) {
			update.set("category",category);
		}
		
		if (!describe.isEmpty()) {
			update.set("desc", describe);
		}
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		update.set("updated_at", current);
		
		UpdateResult result = mongoTemplate.updateFirst(query,update,ProductModel.class);
		
		if (result != null) {
			if (result.getModifiedCount() == 0) {
				return null;
			} else {
				return mongoTemplate.findOne(query,ProductModel.class);
			}
		}
        return null;
	}

}
