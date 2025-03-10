package com.example.project_tracker_system_validation.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotNull(message = "ID cannot be null")
    @Size(min = 3, message = "ID must have more than 2 characters")
    private String id;
    @NotNull(message = "Title cannot be null")
    @Size(min = 9, message = "Title must have more than 8 characters")
    private String title;
    @NotNull(message = "Description cannot be null")
    @Size(min = 16, message = "Description must have more than 15 characters")
    private String description;
    @NotNull(message = "Status cannot be null")
    @Pattern(regexp = "Not Started|In Progress|Completed", message = "Status must be 'Not Started', 'In Progress', or 'Completed'")
    private String status;
    @NotNull(message = "Company name cannot be null")
    @Size(min = 7, message = "Company name must have more than 6 characters")
    private String companyName;
}
