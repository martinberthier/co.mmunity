package net.community.domain.model.user;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.community.domain.model.picture.Picture;
import net.community.domain.model.tag.Tag;



public interface IUserRepository extends JpaRepository<User, Long>{

	List<User> findAll();
	
	List<User> findByTagsContains(Tag tag);
	//List<User> findByTag(Tag tag); // ne fonctionne pas
	
//	List<User> findByActiveIsFalse(boolean active);
//	marche po
//	List<User> findByActiveIsTrue(boolean active);
	
	User findById(long id);
	
	User findByPicturesContains(Picture picture);
	
}

