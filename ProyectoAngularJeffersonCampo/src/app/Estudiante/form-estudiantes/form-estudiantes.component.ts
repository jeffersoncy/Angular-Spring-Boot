import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Estudiante } from '../estudiante';
import { EstudianteService } from '../estudiante.service';

@Component({
  selector: 'app-form-estudiantes',
  templateUrl: './form-estudiantes.component.html',
  styleUrls: ['./form-estudiantes.component.css']
})
export class FormEstudiantesComponent implements OnInit {

  public estudiante: Estudiante = new Estudiante();
  public titulo: string = 'formulario estudiantes';
  public errores: string[] = [];

  constructor(private estudianteService: EstudianteService, private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.cargarEstudiante();
  }

  cargarEstudiante(): void{
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.estudianteService.getEstudiante(id).subscribe( (estudiante) => this.estudiante = estudiante)
      }
    });
  }

  public crearEstudiante(): void{
    this.estudianteService.create(this.estudiante).subscribe(
      response => 
        {
          console.log(response.nombres);
          this.router.navigate(['estudiante/listar-estudiantes']);
          Swal.fire('Nuevo estudiante', `Estudiante ${response.nombres} creado con éxito!`, 'success');
        },
        (err: HttpErrorResponse ) => {
          const map = new Map(Object.entries(err.error));
          const vector = Array.from(map.values());
          this.errores = vector as string[]; 
          console.error('Código del error desde el backend: ' + err.status);
        }
    )
  }

  public update(): void {
    this.estudianteService.update(this.estudiante)
      .subscribe(        
        response => {
          this.router.navigate(['estudiante/listar-estudiantes']);
          Swal.fire('Estudiante Actualizado', `Estudiante${response.nombres} actualizado con éxito!`, 'success');
        },
        (err: HttpErrorResponse )=> {
                  const map = new Map(Object.entries(err.error));
                  const vector= Array.from(map.values());
                  this.errores =vector as string[];
                  console.error('Código del error desde el backend: ' + err.status);         
          }
      )
  }

}
