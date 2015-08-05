package com.mobileapps.sean.tasker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddTaskActivity extends ActionBarActivity {

    private TextView tv_title;
    private EditText et_title;
    private TextView tv_desc;
    private EditText et_desc;
    private DatePicker datePicker;
    private TaskController controller = TaskController.getInstance();

    public static String fileName = "tasks.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        et_title = (EditText) findViewById(R.id.title_editText);
        et_desc = (EditText) findViewById(R.id.desc_editText);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
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
        } else if (id == R.id.action_add) {

            new AlertDialog.Builder(this)
                    .setTitle("Create task")
                    .setMessage("Create new task?")
                    .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String title = et_title.getText().toString();
                            String desc = et_desc.getText().toString();

                            int day, month, year;
                            day = datePicker.getDayOfMonth();
                            month = datePicker.getMonth();
                            year = datePicker.getYear();

                            String dateStr = day + "/" + month + "/" + year;
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            Date d;
                            Task t;

                            try {

                                d = format.parse(dateStr);
                                t = new Task(title, desc, d.getTime());
                                controller.saveToFile(AddTaskActivity.this, t);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_input_add)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }
}
