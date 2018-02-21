package mtqnunsri.gelumbang.zefta.uwalq.kajianku.Activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.R;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter.KajiankuAdapter;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter.viewHolder.RealmMKajiankuAdapter;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.model.MKajianKu;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.realm.RealmController;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.utility.Prefs;

public class MyKajianActivity extends AppCompatActivity {
    Toolbar toolbar;
    private KajiankuAdapter adapter;
    private Realm realm;
    private LayoutInflater inflater;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        this.realm = RealmController.with(this).getRealm();

        setContentView(R.layout.activity_my_kajian);
        toolbar =  (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recycler = (RecyclerView) findViewById(R.id.recycler); 
        setupRecycler();

        RealmController.with(this).refresh();
        setRealmAdapter(RealmController.with(this).getKajian());
        Toast.makeText(this, "Tekan kajian agak lama untuk menghapus kajian", Toast.LENGTH_SHORT).show();

    }

    private void setRealmAdapter(RealmResults<MKajianKu> kajian) {
        RealmMKajiankuAdapter realmAdapter = new RealmMKajiankuAdapter(this.getApplicationContext(), kajian, true);
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
    }
    private void setupRecycler() {
        recycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        adapter = new KajiankuAdapter(this);
        recycler.setAdapter(adapter);
    }
}
