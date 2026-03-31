import { Component } from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";

@Component({
  selector: 'app-themes',
  imports: [HeaderComponent],
  templateUrl: './themes.component.html',
  styleUrl: './themes.component.scss',
  standalone: true
})
export class ThemesComponent {

}
