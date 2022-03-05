package br.com.delivery.auth.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.delivery.auth.config.jwt.JwtService;
import br.com.delivery.auth.dto.MessageDto;
import br.com.delivery.auth.dto.TokenDto;
import br.com.delivery.auth.dto.UserDto;
import br.com.delivery.auth.feignclients.UserFeignClient;
import br.com.delivery.auth.form.CredentialForm;
import br.com.delivery.auth.models.User;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private JwtService jwtService;
	
	private BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userFeignClient.findByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException("Não foi encontrado usuário com email informado.");
		return user;
	}

	public ResponseEntity<Object> authenticate(@Valid CredentialForm form) {

		return authenticateUser(form);
	}

	private ResponseEntity<Object> authenticateUser(CredentialForm form) {
		try {
			UserDetails user = loadUserByUsername(form.getEmail());
			boolean isValidPassword = encode.matches(form.getPassword(), user.getPassword());
			if (isValidPassword) {

				String token = jwtService.generateToken(user);
				System.out.println(token);
				return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			}
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDto("Email ou senha informada estão incorretos. Verifique os dados e tente novamente."),
					HttpStatus.UNAUTHORIZED);
		}

	}

	public ResponseEntity<Object> me(String token) {

		String login = jwtService.obtainLoginUser(token);
		User user = userFeignClient.findByEmail(login);
		return ResponseEntity.ok(UserDto.convert(user));
	}
}
