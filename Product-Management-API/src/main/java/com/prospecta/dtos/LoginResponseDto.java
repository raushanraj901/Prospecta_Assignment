package com.prospecta.dtos;

import lombok.Data;

@Data
public class LoginResponseDto {

	private String token;
	private long expiresIn;

	public void setToken(String token) {
		this.token = token;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

}