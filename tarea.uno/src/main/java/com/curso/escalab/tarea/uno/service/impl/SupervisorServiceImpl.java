package com.curso.escalab.tarea.uno.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.escalab.tarea.uno.model.Supervisor;
import com.curso.escalab.tarea.uno.repo.ISupervisorRepo;
import com.curso.escalab.tarea.uno.service.ISupervisorService;

@Service
public class SupervisorServiceImpl implements ISupervisorService{
	
	@Autowired
	private ISupervisorRepo supervisorRepo;

	@Override
	public Supervisor registrar(Supervisor obj) {
		return supervisorRepo.save(obj);
	}

	@Override
	public Supervisor modificar(Supervisor obj) {
		return supervisorRepo.save(obj);
	}

	@Override
	public List<Supervisor> listar() {
		return supervisorRepo.findAll();
	}

	@Override
	public Supervisor leerPorId(Integer id) {
		Optional<Supervisor> optional = supervisorRepo.findById(id);
		return optional.isPresent() ? optional.get() : new Supervisor();
	}

	@Override
	public boolean eliminar(Integer id) {
		try {
			supervisorRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
