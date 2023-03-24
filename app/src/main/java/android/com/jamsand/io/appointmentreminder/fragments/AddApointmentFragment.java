package android.com.jamsand.io.appointmentreminder.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.com.jamsand.io.appointmentreminder.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

public class AddApointmentFragment extends Fragment {
    TextView txtDate;
    TextView txtTime;

    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;

    static final int DATE_DIALOG_ID = 999;
    static final int TIME_DIALOG_ID = 998;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_appointment, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {

        }
        setCurrentDateAndTime();

    }


//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("Month", month);
//        outState.putInt("Day", day);
//        outState.putInt("Year", year);
//        outState.putInt("Hour", hour);
//        outState.putInt("Minute", minute);
//    }

    void UpdateDisplayDateOrTime(int DateOrTime) {
        switch (DateOrTime) {
            case 0: //Month Day, Year
                txtDate.setText(new StringBuilder()
                        .append(DisplayTheMonthInCharacters(month)).append(" ")
                        .append(day).append(", ")
                        .append(year)
                );
            case 1:
                //set current time int textView
                txtTime.setText(new StringBuilder().append(pad(hour))
                        .append(":").append(pad(minute)));
                txtTime.setText(new StringBuilder()
                        .append(FormatTheHour(hour)).append(":")
                        .append(FormatTheHour(minute)).append(" ")
                        .append(AMorPM(hour)));


        }
    }

//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        month = savedInstanceState.getInt("Month");
//        day = savedInstanceState.getInt("Day");
//        year = savedInstanceState.getInt("Year");
//        hour = savedInstanceState.getInt("Hour");
//        minute = savedInstanceState.getInt("Minute");
//
//        UpdateDisplayDateOrTime(0);
//        UpdateDisplayDateOrTime(1);
//
//    }

    private void setCurrentDateAndTime() {
        txtDate = getActivity().findViewById(R.id.txttvDate);
        txtTime = getActivity().findViewById(R.id.txtvTime);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        txtTime.setText(new StringBuilder().append(hour).append(":").append(minute));

        txtDate.setText(new StringBuilder()
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            txtDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;

            txtTime.setText(new StringBuilder().append(hour).append(":").append(minute));
        }
    };

//    @Override
//    protected Dialog onCreateDialog(int id) {
//        switch (id){
//            case DATE_DIALOG_ID:
//                return new DatePickerDialog(this,datePickerListener,year, month, day);
//            case TIME_DIALOG_ID:
//                return new TimePickerDialog(this,timePickerListener, hour, minute, false);
//        }
//        return null;
//    }

    public void edittextDate(View view) {
    }

    public void btnCancel(View view) {
        getActivity().finish();
    }

    public void btnAddAppointment(View view) {
        EditText editAppointmentName = getActivity().findViewById(R.id.editTaskName);
        Spinner spinnerAppointmentType = getActivity().findViewById(R.id.spinnerTaskType);
        if (!(editAppointmentName.getText().toString().isEmpty())) {
            Intent intent = new Intent();

            intent.putExtra("name", editAppointmentName.getText().toString());

            intent.putExtra("type", spinnerAppointmentType.getSelectedItem().toString());

            intent.putExtra("monthOfYear", DisplayTheMonthInCharacters(month));
            intent.putExtra("dayOfMonth", day);
            intent.putExtra("year", year);

            intent.putExtra("hour", formatHour(hour));
            intent.putExtra("minute", minute);
            intent.putExtra("AMorPM", AMorPM(hour));

//            getActivity().setResult(RESULT_OK, intent);
//            getActivity().finish();
        } else {
            Toast.makeText(getActivity(), "Please enter an Appointment Name", Toast.LENGTH_SHORT).show();
        }
    }

    private String DisplayTheMonthInCharacters(int passedMonth) {
        switch (passedMonth) {
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "Mar";
            case 3:
                return "Apr";
            case 4:
                return "May";
            case 5:
                return "Jun";
            case 6:
                return "Jul";
            case 7:
                return "Aug";
            case 8:
                return "Sept";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";
        }
        return "";
    }

    private int formatHour(int passedHour) {
        if (passedHour > 12) {
            return passedHour -= 12;
        }
        return passedHour;
    }

    private String AMorPM(int passedHour) {
        if (passedHour > 12) {
            return "PM";
        } else {
            return "AM";
        }
    }


    private String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void edittxtTime(View view) {
    }

    public void edittxtDate(View view) {
    }

    // meant to correct the display minute for 0-9 "00 vs 0"
    private String FormatTheMinute(int passedMinute) {
        String forwardTime = Integer.toString(passedMinute);
        if (passedMinute < 10) {
            forwardTime = "0" + forwardTime;
        }
        return forwardTime;
    }

    //Converts the 24 hours PassedHour to a 12 Hour time.
    private int FormatTheHour(int passedHour) {
        if (passedHour > 12) {
            passedHour -= 12;
        }
        return passedHour;
    }
}