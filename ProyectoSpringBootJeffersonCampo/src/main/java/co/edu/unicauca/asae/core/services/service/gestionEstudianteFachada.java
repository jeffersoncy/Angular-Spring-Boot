package co.edu.unicauca.asae.core.services.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.asae.core.models.EstudianteEntity;
import co.edu.unicauca.asae.core.repositories.estudiantesRepository;
import co.edu.unicauca.asae.core.services.DTO.estudianteDTO;

@Service
public class gestionEstudianteFachada implements IGestionEstudiante{
    
    @Autowired
	private estudiantesRepository servicioAccesoDB;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<estudianteDTO> findAll() {
		
		List<EstudianteEntity> objEstudianteEntity = this.servicioAccesoDB.findAll();
		List<estudianteDTO> objEstudianteDTO = this.modelMapper.map(objEstudianteEntity, new TypeToken<List<estudianteDTO>>(){}.getType());
		return objEstudianteDTO;
	}

	@Override
	public estudianteDTO findById(Integer id) {
		EstudianteEntity objEstudianteEntity = this.servicioAccesoDB.findById(id);
		estudianteDTO objEstudianteDTO = this.modelMapper.map(objEstudianteEntity,estudianteDTO.class);
		return objEstudianteDTO;
	}

	@Override
	public estudianteDTO save(estudianteDTO estudiante) {
		System.out.println("datos del servicio: "+this.servicioAccesoDB.findAll().size());
		int total = this.findAll().size();
		if (total > 0) {
			System.out.println("ultimo: " + this.findAll().get(this.findAll().size()-1).getNombres()); 
			estudiante.setId(this.findAll().get(this.findAll().size()-1).getId()+1);
		} else {
			estudiante.setId(0);
		}
		EstudianteEntity objEstudianteEntity = this.modelMapper.map(estudiante, EstudianteEntity.class);
		EstudianteEntity objEstudianteEntity2 = this.servicioAccesoDB.save(objEstudianteEntity);
		estudianteDTO objEstudianteDTO = this.modelMapper.map(objEstudianteEntity2, estudianteDTO.class);
		return objEstudianteDTO;
	}

	@Override
	public estudianteDTO update(Integer id, estudianteDTO estudiante) {
		EstudianteEntity objEstudianteEntity=this.modelMapper.map(estudiante,EstudianteEntity.class);
		EstudianteEntity objEstudianteEntityActualizado = this.servicioAccesoDB.update(id, objEstudianteEntity);
		estudianteDTO objEstudianteDTO = this.modelMapper.map(objEstudianteEntityActualizado, estudianteDTO.class);
		return objEstudianteDTO;
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoDB.delete(id);
	}
}
