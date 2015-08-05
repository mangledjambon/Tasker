package com.mobileapps.sean.tasker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sean on 30/07/15.
 */
public class Task implements Serializable{

    static final long serialVersionUID = 971110374203763525L;

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

    public void setDueDate(int day, int month, int year) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, day);
        c.add(Calendar.MONTH, month);
        c.add(Calendar.YEAR, year);

        long newDateInMillis = c.getTimeInMillis();

        taskDueDate = newDateInMillis;

    }

    public String toString() {

        return getTitle() + "\nDue: " + getDueDate();

    }

}
