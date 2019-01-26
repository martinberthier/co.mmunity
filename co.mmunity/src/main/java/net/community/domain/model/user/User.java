package net.community.domain.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import net.community.domain.model.picture.Picture;
import net.community.domain.model.role.Role;
import net.community.domain.model.tag.Tag;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private long id;
	
	@NotEmpty
	@Getter
	@Setter
	@Size(min = 1, max = 20)
	private String lastname;
	
	@NotEmpty
	@Getter
	@Setter
	@Size(min = 1, max = 20)
	private String surname;
	
	@NotEmpty
	@Getter
	@Setter
	@Size(min = 1, max = 20)
	private String job;
	
	@Email
	@NotEmpty
	@Getter
	@Setter
	@Size(min = 1, max = 20)
	private String email;
	
	@NotEmpty
	@Getter
	@Setter
	@Size(min = 1, max = 20)
	private String password;
	
	@NotEmpty
	@Getter
	@Setter
	private Role role;
	
	@OneToMany(fetch = FetchType.EAGER)
	//FETCH :
	//Permet de préciser comment la propriété est chargée selon deux modes :
	//LAZY : la valeur est chargée uniquement lors de son utilisation
	//EAGER : la valeur est toujours chargée (valeur par défaut)
	//Cette fonctionnalité permet de limiter la quantité de données obtenue par une requête
	@Getter
	@Setter
	private Set<Tag>tags= new HashSet<Tag>();
	
	@OneToMany
	@Getter
	@Setter
	private Set<Picture>pictures = new HashSet<Picture>();

	protected User() {}

	public User(@NotEmpty String lastname, @NotEmpty String surname, @NotEmpty String job, @NotEmpty String email,
			@NotEmpty String password, @NotEmpty Role role, Set<Tag> tags, Set<Picture> pictures) {
		super();
		this.lastname = lastname;
		this.surname = surname;
		this.job = job;
		this.email = email;
		this.password = password;
		this.role = role;
		this.tags = tags;
		this.pictures = pictures;
	}
	
}