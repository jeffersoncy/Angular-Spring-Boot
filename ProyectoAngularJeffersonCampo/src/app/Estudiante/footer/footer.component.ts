import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  public proyecto: any = {anio: '2023', nombreProyecto: 'Taller Angular'};
  public tecnologia: any = {leyenda: 'WebApp desarrollada con ', tec1: 'Angular ', tec2: '- Spring'};
  public autor: string = 'Jefferson Eduardo Campo Yule';

  constructor() { }

  ngOnInit(): void {
  }

}
