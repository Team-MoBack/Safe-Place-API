package com.moBack.backend.api.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;

	@OneToMany(mappedBy="account")
	Set<Review> reviewList;

	@Column(name="password")
	private String password;

	public void addReview(Review review) {
		reviewList.add(review);
		review.setAccount(this);
	}

	public void deleteReview(Review review) {
		reviewList.remove(review);
		review.setAccount(null);
	}
}
