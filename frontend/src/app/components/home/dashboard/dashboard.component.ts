import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Customer } from 'src/app/models/customer';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  @Output() onTokenExpired: EventEmitter<any> = new EventEmitter();
  customer!: Customer;

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.customerService.getCustomer().pipe().subscribe({
      next: (customer) => {
      this.customer = customer;
      this.customer.bankAccountNumber = this.getFormattedBankAccountNumber(this.customer.bankAccountNumber);    
      },
      error: (error: any) => {
        this.onTokenExpired.emit()
      }
    }) 
  }

  getFormattedBankAccountNumber(bankAccountNumber: string) {   
    let bankNumber="";
    for(let i=0; i<bankAccountNumber.length; i++) {
      bankNumber+=bankAccountNumber[i];
      if((i+3)%4===0) {      
        bankNumber+=" ";
      }
    }
    return bankNumber;
  }

}
