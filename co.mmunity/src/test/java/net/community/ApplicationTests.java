package net.community;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.community.domain.model.picture.IPictureRepository;
import net.community.domain.model.picture.Picture;
import net.community.domain.model.role.IRoleRepository;
import net.community.domain.model.role.Role;
import net.community.domain.model.tag.ITagRepository;
import net.community.domain.model.tag.Tag;
import net.community.domain.model.user.IUserRepository;
import net.community.domain.model.user.User;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Autowired
	IUserRepository users;
	
	@Autowired
	IRoleRepository roles;
	
	@Autowired
	ITagRepository tags;
	
	@Autowired
	IPictureRepository pictures;
	
	@Autowired
	Populate populate;
	
	@Before
	public void setUp() throws Exception {
		populate.nettoiePuisCree();
	}
	
	@After
	public void tearDown() throws Exception {
		//populate.nettoyage();
	}
	
//	@Test
//	public void contextLoads() {
//		Role vip = new Role("vip");
//		roles.save(vip);
//		
//		Tag dormir = new Tag("dormir");
//		tags.save(dormir);
//		
//		Set<Tag>tagsAntoine = new HashSet<Tag>();
//		tagsAntoine.add(dormir);
//		
//		Picture photoAntoine = new Picture("bonjour!","url12.com");
//		pictures.save(photoAntoine);
//		
//		Set<Picture>photosAntoine = new HashSet<Picture>();
//		photosAntoine.add(photoAntoine);
//		
//		User antoine = new User("Peglion","Antoine","stagiaire","bactave@mail.com","mypassword",vip,tagsAntoine, photosAntoine);
//		users.save(antoine);
//	}

}

