package com.curso.escalab.tarea.uno.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.escalab.tarea.uno.exception.ModeloNotFoundException;
import com.curso.escalab.tarea.uno.model.Supervisor;
import com.curso.escalab.tarea.uno.service.ISupervisorService;

public class SupervisorController {
	@Autowired
	private ISupervisorService supervisorService;
	
	@GetMapping
	public ResponseEntity<List<Supervisor>> listar(){
		List<Supervisor> supervisores = supervisorService.listar();
		return new ResponseEntity<List<Supervisor>>(supervisores, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Supervisor> listarPorId (@PathVariable("id") Integer id){
		Supervisor supervisor = supervisorService.leerPorId(id);
		if (supervisor.getIdSupervisor() == null) {
			throw new ModeloNotFoundException(String.format("ID %s NO ENCONTRADO", id));
		}
		return new ResponseEntity<Supervisor>(supervisor, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Supervisor supervisor){
		Supervisor supervi = supervisorService.registrar(supervisor);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(supervi.getIdSupervisor())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Supervisor> modificar(@Valid @RequestBody Supervisor supervisor) {
		Supervisor supervi = supervisorService.modificar(supervisor);
		return new ResponseEntity<Supervisor>(supervi, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Supervisor supervisor = supervisorService.leerPorId(id);
		if(supervisor.getIdSupervisor() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		supervisorService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
