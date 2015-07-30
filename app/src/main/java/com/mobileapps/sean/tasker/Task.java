package com.mobileapps.sean.tasker;

/**
 * Created by sean on 30/07/15.
 */
public class Task {

    private String taskDescription;
    private String taskTitle;
    private long taskDueDate;

    public Task(String title, String description, long date) {

        taskTitle = title;
        taskDescription = description;
        taskDueDate = date;

    }

    public String getTitle() {
        return taskTitle;
    }

    public String getDescription() {
        return taskDescription;
    }

    public long getTaskDate() {
        return taskDueDate;
    }

}
