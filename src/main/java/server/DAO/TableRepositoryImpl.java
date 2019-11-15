package server.DAO;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

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
}