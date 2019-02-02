package net.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.community.domain.model.picture.IPictureRepository;
import net.community.domain.model.user.IUserRepository;

@CrossOrigin
@RestController
@RequestMapping("/usercontrol")
public class UserController {
	
	@Autowired
	IPictureRepository pictures;
	
	@Autowired
	IUserRepository users;
	
	
}
