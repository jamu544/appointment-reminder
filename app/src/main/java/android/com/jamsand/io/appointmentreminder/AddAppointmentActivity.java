package android.com.jamsand.io.appointmentreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.com.jamsand.io.appointmentreminder.fragments.AddApointmentFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddAppointmentActivity extends AppCompatActivity implements AddApointmentFragment.OnItemSelectedlistener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
    }
    @Override
    public void onAddAppointmentSelected(Appointment appt) {
        Intent intent = new Intent();

        intent.putExtra("name", appt.name);
        intent.putExtra("type", appt.type);
        intent.putExtra("monthOfYear", appt.monthDate);
        intent.putExtra("dayOfMonth", appt.dayDate);
        intent.putExtra("year", appt.yearDate);

        intent.putExtra("hour", appt.hourTime);
        intent.putExtra("minute", appt.minuteTime);
        intent.putExtra("AMorPM", appt.AMorPMTime);

        setResult(RESULT_OK, intent);
        finish();
    }
}