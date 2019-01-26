package net.community.domain.model.role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private long id;
	
	//@NotEmpty
	@Getter
	@Setter
	private String description;

	protected Role() {}

	public Role(String description) {
		super();
		this.description = description;
	}

}