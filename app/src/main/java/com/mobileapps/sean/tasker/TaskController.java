package com.mobileapps.sean.tasker;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

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

    public int getNumTasksDueThisWeek() {

        int due = 0;

        for (Task t : tasks) {
            if (t.getDueDateInMillis() < getTimeOneWeekFromNow())
                due++;
        }

        return due;
    }

    public boolean loadFromFile(Context c) {

        if (tasks.size() == 0) {

            InputStream in = c.getResources().openRawResource(R.raw.tasks);

            if (in != null) {

                InputStreamReader inputReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputReader);

                try {

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {

                        String[] item;
                        item = TextUtils.split(line, "#");
                        Task task = new Task(item[0], item[1], Long.parseLong(item[2]));

                        if (tasks == null) {
                            tasks = new ArrayList<Task>();
                        }

                        tasks.add(task);
                    }

                    return true;

                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    public boolean fillList(Context c, ListView lv) {

        ArrayList<String> task_details = new ArrayList<String>();

        for (Task t : tasks) {

            String details = t.toString();
            task_details.add(details);

        }

        lv.setAdapter(new ArrayAdapter<>(c,
                android.R.layout.simple_selectable_list_item,
                task_details));

        return true;
    }

    public long getTimeOneWeekFromNow() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, +7);
        return calendar.getTimeInMillis();

    }

    public Task getTask(int index) {

        return tasks.get(index);

    }
}
