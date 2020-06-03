package com.juan.componentes.ordenadores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.juan.componentes.ordenadores.entity.Empleado;

/*
 * ORDENA POR ORDEN ALFABETICO
 */
public interface EmpleadoRrepository extends CrudRepository<Empleado, Long> {

	@Query("SELECT e FROM Empleado e ORDER BY e.nombre")
	public List<Empleado> findAllOrderByNombre();

}
