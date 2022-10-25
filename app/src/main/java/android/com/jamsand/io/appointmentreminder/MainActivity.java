package android.com.jamsand.io.appointmentreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Appointment> appointmentArrayList = new ArrayList<Appointment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSomeTestAppointmentsToStartWith();
    }

    private void createSomeTestAppointmentsToStartWith() {
        appointmentArrayList.add(new Appointment("Doctors Visit", "Health",  "October", 9, 2016,  9,  00,  "AM"));
        appointmentArrayList.add(new Appointment("Doctors Visit", "Health",  "October", 9, 2016,  9,  00,  "AM"));
        appointmentArrayList.add(new Appointment("Doctors Visit", "Health",  "October", 9, 2016,  9,  00,  "AM"));
        appointmentArrayList.add(new Appointment("Doctors Visit", "Health",  "October", 9, 2016,  9,  00,  "AM"));
        appointmentArrayList.add(new Appointment("Doctors Visit", "Health",  "October", 9, 2016,  9,  00,  "AM"));
        appointmentArrayList.add(new Appointment("Doctors Visit", "Health",  "October", 9, 2016,  9,  00,  "AM"));
        appointmentArrayList.add(new Appointment("Doctors Visit", "Health",  "October", 9, 2016,  9,  00,  "AM"));

        for (int i = 0; i < appointmentArrayList.size(); i++) {
            populateTable(i);
        }
    }

    private String setToDateAndTime(Appointment appointment){
        long currentDatAndTime = System.currentTimeMillis();
        SimpleDateFormat formDate = new SimpleDateFormat("MMM d, yyyy");

        String todaysDate = formDate.format(currentDatAndTime);
        String passDate = appointment.monthDate+" "+ appointment.dayDate+", "+appointment.yearDate;

        if(Objects.equals(todaysDate, passDate)){
            return appointment.hourTime+":"+appointment.dayDate+" "+appointment.AMorPMTime;
        }

        return appointment.monthDate+" "+appointment.dayDate+", "+appointment.yearDate;
    }

    private void populateTable(int arrayListCounter) {
        TableLayout appointmentTBL = (TableLayout)  findViewById(R.id.tblTaskContent);

        TableRow newTableRow = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        newTableRow.setLayoutParams(lp);

        TextView txtvName = new TextView(this);
        txtvName.setLayoutParams(lp);
        txtvName.setGravity(Gravity.CENTER);
        txtvName.setText(appointmentArrayList.get(arrayListCounter).name);
        txtvName.setWidth(140);
        txtvName.setTextSize(12);
        txtvName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtvType = new TextView(this);
        txtvType.setLayoutParams(lp);
        txtvType.setGravity(Gravity.CENTER);
        txtvType.setText(appointmentArrayList.get(arrayListCounter).type);
        txtvType.setWidth(140);
        txtvType.setTextSize(12);
        txtvType.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtvDate = new TextView(this);
        txtvDate.setLayoutParams(lp);
        txtvDate.setGravity(Gravity.CENTER);
        txtvDate.setText(setToDateAndTime(appointmentArrayList.get(arrayListCounter)));
        txtvDate.setWidth(140);
        txtvDate.setTextSize(12);
        txtvDate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        newTableRow.addView(txtvName);
        newTableRow.addView(txtvType);
        newTableRow.addView(txtvDate);
       appointmentTBL.addView(newTableRow,arrayListCounter+1);


    }


    public void AddAppointmentBtn(View view) {
        startActivityForResult(new Intent(this, AddAppointmentActivity.class), 1);
    }
}