package com.accenture.trainingcf.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcf.domain.UsersEntity;
import com.accenture.trainingcf.dto.UsersDTO;
import com.accenture.trainingcf.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	UsersRepository rep;
	
	@Autowired
	ModelMapper mapper;
	
	public List<UsersDTO> findAll(String keyword){
		List<UsersEntity> result = null;
		
		if(Strings.isEmpty(keyword)){
			result = rep.findAll();
		}
		else{
			result = rep.findByKeywordWithFuzzy(keyword);
		}
		
		return result.parallelStream().map(item -> {
			return mapper.map(item,  UsersDTO.class);
		}).collect(Collectors.toList());
	}
	
	public UsersDTO findById(String id){
		Optional<UsersEntity> user = rep.findById(id);
		
		if(user.isPresent()){
			return mapper.map(user.get(), UsersDTO.class);
		}
		
		return null;
	}
	
	public UsersDTO saveUser(UsersDTO user){
		UsersEntity userE = mapper.map(user, UsersEntity.class);
		
		if(Strings.isEmpty(user.getId())){
			userE.setCreatedAt(LocalDateTime.now());
			userE.setCreatedBy("app");
		}
		userE.setModifiedAt(LocalDateTime.now());
		userE.setModifiedBy("app");
		
		UsersEntity save = rep.save(userE);
		return mapper.map(save, UsersDTO.class);
	}

	public boolean deleteUser(String id){
		boolean user = rep.existsById(id); 
				
		if(user){
			rep.deleteById(id);
		}
		
		return user;
	}
}