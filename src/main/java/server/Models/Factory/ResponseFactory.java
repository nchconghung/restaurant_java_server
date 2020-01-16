package server.Models.Factory;

import java.util.HashMap;

import server.Models.Factory.Object.Bill;
import server.Models.Factory.Object.Category;
import server.Models.Factory.Object.Department;
import server.Models.Factory.Object.Order;
import server.Models.Factory.Object.OrderSession;
import server.Models.Factory.Object.Product;
import server.Models.Factory.Object.PrototypeObject;
import server.Models.Factory.Object.Table;
import server.Models.Factory.Object.User;
import server.Models.Factory.ResponseObject.BillResponse;
import server.Models.Factory.ResponseObject.CategoryResponse;
import server.Models.Factory.ResponseObject.DepartmentResponse;
import server.Models.Factory.ResponseObject.OrderResponse;
import server.Models.Factory.ResponseObject.OrderSessionResponse;
import server.Models.Factory.ResponseObject.ProductResponse;
import server.Models.Factory.ResponseObject.TableResponse;
import server.Models.Factory.ResponseObject.UserResponse;

// Abstract Factory Design Pattern
public class ResponseFactory extends AbstractFactory{
	
	// Singleton DesignPattern
	private static ResponseFactory instance;
	
	public static ResponseFactory getInstance() {
		if (instance == null) {
			synchronized (ResponseFactory.class) {
				if (instance == null) instance = new ResponseFactory();
			}
		}
		return instance;
	}
	
	// Prototype Design Pattern 
	private HashMap<String,PrototypeObject> samples = new HashMap<>();

	private ResponseFactory() {
		// Add sample	
		// Prototype Design Pattern
		samples.put("bill", new BillResponse());
		samples.put("category", new CategoryResponse());
		samples.put("department",new DepartmentResponse());
		samples.put("order",new OrderResponse());
		samples.put("orderSession",new OrderSessionResponse());
		samples.put("product",new ProductResponse());
		samples.put("table",new TableResponse());
		samples.put("user", new UserResponse());
	}
	
	// Create new instance by cloning object 
	
	@Override
	public Bill createBill() {
		try {
			return (BillResponse) samples.get("bill").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public Category createCategory() {
		try {
			return (CategoryResponse) samples.get("category").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public Department createDepartment() { 
		try {
			return (DepartmentResponse) samples.get("department").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public Order createOrder() {
		try {
			return (OrderResponse) samples.get("order").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public OrderSession createOrderSession() {
		try {
			return (OrderSessionResponse) samples.get("orderSession").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public Product createProduct() {
		try {
			return (ProductResponse) samples.get("product").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public Table createTable() {
		try {
			return (TableResponse) samples.get("table").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public User createUser() {
		try {
			return (UserResponse) samples.get("user").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
}
