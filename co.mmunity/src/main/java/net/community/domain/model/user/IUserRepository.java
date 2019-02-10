package net.community.domain.model.user;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.community.domain.model.picture.Picture;
import net.community.domain.model.role.Role;
import net.community.domain.model.tag.Tag;



public interface IUserRepository extends JpaRepository<User, Long>{

	List<User> findAll();
	
	List<User> findByTagsContains(Tag tag);
	//List<User> findByTag(Tag tag); // ne fonctionne pas
	
	List<User> findByRolesContains(Role role);
	
	List<User> findByActive(boolean active);
	
//	List<User> findByActiveIsFalse(boolean active);
//	marche po
//	List<User> findByActiveIsTrue(boolean active);
	
	User findById(long id);
	
	User findByLastname(String lastname);
	
	User findByName(String name);
	
	User findByPicturesContains(Picture picture);
	
}

