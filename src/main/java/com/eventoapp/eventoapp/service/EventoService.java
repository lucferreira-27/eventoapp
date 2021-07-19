package com.eventoapp.eventoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventoapp.eventoapp.model.Evento;
import com.eventoapp.eventoapp.repositories.EventoRepository;

@Service
public class EventoService {
	@Autowired
	private EventoRepository eventoRepository;
	
	public List<Evento> listar(){
		return eventoRepository.findAll();
	}
	public Evento buscarEvento(long id) {
		return eventoRepository.findById(id).get();
	}
	public void deletarEvento(long id) {
		eventoRepository.deleteById(id);
	}
	public void salvarEvento(Evento evento) {
		eventoRepository.save(evento);
	}	
}
