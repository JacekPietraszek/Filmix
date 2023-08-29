import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject } from 'rxjs';


 export interface AuthResponseData 
{
    kind: string;
    idToken: string;
    email:string;
    username: string;
    refreshToken:string;
    expiresIn:string;
    localId: string;
    registered?: boolean;

    // properties to get from request, response
}

 
 @Injectable({providedIn: 'root'})

 export class AuthService {
    private isLoggedIn = new BehaviorSubject<boolean>(false);

    constructor(private http: HttpClient) {}


    signup(email: string, password:string, username: string)
    {
        return this.http.post<AuthResponseData>('https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyAtnAe9A6iL6yvELIgbdwScKXXN1SyNWvo',
        {
            username: username,
            email: email,
            password: password,
            returnSecureToken:true
        }
        );
    }


    login(email: string,password: string)
    {
return this.http.post<AuthResponseData>('https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyAtnAe9A6iL6yvELIgbdwScKXXN1SyNWvo',
{
    email: email,
    password: password,
    returnSecureToken:true
});


    }
    setLoggedIn(value: boolean) {
        this.isLoggedIn.next(value);
      }
    
      getLoggedIn() {
        return this.isLoggedIn.asObservable();
      }

}