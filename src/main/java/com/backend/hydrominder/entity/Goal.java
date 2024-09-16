package com.backend.hydrominder.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "goal")
    private int goal;

    @Column(name = "fullfilled")
    private boolean fullfilled;

    @Column(name = "margin")
    private int margin;

    @Column(name = "date")
    private Date date;

    public static final int dailygoal = 1500;

    public Goal(int id, int goal, boolean fullfilled, int margin, Date date) {
        this.id = id;
        this.goal = (goal == 0) ? dailygoal : goal;
        this.fullfilled = fullfilled;
        this.margin = margin;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public boolean isFullfilled() {
        return fullfilled;
    }

    public void setFullfilled(boolean fullfilled) {
        this.fullfilled = fullfilled;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //TODO: Implement field to save dose. 
}