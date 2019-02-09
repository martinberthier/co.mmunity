package net.community.domain.model.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import co.simplon.crud.model.task.Task;
import lombok.Data;
import net.community.domain.model.picture.Picture;
import net.community.domain.model.role.Role;
import net.community.domain.model.tag.Tag;


@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Size(min = 1, max = 20)
	private String lastname;
	
	@NotEmpty
	@Size(min = 1, max = 20)
	private String surname;
	
	@NotEmpty
	@Size(min = 1, max = 20)
	private String job;
	
	@Email
	@NotEmpty
	@Size(min = 1, max = 20)
	private String email;
	
	@NotEmpty
	@Size(min = 1, max = 20)
	private String password;
	
	//@NotEmpty
	@OneToMany
	@JoinColumn(name = "role_id")
	private Set<Role> roles = new HashSet<Role>();
	
	
	@OneToMany(fetch = FetchType.EAGER)
	//FETCH :
	//Permet de préciser comment la propriété est chargée selon deux modes :
	//LAZY : la valeur est chargée uniquement lors de son utilisation
	//EAGER : la valeur est toujours chargée (valeur par défaut)
	//Cette fonctionnalité permet de limiter la quantité de données obtenue par une requête
	@JoinColumn(name = "tags_id")
	private Set<Tag>tags= new HashSet<Tag>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "pictures_id")
	private Set<Picture>pictures = new HashSet<Picture>();

	private boolean active;
	
	protected User() {}

	public User(@NotEmpty @Size(min = 1, max = 20) String lastname, @NotEmpty @Size(min = 1, max = 20) String surname,
			@NotEmpty @Size(min = 1, max = 20) String job, @Email @NotEmpty @Size(min = 1, max = 20) String email,
			@NotEmpty @Size(min = 1, max = 20) String password, Set<Role> roles, Set<Tag> tags, Set<Picture> pictures,
			boolean active) {
		super();
		this.lastname = lastname;
		this.surname = surname;
		this.job = job;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.tags = tags;
		this.pictures = pictures;
		this.active = active;
	}

	public void addPicture(Picture picture) {
		this.pictures.add(picture);
	}

	public List<Picture> getPictures()
	{
		return new ArrayList<Picture>(pictures);
	}
	
	
}