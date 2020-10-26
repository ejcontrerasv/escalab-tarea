package com.curso.escalab.tarea.uno.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.escalab.tarea.uno.model.Marca;
import com.curso.escalab.tarea.uno.repo.IMarcaRepo;
import com.curso.escalab.tarea.uno.service.IMarcaService;

@Service
public class MarcaServiceImpl implements IMarcaService {
	@Autowired
	private IMarcaRepo marcaRepo;

	@Override
	public Marca registrar(Marca obj) {
		return marcaRepo.save(obj);
	}

	@Override
	public Marca modificar(Marca obj) {
		return marcaRepo.save(obj);
	}

	@Override
	public List<Marca> listar() {
		return marcaRepo.findAll();
	}

	@Override
	public Marca leerPorId(Integer id) {
		Optional<Marca> optional = marcaRepo.findById(id);
		return optional.isPresent() ? optional.get() : new Marca();
	}

	@Override
	public boolean eliminar(Integer id) {
		try {
			marcaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
