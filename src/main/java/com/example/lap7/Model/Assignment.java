package com.example.lap7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Assignment {
    @NotEmpty(message = "ID can't be empty")
    @Length(min = 2, message = "ID must be more than 1")
    private String id;

    @NotEmpty(message = "Title can't be empty")
    @Length(min = 6, message = "Title must be more than 5")
    private String title;

    @NotEmpty(message = "Description can't be empty")
    @Length(min = 10, max = 100, message = "Description must be between 10 and 100 characters")
    private String description;

    @NotNull(message = "Due date can't be empty")
    @Future(message = "Due date must be in the future")
    private LocalDate dueDate;
}
