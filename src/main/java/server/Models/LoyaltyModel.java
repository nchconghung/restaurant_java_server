package server.Models;

import org.springframework.data.mongodb.core.mapping.Field;

public class LoyaltyModel {
	@Field("poinst")
	private int points;
	
	@Field("level")
	private int level;
	
	public LoyaltyModel() {
		
	}

	public LoyaltyModel(int points, int level) {
		super();
		this.points = points;
		this.level = level;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
