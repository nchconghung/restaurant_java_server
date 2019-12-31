package server.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.And;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import server.Models.BillModel;
import server.Models.OrderModel;
import server.Models.OrderSessionModel;
import server.Models.TableModel;
import server.Models.UserModel;

public class OrderRepositoryImpl implements OrderRepositoryCustom{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public String create(OrderModel order) {
		OrderModel create = mongoTemplate.insert(order);
		
		return create.getId();
	}
	
	public List<OrderModel> findWithManyFilterForWeb(int startDate,int endDate,String employeeSearch,
													int total,String billId,int pageNo,int pageSize){
		Query query = new Query();
		Criteria criteria = new Criteria();
		// Thêm điều kiên cho ngày từ startDate đến endDate 
		if (startDate >= 0) {
			if (endDate >= 0)
				query.addCriteria(Criteria.where("created_at").gte(startDate).lte(endDate));
			else 
				query.addCriteria(Criteria.where("created_at").gte(startDate));
		} else {
			query.addCriteria(Criteria.where("created_at").lte(endDate));
		}
		
		// Thêm điều kiện tìm kiếm người tạo
		if (!employeeSearch.isEmpty()) {
			List<UserModel> users = userRepository.findByNameFTS(employeeSearch);
			List<ObjectId> userIds = new ArrayList<>();
			for (UserModel user : users) {
				userIds.add(new ObjectId(user.getId()));
			}
			query.addCriteria(Criteria.where("creator").in(userIds));
		}
		
		// Thêm điều kiện tổng tiền
		if (total >= 0) {
			query.addCriteria(Criteria.where("total").is(total));
		}
		
		// Thêm điều kiện billId 
		if (!billId.isEmpty()) {
			ObjectId _orderId = new ObjectId(billId);
			query.addCriteria(Criteria.where("_id").is(_orderId));
		}
		
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        query.with(pageable);
        List<OrderModel> data = mongoTemplate.find(query,OrderModel.class);
        System.out.println(query.toString());
		return data;
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
