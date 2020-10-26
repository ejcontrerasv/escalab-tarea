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
import com.curso.escalab.tarea.uno.model.Vendedor;
import com.curso.escalab.tarea.uno.service.IVendedorService;

public class VendedorController {
	
	@Autowired
	private IVendedorService vendedorService;
	
	@GetMapping
	public ResponseEntity<List<Vendedor>> listar(){
		List<Vendedor> vendedores = vendedorService.listar();
		return new ResponseEntity<List<Vendedor>>(vendedores, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vendedor> listarPorId (@PathVariable("id") Integer id){
		Vendedor vendedor = vendedorService.leerPorId(id);
		if (vendedor.getIdVendedor() == null) {
			throw new ModeloNotFoundException(String.format("ID %s NO ENCONTRADO", id));
		}
		return new ResponseEntity<Vendedor>(vendedor, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Vendedor vendedor){
		Vendedor vender = vendedorService.registrar(vendedor);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(vender.getIdVendedor())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Vendedor> modificar(@Valid @RequestBody Vendedor vendedor) {
		Vendedor vender = vendedorService.modificar(vendedor);
		return new ResponseEntity<Vendedor>(vender, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Vendedor vendedor = vendedorService.leerPorId(id);
		if(vendedor.getIdVendedor() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		vendedorService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
