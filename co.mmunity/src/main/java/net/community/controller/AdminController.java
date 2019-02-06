package net.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.community.domain.model.user.User;
import net.community.service.IAdminService;

@Controller
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IAdminService adminService;
	
	//Adduser(Post avec requestMapping) url
		//@RequestMapping(value = "/adduser", method = RequestMethod.POST)
		@PostMapping("/adduser")
		public User addUser(@RequestBody  User newUser) {

			return adminService.addUser(newUser);
			
		}
		
		//Getuser(getavec pathvariablepour l'id)
		@GetMapping("/{id}")
		public User getUser(@PathVariable long id) {
			return adminService.getUser(id);
		}
		
		//Getusers(get)
		@GetMapping("/getusers")
		public List<User> getUsers(){
			return adminService.listerUsers();
		}
		
		//DeleteUser(Deleteavec pathvariablepour l'id)
		@DeleteMapping("/deleteuser/{id}")
		public void deleteUser(@PathVariable long id) {
			adminService.deleteUser(id);
		}
		//marche po
		
		//Updateuser(Put avec requestBody)
		@PutMapping("/updateuser")
		public void updateUser(@RequestBody User user) {
			adminService.updateUser(user);
		}
		
}
