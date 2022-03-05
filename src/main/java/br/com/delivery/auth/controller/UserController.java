package br.com.delivery.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.delivery.auth.interfaces.IUserService;
import br.com.delivery.auth.models.User;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email) {
		var user = userService.findByEmail(email);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		var user = userService.findById(id);
		return ResponseEntity.ok(user);
	}
}
