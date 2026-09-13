package com.example.lap7.Controller;

import com.example.lap7.ApiResponse.ApiResponse;
import com.example.lap7.Model.Course;
import com.example.lap7.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService courseService = new CourseService();

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        ArrayList<Course> courses = courseService.get();

        if (courses.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no courses"));
        }

        return ResponseEntity.status(200).body(courses);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Course course, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if (courseService.add(course)){
            return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID already exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid Course course, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if (courseService.update(id, course)){
            return ResponseEntity.status(200).body(new ApiResponse("Course information updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        if (courseService.delete(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<?> getCoursesByCategory(@PathVariable String category) {
        ArrayList<Course> courses = courseService.getCoursesByCategory(category);
        if (courses.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No courses found with this category"));
        }
        return ResponseEntity.status(200).body(courses);
    }

    @GetMapping("/credit/{credit}")
    public ResponseEntity<?> getCoursesByCredit(@PathVariable int credit) {
        ArrayList<Course> courses = courseService.getCoursesByCredit(credit);
        if (courses.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No courses found with this credit"));
        }
        return ResponseEntity.status(200).body(courses);
    }
}
