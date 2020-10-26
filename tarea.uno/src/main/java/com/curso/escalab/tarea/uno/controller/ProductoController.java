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
import com.curso.escalab.tarea.uno.model.Producto;
import com.curso.escalab.tarea.uno.service.IProductoService;

public class ProductoController {
	@Autowired
	private IProductoService productoService;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar(){
		List<Producto> productos = productoService.listar();
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> listarPorId (@PathVariable("id") Integer id){
		Producto producto = productoService.leerPorId(id);
		if (producto.getIdProducto() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Producto producto){
		Producto product = productoService.registrar(producto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(product.getIdProducto())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto producto) {
		Producto product = productoService.modificar(producto);
		return new ResponseEntity<Producto>(product, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Producto product = productoService.leerPorId(id);
		if(product.getIdProducto() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		productoService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
