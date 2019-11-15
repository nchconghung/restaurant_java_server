package server.Models.Response;

public class UserResp {
	private String _id;
	private String hospital;
	private LoyaltyResp loyalty;
	private int is_update_profile;
	private String fcm;
	private String token;
	private String dob;
	private String avatar_url;
	private String email;
	private String address;
	private String delivery_phone_number;
	private String phone_number;
	private String fullname;
	private String username;
	
	public UserResp() {
		
	}
	
	public UserResp(String _id, String hospital, LoyaltyResp loyalty, int is_update_profile, String fcm, String token,
			String dob, String avatar_url, String email, String address, String delivery_phone_number,
			String phone_number, String fullname, String username) {
		super();
		this._id = _id;
		this.hospital = hospital;
		this.loyalty = loyalty;
		this.is_update_profile = is_update_profile;
		this.fcm = fcm;
		this.token = token;
		this.dob = dob;
		this.avatar_url = avatar_url;
		this.email = email;
		this.address = address;
		this.delivery_phone_number = delivery_phone_number;
		this.phone_number = phone_number;
		this.fullname = fullname;
		this.username = username;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public LoyaltyResp getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(LoyaltyResp loyalty) {
		this.loyalty = loyalty;
	}

	public int getIs_update_profile() {
		return is_update_profile;
	}

	public void setIs_update_profile(int is_update_profile) {
		this.is_update_profile = is_update_profile;
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

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
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

	public String getDelivery_phone_number() {
		return delivery_phone_number;
	}

	public void setDelivery_phone_number(String delivery_phone_number) {
		this.delivery_phone_number = delivery_phone_number;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
