package server.Models;

import org.springframework.data.mongodb.core.mapping.Field;

public class IngredientModel {
	@Field(value="id")
	private String id;
	
	@Field(value="unit")
	private String unit;
	
	@Field(value="amount")
	private double amount;
	
	@Field(value="name")
	private String name;
}
