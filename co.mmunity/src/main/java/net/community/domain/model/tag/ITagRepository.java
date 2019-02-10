package net.community.domain.model.tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.community.domain.model.user.User;

public interface ITagRepository  extends JpaRepository<Tag, Long>{

	Tag findByName(String name);
	List<Tag> findByUsersContains(User user);
	
}
