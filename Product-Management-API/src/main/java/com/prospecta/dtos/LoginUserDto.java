package com.prospecta.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserDto {

	@NotBlank(message = "Email Shoud Not Null")
	@Email(message = "Please enter a valid email")
	private String email;

	@NotBlank(message = "Password Shoud Not Null")
	private String password;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}