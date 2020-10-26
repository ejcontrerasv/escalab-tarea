package com.curso.escalab.tarea.uno.model;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;

	@NotNull(message = "El nombre no puede venir vacio")
	@Size(min = 3, message = "El nombre debe tener minimo 3 caracteres")
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Size(min = 3, max = 150, message = "Direccion debe tener minimo 3 caracteres")
	@Column(name = "direccion", nullable = false)
	private String direccion;

	@Size(min = 9, max = 9, message = "El telefono debe tener 9 caracteres")
	@Column(name = "telefono", nullable = true, length = 9)
	private String telefono;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
