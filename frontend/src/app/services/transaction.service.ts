import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TransactionRequest } from '../models/transaction-request';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AccountService } from './account.service';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http: HttpClient, private accountService: AccountService) { }

  transfer(transactionRequest: TransactionRequest) {
    const token = this.accountService.userValue!.token;
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + token,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Headers': '*',
      }),
    };
    return this.http.post<TransactionRequest>(
      `${environment.apiUrl}/api/transactions`,
      JSON.stringify(transactionRequest),
      httpOptions
    ).pipe()
  }
}
