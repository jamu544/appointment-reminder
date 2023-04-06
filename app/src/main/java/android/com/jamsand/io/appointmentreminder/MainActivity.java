package android.com.jamsand.io.appointmentreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.com.jamsand.io.appointmentreminder.fragments.AddApointmentFragment;
import android.com.jamsand.io.appointmentreminder.fragments.MainFragment;
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

public class MainActivity extends AppCompatActivity implements MainFragment.OnItemSelectedListener, AddApointmentFragment.OnItemSelectedlistener {

    MainFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFragment = new MainFragment();

        FragmentManager   fragmentManager = getSupportFragmentManager();
        FragmentTransaction  ft = fragmentManager.beginTransaction();
        ft.add(R.id.myContainer, myFragment);
        ft.commit();

     }

    @Override
    public void onButtonSelected() {
   //     startActivityForResult(new Intent(this,AddAppointmentActivity.class),1);
        FragmentManager   fragmentManager = getSupportFragmentManager();
        FragmentTransaction  ft = fragmentManager.beginTransaction();
        ft.replace(R.id.myContainer,new AddApointmentFragment());
        ft.commit();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            if (requestCode == RESULT_OK) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                MainFragment myFragment = (MainFragment) fragmentManager.findFragmentById(R.id.mainFragment);
//
//                Appointment myAppointment = new Appointment(
//                        data.getStringExtra("name"),data.getStringExtra("type"),
//                        data.getStringExtra("monthOfYear"), data.getIntExtra("dayOfMonth",0),data.getIntExtra("year",0),
//                        data.getIntExtra("hour",11),data.getIntExtra("minute",11),data.getStringExtra("AMorPM"));
//                myFragment.updateAppointmentListAndDisplay(myAppointment);
//
//            }
//        }
//    }

    @Override
    public void onAddAppointmentSelected(Appointment appt) {
        FragmentManager   fragmentManager = getSupportFragmentManager();
        FragmentTransaction  ft = fragmentManager.beginTransaction();
        myFragment.updateAppointmentListAndDisplay(appt);
        ft.replace(R.id.myContainer,myFragment);
        ft.commit();
    }

    @Override
    public void onCancel() {
        FragmentManager   fragmentManager = getSupportFragmentManager();
        FragmentTransaction  ft = fragmentManager.beginTransaction();
        ft.replace(R.id.myContainer,myFragment);
        ft.commit();
    }
}