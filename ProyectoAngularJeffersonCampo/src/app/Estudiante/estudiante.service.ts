import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import Swal from 'sweetalert2';
import { Estudiante } from './estudiante';

@Injectable({
  providedIn: 'root'
})
export class EstudianteService {
  
  private urlEndPoint: string = 'http://localhost:8080/api/estudiantes';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient, private router: Router) { }
  
  getEstudiantes(): Observable<Estudiante[]>
  {
    return this.http.get<Estudiante[]>(this.urlEndPoint);
  }

  create(estudiante: Estudiante) : Observable<Estudiante> {
    return this.http.post<Estudiante>(this.urlEndPoint, estudiante, {headers: this.httpHeaders}).pipe(
      catchError(
        e => {
          if (e.status == 400) {
            return throwError(e);
          }

          console.log(e.error.mensaje);
          Swal.fire('Error al crear el cliente', e.error.mensaje, 'error');
          return throwError(e);
        })
      );
    }
    
    getEstudiante(id:number): Observable<Estudiante> {
      return this.http.get<Estudiante>(`${this.urlEndPoint}/${id}`).pipe(
        catchError(
          e => {
            this.router.navigate(['/estudiante/listar-estudiantes']);
            console.error(e.error.mensaje);
            Swal.fire('Error al editar la información', e.error.mensaje, 'error');
             return throwError(() => new Error(e));
        })
      );
    }

    update(estudiante: Estudiante): Observable<any> {
      return this.http.put<any>(`${this.urlEndPoint}/${estudiante.id}`, estudiante, { headers: this.httpHeaders }).pipe(
        catchError(
           e => {
            
            if (e.status == 400) {
              return throwError(e);
            }
  
          console.error(e.error.mensaje);
          Swal.fire(e.error.mensaje, e.error.error, 'error');
          return throwError(() => new Error(e));
        })
      );
    }

    delete(codigo: number) : Observable<Estudiante> 
      {
        return this.http.delete<Estudiante>(`${this.urlEndPoint}/?id=${codigo}`, { headers: this.httpHeaders }).pipe(
          catchError(e => {
            console.error(e.error.mensaje);
            Swal.fire(e.error.mensaje, e.error.error, 'error');
            return throwError(() => new Error(e));
          })
        );
      }
    
  }

  


