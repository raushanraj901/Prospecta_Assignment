package com.prospecta.services;

import com.prospecta.dtos.LoginUserDto;
import com.prospecta.dtos.UserDto;
import com.prospecta.exceptions.UserException;
import com.prospecta.model.User;


public interface UserService {
	
	User registerUser(UserDto userDto) throws UserException;
    User loginUser(LoginUserDto loginDetails) throws UserException;
	
}
