package com.example.lap7.Service;

import com.example.lap7.Model.Assignment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class AssignmentService {
    ArrayList<Assignment> assignments = new ArrayList<>();

    public ArrayList<Assignment> get(){
        return assignments;
    }

    public boolean add(Assignment assignment){
        for (int i = 0; i < assignments.size(); i++){
            if (assignments.get(i).getId().equals(assignment.getId())){
                return false;
            }
        }
        assignments.add(assignment);
        return true;
    }

    public boolean update(String id, Assignment assignment){
        for (int i = 0; i < assignments.size(); i++){
            if (assignments.get(i).getId().equals(id)){
                assignments.set(i, assignment);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id){
        for (int i = 0; i < assignments.size(); i++){
            if (assignments.get(i).getId().equals(id)){
                assignments.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Assignment> getUpcomingAssignments() {
        ArrayList<Assignment> targetAssignments = new ArrayList<>();
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getDueDate().isAfter(LocalDate.now())) {
                targetAssignments.add(assignments.get(i));
            }
        }
        return targetAssignments;
    }

    public ArrayList<Assignment> getAssignmentsByTitle(String title) {
        ArrayList<Assignment> targetAssignments = new ArrayList<>();
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getTitle().equals(title)) {
                targetAssignments.add(assignments.get(i));
            }
        }
        return targetAssignments;
    }
}
