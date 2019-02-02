package net.community;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import net.community.domain.model.user.IUserRepository;
import net.community.domain.model.user.User;

@Configuration
@Slf4j
public class LoadDatabase {
	
	@Bean
	CommandLineRunner initDatabase(IUserRepository users) {
		return args -> {
			log.info("Preloading" + users.save(new User("bob","bobby","job","bob@mail.com","123soleil",null, null, null)));
			log.info("Preloading" + users.save(new User("dede","dudu","job2","dede@mail.com","123456",null, null, null)));
			log.info("Preloading" + users.save(new User("dada","dodo","job3","dada@mail.com","123soleil",null, null, null)));
			
			
		};	
	}
}