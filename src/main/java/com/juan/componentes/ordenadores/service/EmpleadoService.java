package com.juan.componentes.ordenadores.service;

import java.util.List;
import java.util.Optional;

import com.juan.componentes.ordenadores.entity.Empleado;

public interface EmpleadoService {
	public Iterable<Empleado> findAllEmpleado();

	Optional<Empleado> finByIdEmpleado(Long id);

	Empleado saveEmpleado(Empleado empleado);

	void deleteByIdEmpleado(Long id);

	List<Empleado> findAllOrderByNombre();

}
