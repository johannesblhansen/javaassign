package com.teamonline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * App User model entity. Used by the AppUserRepository
 * @author Johannes
 *
 */
@Entity
public class AppUser {
	
    @Id
    @GeneratedValue
    private Long id;

	@NotNull
    private String username;

    @NotNull
    private String password;


    public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
