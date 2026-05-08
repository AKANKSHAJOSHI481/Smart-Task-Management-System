package com.stm.smart_task_management.task.dto;

import java.time.LocalDate;

public record CreateTaskRequest(
        String title,
        String description,
        String priority,
        LocalDate dueDate
) {
}
