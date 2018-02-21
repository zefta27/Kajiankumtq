package mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ServiceCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mtqnunsri.gelumbang.zefta.uwalq.kajianku.Activity.DetailKajianActivity;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.R;

/**
 * Created by zefta on 27/02/16.
 */
public class KajianViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener{
        public TextView pengisi, jdl_kajian, alamat, latitude, longitude, tempat, tanggal, waktu ;
        public ImageView imageBackground;
        public Context context;
        public KajianViewHolder(View itemView, Context mContext){
            super(itemView);

            context = mContext;

            jdl_kajian = (TextView) itemView.findViewById(R.id.tv_jdlkajian);
            alamat = (TextView) itemView.findViewById(R.id.tv_alamat);
            latitude = (TextView) itemView.findViewById(R.id.tv_latitude);
            longitude = (TextView) itemView.findViewById(R.id.tv_longitude);
            tempat = (TextView) itemView.findViewById(R.id.tv_tempat);
            waktu = (TextView) itemView.findViewById(R.id.tv_waktu);
            pengisi = (TextView) itemView.findViewById(R.id.tv_pengisi);
            tanggal = (TextView) itemView.findViewById(R.id.tv_tanggal);
            imageBackground = (ImageView) itemView.findViewById(R.id.image_bg);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v){
//            Toast.makeText(context,"Ini Recylcerview", Toast.LENGTH_SHORT).show();
            Intent view = new Intent(context,DetailKajianActivity.class);
//            Intent view = new Intent("mtqnunsri.gelumbang.zefta.uwalq.kajianku.Activity.DetailKajianActivity");
            view.putExtra("pengisi",pengisi.getText().toString());
            view.putExtra("alamat",alamat.getText().toString());
            view.putExtra("jdl_kajian",jdl_kajian.getText().toString());
            view.putExtra("latitude",latitude.getText().toString());
            view.putExtra("longitude",longitude.getText().toString());
            view.putExtra("tempat",tempat.getText().toString());
            view.putExtra("waktu",waktu.getText().toString());
            view.putExtra("tanggal",tanggal.getText().toString());


            Context ctx = itemView.getContext();

            ctx.startActivity(view);
        }



}
