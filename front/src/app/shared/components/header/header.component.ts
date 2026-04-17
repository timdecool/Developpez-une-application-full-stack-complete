import {Component, ElementRef, HostListener, inject} from '@angular/core';
import {NgOptimizedImage} from "@angular/common";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {LogoComponent} from "../logo/logo.component";
import {MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-header',
  imports: [
    NgOptimizedImage,
    RouterLink,
    LogoComponent,
    RouterLinkActive,
    MatIconButton,
    MatIcon
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
  standalone: true
})
export class HeaderComponent {

  authService = inject(AuthService);
  router = inject(Router);
  element = inject(ElementRef);

  menuOpen = false;

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    if(!this.element.nativeElement.contains(event.target)) {
      this.menuOpen = false;
    }
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/'])
  }

}
