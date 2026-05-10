package com.stm.smart_task_management.task;

import com.stm.smart_task_management.ai.AiTaskService;
import com.stm.smart_task_management.ai.dto.TaskPriorityRequest;
import com.stm.smart_task_management.ai.dto.TaskPriorityResponse;
import com.stm.smart_task_management.notification.EmailService;
import com.stm.smart_task_management.task.dto.CreateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmailService emailService;
    private final AiTaskService aiTaskService;
    @CacheEvict(value = "tasks", allEntries = true, key = "T(com.stm.smart_task_management.tenant.TenantContext).getTenant()")
    public Task create(CreateTaskRequest taskRequest){
        Task task = new Task();
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        TaskPriorityResponse aiPriority = aiTaskService.prioritize(new TaskPriorityRequest(taskRequest.title(), taskRequest.description()));
        task.setPriority(aiPriority.priority());
        task.setDueDate(taskRequest.dueDate());
        task.setStatus("PENDIND");
        emailService.sendTaskCreatedEmail("joshiakanksha481@gmail.com");
        return taskRepository.save(task);
    }
    @Cacheable(value = "tasks", key = "T(com.stm.smart_task_management.tenant.TenantContext).getTenant()")
    public List<Task> getAll(){
        System.out.println("FETCHING FROM DB");
        return taskRepository.findAll();
    }
}
