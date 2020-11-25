package com.accenture.trainingcf.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcf.domain.SalesOrderEntity;
import com.accenture.trainingcf.domain.SalesOrderItemEntity;
import com.accenture.trainingcf.domain.SalesOrderEntity;
import com.accenture.trainingcf.dto.SalesOrderDTO;
import com.accenture.trainingcf.dto.SalesOrderItemDTO;
import com.accenture.trainingcf.dto.SalesOrderDTO;
import com.accenture.trainingcf.repository.SalesOrderItemRepository;
import com.accenture.trainingcf.repository.SalesOrderRepository;

@Service
public class SalesOrderService {
		
	@Autowired
	SalesOrderRepository repSO;
	SalesOrderItemRepository repSOI;
	
	@Autowired
	ModelMapper mapper;
	
	public List<SalesOrderDTO> findAll(String keyword){
        List<SalesOrderEntity> result = null;
       
        if (Strings.isEmpty(keyword)){
            result = repSO.findAll();
        } else {
        	result = repSO.findByKeywordWithFuzzy(keyword);
        }
       
        return result.stream().map(item -> {
            SalesOrderDTO salesOrder = mapper.map(item, SalesOrderDTO.class);
            return salesOrder;
        }).collect(Collectors.toList());
    }
	
	public SalesOrderDTO findById(String salesOrderId){
		Optional<SalesOrderEntity> result = repSO.findById(salesOrderId);
		
		if(result.isPresent()){
			return mapper.map(result.get(), SalesOrderDTO.class);
		}
		
		return null;
	}
	
	public List<SalesOrderItemDTO> findItems(String salesOrderId){
		return null;
	}
	
	public SalesOrderDTO saveSalesOrder(SalesOrderDTO salesOrder){
		SalesOrderEntity salesOrderE = mapper.map(salesOrder, SalesOrderEntity.class);
		
		if(Strings.isEmpty(salesOrder.getId())){
			salesOrderE.setCreatedAt(LocalDateTime.now());
			salesOrderE.setCreatedBy("app");
		}
		else{
			salesOrderE.setModifiedAt(LocalDateTime.now());
			salesOrderE.setModifiedBy("app");
		}
		
		SalesOrderEntity savedEntity = repSO.saveAndFlush(salesOrderE);
		salesOrderE.getItems().stream().forEach(item -> {
			item.setSalesOrder(savedEntity);
			repSOI.save(item);
			}
		);
		
		
		return mapper.map(savedEntity, SalesOrderDTO.class);
	}
	
	public boolean deleteSalesOrder(String id){
		boolean so = repSO.existsById(id); 
		
		if(so){
			repSO.deleteById(id);
		}
		
		return so;
	}

	public boolean deleteSalesOrderItem(){
		return false;
	}
	
}
