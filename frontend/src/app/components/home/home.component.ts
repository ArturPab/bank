import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  @Output() onLogout: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  onTokenExpired() {
    this.onLogout.emit();
  }

}
