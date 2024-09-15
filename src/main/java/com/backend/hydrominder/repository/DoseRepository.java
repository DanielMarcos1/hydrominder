package com.backend.hydrominder.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.hydrominder.entity.Dose;

@Repository
public interface DoseRepository extends JpaRepository<Dose, Integer> {
    List<Dose> findByGoalId(Long goalId);
}
