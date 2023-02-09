package co.edu.unicauca.asae.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class EstudianteEntity {
    private Integer id;
	private Integer codigo;
	private tipoIdent tipoIdentificacion;
	private String nombres;
	private String apellidos;
	private String correoInstitucional;
	private double telefono;

    public EstudianteEntity() {
	}
}
