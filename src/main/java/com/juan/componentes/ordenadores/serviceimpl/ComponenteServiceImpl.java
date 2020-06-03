package com.juan.componentes.ordenadores.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juan.componentes.ordenadores.entity.Categoria;
import com.juan.componentes.ordenadores.entity.Componente;
import com.juan.componentes.ordenadores.repository.CategoriaRepository;
import com.juan.componentes.ordenadores.repository.ComponenteRepository;
import com.juan.componentes.ordenadores.service.ComponenteService;

@Service
public class ComponenteServiceImpl implements ComponenteService {

	@Autowired
	private ComponenteRepository componenteRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Componente> findAllComponente() {
		return componenteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Componente> finByIdComponente(Long id) {
		return componenteRepository.findById(id);
	}

	@Override
	@Transactional
	public Componente saveComponente(Componente componente) {
		return componenteRepository.save(componente);
	}

	@Override
	@Transactional
	public void deleteByIdComponente(Long id) {
		componenteRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Componente asociarCategoriaEmpresa(Long idComponente, Long idCategoria) {
		Categoria categoriaAsociada = categoriaRepository.findById(idCategoria).get();
		Componente componente = componenteRepository.findById(idComponente).get();

		componente.getCategorias().add(categoriaAsociada);
		componenteRepository.save(componente);
		return componente;
	}

	@Override
	@Transactional
	public Componente deleteCategoriaEmpresa(Long idComponente, Long idCategoria) {
		Categoria categoriaAsociada = categoriaRepository.findById(idCategoria).get();
		Componente componente = componenteRepository.findById(idComponente).get();

		componente.getCategorias().remove(categoriaAsociada);
		componenteRepository.save(componente);
		return componente;
	}

}
