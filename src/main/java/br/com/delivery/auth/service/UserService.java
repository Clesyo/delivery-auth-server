package br.com.delivery.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.delivery.auth.feignclients.UserFeignClient;
import br.com.delivery.auth.interfaces.IUserService;
import br.com.delivery.auth.models.User;

@Service
public class UserService implements IUserService{
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	public User findByEmail(String email) {
		var user = userFeignClient.findByEmail(email);
		if(user == null) {
			logger.error("User not found "+ email);
			throw new IllegalArgumentException("User not found.");
		}
		logger.info("User found "+ email);
		return user;
	}

	@Override
	public User findById(Long id) {
		var user = userFeignClient.findByid(id);
		if(user == null) {
			logger.error("User not found "+ id);
			throw new IllegalArgumentException("User not found.");
		}
		logger.info("User found "+ id);
		return user;
	}

}
