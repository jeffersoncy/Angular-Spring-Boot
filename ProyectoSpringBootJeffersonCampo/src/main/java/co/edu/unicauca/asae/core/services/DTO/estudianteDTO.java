package co.edu.unicauca.asae.core.services.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import co.edu.unicauca.asae.core.models.tipoIdent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class estudianteDTO {
	
    private Integer id;
	
	@NotNull(message = "{estudiante.codigo.vacio}")
	@Positive(message = "{estudiante.codigo.positive}")
	private Integer codigo;

	@NotNull(message = "{estudiante.tipoIdentificacion.vacio}")
	private tipoIdent tipoIdentificacion;

	@NotNull(message = "{estudiante.nombres.vacio}")
	@Size(min = 5, max = 45, message = "{estudiante.nombres.tamanio}")
	private String nombres;
	
	@NotNull(message = "{estudiante.apellidos.vacio}")
	@Size(min = 5, max = 45, message = "{estudiante.apellidos.tamanio}")
	private String apellidos;

	@NotNull(message = "{estudiante.correoInstitucional.vacio}")
	@Email(message = "{estudiante.correoInstitucional.mask}")
	private String correoInstitucional;

	@NotNull(message = "{estudiante.telefono.vacio}")
	private double telefono;
	
	public estudianteDTO() {
	}
	
}
