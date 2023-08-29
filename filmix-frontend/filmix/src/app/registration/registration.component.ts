import { Component } from '@angular/core';
import { AuthResponseData, AuthService } from '../../services/auth.service';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent {
  error: string | null = null;
  showError: boolean= false;
  
  constructor(private authService: AuthService) {}

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }
    const email = form.value.email;
    const password = form.value.password;
    const username = form.value.username;
    let authObs: Observable <AuthResponseData>;

    this.authService.signup(email, password, username).subscribe(
      (resData) => {
        console.log(resData);
      },
      (error) => {
        console.log(error);
        this.error = 'Niepoprawne dane';
        this.showError=true;
        setTimeout(() => {
          this.showError = false; // hide after 3 seconds
        }, 3000);
      }
    );



    form.reset();


    
  }
  
}



