import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isCollapsed: boolean = true;
  @Output() onLogout: EventEmitter<any> = new EventEmitter();
  links?: Object;

  constructor(private router: Router) {
   }

  ngOnInit(): void {
  }

  onClick(): void {
    this.onLogout.emit();
  }

  hasRoute(loginPath: string, registerPath: string): boolean {
    return (this.router.url===loginPath || this.router.url===registerPath)
  }

}
