import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './models/user';
import { AccountService } from './services/account.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  user?: User | null;

  constructor(private accountService: AccountService, private router: Router){
    this.accountService.user.subscribe(user => this.user = user)
  }

  logout(): void {
    this.accountService.logout();
  }

  hasAuthRoute() {
    return (this.router.url==='/login' || this.router.url==='/register')
  }
}
