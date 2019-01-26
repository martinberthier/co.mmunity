package net.community;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.community.domain.model.picture.IPictureRepository;
import net.community.domain.model.role.IRoleRepository;
import net.community.domain.model.tag.ITagRepository;
import net.community.domain.model.user.IUserRepository;



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
		populate.nettoyage();
	}
	
	@Test
	public void contextLoads() {
		
	}

}

