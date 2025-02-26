// src/app/project-list/project-list.component.ts
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { Project } from '../models/models';
import {CommonModule, DatePipe} from '@angular/common';
import {MatCard, MatCardContent, MatCardModule, MatCardTitle} from '@angular/material/card';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css'],
  imports: [
    DatePipe,
    MatCardTitle,
    MatCardContent,
    MatCard,
    CommonModule,
    MatCardModule
  ],
  standalone: true
})
export class ProjectListComponent implements OnInit {
  projects: Project[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getProjects().subscribe(data => {
      this.projects = data;
    });
  }
}
