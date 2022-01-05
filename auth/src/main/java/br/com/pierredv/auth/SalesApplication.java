package br.com.pierredv.auth;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.pierredv.auth.entity.Permission;
import br.com.pierredv.auth.entity.User;
import br.com.pierredv.auth.repository.PermissionRepository;
import br.com.pierredv.auth.repository.UserRepository;

@SpringBootApplication
public class SalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
	

	
	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
						   BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userRepository, permissionRepository, passwordEncoder);
		};
	}
	private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
						   BCryptPasswordEncoder passwordEncoder) {
		Permission permission = null;
		Permission findPermission = permissionRepository.findByDescription("Admin");
		if (findPermission == null) {
			permission = new Permission();
			permission.setDescription("Admin");
			permission = permissionRepository.save(permission);
		} else {
			permission = findPermission;
		}
		User admin = new User();
		admin.setUserName("admin");
		admin.setIsAccountNonExpired(true);
		admin.setIsAccountNonLocked(true);
		admin.setIsCredentialsNonExpired(true);
		admin.setIsEnabled(true);
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setPermissions(Arrays.asList(permission));
		User find = userRepository.findByUserName("admin");
		if (find == null) {
			userRepository.save(admin);
		}
	}
  
}
