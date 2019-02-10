package net.community.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.community.domain.model.user.IUserRepository;
import net.community.domain.model.user.User;
import net.community.service.IAdminService;

@Service
public class AdminService implements IAdminService {
	
	@Autowired
	IUserRepository users;

	@Override
	public List<User> listerUsers() {

		return users.findAll();
	}
	
	@Override
	public User addUser(User user) {
		return users.save(user);
	}
	
	@Override
	public User getUser(long id) {	
		
		return users.findById(id);
	}

	@Override
	public void deleteUser(long id) {
		users.deleteById(id);
	}
	
	@Override
	public void updateUser(User user) {

//		Optional<User> userToUpdate = users.findById(user.getId());
//
//		User userModif = userToUpdate.get();
//
//		userModif.setEmail(user.getEmail());
//		userModif.setLogin(user.getLogin());
//		users.save(userModif);
//		
//		Determiner le parametre à modifier et réécrire les instructions
	}

}
