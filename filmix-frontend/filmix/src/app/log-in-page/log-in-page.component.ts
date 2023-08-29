import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-log-in-page',
  templateUrl: './log-in-page.component.html',
  styleUrls: ['./log-in-page.component.css'],
})
export class LogInPageComponent {
  error: string = '';
  showError: boolean = false;

  constructor(private authService: AuthService) {}

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }

    const email = form.value.email;
    const password = form.value.password;

    this.authService.login(email, password).subscribe(
      (resData) => {
        console.log(resData);
        this.authService.setLoggedIn(true); // Ustaw stan zalogowania na true po poprawnym zalogowaniu
      },
      (error) => {
        console.log(error);
        this.error = 'Niepoprawny email lub hasło!';
        this.showError = true;
        setTimeout(() => {
          this.showError = false; // ukryj po 5 sekundach
        }, 5000);
      }
    );

    form.reset();
  }
}
