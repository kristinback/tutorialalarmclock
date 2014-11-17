package com.example.kristinback.alarmclock;

import android.app.Activity;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;


public class Alarm_list extends Activity {

    private AlarmModel1 alarmDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        AlarmModel1 alarmDetails = new AlarmModel1();

        getActionBar().setTitle("Create new alarm");
        getActionBar().setDisplayHomeAsUpEnabled(true);


        final LinearLayout ringToneContainer = (LinearLayout) findViewById(R.id.alarm_ringtone_container);
        ringToneContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                startActivityForResult(intent , 1);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    
                    alarmDetails.alarmTone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                    TextView txtToneSelection = (TextView) findViewById(R.id.alarm_label_tone_selection);
                    txtToneSelection.setText(RingtoneManager.getRingtone(this, alarmDetails.alarmTone).getTitle(this));
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alarm_list,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
            case R.id.action_save_alarm_details: {
                updateModelFromLayout();
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateModelFromLayout() {

        TimePicker timePicker = (TimePicker) findViewById(R.id.alarm_details_time_picker);
        alarmDetails.timeMinute = timePicker.getCurrentMinute().intValue();
        alarmDetails.timeHour = timePicker.getCurrentHour().intValue();

        EditText edtName = (EditText) findViewById(R.id.alarm_details_name);
        alarmDetails.name = edtName.getText().toString();

        CheckBox chkWeekly = (CheckBox) findViewById(R.id.alarm_details_repeat_weekly);
        alarmDetails.repeatWeekly = chkWeekly.isChecked();

        CheckBox chkSunday = (CheckBox) findViewById(R.id.alarm_details_repeat_sunday);
        alarmDetails.setRepeatingDay(AlarmModel1.SUNDAY, chkSunday.isChecked());

        CheckBox chkMonday = (CheckBox) findViewById(R.id.alarm_details_repeat_monday);
        alarmDetails.setRepeatingDay(AlarmModel1.MONDAY, chkMonday.isChecked());

        CheckBox chkTuesday = (CheckBox) findViewById(R.id.alarm_details_repeat_tuesday);
        alarmDetails.setRepeatingDay(AlarmModel1.TUESDAY, chkTuesday.isChecked());

        CheckBox chkWednesday = (CheckBox) findViewById(R.id.alarm_details_repeat_wednesday);
        alarmDetails.setRepeatingDay(AlarmModel1.WEDNESDAY, chkWednesday.isChecked());

        CheckBox chkThursday = (CheckBox) findViewById(R.id.alarm_details_repeat_thursday);
        alarmDetails.setRepeatingDay(AlarmModel1.THURSDAY, chkThursday.isChecked());

        CheckBox chkFriday = (CheckBox) findViewById(R.id.alarm_details_repeat_friday);
        alarmDetails.setRepeatingDay(AlarmModel1.FRDIAY, chkFriday.isChecked());

        CheckBox chkSaturday = (CheckBox) findViewById(R.id.alarm_details_repeat_saturday);
        alarmDetails.setRepeatingDay(AlarmModel1.SATURDAY, chkSaturday.isChecked());

        alarmDetails.isEnabled = true;
    }
}
