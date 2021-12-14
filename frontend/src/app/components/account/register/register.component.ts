import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'src/app/services/account.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  form!: FormGroup;
  days!: string[];
  months!: string[];
  years!: string[];
  loading: boolean = false;
  submitted: boolean = false;
  constructor(
    private formBuilder: FormBuilder,
    private accountService: AccountService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    if (this.accountService.userValue) {
      this.router.navigate(['']);
    }
  }

  ngOnInit(): void {
    this.days = new Array(31);
    this.months = new Array(12);
    this.years = new Array(120);
    // adding day options
    this.fillDays();
    this.fillMonths();
    this.fillYears();
    this.form = this.formBuilder.group({
      name: ['', [Validators.required, Validators.min(2)]],
      lastname: ['', [Validators.required, Validators.min(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      day: [this.days[0], Validators.required],
      month: [this.months[0], Validators.required],
      year: [this.years[100], Validators.required],
    });
  }
  fillYears() {
    for (let year = 1; year <= this.years.length; year++) {
      this.years[year - 1] = (year + 1901).toString();
    }
  }
  get f() {
    return this.form.controls;
  }

  private fillMonths() {
    this.months[0] = 'January';
    this.months[1] = 'February';
    this.months[2] = 'March';
    this.months[3] = 'April';
    this.months[4] = 'May';
    this.months[5] = 'June';
    this.months[6] = 'July';
    this.months[7] = 'August';
    this.months[8] = 'September';
    this.months[9] = 'October';
    this.months[10] = 'November';
    this.months[11] = 'December';
  }

  private fillDays() {
    for (let day = 1; day <= this.days.length; day++) {
      this.days[day - 1] = day.toString();
    }
  }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }
    this.loading = true;
    const registerRequest = this.getRegisterRequest();

    this.accountService.register(registerRequest).subscribe({
      next: () => {
        this.router.navigate(['/login']);
        this.showAlert('Created account succesfully!', true);
      },
      error: (err: any) => {
        this.loading = false;
        this.showAlert('Something went wrong!', false);
      },
    });
  }

  getRegisterRequest() {
    const day = this.setRequestDay();
    const month = this.setRequestMonth();
    return {
      email: this.f['email'].value,
      password: this.f['password'].value,
      name: this.f['name'].value,
      lastname: this.f['lastname'].value,
      dateOfBirth: this.getDate(day, month),
    };
  }
  getDate(day: string, month: string): string {
    return this.f['year'].value + '-' + month + '-' + day;
  }
  setRequestMonth() {
    const month = this.months.indexOf(this.f['month'].value) + 1;
    if (month.toString().length == 1) {
      return '0' + month.toString();
    }
    return month.toString();
  }
  setRequestDay() {
    if (this.f['day'].value.length == 1) {
      return '0' + this.f['day'].value;
    }
    return this.f['day'].value;
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
}
