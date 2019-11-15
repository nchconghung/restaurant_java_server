package server.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class UserModel {
	@Id
	private String id;
	
	@Field("role")
	private String role;
	
	@Field("updated_at")
	private int updatedAt;
	
	@Field("created_at")
	private int createdAt;
	
	@Field("is_active")
	private int isActive;
	
	@Field("is_deleted")
	private int isDeleted;
	
	@Field("loyalty")
	private LoyaltyModel loyalty;
	
	@Field("is_update_profile")
	private int isUpdateProfile;
	
	@Field("login_count")
	private int loginCount;
	
	@Field("login_type")
	private int loginType;
	
	@Field("apns")
	private String apns;
	
	@Field("fcm")
	private String fcm;
	
	@Field("token")
	private String token;
	
	@Field("dob")
	private String dob;
	
	@Field("avatar_url")
	private String avatarUrl;
	
	@Field("email")
	private String email;
	
	@Field("address")
	private String address;
	
	@Field("delivery_phone_number")
	private String deliveryPhoneNumer;
	
	@Field("phone_number")
	private String phoneNumber;
	
	@Field("fullname")
	private String fullName;

	@Field("password")
	private String password;
	
	@Field("username")
	private String username;
	
	@Field("hospital")
	private String hospital;
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public UserModel() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(int updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
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

	public LoyaltyModel getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(LoyaltyModel loyalty) {
		this.loyalty = loyalty;
	}

	public int getIsUpdateProfile() {
		return isUpdateProfile;
	}

	public void setIsUpdateProfile(int isUpdateProfile) {
		this.isUpdateProfile = isUpdateProfile;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public String getApns() {
		return apns;
	}

	public void setApns(String apns) {
		this.apns = apns;
	}

	public String getFcm() {
		return fcm;
	}

	public void setFcm(String fcm) {
		this.fcm = fcm;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeliveryPhoneNumer() {
		return deliveryPhoneNumer;
	}

	public void setDeliveryPhoneNumer(String deliveryPhoneNumer) {
		this.deliveryPhoneNumer = deliveryPhoneNumer;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
