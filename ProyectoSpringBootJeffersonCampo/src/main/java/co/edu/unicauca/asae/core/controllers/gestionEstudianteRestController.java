package co.edu.unicauca.asae.core.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.core.services.DTO.estudianteDTO;
import co.edu.unicauca.asae.core.services.service.IGestionEstudiante;

import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("/api")
@Validated
public class gestionEstudianteRestController {

	@Autowired
	private IGestionEstudiante estudianteService;
	
	@GetMapping("/estudiantes")
	public List<estudianteDTO> index(){
		return estudianteService.findAll();
	}
	
	@GetMapping("/estudiantes/{id}")
	public estudianteDTO findByIdEstudiante(@Min(0) @PathVariable Integer id){
		estudianteDTO objEstudiante = null;
		objEstudiante = estudianteService.findById(id);
		return objEstudiante;
	}
	
	@PostMapping("/estudiantes")
	public estudianteDTO create(@Valid @RequestBody estudianteDTO estudiante) {
		estudianteDTO objEstudiante = null;
		objEstudiante = estudianteService.save(estudiante);
		return objEstudiante;
	}
	
	@PutMapping("/estudiantes/{id}")
	public estudianteDTO update(@RequestBody estudianteDTO estudiante, @PathVariable Integer id) {
		estudianteDTO objEstudiante = null;
		estudianteDTO EstudianteActual = estudianteService.findById(id);
		if(EstudianteActual!=null)	
		{
			objEstudiante = estudianteService.update(id,estudiante);
		}
		return objEstudiante;
	}
	
	@DeleteMapping("/estudiantes")
	public Boolean delete(@RequestParam Integer id) {				
		Boolean bandera=false;
		estudianteDTO EstudianteActual = estudianteService.findById(id);
		if(EstudianteActual!=null)		
		{
			bandera = estudianteService.delete(id);
		}
		return bandera;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
		return new ResponseEntity<>("nombre del método y parametros erroneos: " + e.getMessage(),
				HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
}
