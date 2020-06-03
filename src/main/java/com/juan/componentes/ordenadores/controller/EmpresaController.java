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

import com.juan.componentes.ordenadores.entity.Empresa;
import com.juan.componentes.ordenadores.service.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	@Autowired
	private EmpresaService empresaService;

	@GetMapping
	public ResponseEntity<?> getAllEmpresa() {
		return ResponseEntity.ok().body(empresaService.findAllEmpresa());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getByIdEmpresa(@PathVariable Long id) {
		Optional<Empresa> optionalCategoria = empresaService.finByIdEmpresa(id);
		if (optionalCategoria == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(optionalCategoria.get());
	}

	@PostMapping
	public ResponseEntity<?> createEmpresa(@RequestBody Empresa empresa) {
		Empresa empresa1 = empresaService.saveEmpresa(empresa);
		return ResponseEntity.status(HttpStatus.CREATED).body(empresa1);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmpresa(@RequestBody Empresa empresa, @PathVariable Long id) {
		Optional<Empresa> optional = empresaService.finByIdEmpresa(id);
		if (optional == null) {
			return ResponseEntity.notFound().build();
		}
		Empresa empresa1 = optional.get();
		empresa1.setNombre(empresa.getNombre());

		return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.saveEmpresa(empresa1));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmpresa(@PathVariable Long id) {
		empresaService.deleteByIdEmpresa(id);
		return ResponseEntity.noContent().build();
	}

	/*
	 * METODOS DE AÑADIR O BORRAR EMPLEADOS A EMPRESAS
	 */

	@PutMapping("/asociarEmpleado/{idEmpresa}/{idEmpleado}")
	public ResponseEntity<Empresa> addEmpleadoEmpresa(@PathVariable Long idEmpresa, @PathVariable Long idEmpleado) {
		Empresa empresaEmpleadoAdd = empresaService.asociarEmpleadoEmpresa(idEmpresa, idEmpleado);
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaEmpleadoAdd);
	}

	@PutMapping("/deleteEmpleado/{idEmpresa}/{idEmpleado}")
	public ResponseEntity<Empresa> deleteEmpleadoEmpresa(@PathVariable Long idEmpresa, @PathVariable Long idEmpleado) {
		Empresa empresaEmpleadoDelete = empresaService.deleteEmpleadoEmpresa(idEmpresa, idEmpleado);
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaEmpleadoDelete);
	}

	/*
	 * METODOS DE AÑADIR O BORRAR COMPONENTES A EMPRESAS
	 */

	@PutMapping("/asociarComponente/{idEmpresa}/{idComponente}")
	public ResponseEntity<Empresa> addComponenteEmpresa(@PathVariable Long idEmpresa, @PathVariable Long idComponente) {
		Empresa empresaComponenteAdd = empresaService.asociarComponenteEmpresa(idEmpresa, idComponente);
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaComponenteAdd);
	}

	@PutMapping("/deleteComponente/{idEmpresa}/{idComponente}")
	public ResponseEntity<Empresa> deleteComponenteEmpresa(@PathVariable Long idEmpresa,
			@PathVariable Long idComponente) {
		Empresa empresaComponenteDelete = empresaService.deleteComponenteEmpresa(idEmpresa, idComponente);
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaComponenteDelete);
	}

}
