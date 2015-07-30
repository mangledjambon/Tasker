package com.mobileapps.sean.tasker;

import java.util.ArrayList;

/**
 * Created by sean on 30/07/15.
 */
public class TaskController {

    private static TaskController instance;
    static ArrayList<Task> tasks;

    private TaskController() {

        tasks = new ArrayList<Task>();

    }

    public static TaskController getInstance() {

        if (instance == null)
            instance = new TaskController();

        return instance;
    }
}
