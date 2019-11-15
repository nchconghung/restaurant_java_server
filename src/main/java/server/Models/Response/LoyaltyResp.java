package server.Models.Response;

public class LoyaltyResp {
	private int points;
	private int level;
	
	public LoyaltyResp() {
		
	}
	
	public LoyaltyResp(int points, int level) {
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
