import { Routes } from '@angular/router';
import {LightPageComponent} from "./components/light-page/light-page.component";
import {RacinePageComponent} from "./components/racine-page/racine-page.component";

export const routes: Routes = [
  {path: "racine", component: RacinePageComponent},
  {path: "light", component: LightPageComponent},
  {path: "**", redirectTo: "light"}
];
