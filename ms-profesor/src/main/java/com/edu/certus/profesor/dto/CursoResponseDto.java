package com.edu.certus.profesor.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponseDto {
	
	private List<ProfesorDto> profesor;
	private Object curso;

}
