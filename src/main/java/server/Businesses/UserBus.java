package server.Businesses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.DAO.UserRepository;
import server.Models.UserModel;
import server.Models.Response.LoyaltyResp;
import server.Models.Response.UserResp;

@Service
public class UserBus {
	@Autowired
	UserRepository userRepository;
	
	public UserResp getUserResp(String userId) {
		UserModel userModel = userRepository.findById(userId).get();
		
		if (userModel != null) {
			LoyaltyResp loyaltyResp = new LoyaltyResp(userModel.getLoyalty().getPoints(),userModel.getLoyalty().getLevel());
			UserResp userResp = new UserResp(userModel.getId(),userModel.getHospital(),
											 loyaltyResp,userModel.getIsUpdateProfile(),
											 userModel.getFcm(),userModel.getToken(),
											 userModel.getDob(),userModel.getAvatarUrl(),
											 userModel.getEmail(),userModel.getAddress(),
											 userModel.getDeliveryPhoneNumer(),userModel.getPhoneNumber(),
											 userModel.getFullName(),userModel.getUsername());
			return userResp;
		}
		return null;
	}
}
