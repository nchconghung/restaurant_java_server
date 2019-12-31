package server.Businesses;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import server.DAO.OrderRepository;
import server.Models.BillModel;
import server.Models.OrderModel;
import server.Models.OrderSessionModel;
import server.Models.TableModel;
import server.Models.Response.BillResp;
import server.Models.Response.DepartmentResp;
import server.Models.Response.OrderResp;
import server.Models.Response.OrderSessionResp;
import server.Models.Response.OrderWebResp;
import server.Models.Response.ProductTempResp;
import server.Models.Response.TableResp;
import server.Models.Response.UserResp;
import server.Models.Temporary.OrderTemporary;
import server.Models.Temporary.OrderedProductTemporary;
import server.Models.Temporary.SessionAddTemporary;
import server.Models.Temporary.SessionTemporary;

@Service 
public class OrderBus {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductBus productBus;
	
	@Autowired
	TableBus tableBus;
	
	@Autowired
	UserBus userBus;
	
	@Autowired 
	DepartmentBus departmentBus;
	
	public List<OrderResp> getList(Map<String,String[]> params){
        int page = Integer.parseInt(params.get("pg_page")[0]),
            size = Integer.parseInt(params.get("pg_size")[0]),
            status = Integer.parseInt(params.get("search_status")[0]);

        List<OrderResp> list = new ArrayList<>();
        Page<OrderModel> data = null;
        Pageable pageable = PageRequest.of(page, size, Sort.by("created_at").ascending());
        
        if (params.get("start_date_s") != null && params.get("end_date_s") != null) {
        	int startDate = Integer.parseInt(params.get("start_date_s")[0]),
				endDate = Integer.parseInt(params.get("end_date_s")[0]);
        	data = orderRepository.findOrderInTimeWithPageable(startDate,endDate,status,pageable);
        } else {
        	data = orderRepository.findByStatus(status,pageable);
        }
        
        for (OrderModel entity : data) {
        	OrderResp orderResp = converteOrderModelToOrderResp(entity);
        	list.add(orderResp);
        }
        
        return list;
	}

	public String createOrder(OrderTemporary order) {
		List<OrderSessionModel> orderSessionModel = converteSessionTemporaryToOrderSessionModel(order.getSessions());
		
		OrderModel orderModel = new OrderModel();
		orderModel.setTable(order.getTable());
		orderModel.setDepartment(order.getDepartment());
		orderModel.setSessions(orderSessionModel);
		
		Calendar calender  = Calendar.getInstance();			
		int current = (int) calender.getTimeInMillis();
		
		orderModel.setCreatedAt(current);
		orderModel.setUpdateAt(current);
		
		String id = orderRepository.create(orderModel);
		 
		 return id;
	}
	
	public boolean addSession(SessionAddTemporary session) {
		OrderSessionModel sessionModel = converteSessionAddTemporaryToSessionModel(session);
		boolean flat = orderRepository.addSession(session.get_id(),sessionModel);
		
		return flat;
	}
	
	public boolean updateStatus(Map<String, String[]> params) {
		String orderId = params.get("_id")[0];
		int status = Integer.parseInt(params.get("status")[0]);
		
		boolean flat = orderRepository.updateStatus(orderId, status);
		
		return flat;
	}
	
	public boolean updateBilling(OrderResp order) {
		
		List<BillModel> bills = converteBillRespListToBillModelList(order.getBills());
		
		boolean flat = orderRepository.updateBilling(order.get_id(), bills);
		
		return flat;
	}
	
	// Web Admin
	
	public List<OrderWebResp> getListWebAdmin(int startDate, int endDate, String employeeSearch, int total, String billID,
			int pageNo, int pageSize) {
		
		List<OrderModel> modelList = orderRepository.findWithManyFilterForWeb(startDate, endDate, employeeSearch, total, billID, pageNo, pageSize);
		
		List<OrderWebResp> respList = new ArrayList<>();
		
		for (OrderModel order : modelList) {
			respList.add(converteOrderModelToOrderWebResp(order));
		}
		return respList;
	}
	
	/////////////////////////////////////////////////////////////////

	private OrderWebResp converteOrderModelToOrderWebResp(OrderModel entity) {
		List<BillResp> bills  = converteBillModelListToOrderBillRespList(entity.getBills());
		TableResp table = tableBus.getTableResp(entity.getTable());
		List<OrderSessionResp> sessions = converteSessionModelListToSessionRespList(entity.getSessions());
		DepartmentResp department = departmentBus.getDepartmentResp(entity.getDepartment());
		
		UserResp creator = userBus.getUserResp(entity.getCreator());
		
		OrderWebResp orderResp = new OrderWebResp(entity.getId(),bills,
				table,entity.getCreatedAt(),
				entity.getUpdateAt(),entity.getStatus(),
				sessions,department,
				entity.getTotal(),entity.getDiscountPercent(),
				entity.getDiscountCode(),entity.getID(),
				creator);
		
		return orderResp;
	}
	
