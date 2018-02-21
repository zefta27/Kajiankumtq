package mtqnunsri.gelumbang.zefta.uwalq.kajianku;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by acer on 08/05/2017.
 */

public class BackgroundTaskKajian extends AsyncTask<String, Void, String> {
    Context ctx;
    public BackgroundTaskKajian(Context ctx){
        this.ctx = ctx;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String ins_halte = "http://lab.kajianku.com/index.php/mobilekajian/ins_kajian"
                ;
        String method = params[0];
        if(method.equals("BuatKajian"))
        {
            String s_jdlkajian = params[1];
            String s_pengisi = params[2];
            String s_alamat = params[3];
            String s_tanggal = params[4];
            String s_waktu = params[5];
            String s_latitude = params[6];
            String s_longitude = params[7];
            String s_tempat = params[8];

            try {
                URL url = new URL(ins_halte);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("jdl_kajian","UTF-8") +"="+ URLEncoder.encode(s_jdlkajian,"UTF-8")+"&"+
                        URLEncoder.encode("pengisi","UTF-8") +"="+ URLEncoder.encode(s_pengisi,"UTF-8")+"&"+
                        URLEncoder.encode("alamat","UTF-8") +"="+ URLEncoder.encode(s_alamat,"UTF-8")+"&"+
                        URLEncoder.encode("tanggal","UTF-8") +"="+ URLEncoder.encode(s_tanggal,"UTF-8")+"&"+
                        URLEncoder.encode("waktu","UTF-8") +"="+ URLEncoder.encode(s_waktu,"UTF-8")+"&"+
                        URLEncoder.encode("tempat","UTF-8") +"="+ URLEncoder.encode(s_tempat,"UTF-8")+"&"+
                        URLEncoder.encode("latitude","UTF-8") +"="+ URLEncoder.encode(s_latitude,"UTF-8")+"&"+
                        URLEncoder.encode("longitude","UTF-8") +"="+ URLEncoder.encode(s_longitude,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Kajian Anda Berhasil Didaftarkan";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result, Toast.LENGTH_LONG).show();

    }
}
