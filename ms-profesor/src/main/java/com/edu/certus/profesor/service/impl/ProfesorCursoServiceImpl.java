package com.edu.certus.profesor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.profesor.client.CursoClient;
import com.edu.certus.profesor.dto.CursoDto;
import com.edu.certus.profesor.dto.ProfesorCursoDto;
import com.edu.certus.profesor.dto.ResponseDto;
import com.edu.certus.profesor.entity.ProfesorCursoEntity;
import com.edu.certus.profesor.entity.ProfesorEntity;
import com.edu.certus.profesor.repository.ProfesorCursoRepository;
import com.edu.certus.profesor.repository.ProfesorRepository;
import com.edu.certus.profesor.service.ProfesorCursoService;
import com.edu.certus.profesor.util.Constante;
import com.edu.certus.profesor.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ProfesorCursoServiceImpl implements ProfesorCursoService{
	
	@Autowired
	private ProfesorCursoRepository profesorCursoRepository;

	@Autowired
	private ProfesorRepository profesorRepository;

	@Autowired
	private CursoClient cursoClient;

	@Override
	public ResponseDto getAllProfesorCurso() {
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	        List<ProfesorCursoEntity> listProfesorCursoEntity = profesorCursoRepository.findAll();
	        List<ProfesorCursoDto> listProfesorCurso = new ArrayList<ProfesorCursoDto>();

	        for (int i = 0; i < listProfesorCursoEntity.size(); i++) {

	            ProfesorEntity profesorEntity = profesorRepository.findById(listProfesorCursoEntity.get(i).getIdProfesor()).orElse(null);
	            ResponseDto responseDto = cursoClient.readCurso(listProfesorCursoEntity.get(i).getIdCurso());
	            CursoDto cursoDto = mapper.convertValue(responseDto.getData(), CursoDto.class);

	            listProfesorCurso.add(ProfesorCursoDto.builder()
	                    .idProfesor(profesorEntity.getId())
	                    .nombreProfesor(profesorEntity.getNombres() + " " + profesorEntity.getApellidos())
	                    .estadoProfesor(profesorEntity.getEstado())
	                    .idCurso(cursoDto.getId())
	                    .nombreCurso(cursoDto.getDescripcion())
	                    .build());
	        }
	        return Util.getResponse(true, Constante.OPERATION_SUCCESS, listProfesorCurso);
	    } catch (Exception e) {
	        return Util.getResponse(false, Constante.OPERATION_FAILED, null);
	    }
	}

	@Override
	public ResponseDto getProfesorCurso(Long id) {
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	        ProfesorCursoEntity profesorCursoEntity = profesorCursoRepository.findById(id).orElse(null);
	        if (profesorCursoEntity == null) {
	            return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
	        }

	        ProfesorEntity profesorEntity = profesorRepository.findById(profesorCursoEntity.getIdProfesor()).orElse(null);
	        ResponseDto responseDto = cursoClient.readCurso(profesorCursoEntity.getIdCurso());
	        CursoDto cursoDto = mapper.convertValue(responseDto.getData(), CursoDto.class);

	        ProfesorCursoDto profesorCursoDto = ProfesorCursoDto.builder()
	                .idProfesor(profesorEntity.getId())
	                .nombreProfesor(profesorEntity.getNombres() + " " + profesorEntity.getApellidos())
	                .estadoProfesor(profesorEntity.getEstado())
	                .idCurso(cursoDto.getId())
	                .nombreCurso(cursoDto.getDescripcion())
	                .build();

	        return Util.getResponse(true, Constante.OPERATION_SUCCESS, profesorCursoDto);
	    } catch (Exception e) {
	        return Util.getResponse(false, Constante.OPERATION_FAILED, null);
	    }
	}

	@Override
	public ResponseDto createProfesorCurso(ProfesorCursoDto profesorCursoDto) {
	    try {
	        ProfesorCursoEntity profesorCursoEntity = ProfesorCursoEntity.builder()
	                .idProfesor(profesorCursoDto.getIdProfesor())
	                .idCurso(profesorCursoDto.getIdCurso())
	                .build();
	        profesorCursoRepository.save(profesorCursoEntity);
	        return Util.getResponse(true, Constante.OPERATION_SUCCESS, null);
	    } catch (Exception e) {
	        return Util.getResponse(false, Constante.OPERATION_FAILED, null);
	    }
	}


}
