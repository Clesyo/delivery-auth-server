package br.com.delivery.auth.models;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class Role implements Serializable, GrantedAuthority{

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name.toString();
	}
}
