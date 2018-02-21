package mtqnunsri.gelumbang.zefta.uwalq.kajianku.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import mtqnunsri.gelumbang.zefta.uwalq.kajianku.R;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter.KajianHomeAdapter;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.setget.Kajian;

public class CobaRecycle extends AppCompatActivity {
    final String TAG = "Kajianku";

    KajianHomeAdapter adapter;
    public RecyclerView mRecycleView;
    private List<Kajian> feedItemList = new ArrayList<Kajian>();
    ProgressBar progressBar;
    public LinearLayout ll;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba_recycle);
        ll = (LinearLayout) findViewById(R.id.ll);
        ll.setVisibility(View.VISIBLE);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        final String url = "http://lab.kajianku.com/index.php/mobilekajian/show_all_kajian_home";
        new AsyncHttpTask().execute(url);
    }
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;
            Integer result = 0;
            HttpURLConnection urlConnection = null;

            try {

                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();


                if (statusCode ==  200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }

                    parseResult(response.toString());
                    result = 1;
                }else{
                    result = 0;
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            //sembunyikan loading
            ll.setVisibility(View.GONE);

            if (result == 1) {
//                adapter = new KajianHomeAdapter(CobaRecycle.this, feedItemList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Log.e(TAG, "Failed to fetch data!");
            }
        }
    }

    private void parseResult(String result) {
        try {
            JSONArray response = new JSONArray(result);

            if (null == feedItemList) {
                feedItemList = new ArrayList<Kajian>();
            }

            for (int i = 0; i < response.length(); i++) {
                JSONObject post = response.optJSONObject(i);

                Kajian item = new Kajian();
                item.setJdl_kajian(post.optString("jdl_kajian"));
                item.setAlamat(post.optString("alamat"));
                item.setLatitude(post.optString("latitude"));
                item.setLongitude(post.optString("longitude"));
                item.setPengisi(post.optString("pengisi"));
                item.setTanggal(post.optString("tanggal"));
                item.setWaktu(post.optString("waktu"));

                feedItemList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
