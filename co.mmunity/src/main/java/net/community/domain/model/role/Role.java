package net.community.domain.model.role;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import net.community.domain.model.user.User;

@Entity
@Data
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//@NotEmpty
	private String name;
	
	@ManyToMany(mappedBy ="roles")
	@JsonIgnore
	private Set<User> users = new HashSet<User> ();

	protected Role() {}

	
//	public Role(String name) {
//		super();
//		this.name = name;
//	}
	public Role(String name, Set<User> users) {
		super();
		this.name = name;
		this.users = users;
	}


}