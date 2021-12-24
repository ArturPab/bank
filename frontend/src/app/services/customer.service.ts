import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.prod';
import { Customer } from '../models/customer';
import { AccountService } from './account.service';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(
    private http: HttpClient,
    private accountService: AccountService
  ) {}

  getCustomer(): Observable<Customer> {
    const token = this.accountService.userValue!.token;
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + token,
      }),
    };

    return this.http.get<Customer>(
      `${environment.apiUrl}/api/customer`,
      httpOptions
    );
  }
}
