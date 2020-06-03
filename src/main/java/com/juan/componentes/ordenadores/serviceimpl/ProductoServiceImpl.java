package com.juan.componentes.ordenadores.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juan.componentes.ordenadores.entity.Producto;
import com.juan.componentes.ordenadores.repository.ProductoRepository;
import com.juan.componentes.ordenadores.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Producto> findAllProducto() {

		return productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> finByIdProducto(Long id) {
		return productoRepository.findById(id);
	}

	@Override
	@Transactional
	public Producto saveProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	@Transactional
	public void deleteByIdProducto(Long id) {
		productoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAllOrderByNombreProducto() {
		return productoRepository.findAllOrderByNombreProducto();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAllOrderByPrecioProducto() {
		return productoRepository.findAllOrderByPrecioProducto();
	}

}
