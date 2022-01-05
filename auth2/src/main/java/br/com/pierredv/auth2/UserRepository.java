package br.com.pierredv.auth2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.pierredv.auth2.entiry.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//@Query("SELECT u FROM User u WHERE u.userName =:userName")
	//User findByUserName(@Param("userName") String userName);
	
	User findByEmail(String email);
	
	

}
