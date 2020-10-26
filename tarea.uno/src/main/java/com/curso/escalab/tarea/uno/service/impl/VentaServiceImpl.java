package com.curso.escalab.tarea.uno.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.escalab.tarea.uno.model.Venta;
import com.curso.escalab.tarea.uno.repo.IVentaRepo;
import com.curso.escalab.tarea.uno.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService{
	
	@Autowired
	private IVentaRepo ventaRepo;

	@Override
	public Venta registrar(Venta obj) {
		return ventaRepo.save(obj);
	}

	@Override
	public Venta modificar(Venta obj) {
		return ventaRepo.save(obj);
	}

	@Override
	public List<Venta> listar() {
		return ventaRepo.findAll();
	}

	@Override
	public Venta leerPorId(Integer id) {
		Optional<Venta> optional = ventaRepo.findById(id);
		return optional.isPresent() ? optional.get() : new Venta();
	}

	@Override
	public boolean eliminar(Integer id) {
		try {
			ventaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
