import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Estudiante } from '../estudiante';
import { EstudianteService } from '../estudiante.service';

@Component({
  selector: 'app-listar-estudiantes',
  templateUrl: './listar-estudiantes.component.html',
  styleUrls: ['./listar-estudiantes.component.css']
})
export class ListarEstudiantesComponent implements OnInit {

  estudiantes:Estudiante[] = [];
  private estudianteService: EstudianteService;

  constructor(estudianteService:EstudianteService, private router: Router) {
    this.estudianteService = estudianteService;
  }

  ngOnInit(): void {
    this.estudianteService.getEstudiantes().subscribe(
      res => this.estudiantes = res
    );

  }

  eliminarEstudiante(estudiante : Estudiante) : void{
    Swal.fire({
      title: 'Estas seguro?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: `Si, eliminar`,
      denyButtonText: `No, cancelar`,
    }).then((result) => {
      if(result.isConfirmed){
        this.estudianteService.delete(estudiante.id).subscribe(
          response=>{
            this.estudiantes = this.estudiantes.filter(est => est! = estudiante )
            this.router.navigateByUrl('estudiante/form-estudiantes', {skipLocationChange: true}).then(()=> this.router.navigate(["estudiante/listar-estudiantes"]));
            Swal.fire('Estudiante eliminado!',`Estudiante ${estudiante.nombres} eliminado con éxito`, 'success') 
          }
        )
      } else if (result.isDenied) {
        Swal.fire('Eliminación cancelada', '', 'info')
      }
    })
  }
}
