package net.community.domain.model.picture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.community.domain.model.user.User;

public interface IPictureRepository extends JpaRepository <Picture, Long> {
	
//	List<Picture> findByUser(User user);
//	
//	List<Picture> findByUserId(long id);
	
	Picture findById(long id);
	
	Picture findByName(String name);
}

