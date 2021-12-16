import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MatInputModule } from '@angular/material/input';
import { LoginComponent } from './components/account/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { RegisterComponent } from './components/account/register/register.component';
import { ErrorStateMatcher, ShowOnDirtyErrorStateMatcher } from '@angular/material/core';
import { DashboardComponent } from './components/home/dashboard/dashboard.component';
import { TransactionComponent } from './components/home/transaction/transaction.component';

@NgModule({
  declarations: [AppComponent, LoginComponent, HomeComponent, HeaderComponent, RegisterComponent, DashboardComponent, TransactionComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule
  ],
  providers: [{
    provide:ErrorStateMatcher,useClass: ShowOnDirtyErrorStateMatcher
  }],
  bootstrap: [AppComponent],
})
export class AppModule {}
