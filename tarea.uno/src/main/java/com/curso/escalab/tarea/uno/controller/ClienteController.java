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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.escalab.tarea.uno.exception.ModeloNotFoundException;
import com.curso.escalab.tarea.uno.model.Cliente;
import com.curso.escalab.tarea.uno.service.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar(){
		List<Cliente> clientes = clienteService.listar();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarPorId (@PathVariable("id") Integer id){
		Cliente cliente = clienteService.leerPorId(id);
		if (cliente.getIdCliente() == null) {
			throw new ModeloNotFoundException(String.format("ID %s NO ENCONTRADO", id));
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Cliente cliente){
		Cliente client = clienteService.registrar(cliente);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(client.getIdCliente())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Cliente> modificar(@Valid @RequestBody Cliente cliente) {
		Cliente client = clienteService.modificar(cliente);
		return new ResponseEntity<Cliente>(client, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Cliente client = clienteService.leerPorId(id);
		if(client.getIdCliente() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		clienteService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	

}
