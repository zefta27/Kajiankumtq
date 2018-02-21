package mtqnunsri.gelumbang.zefta.uwalq.kajianku.Activity;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import mtqnunsri.gelumbang.zefta.uwalq.kajianku.R;


public class MyScheduleActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button tryIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Jadwal Saya");
        setSupportActionBar(toolbar);
        tryIntent = (Button) findViewById(R.id.btnTry);
        tryIntent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2017, 2, 19, 7, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2017, 2, 19, 8, 30);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, "Yoga")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
        startActivity(intent);


    }


}
