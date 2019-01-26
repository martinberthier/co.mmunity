package net.community.domain.model.picture;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Picture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private long id;
	
	@Getter
	@Setter
	private String pictureComment;
	
	@Getter
	@Setter
	private String url;

	protected Picture() {
		super();
	}

	public Picture(String pictureComment, String url) {
		super();
		this.pictureComment = pictureComment;
		this.url = url;
	}
	
	
	
}
