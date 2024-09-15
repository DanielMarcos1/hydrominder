package com.backend.hydrominder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hydrominder.entity.Goal;
import com.backend.hydrominder.repository.GoalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    public Goal getGoalById(int id) {
        Optional<Goal> goal = goalRepository.findById(id);
        return goal.orElse(null);
    }

    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal updateGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public void deleteGoal(int id) {
        goalRepository.deleteById(id);
    }

    public List<Goal> getGoalsByFullfilled(boolean fullfilled) {
        return goalRepository.findAll().stream()
                .filter(goal -> goal.isFullfilled() == fullfilled)
                .toList();
    }
}
