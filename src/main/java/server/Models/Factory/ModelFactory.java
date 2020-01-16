package server.Models.Factory;

import java.util.HashMap;

import server.Models.Factory.ModelObject.BillModel;
import server.Models.Factory.ModelObject.CategoryModel;
import server.Models.Factory.ModelObject.DepartmentModel;
import server.Models.Factory.ModelObject.OrderModel;
import server.Models.Factory.ModelObject.OrderSessionModel;
import server.Models.Factory.ModelObject.ProductModel;
import server.Models.Factory.ModelObject.TableModel;
import server.Models.Factory.ModelObject.UserModel;
import server.Models.Factory.Object.Bill;
import server.Models.Factory.Object.Category;
import server.Models.Factory.Object.Department;
import server.Models.Factory.Object.Order;
import server.Models.Factory.Object.OrderSession;
import server.Models.Factory.Object.Product;
import server.Models.Factory.Object.PrototypeObject;
import server.Models.Factory.Object.Table;
import server.Models.Factory.Object.User;

public class ModelFactory extends AbstractFactory{

	// Singleton DesignPattern
	private static ModelFactory instance;
	
	public static AbstractFactory getInstance() {
		if (instance == null) {
			synchronized (ResponseFactory.class) {
				if (instance == null) instance = new ModelFactory();
			}
		}
		return instance;
	}
	
	// Prototype Design Pattern 
	private HashMap<String,PrototypeObject> samples = new HashMap<>();

	private ModelFactory() {
		// Add sample	
		// Prototype Design Pattern
		samples.put("bill", new BillModel());
		samples.put("category", new CategoryModel());
		samples.put("department",new DepartmentModel());
		samples.put("order",new OrderModel());
		samples.put("orderSession",new OrderSessionModel());
		samples.put("product",new ProductModel());
		samples.put("table",new TableModel());
		samples.put("user", new UserModel());
	}
	
	// Create new instance by cloning object 
	
	@Override
	public Bill createBill() {
		try {
			return (BillModel) samples.get("bill").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public Category createCategory() {
		try {
			return (CategoryModel) samples.get("category").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public Department createDepartment() {
		try {
			return (DepartmentModel) samples.get("department").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public Order createOrder() {
		try {
			return (OrderModel) samples.get("order").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public OrderSession createOrderSession() {
		try {
			return (OrderSessionModel) samples.get("order").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public Product createProduct() {
		try {
			return (ProductModel) samples.get("product").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public Table createTable() {
		try {
			return (TableModel) samples.get("table").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public User createUser() {
		try {
			return (UserModel) samples.get("user").clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
