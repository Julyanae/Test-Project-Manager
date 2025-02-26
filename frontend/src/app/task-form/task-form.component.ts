import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../api.service';
import { Task } from '../models/models';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import {MatFormField, MatOption, MatSelect} from '@angular/material/select';

@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css'],
  imports: [
    MatSelect,
    MatOption,
    MatFormField,
    CommonModule,
    MatCardModule
  ],
  standalone: true
})
export class TaskFormComponent implements OnInit {
  @Input() projectId!: number;
  taskForm!: FormGroup;

  constructor(private fb: FormBuilder, private apiService: ApiService) {}

  ngOnInit(): void {
    this.taskForm = this.fb.group({
      title: ['', Validators.required],
      description: [''],
      status: ['pending', Validators.required],
      dueDate: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.taskForm.valid) {
      const newTask: Task = {
        ...this.taskForm.value,
        projectId: this.projectId
      };
      this.apiService.createTask(newTask).subscribe(response => {
        console.log('Tâche créée:', response);
      });
    }
  }
}

