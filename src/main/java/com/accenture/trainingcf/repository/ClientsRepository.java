package com.accenture.trainingcf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.trainingcf.domain.ClientsEntity;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, String>{
	@Query("SELECT k FROM ClientsEntity k WHERE function('contains', k.name , :keyword, function('fuzzy', 0.5)) = function('contains_rhs') or function('contains', k.familyName , :keyword, function('fuzzy', 0.5)) = function('contains_rhs')")
	public List<ClientsEntity> findByKeywordWithFuzzy(@Param("keyword") String keyword);
}