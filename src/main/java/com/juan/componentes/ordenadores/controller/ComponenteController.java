package com.juan.componentes.ordenadores.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.componentes.ordenadores.entity.Componente;
import com.juan.componentes.ordenadores.service.ComponenteService;

@RestController
@RequestMapping("/componente")
public class ComponenteController {

	@Autowired
	ComponenteService componenteService;

	@GetMapping
	public ResponseEntity<?> getAllComponente() {
		return ResponseEntity.ok().body(componenteService.findAllComponente());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getByIdComponente(@PathVariable Long id) {
		Optional<Componente> optional = componenteService.finByIdComponente(id);
		if (optional == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(optional.get());
	}

	@PostMapping
	public ResponseEntity<?> createComponente(@RequestBody Componente componente) {
		Componente componente1 = componenteService.saveComponente(componente);
		return ResponseEntity.status(HttpStatus.CREATED).body(componente1);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateComponente(@RequestBody Componente componente, @PathVariable Long id) {
		Optional<Componente> optional = componenteService.finByIdComponente(id);
		if (optional == null) {
			return ResponseEntity.notFound().build();
		}
		Componente componente1 = optional.get();
		componente1.setNombre(componente.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(componenteService.saveComponente(componente1));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
		componenteService.deleteByIdComponente(id);
		return ResponseEntity.noContent().build();
	}
	/*
	 * METODOS DE AÑADIR O BORRAR CATEGORIAS A COMPONENTES
	 */

	@PutMapping("/asociarCategoria/{idComponente}/{idCategoria}")
	public ResponseEntity<Componente> addCategoriaComponente(@PathVariable Long idComponente,
			@PathVariable Long idCategoria) {
		Componente componenteCatgoriaAsociada = componenteService.asociarCategoriaEmpresa(idComponente, idCategoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(componenteCatgoriaAsociada);
	}

	@PutMapping("/deleteCategoria/{idComponente}/{idCategoria}")
	public ResponseEntity<Componente> deleteCategoriaComponente(@PathVariable Long idComponente,
			@PathVariable Long idCategoria) {
		Componente componenteCatgoriaBorrar = componenteService.deleteCategoriaEmpresa(idComponente, idCategoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(componenteCatgoriaBorrar);
	}

}
