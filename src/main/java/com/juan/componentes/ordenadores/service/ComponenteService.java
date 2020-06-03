package com.juan.componentes.ordenadores.service;

import java.util.Optional;

import com.juan.componentes.ordenadores.entity.Componente;

public interface ComponenteService {

	Iterable<Componente> findAllComponente();

	Optional<Componente> finByIdComponente(Long id);

	Componente saveComponente(Componente componente);

	void deleteByIdComponente(Long id);

	Componente asociarCategoriaEmpresa(Long idComponente, Long idCategoria);

	Componente deleteCategoriaEmpresa(Long idComponente, Long idCategoria);

}
