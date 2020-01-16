package server.Models.Factory;

import server.Models.Factory.Object.Bill;
import server.Models.Factory.Object.Category;
import server.Models.Factory.Object.Department;
import server.Models.Factory.Object.Order;
import server.Models.Factory.Object.OrderSession;
import server.Models.Factory.Object.Product;
import server.Models.Factory.Object.Table;
import server.Models.Factory.Object.User;

// Abstract Factory Design Pattern
public abstract class AbstractFactory {
	public abstract  Bill createBill();
	public abstract Category createCategory();
	public abstract Department createDepartment();
	public abstract Order createOrder();
	public abstract OrderSession createOrderSession();
	public abstract Product createProduct();
	public abstract Table createTable();
	public abstract User createUser();
}
