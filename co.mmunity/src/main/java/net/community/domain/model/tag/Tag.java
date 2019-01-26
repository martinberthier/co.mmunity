package net.community.domain.model.tag;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private long id;
	
	@Getter
	@Setter
	private String libelle;

	protected Tag() {
		super();
	}

	public Tag(String libelle) {
		super();
		this.libelle = libelle;
	}
}
