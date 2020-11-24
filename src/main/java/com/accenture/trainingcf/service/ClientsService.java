package com.accenture.trainingcf.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.criterion.Example;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcf.domain.ClientsEntity;
import com.accenture.trainingcf.dto.ClientsDTO;
import com.accenture.trainingcf.repository.ClientsRepository;

@Service
public class ClientsService {

	@Autowired
	ClientsRepository rep;
	
	@Autowired
	ModelMapper mapper;
	
	public List<ClientsDTO> findAll(String keyword){
		List<ClientsEntity> result = null;
		
		if(Strings.isEmpty(keyword)){
			result = rep.findAll();
		}
		else{
			result = rep.findByKeywordWithFuzzy(keyword);
		}
		
		return result.parallelStream().map(item -> {
			return mapper.map(item,  ClientsDTO.class);
		}).collect(Collectors.toList());
	}
	
	public ClientsDTO findById(String id){
		Optional<ClientsEntity> cli = rep.findById(id);
		
		if(cli.isPresent()){
			return mapper.map(cli.get(), ClientsDTO.class);
		}
		
		return null;
	}
	
	public ClientsDTO saveClient(ClientsDTO client){
		if(Strings.isEmpty(client.getId())){
			client.setCreatedAt(LocalDateTime.now().toString());
			client.setCreatedBy("app");
		}
		else{
			client.setModifiedAt(LocalDateTime.now().toString());
			client.setModifiedBy("app");
		}
		
		ClientsEntity save = rep.save(mapper.map(client, ClientsEntity.class));
		
		return mapper.map(save, ClientsDTO.class);
	}

	public boolean deleteClient(String id){
		boolean cli = rep.existsById(id); 
				
		if(cli){
			rep.deleteById(id);
		}
		
		return cli;
	}
}
