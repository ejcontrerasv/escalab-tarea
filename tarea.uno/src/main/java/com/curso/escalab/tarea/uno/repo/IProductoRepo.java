package com.curso.escalab.tarea.uno.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.escalab.tarea.uno.model.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Integer>{

}
