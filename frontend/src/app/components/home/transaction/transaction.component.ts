import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AccountService } from 'src/app/services/account.service';
import { TransactionService } from 'src/app/services/transaction.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css'],
})
export class TransactionComponent implements OnInit {
  form!: FormGroup;
  submitted: boolean = false;
  loading: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private transactionService: TransactionService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      recipient: ['', [Validators.required, Validators.minLength(2)]],
      recipientAccountNumber: [
        '',
        [Validators.required, Validators.pattern(/^([0-9]{26})$/)],
      ],
      amount: [
        '',
        [Validators.required, Validators.nullValidator, Validators.min(0.01), Validators.max(1000000000)],
      ],
      title: ['', [Validators.required, Validators.minLength(2)]],
    });
  }

  get f() {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {  
      console.log(this.f['amount'].errors);
         
      return;
    }  

    this.loading = true;
    const transactionRequest = this.getTransactionRequest();
    
    this.transactionService.transfer(transactionRequest).subscribe({
      next: () => {
        this.clearForm()
        this.submitted = false;
        this.loading = false;
        this.showAlert('Funds transfered succesfully!', true);
      },
      error: (err: any) => {
        this.loading = false;
        this.showAlert('Something went wrong!', false);
      }
    })
  }
  showAlert(message: string, isSuccess: boolean) {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer);
        toast.addEventListener('mouseleave', Swal.resumeTimer);
      },
    });

    Toast.fire({
      icon: isSuccess ? 'success' : 'error',
      title: message,
    });
  }
  
  clearForm() {
    this.f['title'].reset();
    this.f['recipient'].reset();
    this.f['recipientAccountNumber'].reset()
    this.f['amount'].reset()
  }
  getTransactionRequest() {
    return {
      title: this.f['title'].value,
      recipient: this.f['recipient'].value,
      recipientAccountNumber: this.f['recipientAccountNumber'].value,
      amount: Number(this.f['amount'].value)
    }
  }
}
