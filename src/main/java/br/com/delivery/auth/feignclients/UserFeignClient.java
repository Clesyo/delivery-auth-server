package br.com.delivery.auth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.delivery.auth.models.User;

@Component
@FeignClient(name = "delivery-user", path = "/users")
public interface UserFeignClient {

	@GetMapping("/search")
	User findByEmail(@RequestParam String email);
	
	@GetMapping("/{id}")
	User findByid(@PathVariable Long id);
}
