package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.repository.Estilos;

@Controller
public class CervejasController {
	
	@Autowired
	Estilos estilos;
	
	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja){
		
		ModelAndView modelAndView = new ModelAndView("cerveja/CadastroCerveja");
		
		modelAndView.addObject("sabores", Sabor.values());
		modelAndView.addObject("estilos", estilos.findAll());
		modelAndView.addObject("origens", Origem.values());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes){
		
//		if( result.hasErrors( )){
//			model.addAttribute("mensagem", "Erro no formulário");
//			System.out.println("Tem erro sim!");
//			return novo(cerveja);
//		}
		
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!"); //flash attribute permanece mesmo depois do redirect
		
		System.out.println(">>>>>>>>> SKU: " + cerveja.getSku());
		System.out.println(">>>>>>>>> Nome: " + cerveja.getNome());
		System.out.println(">>>>>>>>> Descrição: " + cerveja.getDescricao());
		System.out.println(">>>>>>>>> Origem: " + cerveja.getOrigem());
		System.out.println(">>>>>>>>> Origem: " + cerveja.getSabor());
		return new ModelAndView("redirect:/cervejas/novo");
	}
	
}
