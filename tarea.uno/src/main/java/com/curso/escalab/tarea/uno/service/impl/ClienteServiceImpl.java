package com.curso.escalab.tarea.uno.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.escalab.tarea.uno.model.Cliente;
import com.curso.escalab.tarea.uno.repo.IClienteRepo;
import com.curso.escalab.tarea.uno.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteRepo clienteRepo;

	@Override
	public Cliente registrar(Cliente obj) {
		return clienteRepo.save(obj);
	}

	@Override
	public Cliente modificar(Cliente obj) {
		return clienteRepo.save(obj);
	}

	@Override
	public List<Cliente> listar() {
		return clienteRepo.findAll();
	}

	@Override
	public Cliente leerPorId(Integer id) {
		Optional<Cliente> optional = clienteRepo.findById(id);
		return optional.isPresent() ? optional.get() : new Cliente();
	}

	@Override
	public boolean eliminar(Integer id) {
		try {
			clienteRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
