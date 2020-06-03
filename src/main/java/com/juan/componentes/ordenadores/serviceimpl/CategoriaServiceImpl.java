package com.juan.componentes.ordenadores.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juan.componentes.ordenadores.entity.Categoria;
import com.juan.componentes.ordenadores.entity.Producto;
import com.juan.componentes.ordenadores.repository.CategoriaRepository;
import com.juan.componentes.ordenadores.repository.ProductoRepository;
import com.juan.componentes.ordenadores.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Categoria> findAllCategoria() {
		return categoriaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Categoria> finByIdCategoria(Long id) {
		return categoriaRepository.findById(id);
	}

	@Override
	@Transactional
	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	@Transactional
	public void deleteByIdCategoria(Long id) {
		categoriaRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Categoria asociarProductoCategoria(Long idCategoria, Long idProducto) {
		Producto productoAsociado = productoRepository.findById(idProducto).get();
		Categoria categoria = categoriaRepository.findById(idCategoria).get();

		categoria.getProductos().add(productoAsociado);
		categoriaRepository.save(categoria);
		return categoria;
	}

	@Override
	@Transactional
	public Categoria deleteProductoCategoria(Long idCategoria, Long idProducto) {
		Producto productoAsociado = productoRepository.findById(idProducto).get();
		Categoria categoria = categoriaRepository.findById(idCategoria).get();

		categoria.getProductos().remove(productoAsociado);
		categoriaRepository.save(categoria);
		return categoria;
	}

}
