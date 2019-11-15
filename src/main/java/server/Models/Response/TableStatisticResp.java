package server.Models.Response;

public class TableStatisticResp {
	private int number_of_orders;
	private int number_of_foods;
	private long total_revenue;
	private int number_of_pending_orders;
	
	public TableStatisticResp() {
		
	}

	public int getNumber_of_orders() {
		return number_of_orders;
	}

	public void setNumber_of_orders(int number_of_orders) {
		this.number_of_orders = number_of_orders;
	}

	public int getNumber_of_foods() {
		return number_of_foods;
	}

	public void setNumber_of_foods(int number_of_foods) {
		this.number_of_foods = number_of_foods;
	}

	public long getTotal_revenue() {
		return total_revenue;
	}

	public void setTotal_revenue(long total_revenue) {
		this.total_revenue = total_revenue;
	}

	public int getNumber_of_pending_orders() {
		return number_of_pending_orders;
	}

	public void setNumber_of_pending_orders(int number_of_pending_orders) {
		this.number_of_pending_orders = number_of_pending_orders;
	}
	
	
}
