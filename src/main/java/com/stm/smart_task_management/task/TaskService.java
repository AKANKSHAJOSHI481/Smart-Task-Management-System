package com.stm.smart_task_management.task;

import com.stm.smart_task_management.task.dto.CreateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    public Task create(CreateTaskRequest taskRequest){
        Task task = new Task();
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setPriority(taskRequest.priority());
        task.setDueDate(taskRequest.dueDate());
        task.setStatus("PENDIND");

        return taskRepository.save(task);
    }
    public List<Task> getAll(){
        return taskRepository.findAll();
    }
}
