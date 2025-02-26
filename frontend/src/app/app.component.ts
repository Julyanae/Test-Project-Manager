
import { Component } from '@angular/core';
import {ProjectListComponent} from './project-list/project-list.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [
    ProjectListComponent
  ],
  standalone: true
})
export class AppComponent {
  title = 'frontend2';
}
