package br.com.delivery.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.delivery.auth.form.CredentialForm;
import br.com.delivery.auth.service.AuthenticationService;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticateController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping
	public ResponseEntity<Object> auth(@RequestBody @Valid CredentialForm form, BindingResult result)
			throws MethodArgumentNotValidException, NoSuchMethodException, SecurityException {

		return authenticationService.authenticate(form);
	}

	@GetMapping("/me")
	public ResponseEntity<Object> me(@RequestHeader String token) {

		return authenticationService.me(token);
	}
}
