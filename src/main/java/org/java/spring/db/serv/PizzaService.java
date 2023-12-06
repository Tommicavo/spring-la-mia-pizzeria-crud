package org.java.spring.db.serv;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.java.spring.db.repo.PizzaRepository;

import java.util.List;
import org.java.spring.db.pojo.Pizza;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	public List<Pizza> findAll() {
		return pizzaRepository.findAll();
	}

	public Pizza findById(int id) {
		return pizzaRepository.findById(id).get();
	}

	public void save(Pizza pizza) {
		pizzaRepository.save(pizza);
	}

	public List<Pizza> searchPizza(String query) {
		return pizzaRepository.findPizzaByNameContainingIgnoreCase(query);
	}

	public void delete(Pizza pizza) {
		pizzaRepository.delete(pizza);
	}
}
