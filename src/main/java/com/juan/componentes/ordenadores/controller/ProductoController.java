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

import com.juan.componentes.ordenadores.entity.Producto;
import com.juan.componentes.ordenadores.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping
	public ResponseEntity<?> getAllProducto() {
		return ResponseEntity.ok().body(productoService.findAllProducto());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getByIdProducto(@PathVariable Long id) {
		Optional<Producto> optionalCategoria = productoService.finByIdProducto(id);
		if (optionalCategoria == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(optionalCategoria.get());
	}

	@GetMapping("/getAllOrdered")
	public ResponseEntity<?> getAllOrderByNombre() {
		return ResponseEntity.ok().body(productoService.findAllOrderByNombreProducto());
	}

	@GetMapping("/getAllOrderedPrecio")
	public ResponseEntity<?> getAllOrderByPrecio() {
		return ResponseEntity.ok().body(productoService.findAllOrderByPrecioProducto());
	}

	@PostMapping
	public ResponseEntity<?> createProducto(@RequestBody Producto producto) {
		Producto producto1 = productoService.saveProducto(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(producto1);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProducto(@RequestBody Producto producto, @PathVariable Long id) {
		Optional<Producto> optional = productoService.finByIdProducto(id);
		if (optional == null) {
			return ResponseEntity.notFound().build();
		}
		Producto producto1 = optional.get();
		producto1.setNombre(producto.getNombre());
		producto1.setModelo(producto.getModelo());
		producto1.setDescripcion(producto.getDescripcion());
		producto1.setPrecio(producto.getPrecio());

		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.saveProducto(producto1));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmpresa(@PathVariable Long id) {
		productoService.deleteByIdProducto(id);
		return ResponseEntity.noContent().build();
	}

}
