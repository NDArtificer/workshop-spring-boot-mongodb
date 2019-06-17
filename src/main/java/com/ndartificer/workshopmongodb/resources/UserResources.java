package com.ndartificer.workshopmongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndartificer.workshopmongodb.domain.User;
import com.ndartificer.workshopmongodb.dto.UserDTO;
import com.ndartificer.workshopmongodb.services.UserService;

@RestController
@RequestMapping(value ="/users")
public class UserResources {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value ="/{id}",method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){
	
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x-> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
