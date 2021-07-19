package com.eventoapp.eventoapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eventoapp.eventoapp.model.Convidado;
import com.eventoapp.eventoapp.model.Evento;
import com.eventoapp.eventoapp.service.ConvidadoService;
import com.eventoapp.eventoapp.service.EventoService;

@Controller
public class EventoController {

	@Autowired
	private EventoService eventoService;
	@Autowired
	private ConvidadoService convidadoService;
	@GetMapping("cadastrarEvento")
	public String cadastrarEvento(Evento evento) {
		System.out.println("cadastrar");
		return "evento/cadastroEvento";
	}

	@PostMapping("cadastrarEvento")
	public String salvarEvento(@Valid Evento evento, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("Empty");
			return "evento/cadastroEvento";
		}
		
		eventoService.salvarEvento(evento);
		model.addAttribute("validation", true);
		return "evento/cadastroEvento";
	}
	@GetMapping("eventos")
	public String listarEventos(Model model) {
		List<Evento> eventos = eventoService.listar();
		System.out.println(eventos);
		model.addAttribute("eventos", eventos);

		return  "index";
	}
	
	@GetMapping("{codigo}")
	public String detalhesEvento(@PathVariable long codigo, Model model) {
		Evento evento =  eventoService.buscarEvento(codigo);
		List<Convidado> convidados = convidadoService.buscarConvidados(evento);
		evento.setConvidados(convidados);
		model.addAttribute("eventoEscolhido", evento);
		return "evento/evento";
	}
	
	@PostMapping("{codigo}")
	public String adicionarConvidado(@PathVariable long codigo, Convidado convidado) {
		Evento evento =  eventoService.buscarEvento(codigo);
		convidado.setEvento(evento);
		convidadoService.salvarConvidado(convidado);
		
		return "redirect:{codigo}";
	}
	@GetMapping("evento/deletar/{codigo}")
	public String deleteEvento(@PathVariable long codigo) {
		eventoService.deletarEvento(codigo);
		
		return "redirect:/eventos";
	}
	@GetMapping("convidado/deletar/{codigo}")
	public String deleteConvidado(@PathVariable long codigo) {
		Convidado convidado = convidadoService.buscarConvidado(codigo);
		convidadoService.deletarConvidado(convidado.getId());
		Evento evento =  convidado.getEvento();
		
		return "redirect:/" + evento.getId();
	}
	

}
