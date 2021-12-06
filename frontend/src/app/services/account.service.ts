import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { User } from '../models/user';
import { map } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Headers': '*',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  constructor(private http: HttpClient) {}

  login(email: string, password: string) {
    return this.http
      .post<User>(
        `${environment.apiUrl}/login`,
        { email, password },
        httpOptions
      )
      .pipe(
        map((user) => {
          console.log(user);         
          return user;
        })
      );
  }
}
