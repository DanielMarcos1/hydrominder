package com.backend.hydrominder.entity;

import java.util.Date;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Table(name = "Dose")
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDose")
    private int idDose;
    
    @Nonnull
    @Column(name = "mls")
    private int mls;

    @Column(name = "date", columnDefinition = "DATE")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "idGoal")
    private Goal goal;

    public Dose(int idDose, int mls, Date date, Goal goal) {
        this.idDose = idDose;
        this.mls = mls;
        this.date = date;
        this.goal = goal;
    }

    public int getIdDose() {
        return idDose;
    }

    public void setIdDose(int idDose) {
        this.idDose = idDose;
    }

    public int getMls() {
        return mls;
    }

    public void setMls(int mls) {
        this.mls = mls;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // TODO: Implement goal

}