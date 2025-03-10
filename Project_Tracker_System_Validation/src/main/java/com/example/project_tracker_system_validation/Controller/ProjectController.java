package com.example.project_tracker_system_validation.Controller;

import com.example.project_tracker_system_validation.Api.ApiResponse;
import com.example.project_tracker_system_validation.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("api/v2/track")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    //GET
    @GetMapping("/get")
    public ArrayList<Project> getProjects() {
        return projects;
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Added"));
    }
    @PutMapping("update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody  @Valid Project project ,Errors errors) {
       if(errors.hasErrors()){
           String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
       }
        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("Updated"));
    }
    //DELETE
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("deleted"));
    }
    //SEARCH
    @GetMapping("/search/{title}")
    public ResponseEntity searchProject(@PathVariable String title) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getTitle().equalsIgnoreCase(title)) {
                return ResponseEntity.status(200).body(new ApiResponse("found"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Notfound"));
    }
    //DISPLAY
    @GetMapping("/company/{companyName}")
    public ResponseEntity displayProjects(@PathVariable String companyName) {
        ArrayList<Project> display = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getCompanyName().equalsIgnoreCase(companyName)) {
                display.add(projects.get(i));
            }
        }
        return ResponseEntity.status(200).body(display);
    }
}
