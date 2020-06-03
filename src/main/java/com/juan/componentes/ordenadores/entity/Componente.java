package com.juan.componentes.ordenadores.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "componentes")
public class Componente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Categoria> categorias;

	/*
	 * VARIAS EMPRESAS TIENES COMPONENTES Y LOS COMPONENTES PUEDE ESTAR EN VARIAS
	 * EMPRESAS
	 */

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "componente_id"), inverseJoinColumns = @JoinColumn(name = "empresa_id"))
	private List<Empresa> empresas;

	public Componente() {
		this.categorias = new ArrayList<Categoria>();
		this.empresas = new ArrayList<Empresa>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}
