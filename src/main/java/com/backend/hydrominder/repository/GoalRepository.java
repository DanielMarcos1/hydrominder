package com.backend.hydrominder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.hydrominder.entity.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {}
