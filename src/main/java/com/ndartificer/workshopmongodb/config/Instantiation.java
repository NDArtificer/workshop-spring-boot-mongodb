package com.ndartificer.workshopmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ndartificer.workshopmongodb.domain.Post;
import com.ndartificer.workshopmongodb.domain.User;
import com.ndartificer.workshopmongodb.dto.AuthorDTO;
import com.ndartificer.workshopmongodb.dto.CommentDTO;
import com.ndartificer.workshopmongodb.repository.PostRepository;
import com.ndartificer.workshopmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRespository;
	
	@Override
	public void run(String... args) throws Exception {
	

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRespository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRespository.saveAll(Arrays.asList(maria, alex ,bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem!", "Viajar para Olinda-PE, abraços! ", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom Dia!", "Segundo dia em Olinda-PE, curtindo as férias! ", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem, meu amigo!", sdf.parse("21/07/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/07/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha uma ótimo dia!", sdf.parse("23/07/2018"), new AuthorDTO(alex));
		
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRespository.save(maria);	
	}

}
