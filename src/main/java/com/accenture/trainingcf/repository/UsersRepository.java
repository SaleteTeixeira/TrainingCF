package com.accenture.trainingcf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.trainingcf.domain.UsersEntity;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String>{
	@Query("SELECT k FROM UsersEntity k WHERE function('contains', k.name , :keyword, function('fuzzy', 0.5)) = function('contains_rhs')")
	public List<UsersEntity> findByKeywordWithFuzzy(@Param("keyword") String keyword);
}
