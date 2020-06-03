package com.juan.componentes.ordenadores.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juan.componentes.ordenadores.entity.Empleado;
import com.juan.componentes.ordenadores.repository.EmpleadoRrepository;
import com.juan.componentes.ordenadores.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRrepository empleadoRepository;
	
	

	@Override
	@Transactional(readOnly = true)
	public Iterable<Empleado> findAllEmpleado() {
		return empleadoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Empleado> finByIdEmpleado(Long id) {
		return empleadoRepository.findById(id);
	}

	@Override
	@Transactional
	public Empleado saveEmpleado(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	@Override
	@Transactional
	public void deleteByIdEmpleado(Long id) {
		empleadoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAllOrderByNombre() {
		return empleadoRepository.findAllOrderByNombre();
	}

}
