package com.example.event_project_validation.Controller;

import com.example.event_project_validation.Api.ApiResponse;
import com.example.event_project_validation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v2/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    //GET
    @GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }

    //ADD
    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Added"));
    }
    //UPDATE
    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid Event event , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index , event);
        return ResponseEntity.status(200).body(new ApiResponse("Updated"));
    }
    //DELETE
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index){
        if(index > events.size()){
            return ResponseEntity.status(500).body("Not found");

        }
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted"));
    }

    //Search
    @GetMapping("/search/{id}")
    public ResponseEntity searchEvents(@PathVariable int id) {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if(event.getId() == id){
                return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(400).body("Not Found");

    }
    //Capacity
    @PutMapping("/{id}/capacity/{newCapacity}")
    public ResponseEntity changeCapacity(@PathVariable int id, @PathVariable int newCapacity) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == id) {
                events.get(i).setCapacity(newCapacity);
                return ResponseEntity.status(200).body(new ApiResponse("Good++"));
            }

        }
        return ResponseEntity.status(400).body("Not found");

    }


}
