package com.edu.certus.profesor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name="curso_profesor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorCursoEntity {
	
	@Id
	@Column(name="cod_curso")
	private Long idCurso;
	@Column(name="cod_profesor")
	private Long idProfesor;
	
	
	
	
}
