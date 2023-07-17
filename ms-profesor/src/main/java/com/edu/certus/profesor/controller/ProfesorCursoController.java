package com.edu.certus.profesor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.certus.profesor.dto.ProfesorCursoDto;
import com.edu.certus.profesor.dto.ResponseDto;
import com.edu.certus.profesor.service.ProfesorCursoService;

@RestController
@RequestMapping("/v1/curso-profesor")
public class ProfesorCursoController {
	
	@Autowired
	private ProfesorCursoService profesorCursoService;

	@GetMapping
	public ResponseEntity<ResponseDto> getAllProfesorCurso(){
	    return ResponseEntity.status(HttpStatus.OK).body(profesorCursoService.getAllProfesorCurso());
	}

	@GetMapping("/{idProfesor}")
	public ResponseEntity<ResponseDto> getProfesorCurso(@PathVariable("idProfesor") Long idProfesor){
	    return ResponseEntity.status(HttpStatus.OK).body(profesorCursoService.getProfesorCurso(idProfesor));
	}

	@PostMapping
	public ResponseEntity<ResponseDto> createProfesorCurso(@RequestBody ProfesorCursoDto profesorCursoDto){
	    return ResponseEntity.status(HttpStatus.CREATED).body(profesorCursoService.createProfesorCurso(profesorCursoDto));
	}

}
