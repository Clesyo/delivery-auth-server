package br.com.delivery.auth.interfaces;

import br.com.delivery.auth.models.User;

public interface IUserService {

	User findByEmail(String email);
	
	User findById(Long id);
}
