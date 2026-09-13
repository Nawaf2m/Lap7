package com.example.lap7.Controller;

import com.example.lap7.ApiResponse.ApiResponse;
import com.example.lap7.Model.Assignment;
import com.example.lap7.Service.AssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/assignment")
public class AssignmentController {
    private final AssignmentService assignmentService = new AssignmentService();

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        ArrayList<Assignment> assignments = assignmentService.get();

        if (assignments.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("There is no assignments"));
        }

        return ResponseEntity.status(200).body(assignments);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Assignment assignment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if (assignmentService.add(assignment)) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment added successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("ID already exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid Assignment assignment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if (assignmentService.update(id, assignment)) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment information updated successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (assignmentService.delete(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingAssignments() {
        ArrayList<Assignment> assignments = assignmentService.getUpcomingAssignments();

        if (assignments.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("There is no upcoming assignments"));
        }

        return ResponseEntity.status(200).body(assignments);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> getAssignmentsByTitle(@PathVariable String title) {
        ArrayList<Assignment> assignments = assignmentService.getAssignmentsByTitle(title);

        if (assignments.isEmpty()) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse("No assignments found with this title"));
        }

        return ResponseEntity.status(200).body(assignments);
    }
}
