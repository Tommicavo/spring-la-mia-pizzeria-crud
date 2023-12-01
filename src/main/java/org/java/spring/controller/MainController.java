package org.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.java.spring.db.serv.PizzaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.java.spring.db.pojo.Pizza;

@Controller
public class MainController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/")
	public String index(Model model, @RequestParam(required = false) String searchedWord) {

		List<Pizza> pizzas;
		if (searchedWord == null) {
			pizzas = pizzaService.findAll();
		} else {
			pizzas = pizzaService.searchPizza(searchedWord);
			model.addAttribute("searchedWord", searchedWord);
		}

		model.addAttribute("pizzas", pizzas);
		return "pizzas";
	}

	@GetMapping("/pizzas/{id}")
	public String show(Model model, @PathVariable int id) {

		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		return "pizza";
	}
}
