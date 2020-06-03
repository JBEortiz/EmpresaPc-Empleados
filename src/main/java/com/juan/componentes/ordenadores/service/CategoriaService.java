package com.juan.componentes.ordenadores.service;

import java.util.Optional;

import com.juan.componentes.ordenadores.entity.Categoria;

public interface CategoriaService {

	public Iterable<Categoria> findAllCategoria();

	public Optional<Categoria> finByIdCategoria(Long id);

	public Categoria saveCategoria(Categoria categoria);

	public void deleteByIdCategoria(Long id);

	Categoria asociarProductoCategoria(Long idCategoria, Long idProducto);

	Categoria deleteProductoCategoria(Long idCategoria, Long idProducto);

}
