package co.edu.unicauca.asae.core.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.core.models.EstudianteEntity;
import co.edu.unicauca.asae.core.models.tipoIdent;

@Repository
public class estudiantesRepository {
    
    private ArrayList<EstudianteEntity> ListaEstudiantes;
	
    public estudiantesRepository() {
		this.ListaEstudiantes = new ArrayList<EstudianteEntity>();
		cargarEstudiantes();
	}

    public List<EstudianteEntity> findAll(){
		System.out.println("Invocando a listarEstudiantes");
		return this.ListaEstudiantes;
	}
	
	public EstudianteEntity findById(Integer identificacion) {
		System.out.println("Invocando a consultar un estudiante");
		EstudianteEntity objEstudiante=null;
		for (EstudianteEntity estudiante: ListaEstudiantes) {
			if(estudiante.getId() == identificacion) {
				objEstudiante = estudiante;
				break;
			}	
		}
		return objEstudiante;
	}

    public EstudianteEntity save(EstudianteEntity estudiante) {
		System.out.println("Invocando a consultar un estudiante");
		EstudianteEntity objEstudiante = null;
		if(this.ListaEstudiantes.add(estudiante)) {
			objEstudiante = estudiante;
		}
		
		return objEstudiante;
	}
	
	public EstudianteEntity update(Integer id, EstudianteEntity estudiante) {
		System.out.println("Invocando a consultar un estudiante");
		EstudianteEntity objEstudiante=null;
		
		for (int i=0; i< this.ListaEstudiantes.size(); i++) {
			if(this.ListaEstudiantes.get(i).getId() == id) {
				this.ListaEstudiantes.set(i,estudiante);
				objEstudiante=estudiante;
				break;
			}
		} 
		
		return objEstudiante;
	}
	
	public boolean delete(Integer id) {
		System.out.println("Invocando a consultar un estudiante");
		boolean bandera = false;
		
		for (int i=0; i< this.ListaEstudiantes.size(); i++) {
			if(this.ListaEstudiantes.get(i).getId() == id) {
				this.ListaEstudiantes.remove(i);
				bandera=true;
				break;
			}
		}
		return bandera;
	}
	
	private void cargarEstudiantes()
	{
		EstudianteEntity objEstudiante1 = new EstudianteEntity(0,10611,tipoIdent.CC,"Jhosua","Ramos Sanchez","jhosuaramos@unicauca.edu.co",314759667);
		this.ListaEstudiantes.add(objEstudiante1);
		EstudianteEntity objEstudiante2 = new EstudianteEntity(1,10612,tipoIdent.CC,"Juan Pablo","Montoya","juanpablom@unicauca.edu.co",314754556);
		this.ListaEstudiantes.add(objEstudiante2);
		EstudianteEntity objEstudiante3 = new EstudianteEntity(2,10613,tipoIdent.CE,"George","Brown","georgebrown@unicauca.edu.co",314757788);
		this.ListaEstudiantes.add(objEstudiante3);
		EstudianteEntity objEstudiante4 = new EstudianteEntity(3,10614,tipoIdent.TI,"Duvan Camilo","Ruiz Sanchez","duvanca@unicauca.edu.co",314755566);
		this.ListaEstudiantes.add(objEstudiante4);
	} 
}
