import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/services/auth.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  isLoggedIn$: Observable<boolean> | null = null;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.isLoggedIn$ = this.authService.getLoggedIn();
  }

}
