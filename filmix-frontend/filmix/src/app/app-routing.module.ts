import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewsPageComponent } from './news-page/news-page.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LogInPageComponent } from './log-in-page/log-in-page.component';
import { FilmsComponent } from './films/films.component';
import { RegistrationComponent } from './registration/registration.component';
import { RankingComponent } from './ranking/ranking.component';
import { TvSeriesComponent } from './tv-series/tv-series.component';
import { MovieDetailsComponent } from './tv-series-details/movie-details.component';


const routes: Routes = [
  { path: '', redirectTo: '/strona-główna', pathMatch: 'full' },
  { path: 'strona-główna', component: LandingPageComponent },
  { path: 'newsy', component: NewsPageComponent },
  { path: 'zaloguj', component: LogInPageComponent },
  { path: 'filmy', component: FilmsComponent },
  { path: 'rejestracja', component: RegistrationComponent },
  { path: 'rankingi', component: RankingComponent },
  { path: 'seriale', component: TvSeriesComponent },
  { path: 'film/:title', component: MovieDetailsComponent },
  { path: 'serial/:title', component: MovieDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
