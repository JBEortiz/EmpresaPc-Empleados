package com.juan.componentes.ordenadores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.juan.componentes.ordenadores.entity.Producto;

/*
 * ORDENA POR ORDEN ALFABETICO Y POR PRECIO
 */
public interface ProductoRepository extends CrudRepository<Producto, Long> {

	@Query("SELECT p FROM Producto p ORDER BY p.nombre")
	public List<Producto> findAllOrderByNombreProducto();

	@Query("SELECT pr FROM Producto pr ORDER BY pr.precio")
	public List<Producto> findAllOrderByPrecioProducto();

}
