package com.example.lap7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "ID can't be empty")
    @Length(min = 2, message = "ID must be more than 1")
    private String id;

    @NotEmpty(message = "Name can't be empty")
    @Length(min = 2, message = "Name must be more than 1")
    private String name;

    @NotEmpty(message = "Category can't be empty")
    @Pattern(regexp = "^(Programming|Math|Science)$", message = "Category must be Programming, Math, or Science")
    private String category;

    @NotNull(message = "Credit can't be empty")
    @Min(value = 1, message = "Credit must be at least 1")
    @Max(value = 6, message = "Credit cannot be more than 6")
    private int credit;
}
