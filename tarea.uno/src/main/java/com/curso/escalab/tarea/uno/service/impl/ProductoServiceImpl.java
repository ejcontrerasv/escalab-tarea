package com.curso.escalab.tarea.uno.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.escalab.tarea.uno.model.Producto;
import com.curso.escalab.tarea.uno.repo.IProductoRepo;
import com.curso.escalab.tarea.uno.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoRepo productoRepo;

	@Override
	public Producto registrar(Producto obj) {
		return productoRepo.save(obj);
	}

	@Override
	public Producto modificar(Producto obj) {
		return productoRepo.save(obj);
	}

	@Override
	public List<Producto> listar() {
		return productoRepo.findAll();
	}

	@Override
	public Producto leerPorId(Integer id) {
		Optional<Producto> optional = productoRepo.findById(id);
		return optional.isPresent() ? optional.get() : new Producto();
	}

	@Override
	public boolean eliminar(Integer id) {
		try {
			productoRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
