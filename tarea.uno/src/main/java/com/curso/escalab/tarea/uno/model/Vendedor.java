package com.curso.escalab.tarea.uno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vendedores")
public class Vendedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVendedor;

	@ManyToOne
	@JoinColumn(name = "id_supervisor", nullable = false, foreignKey = @ForeignKey(name = "FK_vendedor_supervisor"))
	private Supervisor supervisor;

	@NotNull(message = "El nombre no puede venir vacio")
	@Size(min = 3, message = "El nombre debe tener minimo 3 caracteres")
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Size(min = 3, max = 150, message = "Direccion debe tener minimo 3 caracteres")
	@Column(name = "direccion", nullable = false)
	private String direccion;

	public Integer getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Integer idVendedor) {
		this.idVendedor = idVendedor;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
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

}
