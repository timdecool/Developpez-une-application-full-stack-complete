import {Routes} from "@angular/router";
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {ArticlesComponent} from "./pages/articles/articles.component";
import {ThemesComponent} from "./pages/themes/themes.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {ArticleDetailsComponent} from "./pages/article-details/article-details.component";
import {ArticleCreationComponent} from "./pages/article-creation/article-creation.component";
import {publicGuard} from "./shared/guards/public.guard";
import {authGuard} from "./shared/guards/auth.guard";

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [publicGuard]
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [publicGuard]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [publicGuard]
  },
  {
    path: 'articles',
    component: ArticlesComponent,
    canActivate: [authGuard]
  },
  {
    path: 'articles/new',
    component: ArticleCreationComponent,
    canActivate: [authGuard]
  },
  {
    path: 'articles/:id',
    component: ArticleDetailsComponent,
    canActivate: [authGuard]
  },
  {
    path: 'themes',
    component: ThemesComponent,
    canActivate: [authGuard]
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [authGuard]
  },
  {
    path: '**',
    redirectTo: '/articles'
  }
]
