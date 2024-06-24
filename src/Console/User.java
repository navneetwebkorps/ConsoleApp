package Console;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private String email;
	
	private List<phone> phones= new ArrayList<phone>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<phone> getPhones() {
		return phones;
	}

	public void setPhones(List<phone> phones) {
		this.phones = phones;
	}

	public User(String name, String email, List<phone> phones) {
		super();
		this.name = name;
		this.email = email;
		this.phones = phones;
	}

	
	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", phones=" + phones + "]";
	}
	
	
}