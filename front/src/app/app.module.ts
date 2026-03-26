import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import {MatButton} from "@angular/material/button";
import {NgOptimizedImage} from "@angular/common";

@NgModule({
  declarations: [AppComponent, HomeComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButton,
    NgOptimizedImage,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
