package it.jac.management.service;

import java.util.Optional;

import it.jac.management.model.User;

public interface UserService {

	public Optional<User> get(Long id);

	public User create(User c);
	
	public void delete(Long id);
	
	public User update(User c, Long id);
	
	public User login(String u, String p);
}