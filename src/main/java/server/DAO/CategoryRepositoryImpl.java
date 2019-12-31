package server.DAO;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import server.Models.CategoryModel;
import server.Models.OrderModel;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public CategoryModel create(String img,String name) {
		CategoryModel model = new CategoryModel();
		model.setImage(img);
		model.setName(name);
		model.setIsActive(1);
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		
		model.setCreatedAt(current);
		model.setUpdatedAt(current);
		
		CategoryModel create = mongoTemplate.insert(model);
		return create;
	}

	@Override
	public CategoryModel update(String img, String name, String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		
//		CategoryModel category = mongoTemplate.findOne(query,CategoryModel.class);
		
		Update update = new Update();
		
		if (!img.isEmpty()) {
			update.set("image",img);
		}
		update.set("name", name);
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		update.set("updated_at", current);
		
		UpdateResult result = mongoTemplate.updateFirst(query,update,CategoryModel.class);
		
		if (result != null) {
			if (result.getModifiedCount() == 0) {
				return null;
			} else {
				return mongoTemplate.findOne(query,CategoryModel.class);
			}
		}
        return null;
	}

}
