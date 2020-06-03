package com.juan.componentes.ordenadores.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juan.componentes.ordenadores.entity.Componente;
import com.juan.componentes.ordenadores.entity.Empleado;
import com.juan.componentes.ordenadores.entity.Empresa;
import com.juan.componentes.ordenadores.repository.ComponenteRepository;
import com.juan.componentes.ordenadores.repository.EmpleadoRrepository;
import com.juan.componentes.ordenadores.repository.EmpresaRepository;
import com.juan.componentes.ordenadores.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpleadoRrepository empleadoRepository;

	@Autowired
	private ComponenteRepository componenteRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Empresa> findAllEmpresa() {
		return empresaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Empresa> finByIdEmpresa(Long id) {
		return empresaRepository.findById(id);
	}

	@Override
	@Transactional
	public Empresa saveEmpresa(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	@Override
	@Transactional
	public void deleteByIdEmpresa(Long id) {
		empresaRepository.deleteById(id);
	}

	/*
	 * @ Al ser un optional pongo un .get(); al final en vez de dejarlo
	 * Optional<Empleado> para que no de un error
	 */
	@Override
	@Transactional
	public Empresa asociarEmpleadoEmpresa(Long idEmpresa, Long idEmpleado) {

		Empleado empleadoAsoaciado = empleadoRepository.findById(idEmpleado).get();
		Empresa empresa = empresaRepository.findById(idEmpresa).get();

		empresa.getEmpleados().add(empleadoAsoaciado);
		empresaRepository.save(empresa);

		return empresa;
	}

	@Override
	@Transactional
	public Empresa deleteEmpleadoEmpresa(Long idEmpresa, Long idEmpleado) {
		Empleado empleadoBorrado = empleadoRepository.findById(idEmpleado).get();
		Empresa empresa = empresaRepository.findById(idEmpresa).get();

		empresa.getEmpleados().remove(empleadoBorrado);
		empresaRepository.save(empresa);

		return empresa;
	}

	@Override
	@Transactional
	public Empresa asociarComponenteEmpresa(Long idEmpresa, Long idComponente) {
		Componente componenteAsociado = componenteRepository.findById(idComponente).get();
		Empresa empresa = empresaRepository.findById(idEmpresa).get();

		empresa.getComponentes().add(componenteAsociado);
		empresaRepository.save(empresa);
		return empresa;
	}

	@Override
	@Transactional
	public Empresa deleteComponenteEmpresa(Long idEmpresa, Long idComponente) {
		Componente componenteBorrado = componenteRepository.findById(idComponente).get();
		Empresa empresa = empresaRepository.findById(idEmpresa).get();

		empresa.getComponentes().remove(componenteBorrado);
		empresaRepository.save(empresa);
		return empresa;
	}

}
