package server.DAO;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import server.Models.DepartmentModel;
import server.Models.TableModel;

public class TableRepositoryImpl implements TableRepositoryCustom{
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public boolean updateStatus(String id,int status){

        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("status",status);
        
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		update.set("updated_at",current);
        
        UpdateResult result = mongoTemplate.updateFirst(query,update,TableModel.class);

        if (result != null)
            return result.getModifiedCount() == 0 ? false:true;
        return false;
    }

	@Override
	public TableModel create(String name, String department) {
		TableModel pd = new TableModel();
		
		pd.setName(name);
		pd.setDepartment(department);
		pd.setActive(1);
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		
		pd.setCreatedAt(current);
		pd.setUpdatedAt(current);
		
		TableModel create = mongoTemplate.insert(pd);
		return create;
	}

	@Override
	public TableModel update(String name, String department, String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		
		
		Update update = new Update();
		
		if (!name.isEmpty()) {
			update.set("name", name);
		}
		
		if (!department.isEmpty()) {
			update.set("department", department);
		}
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		update.set("updated_at", current);
		
		UpdateResult result = mongoTemplate.updateFirst(query,update,TableModel.class);
		
		if (result != null) {
			if (result.getModifiedCount() == 0) {
				return null;
			} else {
				return mongoTemplate.findOne(query,TableModel.class);
			}
		}
        return null;
	}
}