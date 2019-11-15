package server.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection= "table_orders")
public class OrderModel {
	@Id
	private String id;
	
	@Field(value="creator")
	private String creator;
	
	@Field(value="table")
	private String table;
	
	@Field(value="department")
	private String department;
	
	@Field(value="hospital")
	private String hospital;
	
	@Field(value="updated_at")
	private int updateAt;
	
	@Field(value="created_at")
	private int createdAt;
	
	@Field(value="billed_at")
	private int billedAt;
	
    @Field(value = "is_active")
    private int isActive;

    @Field(value = "is_deleted")
    private int isDeleted;

    @Field(value = "status")
    private int status;
    
    @Field(value="bills")
    private List<BillModel> bills;
    
    @Field(value="sessions")
    private List<OrderSessionModel> sessions;
    
    @Field(value="total")
    private int total;
    
    @Field(value="loyalty_points")
    private int loyaltyPoint;
    
    @Field(value="discount_percent")
    private int discountPercent;
    
    @Field(value="discount_code")
    private String discountCode;
    
    @Field(value="id")
    private String ID;
    
    public OrderModel() {
    	
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}
	
	public int getBilledAt() {
		return billedAt;
	}

	public void setBilledAt(int billedAt) {
		this.billedAt = billedAt;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public int getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(int updateAt) {
		this.updateAt = updateAt;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<BillModel> getBills() {
		return bills;
	}

	public void setBills(List<BillModel> bills) {
		this.bills = bills;
	}

	public List<OrderSessionModel> getSessions() {
		return sessions;
	}

	public void setSessions(List<OrderSessionModel> sessions) {
		this.sessions = sessions;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getLoyaltyPoint() {
		return loyaltyPoint;
	}

	public void setLoyaltyPoint(int loyaltyPoint) {
		this.loyaltyPoint = loyaltyPoint;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
    
    
}
