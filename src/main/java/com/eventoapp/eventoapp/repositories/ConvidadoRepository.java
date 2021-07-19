package com.eventoapp.eventoapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventoapp.eventoapp.model.Convidado;
import com.eventoapp.eventoapp.model.Evento;

@Repository
public interface ConvidadoRepository  extends JpaRepository<Convidado, Long>{
	public List<Convidado> findByEvento(Evento evento);
}