	private OrderResp converteOrderModelToOrderResp(OrderModel entity) {
		List<BillResp> bills  = converteBillModelListToOrderBillRespList(entity.getBills());
		TableResp table = tableBus.getTableResp(entity.getTable());
		List<OrderSessionResp> sessions = converteSessionModelListToSessionRespList(entity.getSessions());
		DepartmentResp department = departmentBus.getDepartmentResp(entity.getDepartment());
		
		OrderResp orderResp = new OrderResp(entity.getId(),bills,
				table,entity.getCreatedAt(),
				entity.getUpdateAt(),entity.getStatus(),
				sessions,department,
				entity.getTotal(),entity.getDiscountPercent(),
				entity.getDiscountCode(),entity.getID());
		
		return orderResp;
	}

	private List<BillResp> converteBillModelListToOrderBillRespList(List<BillModel> bills) {
		List<BillResp> result = new ArrayList<>();
		
		for (BillModel entity : bills) {
			ProductTempResp productTempResp = productBus.getProductTempResp(entity.getProduct());
			BillResp billResp = new BillResp(entity.getPrice(),productTempResp,
											 entity.getId(),entity.getNote(),entity.getQuantity());
			result.add(billResp);
		}
		
		return result;
	}
	
	private List<OrderSessionResp> converteSessionModelListToSessionRespList(List<OrderSessionModel> sessions) {
		List<OrderSessionResp> result = new ArrayList<>();
		
		for (OrderSessionModel entity : sessions) {
			UserResp creator = userBus.getUserResp(entity.getCreator());
			List<BillResp> detail = converteBillModelListToOrderBillRespList(entity.getDetail());
			
			OrderSessionResp sessionResp = new OrderSessionResp(entity.getId(),entity.getSubTotal(),
																entity.getCreateAt(),creator,detail);
			result.add(sessionResp);
		}
		return result;
	}

	private List<OrderSessionModel> converteSessionTemporaryToOrderSessionModel(List<SessionTemporary> sessions) {
		List<OrderSessionModel> result = new ArrayList<>();
		
		for (SessionTemporary entity : sessions) {
			OrderSessionModel sessionModel = new OrderSessionModel();
			int subTotal = 0;
			
			sessionModel.setCreateAt(entity.getCreated_at());
			sessionModel.setCreator(entity.getCreator());
			List<BillModel> bills = converteOrderedProductTemporaryListToBillModelList(entity.getDetail());
			sessionModel.setDetail(bills);
			
			
			for (BillModel bill : bills) {
				subTotal += bill.getPrice()*bill.getQuantity();
			}
			
			sessionModel.setSubTotal(subTotal);
			
			result.add(sessionModel);
		}
		
		return result;
	}

	private List<BillModel> converteOrderedProductTemporaryListToBillModelList(List<OrderedProductTemporary> detail) {
		List<BillModel> result = new ArrayList<>();
		
		for (OrderedProductTemporary e : detail) {
			int price = productBus.getPriceByProductId(e.getProduct());
			
			BillModel billModel = new BillModel(price,e.getProduct(),e.getNote(),e.getQuantity());
			
			result.add(billModel);
		}
		
		return result;
	}

	private OrderSessionModel converteSessionAddTemporaryToSessionModel(SessionAddTemporary session) {
		OrderSessionModel sessionModel = new OrderSessionModel();
		SessionTemporary sessionTemporary = session.getSession();
		
		int subTotal = 0;
		
		sessionModel.setCreateAt(sessionTemporary.getCreated_at());
		sessionModel.setCreator(sessionTemporary.getCreator());
		List<BillModel> bills = converteOrderedProductTemporaryListToBillModelList(sessionTemporary.getDetail());
		sessionModel.setDetail(bills);
		
		for (BillModel bill : bills) {
			subTotal += bill.getPrice()*bill.getQuantity();
		}
		
		sessionModel.setSubTotal(subTotal);
		
		return sessionModel;
	}
	
	private List<BillModel> converteBillRespListToBillModelList(List<BillResp> bills) {
		List<BillModel> result = new ArrayList<>();
		
		for (BillResp bill : bills) {
			BillModel billModel = new BillModel(bill.getPrice(),bill.getProduct().get_id(),
												bill.getNote(),bill.getQuantity());
			
			result.add(billModel);
		}
		return result;
	}



}
