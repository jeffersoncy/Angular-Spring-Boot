import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { ListarEstudiantesComponent } from './Estudiante/listar-estudiantes/listar-estudiantes.component';
import { FooterComponent } from './Estudiante/footer/footer.component';
import { FormEstudiantesComponent } from './Estudiante/form-estudiantes/form-estudiantes.component';
import { HeaderComponent } from './Estudiante/header/header.component';
import { HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
const routes: Routes = [
  {path: '', redirectTo: 'estudiante/listar-estudiantes', pathMatch: 'full'},
  {path: 'estudiante/footer', component: FooterComponent},
  {path: 'estudiante/header', component: HeaderComponent},
  {path: 'estudiante/listar-estudiantes', component: ListarEstudiantesComponent},
  {path: 'estudiante/form-estudiantes', component: FormEstudiantesComponent},
  {path: 'estudiante/form-estudiantes/:id', component: FormEstudiantesComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    ListarEstudiantesComponent,
    FooterComponent,
    FormEstudiantesComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
