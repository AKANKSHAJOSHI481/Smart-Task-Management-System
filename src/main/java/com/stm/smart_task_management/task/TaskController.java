package com.stm.smart_task_management.task;

import com.stm.smart_task_management.task.dto.CreateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public Task create(@RequestBody CreateTaskRequest request){
        return taskService.create(request);
    }

    @GetMapping
    public List<Task> getAll(){
        return taskService.getAll();
    }
}
