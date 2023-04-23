package backend.project.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
	
//THIS CLASS IS FOR THE EXISTING USERS HARDCODED AND THE NEW USERS CREATED

@Table(name = "users")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	// Username with unique constraint
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String passwordHash;

	
	@Column(name = "role", nullable = false)
	private String role;	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Review> reviews;
	
public User() {}
	
	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
