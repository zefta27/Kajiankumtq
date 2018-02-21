
package mtqnunsri.gelumbang.zefta.uwalq.kajianku.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mtqnunsri.gelumbang.zefta.uwalq.kajianku.BackgroundTaskKajian;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.R;

public class BuatKajianActivity extends AppCompatActivity  {
    public Button btnSubmit, btnLokasi;
    public EditText etNamaPengisi, etNamaKajian, etAlamat, etTempat;
    public DatePicker datePicker;
    public TimePicker timePicker;
    TextView jdlTempat, jdlAlamat,  hidLatitude, hidLongitude,imageName;
    Toolbar toolbar;
    public ImageButton imageButton;
    DrawerLayout drawerLayout;
    Bitmap bitmap;
    ImageView imageView;
    int success;

    private int PLACE_PICKER_REQ = 1;
    private int GALLERY_PICKER_REQ = 1;

    private static final String TAG = MainActivity.class.getSimpleName();

    // sesuiakan ip address laptop/pc atau ip emulator android bawaan 10.0.2.2
    private String UPLOAD_URL ="http://lab.kajianku.com/index.php/mobilekajian/upl_image";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_kajian);
        setAtributeTemplate();

        imageButton = (ImageButton) findViewById(R.id.uplFoto);
        btnSubmit = (Button)findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insDB();
                    }
        });

        etAlamat = (EditText) findViewById(R.id.et_alamat);
        etTempat = (EditText) findViewById(R.id.et_tempat);

        imageName =  (TextView) findViewById(R.id.tv_ImageName);
        jdlAlamat = (TextView) findViewById(R.id.jdl_alamat);
        jdlTempat = (TextView) findViewById(R.id.jdl_tempat);
        jdlAlamat.setVisibility(View.INVISIBLE);
        jdlTempat.setVisibility(View.INVISIBLE);

        hidLatitude = (TextView) findViewById(R.id.hid_latitude);
        hidLongitude = (TextView) findViewById(R.id.hid_longitude);

        btnLokasi = (Button)findViewById(R.id.btn_lokasi_kajian);
        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(BuatKajianActivity.this), PLACE_PICKER_REQ);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

//                Intent iMap = new Intent(BuatKajianActivity.this, SetLocationActivity.class);
//                startActivity(iMap);
            }
        });

        etNamaKajian = (EditText)findViewById(R.id.et_nama_kajian);
        etNamaPengisi = (EditText)findViewById(R.id.et_pengisi);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        datePicker.setSpinnersShown(true);
        datePicker.setCalendarViewShown(false);

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuatKajianActivity.this, "Bakal Upload gambar", Toast.LENGTH_SHORT).show();
            }
        });

        imageView = (ImageView) findViewById(R.id.imageView);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // menangkap hasil balikan dari Place Picker, dan menampilkannya pada TextView
        if (requestCode == PLACE_PICKER_REQ) {
            if (resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(data, this);
                jdlTempat.setVisibility(View.VISIBLE);
                jdlAlamat.setVisibility(View.VISIBLE);

                String s_Alamat = String.format(" %s",place.getAddress());
                etAlamat.setText(s_Alamat);
                String s_tempat = String.format(" %s",place.getName());
                etTempat.setText(s_tempat);

                String s_tvLatit = String.format(" %s",place.getLatLng().latitude);
                String s_tvLongit = String.format(" %s",place.getLatLng().longitude);
                hidLatitude.setText(s_tvLatit);
                hidLongitude.setText(s_tvLongit);

                etAlamat.setVisibility(View.VISIBLE);
                etTempat.setVisibility(View.VISIBLE);
            }
        }

    }



    public void setAtributeTemplate() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close
//        );
//        drawerLayout.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view) ;
//        navigationView.setNavigationItemSelectedListener(this);

    }

    String createStringWaktu (TimePicker timepicker){
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        String s_hour = Integer.toString(hour);
        String s_minute = Integer.toString(minute);
        String c_waktu =s_hour+":"+s_minute;
        return c_waktu;

    }

    String createStringTanggal (DatePicker datePicker)
    {
        String c_tanggal = null;
        int day  = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String s_day = Integer.toString(day);
        String s_month = Integer.toString(month);
        String s_year = Integer.toString(year);

        c_tanggal = s_year+"-"+s_month+"-"+s_day;
        return c_tanggal;
    }


    private void insDB() {
        String strNamakajian, strNamaPengisi, strAlamat, strTanggal, strWaktu, strTempat, strLatitude, strLongitude;
        strTanggal = createStringTanggal(datePicker);
        strWaktu = createStringWaktu(timePicker);
        String method = "BuatKajian";
        strNamakajian = etNamaKajian.getText().toString();
        strNamaPengisi = etNamaPengisi.getText().toString();
        strAlamat = etAlamat.getText().toString();
        strLatitude = hidLatitude.getText().toString();
        strLongitude = hidLongitude.getText().toString();
        strTempat = etTempat.getText().toString();

        BackgroundTaskKajian backgroundTaskKajian = new BackgroundTaskKajian(this);
        backgroundTaskKajian.execute(method, strNamakajian, strNamaPengisi, strAlamat, strTanggal, strWaktu, strLatitude, strLongitude, strTempat);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }


}
