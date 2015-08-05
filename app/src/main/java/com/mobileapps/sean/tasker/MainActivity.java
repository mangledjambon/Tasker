package com.mobileapps.sean.tasker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    /*
    * TODO      write new task to serializable file
    * TODO      TaskActivity for each task
    *
    *       requirement #1 - DONE
    *       requirement #2 - 50% -> need to complete writing new tasks
    *       requirement #3 - DONE
    *       requirement #4 - NOT DONE -> need to display details of task
    *                                 -> change task due date
    *                                 -> share button
    */


    private ListView taskList;
    private boolean loaded;
    private TaskController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //file is not loaded
        loaded = false;

        // get singleton instance of controller
        controller = TaskController.getInstance();

        taskList = (ListView) findViewById(R.id.list);

        // if loadFromFile returns true, then data is loaded, so fill the list
        if (loadFromFile(this)) {
            loaded = fillList();
        }

        // if the list is loaded, display a message with num of tasks due this week
        if (loaded) {
            setListViewOnItemClickListener(taskList);
            addSharedPreferences();
        } else {
            displayToast("Something happened. Tasks could not be loaded.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_newtask) {

            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean loadFromFile(Context context) {
        return controller.loadFromFile(this);
    }

    public boolean  fillList() {
        return controller.fillList(this, taskList);
    }

    public void displayToast(String message) {

        Toast toast = Toast.makeText(getApplicationContext(), (CharSequence) message, Toast.LENGTH_SHORT);
        toast.show();

    }

    public void addSharedPreferences() {

        SharedPreferences prefs = getSharedPreferences("MainActivity", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("message", "You have " + controller.getNumTasksDueThisWeek() + " tasks due this week.");
        editor.apply();

        displayToast(prefs.getString("message", "No tasks found."));
    }

    public void setListViewOnItemClickListener(ListView lv) {

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent taskActivity = new Intent(MainActivity.this, TaskActivity.class);
                taskActivity.putExtra("task", controller.getTask(position));
                startActivity(taskActivity);

            }
        });
    }

    public void refresh() {
        taskList.invalidateViews();
    }

}
