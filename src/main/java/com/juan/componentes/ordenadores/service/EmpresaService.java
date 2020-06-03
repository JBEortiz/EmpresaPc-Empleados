package com.juan.componentes.ordenadores.service;

import java.util.Optional;

import com.juan.componentes.ordenadores.entity.Empresa;

public interface EmpresaService {

	Iterable<Empresa> findAllEmpresa();

	Optional<Empresa> finByIdEmpresa(Long id);

	Empresa saveEmpresa(Empresa empresa);

	void deleteByIdEmpresa(Long id);

	Empresa asociarEmpleadoEmpresa(Long idEmpresa, Long idEmpleado);

	Empresa deleteEmpleadoEmpresa(Long idEmpresa, Long idEmpleado);

	Empresa asociarComponenteEmpresa(Long idEmpresa, Long idComponente);

	Empresa deleteComponenteEmpresa(Long idEmpresa, Long idComponente);

}
