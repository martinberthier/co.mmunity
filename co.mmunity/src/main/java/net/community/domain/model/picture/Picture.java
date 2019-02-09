package net.community.domain.model.picture;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;
import net.community.domain.model.user.User;

@Entity
@Data
public class Picture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String pictureComment;
	
	private Long size;
	
	@ManyToOne
	private User user;
	

	protected Picture() {}

	public Picture(String name, String pictureComment, Long size, User user) {
		super();
		this.name = name;
		this.pictureComment = pictureComment;
		this.size = size;
		this.user = user;
	}

	
	
}
