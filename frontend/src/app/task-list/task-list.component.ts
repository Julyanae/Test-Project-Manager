import {Component, Input, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import {ApiService} from '../api.service';
import {Task} from '../models/models'

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
  standalone:true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatCardModule
  ],
})
export class TaskListComponent implements OnInit {
  @Input() projectId!: number;
  tasks: Task[] = [];
  filteredTasks: Task[] = [];
  searchTitle = '';
  selectedStatus = '';

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks(): void {
    this.apiService.getTasksByProject(this.projectId).subscribe(data => {
      this.tasks = data;
      this.applyFilter();
    });
  }

  applyFilter(): void {
    this.filteredTasks = this.tasks.filter(task => {
      const matchesTitle = task.title.toLowerCase().includes(this.searchTitle.toLowerCase());
      const matchesStatus = this.selectedStatus ? task.status === this.selectedStatus : true;
      return matchesTitle && matchesStatus;
    });
  }
}
