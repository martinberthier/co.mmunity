package net.community.domain.model.tag;

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
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;

	@ManyToMany(mappedBy ="tags")
	@JsonIgnore
	private Set<User> users = new HashSet<User> ();
	
	protected Tag() {}
//
//	public Tag(String name) {
//		super();
//		this.name = name;
//	}
	
	public Tag(String name, Set<User> users) {
		super();
		this.name = name;
		this.users = users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
}
