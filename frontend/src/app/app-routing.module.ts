import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/account/login/login.component';
import { RegisterComponent } from './components/account/register/register.component';
import { DashboardComponent } from './components/home/dashboard/dashboard.component';
import { HistoryComponent } from './components/home/history/history.component';
import { HomeComponent } from './components/home/home.component';
import { TransactionComponent } from './components/home/transaction/transaction.component';
import { AuthGuard } from './helpers/auth.guard';

const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard], children: [
    {path:'', component: DashboardComponent},
    {path:'transaction', component: TransactionComponent},
    {path:'history', component:HistoryComponent}
  ]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},

  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
