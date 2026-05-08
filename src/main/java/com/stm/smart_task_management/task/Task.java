package com.stm.smart_task_management.task;

import com.stm.smart_task_management.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private String assignedTo;

}
