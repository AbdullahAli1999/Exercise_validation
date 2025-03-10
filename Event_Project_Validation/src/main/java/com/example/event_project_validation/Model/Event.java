package com.example.event_project_validation.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotNull
    @Min(value = 2, message = "ID must be at least 2")
    @Max(value = 100, message = "ID must be less than 100")

    private int id;
    @NotEmpty(message = "The description should be not Empty!")
    @Size(min = 15 , max = 100, message = "Length more than 15 and less than 100")
    private String description;
    @NotNull(message = "Capacity cannot be null")
    @Min(value = 26, message = "Capacity must be more than 25")
    @Digits(integer = 5, fraction = 0, message = "Capacity must be a number")
   // @Pattern(regexp = "^[+-]?[0-9]+(\\.[0-9]+)?$", message = "Just a valid number (optional decimal allowed).")
    private int capacity;
    private LocalDate startDate;
    private LocalDate endDate;

}
