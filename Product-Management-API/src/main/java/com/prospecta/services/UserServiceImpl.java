package com.prospecta.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prospecta.dtos.LoginUserDto;
import com.prospecta.dtos.UserDto;
import com.prospecta.enums.Roles;
import com.prospecta.exceptions.UserException;
import com.prospecta.model.Role;
import com.prospecta.model.User;
import com.prospecta.repositories.RoleRepository;
import com.prospecta.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User registerUser(UserDto userDto) throws UserException {
		if (userDto == null) {
			throw new UserException("User details cannot be null.");
		}

		if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
			throw new UserException("User with email " + userDto.getEmail() + " already exists.");
		}

		Optional<Role> optionalRole = roleRepository.findByName(Roles.ADMIN);
		if (optionalRole.isEmpty()) {
			throw new UserException("Role 'ADMIN' not found.");
		}

		User user = new User();
		user.setRole(optionalRole.get());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPhone(userDto.getPhone());

		return userRepository.save(user);
	}

	@Override
	public User loginUser(LoginUserDto loginDetails) throws UserException {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDetails.getEmail(), loginDetails.getPassword()));

		return userRepository.findByEmail(loginDetails.getEmail())
				.orElseThrow(() -> new UserException("Customer not found for email: " + loginDetails.getEmail()));
	}

}
