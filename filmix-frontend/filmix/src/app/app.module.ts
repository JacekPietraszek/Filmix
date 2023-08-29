import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { NavigationComponent } from './landing-page/navigation/navigation.component';
import { SearchBarComponent } from './landing-page/navigation/search-bar/search-bar.component';
import { MainContentComponent } from './landing-page/main-content/main-content.component';
import { NewsPageComponent } from './news-page/news-page.component';
import { LogInPageComponent } from './log-in-page/log-in-page.component';
import { FooterComponent } from './landing-page/footer/footer.component';
import { FilmsComponent } from './films/films.component';
import { HttpClientModule } from '@angular/common/http';
import { RegistrationComponent } from './registration/registration.component';
import { RankingComponent } from './ranking/ranking.component';
import { TvSeriesComponent } from './tv-series/tv-series.component';
import { FormsModule } from '@angular/forms';
import { MovieDetailsComponent } from './tv-series-details/movie-details.component';
import { OrganizingVideos } from './models/video.content'; 

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    NavigationComponent,
    SearchBarComponent,
    MainContentComponent,
    NewsPageComponent,
    LogInPageComponent,
    FooterComponent,
    FilmsComponent,
    RegistrationComponent,
    RankingComponent,
    TvSeriesComponent,
    MovieDetailsComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [OrganizingVideos],
  bootstrap: [AppComponent],
})
export class AppModule {}
