package server.Businesses;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.DAO.OrderRepository;
import server.Models.OrderModel;
import server.Models.OrderSessionModel;
import server.Models.Response.TableStatisticResp;

@Service
public class TableStatisticBus {
	
	@Autowired
	OrderRepository orderRepository;
	
	public TableStatisticResp getStatistics(Map<String,String[]> params) {
		int startDate = Integer.parseInt(params.get("start_date_s")[0]);
		int endDate = Integer.parseInt(params.get("end_date_s")[0]);
		
		List<OrderModel> data = null;
		TableStatisticResp result = new TableStatisticResp();
		
		int numberOrder=0,
			numberFood=0,
			totalRevenue=0,
			numberPendingOrder=0;
		
		data = orderRepository.findOrderInTime(startDate, endDate);
		numberOrder = data.size();
		
		for (OrderModel entity : data) {
			totalRevenue += entity.getTotal();
			System.out.println(entity.getId());
			for (OrderSessionModel sessionModel : entity.getSessions()) {
				numberFood += sessionModel.getDetail().size();
			}
			
			if (entity.getStatus() == 0) numberPendingOrder++;
		}
		
		result.setNumber_of_orders(numberOrder);result.setNumber_of_foods(numberFood);
		result.setTotal_revenue(totalRevenue);result.setNumber_of_pending_orders(numberPendingOrder);
		
		return result;
	}
}
