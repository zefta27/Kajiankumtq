package mtqnunsri.gelumbang.zefta.uwalq.kajianku.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * Created by root on 03/06/17.
 */


public class OneFragment extends Fragment{
    final String TAG = "Kajianku";

    KajianHomeAdapter adapter;
    public RecyclerView mRecycleView;
    private List<Kajian> feedItemList = new ArrayList<Kajian>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final String url = "http://lab.kajianku.com/index.php/mobilekajian/show_all_kajian_home";
//              View inflate =  inflater.inflate(R.layout.fragment_one, container, true);
        inflater = LayoutInflater.from(container.getContext());
        View inflate = inflater.inflate(R.layout.fragment_one, container, false);
//        if(inflate.getParent()!=null)
//            ((ViewGroup)tv.getParent()).removeView(tv); // <- fix
//        layout.addView(tv);

        new AsyncHttpTask().execute(url);

      mRecycleView = (RecyclerView) inflate.findViewById(R.id.rcFragmentOne);
//        MyAdapter adapter = new MyAdapter(new String[]{"test one", "test two", "test three", "test four", "test five" , "test six" , "test seven"});
        mRecycleView.setAdapter(adapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
//        loadJSON();
        return inflate;

        
    }
//    private void loadJSON(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://lab.kajianku.com/index.php/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RequestInterface request = retrofit.create(RequestInterface.class);
//        Call<JSONResponse> call = request.getJSON();
//        call.enqueue(new Callback<JSONResponse>() {
//            @Override
//            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
//
//                JSONResponse jsonResponse = response.body();
//                feedItemList = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
////                adapter = new KajianHomeAdapter(OneFragment.this, feedItemList);
//                mRecycleView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<JSONResponse> call, Throwable t) {
//                Log.d("Error",t.getMessage());
//            }
//        });
//    }

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
            if (result == 1) {
                adapter = new KajianHomeAdapter(OneFragment.this, feedItemList);
//                adapter = (Adapter) new KajianHomeAdapter();
//                mRecycleView.setAdapter(adapter);
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
                String url_changed = post.optString("image_foto");

                item.setImageBackground(url_changed);

                feedItemList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
