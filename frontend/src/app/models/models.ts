
export interface Project {
  id?: number;
  name: string;
  description: string;
  startDate: Date;
  endDate: Date;
  tasks?: Task[];
}

export interface Task {
  id?: number;
  title: string;
  description: string;
  status: string;
  dueDate: Date;
  projectId?: number;
}
