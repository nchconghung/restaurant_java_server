package server.DAO;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import server.Models.CategoryModel;
import server.Models.DepartmentModel;
//import server.Models.ProductModel;

public class DepartmentRepositoryImpl implements DepartmentRepositoryCustom {
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public DepartmentModel create(String name) {
		DepartmentModel pd = new DepartmentModel();
	
		pd.setName(name);
		pd.setActive(1);
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		
		pd.setCreatedAt(current);
		pd.setUpdatedAt(current);
		
		DepartmentModel create = mongoTemplate.insert(pd);
		return create;
	}
	
	@Override
	public DepartmentModel update(String name, String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		
		
		Update update = new Update();
		
		update.set("name", name);
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		update.set("updated_at", current);
		
		UpdateResult result = mongoTemplate.updateFirst(query,update,DepartmentModel.class);
		
		if (result != null) {
			if (result.getModifiedCount() == 0) {
				return null;
			} else {
				return mongoTemplate.findOne(query,DepartmentModel.class);
			}
		}
        return null;
	}
}
