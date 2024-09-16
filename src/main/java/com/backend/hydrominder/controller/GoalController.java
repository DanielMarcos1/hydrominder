package com.backend.hydrominder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.hydrominder.entity.Goal;
import com.backend.hydrominder.service.GoalService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/1.0/hydrominder/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @GetMapping
    @Operation(summary = "Get all goals")
    public ResponseEntity<List<Goal>> getAllGoals() {
        return new ResponseEntity<>(goalService.getAllGoals(), HttpStatus.OK);
    }

    
    @GetMapping("/dailygoal")
    @Operation(summary = "Get the base daily goal value")
    public ResponseEntity<Map<String, Integer>> getDailyGoal() {
        Map<String, Integer> response = new HashMap<>();
        response.put("dailyGoal", Goal.dailygoal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a goal by ID")
    public ResponseEntity<Goal> getGoalById(@PathVariable int id) {
        Goal goal = goalService.getGoalById(id);
        if (goal != null) {
            return new ResponseEntity<>(goal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Create a new goal")
    public ResponseEntity<Map<String, Object>> createGoal(@RequestBody Goal goal) {
        Goal createdGoal = goalService.createGoal(goal);
        Map<String, Object> response = new HashMap<>();
        response.put("goal", createdGoal);
        response.put("message", "criado com sucesso");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a goal by ID")
    public ResponseEntity<Map<String, Object>> updateGoal(@PathVariable int id, @RequestBody Goal goal) {
        Goal existingGoal = goalService.getGoalById(id);
        if (existingGoal != null) {
            goal.setId(id);
            Goal updatedGoal = goalService.updateGoal(goal);
            Map<String, Object> response = new HashMap<>();
            response.put("goal", updatedGoal);
            response.put("message", "atualizado com sucesso");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a goal by ID")
    public ResponseEntity<Map<String, String>> deleteGoal(@PathVariable int id) {
        Goal existingGoal = goalService.getGoalById(id);
        if (existingGoal != null) {
            goalService.deleteGoal(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "deletado com sucesso");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fullfilled/{fullfilled}")
    @Operation(summary = "Get goals by fullfilled status")
    public ResponseEntity<List<Goal>> getGoalsByFullfilled(@PathVariable boolean fullfilled) {
        return new ResponseEntity<>(goalService.getGoalsByFullfilled(fullfilled), HttpStatus.OK);
    }
}