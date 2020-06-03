package com.juan.componentes.ordenadores.service;

import java.util.List;
import java.util.Optional;

import com.juan.componentes.ordenadores.entity.Producto;

public interface ProductoService {

	public Iterable<Producto> findAllProducto();

	public Optional<Producto> finByIdProducto(Long id);

	public Producto saveProducto(Producto producto);

	public void deleteByIdProducto(Long id);

	List<Producto> findAllOrderByNombreProducto();

	List<Producto> findAllOrderByPrecioProducto();

}
