import { Component, OnInit } from '@angular/core';
import {RouterLink} from "@angular/router";
import {MatButton, MatButtonModule} from "@angular/material/button";
import {NgOptimizedImage} from "@angular/common";
import {MainContainerComponent} from "../../shared/components/main-container/main-container.component";
import {LogoComponent} from "../../shared/components/logo/logo.component";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
  imports: [
    RouterLink,
    MatButtonModule,
    MatButton,
    NgOptimizedImage,
    MainContainerComponent,
    LogoComponent
  ],
    standalone: true
})
export class HomeComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

}
