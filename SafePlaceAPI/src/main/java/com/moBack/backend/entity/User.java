package com.moBack.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	
	@Column(name="first_name")
	String firstName;
	
	@Column(name="last_name")
	String lastName;
	
	@Column(name="email")
	String email;
	
	@Column(name="password")
	String password;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_position_id")
	private UserPosition userPosition;
	
	public User() {
		
	}
	
	public User(int id, String firstName, String lastName, String email, String password, UserPosition userPosition) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userPosition = userPosition;
	}
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public UserPosition getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(UserPosition userPosition) {
		this.userPosition = userPosition;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
    public static User createMember(String firstName, String lastName, String email, String password) {
        return new User(firstName,lastName,email,password);
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", userPosition=" + userPosition + "]";
	}

    
}
