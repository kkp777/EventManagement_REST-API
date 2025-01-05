package in.kkpit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


public class AuthModel {
	private String email;
	private String password;
	public AuthModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public AuthModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
