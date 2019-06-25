package com.ndartificer.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndartificer.workshopmongodb.domain.Post;
import com.ndartificer.workshopmongodb.repository.PostRepository;
import com.ndartificer.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public List<Post> findAll(){
		return repo.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow( () ->  new ObjectNotFoundException("objeto não encontrado!")); 
		
	}
	
}