package com.juan.componentes.ordenadores.repository;

import org.springframework.data.repository.CrudRepository;

import com.juan.componentes.ordenadores.entity.Componente;


public interface ComponenteRepository extends CrudRepository<Componente, Long>{

}
