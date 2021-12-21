import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/models/transaction';
import { AccountService } from 'src/app/services/account.service';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  transactions!: Transaction[];
  constructor(private transactionService: TransactionService, private accountService: AccountService) { }

  ngOnInit(): void {
    this.transactionService.getAllTransactions().pipe().subscribe({
      next: (transactions) => {
        this.transactions = transactions;
        this.transactions.forEach((transaction) =>{
          this.setTransaction(transaction);
        })
      },
      error: (error: any) => {
        this.accountService.logout();
      },
    })
  }
  private setTransaction(transaction: Transaction) {
    transaction.senderBankNumber = this.getFormattedBankAccountNumber(transaction.senderBankNumber);
    transaction.recipientAccountNumber = this.getFormattedBankAccountNumber(transaction.recipientAccountNumber);
    transaction.amount = this.getFormattedAmount(transaction.amount);
  }

  getFormattedBankAccountNumber(bankAccountNumber: string): string {
    let bankNumber = '';
    for (let i = 0; i < bankAccountNumber.length; i++) {
      bankNumber += bankAccountNumber[i];
      if ((i + 3) % 4 === 0) {
        bankNumber += ' ';
      }
    }
    return bankNumber;
  }

  getFormattedAmount(amount: any): any {
    return amount.toFixed(2);
  }

}
