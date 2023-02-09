package co.edu.unicauca.asae.core.services.service;

import java.util.List;

import co.edu.unicauca.asae.core.services.DTO.estudianteDTO;

public interface IGestionEstudiante {
	
    public List<estudianteDTO> findAll();
	public estudianteDTO findById(Integer id);
	public estudianteDTO save(estudianteDTO estudiante);
	public estudianteDTO update(Integer id, estudianteDTO estudiante);
	public boolean delete(Integer id);
}
