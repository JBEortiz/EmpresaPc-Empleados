package com.juan.componentes.ordenadores.repository;

import org.springframework.data.repository.CrudRepository;

import com.juan.componentes.ordenadores.entity.Empresa;


public interface EmpresaRepository extends CrudRepository<Empresa, Long>{

}
