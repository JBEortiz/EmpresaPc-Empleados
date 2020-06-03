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

import com.juan.componentes.ordenadores.entity.Categoria;
import com.juan.componentes.ordenadores.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<?> getAllCategorias() {
		return ResponseEntity.ok().body(categoriaService.findAllCategoria());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getByIdCategorias(@PathVariable Long id) {
		Optional<Categoria> optionalCategoria = categoriaService.finByIdCategoria(id);
		if (optionalCategoria == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(optionalCategoria.get());
	}

	@PostMapping
	public ResponseEntity<?> createCategoria(@RequestBody Categoria categoria) {
		Categoria categoria1 = categoriaService.saveCategoria(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoria1);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategoria(@RequestBody Categoria categoria, @PathVariable Long id) {
		Optional<Categoria> optional = categoriaService.finByIdCategoria(id);
		if (optional == null) {
			return ResponseEntity.notFound().build();
		}
		Categoria categoria1 = optional.get();
		categoria1.setNombre(categoria.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.saveCategoria(categoria1));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
		categoriaService.deleteByIdCategoria(id);
		return ResponseEntity.noContent().build();
	}

	/*
	 * METODOS DE AÑADIR O BORRAR PRODUCTO A UNA CATEGORIA
	 */

	@PutMapping("/asociarProducto/{idCategoria}/{idProducto}")
	public ResponseEntity<Categoria> addProductoCategoria(@PathVariable Long idCategoria,
			@PathVariable Long idProducto) {
		Categoria categoriaProductoAsociada = categoriaService.asociarProductoCategoria(idCategoria, idProducto);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaProductoAsociada);
	}

	@PutMapping("/deleteProducto/{idCategoria}/{idProducto}")
	public ResponseEntity<Categoria> deleteProductoCategoria(@PathVariable Long idCategoria,
			@PathVariable Long idProducto) {
		Categoria categoriaProductoBorrar = categoriaService.deleteProductoCategoria(idCategoria, idProducto);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaProductoBorrar);
	}

}
