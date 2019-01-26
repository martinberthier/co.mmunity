package net.community;

import java.util.HashSet;
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
		
		Role admin = new Role("administrateur");
		Role regular = new Role("utilisateur lambda");
		
		roles.save(admin);
		roles.save(regular);
		
		Tag boxe = new Tag("Boxe");
		Tag musique = new Tag("Musique");
		Tag cuisine = new Tag("Cuisine");
		Tag poney = new Tag("Poney");
		
		tags.save(boxe);
		tags.save(musique);
		tags.save(cuisine);
		tags.save(poney);
		
		Set<Tag>tagsUtilisateur1 = new HashSet<Tag>();
		tagsUtilisateur1.add(cuisine);
		
		Set<Tag>tagsUtilisateur2 = new HashSet<Tag>();
		tagsUtilisateur2.add(boxe);
		tagsUtilisateur2.add(poney);
		
		Set<Tag>tagsUtilisateur3 = new HashSet<Tag>();
		tagsUtilisateur3.add(musique);
		
		Picture photo1Utilisateur1 = new Picture("Ma première photo !","url1.com");
		Picture photo2Utilisateur1 = new Picture("","url2.com");
		Picture photo3Utilisateur1 = new Picture("Encore une !","url3.com");
		
		Picture photo1Utilisateur2 = new Picture("Moi en train de faire du poney","url4.com");
		Picture photo2Utilisateur2 = new Picture("Encore moi, encore sur un poney","url5.com");
		
		Picture photo1Utilisateur3 = new Picture("","url6.com");
		Picture photo2Utilisateur3 = new Picture("C'est toujours moi","url6.com");
		
		pictures.save(photo1Utilisateur1);
		pictures.save(photo2Utilisateur1);
		pictures.save(photo3Utilisateur1);
		
		pictures.save(photo1Utilisateur2);
		pictures.save(photo2Utilisateur2);
		
		pictures.save(photo1Utilisateur3);
		pictures.save(photo2Utilisateur3);
		
		Set<Picture>picturesUtilisateur1 = new HashSet<Picture>();
		picturesUtilisateur1.add(photo1Utilisateur1);
		picturesUtilisateur1.add(photo2Utilisateur1);
		picturesUtilisateur1.add(photo3Utilisateur1);
		
		Set<Picture>picturesUtilisateur2 = new HashSet<Picture>();
		picturesUtilisateur2.add(photo1Utilisateur2);
		picturesUtilisateur2.add(photo2Utilisateur2);
		
		Set<Picture>picturesUtilisateur3 = new HashSet<Picture>();
		picturesUtilisateur3.add(photo1Utilisateur3);
		picturesUtilisateur3.add(photo2Utilisateur3);
		
		User martin = new User("berthier","martin","developpeur","martinb@mail.com","123456", admin, tagsUtilisateur1, picturesUtilisateur1);
		User claire = new User("peglion","claire","superstar","clairep@mail.com","topsecret",regular, tagsUtilisateur2, picturesUtilisateur2);
		User juju = new User("crevette","juliette","diva","juju@mail.com","monmdp",regular,tagsUtilisateur3, picturesUtilisateur3);
		
		users.save(martin);
		users.save(claire);
		users.save(juju);
		
	}
}