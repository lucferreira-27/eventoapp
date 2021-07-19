package com.eventoapp.eventoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventoapp.eventoapp.model.Convidado;
import com.eventoapp.eventoapp.model.Evento;
import com.eventoapp.eventoapp.repositories.ConvidadoRepository;

@Service
public class ConvidadoService {
	@Autowired
	private ConvidadoRepository convidadoRepository;
	
	public List<Convidado> listar(){
		return convidadoRepository.findAll();
	}
	public Convidado buscarConvidado(long id) {
		return convidadoRepository.findById(id).get();
	}
	public void deletarConvidado(long id) {
		 convidadoRepository.deleteById(id);
	}
	public List<Convidado> buscarConvidados(Evento evento){
		return convidadoRepository.findByEvento(evento);
	}
	public void salvarConvidado(Convidado convidado) {
		convidadoRepository.save(convidado);
	}	
}
