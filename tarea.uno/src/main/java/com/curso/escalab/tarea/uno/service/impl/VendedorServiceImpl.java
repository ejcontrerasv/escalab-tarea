package com.curso.escalab.tarea.uno.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.escalab.tarea.uno.model.Vendedor;
import com.curso.escalab.tarea.uno.repo.IVendedorRepo;
import com.curso.escalab.tarea.uno.service.IVendedorService;

@Service
public class VendedorServiceImpl implements IVendedorService{
	
	@Autowired
	private IVendedorRepo vendedorRepo;

	@Override
	public Vendedor registrar(Vendedor obj) {
		return vendedorRepo.save(obj);
	}

	@Override
	public Vendedor modificar(Vendedor obj) {
		return vendedorRepo.save(obj);
	}

	@Override
	public List<Vendedor> listar() {
		return vendedorRepo.findAll();
	}

	@Override
	public Vendedor leerPorId(Integer id) {
		Optional<Vendedor> optional = vendedorRepo.findById(id);
		return optional.isPresent() ? optional.get() : new Vendedor();
	}

	@Override
	public boolean eliminar(Integer id) {
		try {
			vendedorRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
