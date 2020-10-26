package com.curso.escalab.tarea.uno.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.escalab.tarea.uno.model.Cliente;


public interface IClienteRepo extends JpaRepository<Cliente, Integer> {
	
}