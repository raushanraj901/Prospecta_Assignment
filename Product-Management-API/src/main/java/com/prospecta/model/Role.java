package com.prospecta.model;

import com.prospecta.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "roles")
@Data
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;

	@Column(unique = true, nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles name;

	public void setName(Roles roleName) {
		this.name = roleName;

	}

	public Roles getName() {
		return name;
	}
}
