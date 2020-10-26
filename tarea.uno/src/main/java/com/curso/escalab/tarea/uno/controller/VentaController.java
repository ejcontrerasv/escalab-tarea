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
import com.curso.escalab.tarea.uno.model.Venta;
import com.curso.escalab.tarea.uno.service.IVentaService;

public class VentaController {
	
	@Autowired
	private IVentaService ventaService;
	
	@GetMapping
	public ResponseEntity<List<Venta>> listar(){
		List<Venta> ventas = ventaService.listar();
		return new ResponseEntity<List<Venta>>(ventas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Venta> listarPorId (@PathVariable("id") Integer id){
		Venta venta = ventaService.leerPorId(id);
		if (venta.getIdVenta()== null) {
			throw new ModeloNotFoundException(String.format("ID %s NO ENCONTRADO", id));
		}
		return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Venta venta){
		Venta vent = ventaService.registrar(venta);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(vent.getIdVenta())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Venta> modificar(@Valid @RequestBody Venta venta) {
		Venta vent = ventaService.modificar(venta);
		return new ResponseEntity<Venta>(vent, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Venta venta = ventaService.leerPorId(id);
		if(venta.getIdVenta() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		ventaService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
