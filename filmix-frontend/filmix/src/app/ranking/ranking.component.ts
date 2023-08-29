import { Component } from '@angular/core';
import { FilmsComponent } from '../films/films.component';
import { FilmsService } from '../../services/films.service';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css'],
})
export class RankingComponent {
  activeTab: string = 'filmy';

  changeTab(tab: string): void {
    this.activeTab = tab;
  }
}
