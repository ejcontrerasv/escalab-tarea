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
import com.curso.escalab.tarea.uno.model.Marca;
import com.curso.escalab.tarea.uno.service.IMarcaService;

public class MarcaController {
	@Autowired
	private IMarcaService marcaService;
	
	@GetMapping
	public ResponseEntity<List<Marca>> listar(){
		List<Marca> marcas = marcaService.listar();
		return new ResponseEntity<List<Marca>>(marcas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Marca> listarPorId (@PathVariable("id") Integer id){
		Marca marca = marcaService.leerPorId(id);
		if (marca.getIdMarca() == null) {
			throw new ModeloNotFoundException(String.format("ID %s NO ENCONTRADO", id));
		}
		return new ResponseEntity<Marca>(marca, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Marca marca){
		Marca mark = marcaService.registrar(marca);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(mark.getIdMarca())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Marca> modificar(@Valid @RequestBody Marca marca) {
		Marca mark = marcaService.modificar(marca);
		return new ResponseEntity<Marca>(mark, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Marca marca = marcaService.leerPorId(id);
		if(marca.getIdMarca() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		marcaService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
