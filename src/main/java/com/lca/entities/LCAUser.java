package com.lca.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class LCAUser {
	
	@Id
	@GeneratedValue
	private Long userId;
	
	@Column(unique=true)
	private String userName;
	
	@Column(unique=true)
	private String email;
	
	@Column
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "lca_user_roles",
			joinColumns = @JoinColumn(
			name = "user_id", referencedColumnName = "userid"),
			inverseJoinColumns = @JoinColumn(
			name = "role_id", referencedColumnName = "roleid"))
	private Collection<Role> roles;

	public LCAUser(String userName, String password, String email) {
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
}
