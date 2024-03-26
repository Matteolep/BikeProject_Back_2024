import { Component } from '@angular/core';
import {NgClass, NgForOf} from "@angular/common";
import {LightComponent} from "../light/light.component";
import {LightService} from "../../services/light.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-light-page',
  standalone: true,
  imports: [
    NgClass,
    NgForOf,
    LightComponent,
    FormsModule
  ],
  templateUrl: './light-page.component.html',
  styleUrl: './light-page.component.css'
})
export class LightPageComponent {

  light: any = {
    name: "",
    color: ""
  }


  constructor(protected lightService: LightService) {}

  handleTrigger(id: number) {
    this.lightService.removeLight(id);
  }

  switchLight(light: any) {
    console.log("switching : " + light);
    this.lightService.toggleLight(light);
  }
}
