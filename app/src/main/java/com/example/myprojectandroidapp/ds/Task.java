package com.example.myprojectandroidapp.ds;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable {
    private int id;
    private String taskName;
    private String taskDesc;
    private LocalDate dateCreated;
    private LocalDate dateCompleted;
    private LocalDate deploymentDate;

    public Task(int id, String taskName, String taskDesc, LocalDate dateCreated, LocalDate dateCompleted, LocalDate deploymentDate) {
        this.id = id;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
        this.deploymentDate = deploymentDate;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public LocalDate getDeploymentDate() {
        return deploymentDate;
    }

    public void setDeploymentDate(LocalDate deploymentDate) {
        this.deploymentDate = deploymentDate;
    }
}
