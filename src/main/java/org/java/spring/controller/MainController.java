package org.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.java.spring.db.serv.PizzaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

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

	@GetMapping("/pizzas/create")
	public String create(Model model) {

		Pizza pizza = new Pizza();
		model.addAttribute(pizza);
		model.addAttribute("create", true);
		return "formPizza";
	}

	@PostMapping("/pizzas/create")
	public String store(
			Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {

		return savePizza(model, bindingResult, pizza);
	}

	@GetMapping("pizzas/edit/{id}")
	public String edit(Model model, @PathVariable int id) {

		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		model.addAttribute("create", false);
		return "formPizza";
	}

	@PostMapping("pizzas/edit/{id}")
	public String update(Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {

		return savePizza(model, bindingResult, pizza);
	}

	public String savePizza(Model model, BindingResult bindingResult, Pizza pizza) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			return "formPizza";
		}

		try {
			pizzaService.save(pizza);
		} catch (Exception e) {
			System.out.println("Create Error: " + e.getMessage());
			return "formPizza";
		}
		return "redirect:/";
	}

	@PostMapping("/pizzas/delete/{id}")
	public String delete(@PathVariable int id) {

		Pizza pizza = pizzaService.findById(id);
		pizzaService.delete(pizza);

		return "redirect:/";
	}
}
