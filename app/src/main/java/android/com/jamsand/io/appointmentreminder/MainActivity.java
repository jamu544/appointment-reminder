package android.com.jamsand.io.appointmentreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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

public class MainActivity extends AppCompatActivity implements MainFragment.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     }

    @Override
    public void onButtonSelected() {
        startActivityForResult(new Intent(this,AddAppointmentActivity.class),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (requestCode == RESULT_OK) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                MainFragment myFragment = (MainFragment) fragmentManager.findFragmentById(R.id.mainFragment);

                Appointment myAppointment = new Appointment(
                        data.getStringExtra("name"),data.getStringExtra("type"),
                        data.getStringExtra("monthOfYear"), data.getIntExtra("dayOfMonth",0),data.getIntExtra("year",0),
                        data.getIntExtra("hour",11),data.getIntExtra("minute",11),data.getStringExtra("AMorPM"));
                myFragment.updateAppointmentListAndDisplay(myAppointment);

            }
        }
    }

}