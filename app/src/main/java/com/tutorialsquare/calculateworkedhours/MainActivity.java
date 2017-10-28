package com.tutorialsquare.calculateworkedhours;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

EditText stime,etime;
    TextView text;
    Button button;
int start_hour,start_minute, end_hour,end_minute, result_hours, result_minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  initiate the edit text
        stime = (EditText) findViewById(R.id.start);
        // perform click event listener on edit text
        stime.setOnClickListener(this);
        etime = (EditText) findViewById(R.id.end);
        // perform click event listener on edit text
        etime.setOnClickListener(this);
        text = (TextView) findViewById(R.id.text1);
        text.setText("");
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch( id) {
            case R.id.button1:
            {
                if (start_hour > end_hour) {
                    Toast.makeText(this, "Invalid!", Toast.LENGTH_LONG).show();
                    return;
                }
                result_hours = end_hour - start_hour;
                if ( end_minute < start_minute) {
                    result_minute = 60 - start_minute + end_minute;
                    result_hours -= 1;
                }
                else
                    result_minute = end_minute - start_minute;

                text.setText("hours "+result_hours+" "+"minutes "+result_minute);
                break;
            }
            case R.id.start:
            case R.id.end:
            {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;


                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        if (id == R.id.start) {
                            start_hour = selectedHour;
                            start_minute = selectedMinute;
                            stime.setText(selectedHour + ":" + selectedMinute);
                        } else {
                            end_hour = selectedHour;
                            end_minute = selectedMinute;
                            etime.setText(selectedHour + ":" + selectedMinute);
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;
            }
            default:
                break;
        }
    }
}
