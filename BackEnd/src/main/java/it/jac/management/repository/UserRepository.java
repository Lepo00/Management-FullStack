package it.jac.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.management.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByIvaCode(String ivaCode);
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
}
