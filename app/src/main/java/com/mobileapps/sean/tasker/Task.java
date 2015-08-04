package com.mobileapps.sean.tasker;

import android.text.format.DateFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sean on 30/07/15.
 */
public class Task implements Serializable{

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

    public String getDueDate() {

        String dateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(taskDueDate));
        return dateString;

    }

    public long getDueDateInMillis() {
        return taskDueDate;
    }

    public void setDueDate(long newDateInMillis) {

        taskDueDate = newDateInMillis;

    }

    public String toString() {

        return getTitle() + "\nDue: " + getDueDate();

    }

}
