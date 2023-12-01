package org.java.spring.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.java.spring.db.pojo.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
	
	List<Pizza> findPizzaByNameContainingIgnoreCase(String query);
}
