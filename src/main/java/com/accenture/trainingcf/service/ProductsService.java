package com.accenture.trainingcf.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcf.domain.ProductsEntity;
import com.accenture.trainingcf.dto.ProductsDTO;
import com.accenture.trainingcf.repository.ProductsRepository;

@Service
public class ProductsService {
	
	@Autowired
	ProductsRepository rep;
	
	@Autowired
	ModelMapper mapper;
	
	public List<ProductsDTO> findAll(String keyword){
		List<ProductsEntity> result = null;
		
		if(Strings.isEmpty(keyword)){
			result = rep.findAll();
		}
		else{
			/*ProductsEntity exampleEntity = new ProductsEntity();
			exampleEntity.setName(name);
			Example example = Example.create(exampleEntity);
			result = rep.findAll(example);*/
			
			result = rep.findByKeywordWithFuzzy(keyword);
		}
		
		return result.parallelStream().map(item -> {
			return mapper.map(item,  ProductsDTO.class);
		}).collect(Collectors.toList());
	}
	
	public ProductsDTO findById(String id){
		Optional<ProductsEntity> prod = rep.findById(id);
		
		if(prod.isPresent()){
			return mapper.map(prod.get(), ProductsDTO.class);
		}
		
		return null;
	}
	
	public ProductsDTO saveProduct(ProductsDTO product){
		ProductsEntity productE = mapper.map(product, ProductsEntity.class);
		
		if(Strings.isEmpty(product.getId())){
			productE.setCreatedAt(LocalDateTime.now());
			productE.setCreatedBy("app");
		}
		productE.setModifiedAt(LocalDateTime.now());
		productE.setModifiedBy("app");
		
		ProductsEntity save = rep.save(productE);
		
		return mapper.map(save, ProductsDTO.class);
	}

	public boolean deleteProduct(String id){
		boolean prod = rep.existsById(id); 
				
		if(prod){
			rep.deleteById(id);
		}
		
		return prod;
	}
}