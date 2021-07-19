package com.eventoapp.eventoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventoapp.eventoapp.model.Evento;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
	
}
