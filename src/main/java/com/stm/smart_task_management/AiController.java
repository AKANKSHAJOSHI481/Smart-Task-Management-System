package com.stm.smart_task_management;

import com.stm.smart_task_management.ai.AiTaskService;
import com.stm.smart_task_management.ai.dto.TaskPriorityRequest;
import com.stm.smart_task_management.ai.dto.TaskPriorityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {
    private final AiTaskService aiTaskService;

    @PostMapping("/prioritize")
    public TaskPriorityResponse prioritize(@RequestBody TaskPriorityRequest request){
        return aiTaskService.prioritize(request);
    }
}
