package net.community.service;

import java.util.List;

import net.community.domain.model.user.User;


public interface IAdminService {
	
	List<User> listerUsers();

	User addUser(User user);

	User getUser(long id);

	void deleteUser(long id);

	void updateUser(User user);

}
