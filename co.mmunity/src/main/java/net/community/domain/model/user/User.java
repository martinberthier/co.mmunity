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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String name;
	
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
	@ManyToMany
	@JoinTable(name = "role_id")
	@JsonIgnore
	private Set<Role> roles = new HashSet<Role>();
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	//FETCH :
	//Permet de préciser comment la propriété est chargée selon deux modes :
	//LAZY : la valeur est chargée uniquement lors de son utilisation
	//EAGER : la valeur est toujours chargée (valeur par défaut)
	//Cette fonctionnalité permet de limiter la quantité de données obtenue par une requête
	@JoinTable(name = "tags_id")
	@JsonIgnore
	private Set<Tag>tags= new HashSet<Tag>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "pictures_id")
	@JsonIgnore
	private List<Picture>pictures = new ArrayList<Picture>();

	private boolean active;
	
	protected User() {}

	public User(@NotEmpty @Size(min = 1, max = 20) String lastname, @NotEmpty @Size(min = 1, max = 20) String name,
			@NotEmpty @Size(min = 1, max = 20) String job, @Email @NotEmpty @Size(min = 1, max = 20) String email,
			@NotEmpty @Size(min = 1, max = 20) String password, Set<Role> roles, Set<Tag> tags, List<Picture> pictures,
			boolean active) {
		super();
		this.lastname = lastname;
		this.name = name;
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

	public List<Picture> getPictures ()
	{
		return new ArrayList<Picture>(pictures);
	}
	
	
	public void removePicture(Picture picture)
	{
		this.pictures.remove(picture);
	}
	public void addRole(Role role) {
		this.roles.add(role);
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
	}

	public Set<Role> GetRoles() {
		return new HashSet<Role>(roles);
	}
	
	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	public void removeTag(Tag tag) {
		this.tags.remove(tag);
	}

	public Set<Tag> GetTags() {
		return new HashSet<Tag>(tags);
	}

}