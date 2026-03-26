import { Component, OnInit } from '@angular/core';
import {RouterLink} from "@angular/router";
import {MatButton, MatButtonModule} from "@angular/material/button";
import {NgOptimizedImage} from "@angular/common";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
  imports: [
    RouterLink,
    MatButtonModule,
    MatButton,
    NgOptimizedImage
  ],
    standalone: true
})
export class HomeComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

}
