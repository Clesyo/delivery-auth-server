package br.com.delivery.auth.dto;

import br.com.delivery.auth.models.User;

public class UserDto {

	private String name;
	private String email;

	public UserDto(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public static UserDto convert(User user) {
		return new UserDto(user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
