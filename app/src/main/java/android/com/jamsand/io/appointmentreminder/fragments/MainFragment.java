package android.com.jamsand.io.appointmentreminder.fragments;

import android.app.Fragment;
import android.com.jamsand.io.appointmentreminder.AddAppointmentActivity;
import android.com.jamsand.io.appointmentreminder.Appointment;
import android.com.jamsand.io.appointmentreminder.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainFragment extends Fragment {

    public ArrayList<Appointment> appointmentArrayList = new ArrayList<Appointment>();

    private OnItemSelectedListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container,false);

        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonSelected();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    +" must implement MyListFragment.OnItemSelectedListsner ");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {

        }
        createSomeTestAppointmentsToStartWith();

    }


    public interface OnItemSelectedListener {
        public void onButtonSelected();
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outSate) {
//        super.onSaveInstanceState(outSate);
//        outSate.putParcelableArrayList("Appointment_Array_List", appointmentArrayList);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        appointmentArrayList = savedInstanceState.getParcelableArrayList("Appointment_Array_List");
//
//        for (int i = 0;i < appointmentArrayList.size();i++){
//            populateTable(i);
//        }
//    }

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
        TableLayout appointmentTBL = (TableLayout)  getActivity().findViewById(R.id.tblTaskContent);

        TableRow newTableRow = new TableRow(getActivity());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        newTableRow.setLayoutParams(lp);

        TextView txtvName = new TextView(getActivity());
        txtvName.setLayoutParams(lp);
        txtvName.setGravity(Gravity.CENTER);
        txtvName.setText(appointmentArrayList.get(arrayListCounter).name);
        txtvName.setWidth(140);
        txtvName.setTextSize(12);
        txtvName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtvType = new TextView(getActivity());
        txtvType.setLayoutParams(lp);
        txtvType.setGravity(Gravity.CENTER);
        txtvType.setText(appointmentArrayList.get(arrayListCounter).type);
        txtvType.setWidth(140);
        txtvType.setTextSize(12);
        txtvType.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtvDate = new TextView(getActivity());
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
        startActivityForResult(new Intent(getActivity(), AddAppointmentActivity.class), 1);
    }
}
