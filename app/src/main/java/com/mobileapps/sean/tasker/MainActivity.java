package com.mobileapps.sean.tasker;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.StringTokenizer;


public class MainActivity extends ListActivity {

    /*
    * TODO      implement load from text file (at least 3 tasks)
    * TODO      add action bar + add task button (write to serializable file)
    * TODO      SharedPreferences, add TaskActivity for each task
    */


    private ListView taskList;
    private boolean loaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loaded = false;

        if (loadFromFile(this))
            loaded = fillList();

        if (loaded)
            displayToast("Tasks loaded successfully.");
        else
            displayToast("Something happened. Tasks could not be loaded.");

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

        return super.onOptionsItemSelected(item);
    }

    public boolean loadFromFile(Context context) {

        InputStream in = context.getResources().openRawResource(R.raw.tasks);
        StringTokenizer st = new StringTokenizer(in.toString(), "#");
        System.out.println(st);

        return true;
    }

    public boolean  fillList() {
        return true;
    }

    public void displayToast(String message) {

        Toast toast = Toast.makeText(getApplicationContext(), (CharSequence) message, Toast.LENGTH_SHORT);
        toast.show();

    }

}
