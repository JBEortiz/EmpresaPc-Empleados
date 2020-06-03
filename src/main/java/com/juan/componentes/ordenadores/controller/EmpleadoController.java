package com.juan.componentes.ordenadores.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.juan.componentes.ordenadores.entity.Empleado;
import com.juan.componentes.ordenadores.service.EmpleadoService;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping
	public ResponseEntity<?> getAllEmpleados() {
		return ResponseEntity.ok().body(empleadoService.findAllEmpleado());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getByIdEmpleado(@PathVariable Long id) {
		Optional<Empleado> optional = empleadoService.finByIdEmpleado(id);
		if (optional == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(optional.get());
	}

	@GetMapping("/getAllOrdered")
	public ResponseEntity<?> getAllOrderByNombre() {
		return ResponseEntity.ok().body(empleadoService.findAllOrderByNombre());
	}

	@PostMapping
	public ResponseEntity<?> createEmpleado(@RequestBody Empleado empleado) {
		Empleado empleado1 = empleadoService.saveEmpleado(empleado);
		return ResponseEntity.status(HttpStatus.CREATED).body(empleado1);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmpeleado(@RequestBody Empleado empleado, @PathVariable Long id) {
		Optional<Empleado> optional = empleadoService.finByIdEmpleado(id);
		if (optional == null) {
			return ResponseEntity.notFound().build();
		}
		Empleado empleado1 = optional.get();
		empleado1.setNombre(empleado.getNombre());
		empleado1.setApellido(empleado.getApellido());
		empleado1.setEdad(empleado.getEdad());
		empleado1.setSalario(empleado.getSalario());
		return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.saveEmpleado(empleado1));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable Long id) {
		empleadoService.deleteByIdEmpleado(id);
		return ResponseEntity.noContent().build();
	}

	/*
	 * PRUEBA DE METODO SIN DELEGAR A LA BASE DE DATOS LAMBDAS BASICAS
	 */

	@PutMapping("/lambdaUpdateEmpleado/{id}")
	public Optional<Empleado> lambdaUpdateEmpleado(@RequestBody Empleado empleado, @PathVariable Long id) {
		return empleadoService.finByIdEmpleado(id).map(empleado1 -> {
			empleado1.setNombre(empleado.getNombre());
			empleado1.setApellido(empleado.getApellido());
			empleado1.setEdad(empleado.getEdad());
			empleado1.setSalario(empleado.getSalario());
			return empleado1;
		});
	}

	@GetMapping("/lambdaGetEmpleadoNombres")
	public ResponseEntity<List<String>> getEmpleadosLambdaEdad() {
		List<String> empleados = new ArrayList<>();
		empleados = ((Collection<Empleado>) empleadoService.findAllEmpleado()).stream().map(Empleado::getNombre)
				.collect(Collectors.toList());
		return new ResponseEntity<List<String>>(empleados, HttpStatus.OK);
	}

	@GetMapping("/lambdaGetEmpleadoSalario")
	public ResponseEntity<List<Empleado>> getEmpleadosLambdaSlario() {
		List<Empleado> empleados = new ArrayList<>();
		empleados = ((Collection<Empleado>) empleadoService.findAllEmpleado()).stream().filter(e -> e.getEdad() < 40)
				.filter(e -> e.getSalario() >= 3000).collect(Collectors.toList());
		return new ResponseEntity<List<Empleado>>(empleados, HttpStatus.OK);
	}

}
