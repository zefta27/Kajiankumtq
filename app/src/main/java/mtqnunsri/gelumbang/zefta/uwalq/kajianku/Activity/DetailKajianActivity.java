package mtqnunsri.gelumbang.zefta.uwalq.kajianku.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.R;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter.viewHolder.RealmMKajiankuAdapter;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.model.MKajianKu;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.peta.SelectKajianActivity;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.realm.RealmController;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.utility.Prefs;

public class DetailKajianActivity extends AppCompatActivity {
    TextView tvPengisi, tvAlamat, tvJdlKajian, tvLatitude, tvLongitude, tvTempat, tvWaktu, tvTanggal;
    String strPengisi, strAlamat, strJdlKajian, strLatitude, strLongitude, strTempat, strWaktu, strTanggal;
    Toolbar toolbar;
    Button btnLocatioMapDetail, btnSaveKajian;

    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);



//        realm = Realm.getDefaultConfiguration();

        setContentView(R.layout.activity_detail_kajian);

        iniateElement();
        setAtributeTemplate();
        tvPengisi.setText(strPengisi);
        tvJdlKajian.setText(strJdlKajian);
        tvTempat.setText(strTempat);
        tvAlamat.setText(strAlamat);
        tvTanggal.setText(strTanggal);
        tvWaktu.setText(strWaktu);


        btnLocatioMapDetail = (Button) findViewById(R.id.btnMapDetailLocation);
        btnLocatioMapDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strLongitude.replaceAll("%20","+");
                strLatitude.replaceAll("%20","+");
                strAlamat.replaceAll("%20","+");
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("geo")
                        .path(strLatitude+","+strLongitude)
                        .query(strAlamat);
//                Uri addressUri = builder.build();
                Uri addressUri = Uri.parse("geo:"+strLatitude+","+strLongitude+"?q="+strAlamat);
//                String sUri = ad
//                Toast.makeText(DetailKajianActivity.this, sUri, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(addressUri);

                if (intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(intent);
                }
//                Intent intent = new Intent(DetailKajianActivity.this, SelectKajianActivity.class);
//                intent.putExtra("latitude",strLatitude);
//                intent.putExtra("longitude",strLongitude);
//                intent.putExtra("alamat",strAlamat);
//                startActivity(intent);
            }
        });



        // refresh the realm instance
        RealmController.with(this).refresh();
        btnSaveKajian = (Button)findViewById(R.id.btnSavekajian);
        btnSaveKajian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MKajianKu mKajianKu = new MKajianKu();
                mKajianKu.setId((int) (RealmController.getInstance().getKajian().size() + System.currentTimeMillis()));
                mKajianKu.setJdl_kajian(strJdlKajian);
                mKajianKu.setPengisi(strPengisi);
                mKajianKu.setAlamat(strAlamat);
                mKajianKu.setTempat(strTempat);
                mKajianKu.setLatitude(strLatitude);
                mKajianKu.setLongitude(strLongitude);
                mKajianKu.setTanggal(strTanggal);
                mKajianKu.setWaktu(strWaktu);

                realm.beginTransaction();
                realm.copyToRealm(mKajianKu);
                realm.commitTransaction();

                Toast.makeText(DetailKajianActivity.this, "Kajian Berhasil Disimpan!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setAtributeTemplate() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
    private void iniateElement() {
        strPengisi = getIntent().getExtras().getString("pengisi");
        tvPengisi = (TextView) findViewById(R.id.tvPengisi);

        strJdlKajian = getIntent().getExtras().getString("jdl_kajian");
        tvJdlKajian = (TextView) findViewById(R.id.tvJudulKajian);

        strPengisi = getIntent().getExtras().getString("pengisi");
        tvPengisi = (TextView) findViewById(R.id.tvPengisi);

        strAlamat = getIntent().getExtras().getString("alamat");
        tvAlamat = (TextView) findViewById(R.id.tvAlamat);

        strTempat = getIntent().getExtras().getString("tempat");
        tvTempat = (TextView) findViewById(R.id.tvTempat);

        strPengisi = getIntent().getExtras().getString("pengisi");
        tvPengisi = (TextView) findViewById(R.id.tvPengisi);

        strAlamat = getIntent().getExtras().getString("alamat");
        tvAlamat = (TextView) findViewById(R.id.tvAlamat);

        strTanggal = getIntent().getExtras().getString("tanggal");
        tvTanggal = (TextView) findViewById(R.id.tvTanggal);

        strWaktu = getIntent().getExtras().getString("waktu");
        tvWaktu = (TextView) findViewById(R.id.tvWaktu);

        strLatitude = getIntent().getExtras().getString("latitude");
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);

        strLongitude = getIntent().getExtras().getString("longitude");
        tvLongitude = (TextView) findViewById(R.id.tvlongitude);






    }

}
