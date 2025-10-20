package com.deboravivia.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import com.deboravivia.workshopmongo.domain.User;
import com.deboravivia.workshopmongo.dto.UserDTO;
import com.deboravivia.workshopmongo.repository.UserRepository;
import com.deboravivia.workshopmongo.service.exception.ObjectNotFoundException;



@Service
public class UserService {

	private final UserRepository repo;

    UserService(UserRepository repo) {
        this.repo = repo;
    }
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public Optional<User> findById(String id) {
		Optional<User> user = repo.findById(id);
		return Optional.ofNullable(user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!")));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = repo.findById(obj.getId()).get();
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
