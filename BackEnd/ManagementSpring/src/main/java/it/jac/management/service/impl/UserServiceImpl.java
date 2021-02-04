package it.jac.management.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.management.model.User;
import it.jac.management.repository.UserRepository;
import it.jac.management.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Optional<User> get(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User create(User c) {
		if (userRepository.findByIvaCode(c.getIvaCode()) == null
				&& userRepository.findByUsername(c.getUsername()) == null)
			return userRepository.save(c);
		return null;
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User update(User user, Long id) {
		return userRepository.findById(id).map(c -> { // update if entity already exists
			user.setId(c.getId());
			return create(user);
		}).orElseGet(() -> { // create if entity not exists
			return create(user);
		});
	}

	@Override
	public User login(String u, String p) {
		return userRepository.findByUsernameAndPassword(u, p);
	}

}
