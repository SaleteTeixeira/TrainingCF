package com.accenture.trainingcf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.trainingcf.domain.SalesOrderEntity;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrderEntity, String>{
	
	@Query("SELECT k FROM SalesOrderEntity k WHERE k.status LIKE :keyword")
	public List<SalesOrderEntity> findByKeyword(@Param("keyword") String keyword);
	
	@Query("SELECT k FROM SalesOrderEntity k WHERE function('contains', k.status , :keyword, function('fuzzy', 0.5)) = function('contains_rhs')")
	public List<SalesOrderEntity> findByKeywordWithFuzzy(@Param("keyword") String keyword);
	
}