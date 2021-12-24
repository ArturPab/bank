import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Customer } from 'src/app/models/customer';
import { AccountService } from 'src/app/services/account.service';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  @Output() onTokenExpired: EventEmitter<any> = new EventEmitter();
  customer: Customer = new Customer();

  constructor(
    private customerService: CustomerService,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.customerService
      .getCustomer()
      .pipe()
      .subscribe({
        next: (customer) => {
          this.customer = customer;
          
          this.customer.bankAccountNumber = this.getFormattedBankAccountNumber(
            this.customer.bankAccountNumber
          );
          this.customer.balance = this.getFormattedBalance(
            this.customer.balance
          );
        },
        error: (error: any) => {
          this.accountService.logout();
        },
      });
  }
  getFormattedBalance(balance: any): any {
    return balance.toFixed(2);
  }

  getFormattedBankAccountNumber(bankAccountNumber: string) {   
    let bankNumber = '';
    for (let i = 0; i < bankAccountNumber.length; i++) {
      bankNumber += bankAccountNumber[i];
      if ((i + 3) % 4 === 0) {
        bankNumber += ' ';
      }
    }
    return bankNumber;
  }
}
