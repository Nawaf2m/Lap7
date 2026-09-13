package com.example.lap7.Service;

import com.example.lap7.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> get(){
        return courses;
    }

    public boolean add(Course course){
        for (int i = 0; i < courses.size(); i++){
            if (courses.get(i).getId().equals(course.getId())){
                return false;
            }
        }
        courses.add(course);
        return true;
    }

    public boolean update(String id, Course course){
        for (int i = 0; i < courses.size(); i++){
            if (courses.get(i).getId().equals(id)){
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id){
        for (int i = 0; i < courses.size(); i++){
            if (courses.get(i).getId().equals(id)){
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> getCoursesByCategory(String category) {
        ArrayList<Course> targetCourses = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCategory().equals(category)) {
                targetCourses.add(courses.get(i));
            }
        }
        return targetCourses;
    }

    public ArrayList<Course> getCoursesByCredit(int credit) {
        ArrayList<Course> targetCourses = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCredit() == credit) {
                targetCourses.add(courses.get(i));
            }
        }
        return targetCourses;
    }
}
