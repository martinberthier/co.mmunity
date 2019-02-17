package net.community;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.community.domain.model.picture.IPictureRepository;
import net.community.domain.model.picture.Picture;
import net.community.domain.model.role.IRoleRepository;
import net.community.domain.model.role.Role;
import net.community.domain.model.tag.ITagRepository;
import net.community.domain.model.tag.Tag;
import net.community.domain.model.user.IUserRepository;
import net.community.domain.model.user.User;



@Component
public class Populate {

	@Autowired
	IUserRepository users;
	
	@Autowired
	IRoleRepository roles;
	
	@Autowired
	ITagRepository tags;
	
	@Autowired
	IPictureRepository pictures;
	
	@Transactional
	public void nettoyage() {
		users.deleteAll();
		roles.deleteAll();
		tags.deleteAll();
		pictures.deleteAll();
	}
	
	public boolean hasNoData()
	{		
		return (users.count()==0 && tags.count()==0);
	}
	public void nettoiePuisCree()
	{
		nettoyage();
		creerJeuDessai();
	}
	
	public void creerJeuDessai() {
		
//		
//		
//		Role admin = new Role("administrateur", null);
//		Role regular = new Role("utilisateur lambda", null);
//		
//		roles.save(regular);
//		roles.save(admin);
//		
//		Set<Role>adminRole = new HashSet<Role>();
//		adminRole.add(regular);
//		adminRole.add(admin);
//		
//		Set<Role>regularRole = new HashSet<Role>();
//		regularRole.add(regular);
//		
//		
//		Tag boxe = new Tag("Boxe", null);
//		Tag musique = new Tag("Musique", null);
//		Tag cuisine = new Tag("Cuisine", null);
//		Tag poney = new Tag("Poney", null);
//		
//		tags.save(boxe);
//		tags.save(musique);
//		tags.save(cuisine);
//		tags.save(poney);
//		
//		Set<Tag>tagsUtilisateur1 = new HashSet<Tag>();
//		tagsUtilisateur1.add(cuisine);
//		
//		Set<Tag>tagsUtilisateur2 = new HashSet<Tag>();
//		tagsUtilisateur2.add(boxe);
//		tagsUtilisateur2.add(poney);
//		
//		Set<Tag>tagsUtilisateur3 = new HashSet<Tag>();
//		tagsUtilisateur3.add(musique);
//		
//		
//		
//		
//		List<Picture>picturesUtilisateur1 = new ArrayList<Picture>();
//		
//		List<Picture>picturesUtilisateur2 = new ArrayList<Picture>();
//		
//		List<Picture>picturesUtilisateur3 = new ArrayList<Picture>();
//		
//		User martin = new User("berthier","martin","developpeur","martinb@mail.com","123456", adminRole, tagsUtilisateur1, picturesUtilisateur1, true);
//		User claire = new User("peglion","claire","superstar","clairep@mail.com","topsecret",regularRole, tagsUtilisateur2, picturesUtilisateur2, true);
//		User juju = new User("crevette","juliette","diva","juju@mail.com","monmdp",regularRole,tagsUtilisateur3, picturesUtilisateur3, false);
//		
//		
//		
//		Picture photo1Utilisateur1 = new Picture("Ma première photo !","wow", 123L, martin);
//		Picture photo2Utilisateur1 = new Picture("Ma première photo !","wow", 123L, martin);
//		Picture photo3Utilisateur1 = new Picture("Ma première photo !","wow", 123L, martin);
//		
//		Picture photo1Utilisateur2 = new Picture("Ma première photo !","wow", 123L, claire);
//		Picture photo2Utilisateur2 = new Picture("Ma première photo !","wow", 123L, claire);
//		
//		Picture photo1Utilisateur3 = new Picture("Ma première photo !","wow", 123L, juju);
//		Picture photo2Utilisateur3 = new Picture("Ma première photo !","wow", 123L, juju);
//		
//		
//		users.save(martin);
//		users.save(claire);
//		users.save(juju);
//		
//		pictures.save(photo1Utilisateur1);
//		pictures.save(photo2Utilisateur1);
//		pictures.save(photo3Utilisateur1);
//		
//		pictures.save(photo1Utilisateur2);
//		pictures.save(photo2Utilisateur2);
//		
//		pictures.save(photo1Utilisateur3);
//		pictures.save(photo2Utilisateur3);
//		
		
		
	}
}