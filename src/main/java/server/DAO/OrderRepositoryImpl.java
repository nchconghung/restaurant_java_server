package server.DAO;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import lombok.extern.java.Log;
import server.Models.BillModel;
import server.Models.OrderModel;
import server.Models.OrderSessionModel;
import server.Models.TableModel;

public class OrderRepositoryImpl implements OrderRepositoryCustom{
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String create(OrderModel order) {
		OrderModel create = mongoTemplate.insert(order);
		
		return create.getId();
	}

	@Override
	public boolean addSession(String orderId, OrderSessionModel session) {
		Query query = new Query(Criteria.where("_id").is(orderId));
		OrderModel order = mongoTemplate.findOne(query, OrderModel.class);
		
		if (order == null) {
			return false;
		}
		
		List<OrderSessionModel> sessions = order.getSessions();
		sessions.add(session);
		
		Update update = new Update();
		update.set("sessions", sessions);
		update.set("total",order.getTotal()+session.getSubTotal());
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		update.set("updated_at",current);
		
        UpdateResult result = mongoTemplate.updateFirst(query,update,OrderModel.class);
        if (result != null)
            return result.getModifiedCount() == 0 ? false:true;
        return false;
	}

	@Override
	public boolean updateBilling(String orderId,List<BillModel> bills) {
		Query query = new Query(Criteria.where("_id").is(orderId));
		
		OrderModel order = mongoTemplate.findOne(query, OrderModel.class);
		
		Update update = new Update();
		update.set("bills", order.getBills().addAll(bills));
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		update.set("updated_at",current);
		update.set("billed_at", current);
		
		UpdateResult result = mongoTemplate.updateFirst(query,update,OrderModel.class);

        if (result != null)
            return result.getModifiedCount() == 0 ? false:true;
        return false;
	}

	@Override
	public boolean updateStatus(String orderId, int status) {
        Query query = new Query(Criteria.where("_id").is(orderId));
        Update update = new Update();
        update.set("status",status);
        
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		update.set("updated_at",current);

        UpdateResult result = mongoTemplate.updateFirst(query,update,OrderModel.class);

        if (result != null)
            return result.getModifiedCount() == 0 ? false:true;
        return false;
	}

}
