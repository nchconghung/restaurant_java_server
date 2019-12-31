package server.Businesses;

import org.springframework.stereotype.Service;

import server.Models.UserModel;

@Service
public class AuthBus {
	public UserModel autheticateUser(String token) {
		UserModel user = new UserModel();
		return user;
	}
}
