package server.DAO;

import java.util.List;

import server.Models.BillModel;
import server.Models.OrderModel;
import server.Models.OrderSessionModel;

public interface OrderRepositoryCustom {
	public String create(OrderModel order);
	public boolean addSession(String orderId,OrderSessionModel session);
	public boolean updateBilling(String orderId,List<BillModel> bills);
	public boolean updateStatus(String orderId,int status);
}
