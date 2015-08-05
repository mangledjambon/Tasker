package com.mobileapps.sean.tasker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class TaskActivity extends ActionBarActivity {

    /*
    * TODO        add 'Change Task due date' using date picker + 'Share' buttons to TaskActivity
    * TODO        add extra feature chosen by me (not decided yet)
    */

    private TextView title, description, date;
    private Button changeButton, shareButton;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        title = (TextView) findViewById(R.id.tv_title);
        description = (TextView) findViewById(R.id.tv_description);
        date = (TextView) findViewById(R.id.tv_dueDate);

        Bundle bundle = getIntent().getExtras();
        task = (Task) bundle.get("task");

        title.setText(task.getTitle());
        description.setText(task.getDescription());
        date.setText(task.getDueDate());

        changeButton = (Button) findViewById(R.id.btn_changeDate);
        shareButton = (Button) findViewById(R.id.btn_share);
        changeButton.setText(R.string.btn_changeDate);
        shareButton.setText(R.string.btn_share);

        setButtonClickListener(shareButton, task);
        setButtonClickListener(changeButton, task);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task, menu);
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

    public void setButtonClickListener(final Button b, final Task t) {

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b.getId() == R.id.btn_share) {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND, Uri.parse(t.toString()));
                    shareIntent.setType("text/plain");
                    startActivity(Intent.createChooser(shareIntent, "Share task via"));

                } else if (b.getId() == R.id.btn_changeDate) {

                    showDialog(1);

                }
            }
        });
    }

    protected Dialog onCreateDialog(int id) {

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);

        if (id == 1) {
            return new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    task.setDueDate(arg1, arg2+1, arg3);
                    date.setText(task.getDueDate());
                }

            }, year, month, day);
        }

        return null;
    }
}
