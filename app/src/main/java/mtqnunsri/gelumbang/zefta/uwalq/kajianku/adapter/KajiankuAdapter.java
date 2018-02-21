package mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.zip.Inflater;

import io.realm.Realm;
import io.realm.RealmResults;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.R;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter.viewHolder.RealmMKajiankuAdapter;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.model.MKajianKu;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.realm.RealmController;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.utility.Prefs;

/**
 * Created by root on 04/07/17.
 */

public class KajiankuAdapter extends RealmRecyclerViewAdapter<MKajianKu>{
    public Context context;
    private Realm realm;
    private LayoutInflater inflater;
    public KajiankuAdapter( Context context1) {
        this.context = context1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_kajian, parent, false);

        return new CardViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        realm = RealmController.getInstance().getRealm();

        final MKajianKu mKajianKu = getItem(position);

        final CardViewHolder holder = (CardViewHolder) viewHolder;

        holder.txtJdlKajian.setText(mKajianKu.getJdl_kajian());
        holder.txtPengisi.setText(mKajianKu.getPengisi());
        holder.txtAlamat.setText(mKajianKu.getAlamat());
        holder.txtTempat.setText(mKajianKu.getTempat());
        holder.txtLatitude.setText(mKajianKu.getLatitude());
        holder.txtLongitude.setText(mKajianKu.getLongitude());
        holder.txtWaktu.setText(mKajianKu.getWaktu());
        holder.txtTanggal.setText(mKajianKu.getTanggal());


        if (mKajianKu.getImageUrl() != null) {
            Glide.with(context)
                    .load(mKajianKu.getImageUrl().replace("https", "http"))
                    .asBitmap()
                    .fitCenter()
                    .into(holder.imageBackground);
        }



        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                RealmResults<MKajianKu> results = realm.where(MKajianKu.class).findAll();

                MKajianKu kajianKu = results.get(position);
                String judul = kajianKu.getJdl_kajian();

                realm.beginTransaction();

                results.remove(position);
                realm.commitTransaction();

                if (results.size() == 0)
                {
                    Prefs.with(context).setPreLoad(false);
                }
                notifyDataSetChanged();

                Toast.makeText(context, judul + " is removed from Realm", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter()!=null)
        {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView card;
        public TextView txtJdlKajian, txtPengisi, txtAlamat, txtTempat, txtTanggal, txtWaktu, txtLatitude, txtLongitude;
        public ImageView imageBackground;
        public CardViewHolder(View itemView, Context mContext) {

            super(itemView);

            context =  mContext;
            card = (CardView) itemView.findViewById(R.id.card_my_kajian);
            txtJdlKajian = (TextView) itemView.findViewById(R.id.my_kajian_judul);
            txtPengisi = (TextView) itemView.findViewById(R.id.my_kajian_pengisi);
            txtAlamat = (TextView) itemView.findViewById(R.id.my_kajian_alamat);
            txtTempat = (TextView) itemView.findViewById(R.id.my_kajian_tempat);
            txtTanggal = (TextView) itemView.findViewById(R.id.my_kajian_tanggal);
            txtWaktu = (TextView) itemView.findViewById(R.id.my_kajian_waktu);
            txtLatitude = (TextView) itemView.findViewById(R.id.my_kajian_latitude);
            txtLongitude = (TextView) itemView.findViewById(R.id.my_kajian_longitude);
            imageBackground = (ImageView) itemView.findViewById(R.id.image_bg);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View content = inflater.inflate(R.layout.edit_item, null);
            final TextView detailJdlKajian =  (TextView) content.findViewById(R.id.ei_jdl_kajian);
            final TextView detailPengisi = (TextView) content.findViewById(R.id.ei_pengisi);
            final TextView detailAlamat = (TextView) content.findViewById(R.id.ei_alamat);
            TextView detailTempat = (TextView) content.findViewById(R.id.ei_tempat);
            TextView detailTanggal = (TextView) content.findViewById(R.id.ei_tanggal);
            TextView detailWaktu = (TextView) content.findViewById(R.id.ei_waktu);

            final String sAlamat = txtAlamat.getText().toString();
            final String sJdlKajian = txtJdlKajian.getText().toString();
            final String sPengisi = txtPengisi.getText().toString();
            final String sTempat = txtTempat.getText().toString();
            final String sTanggal = txtTanggal.getText().toString();
            final String sWaktu = txtWaktu.getText().toString();

            detailJdlKajian.setText("Judul Kajian : "+sJdlKajian);
            detailPengisi.setText("Pengisi : "+sPengisi);
            detailAlamat.setText("Alamat : "+sAlamat);
            detailTempat.setText("Tempat : "+sTempat);
            detailTanggal.setText("Tanggal :"+sTanggal);
            detailWaktu.setText("Waktu : "+sWaktu);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(content).setTitle("Detail Kajian")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            detailJdlKajian.setText("Judul Kajian : "+sJdlKajian);
//                            detailPengisi.setText("Pengisi : "+sPengisi);
//                            detailAlamat.setText("Alamat : "+sAlamat);
                        }
                    })
                    .setPositiveButton("Ingatkan saya", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context, "go to Calender", Toast.LENGTH_SHORT).show();
                        String pTanggal = sTanggal;
                        String[] parts = pTanggal.split("-");
                        int iTahun = Integer.parseInt(parts[0]);
                        int iBulan = Integer.parseInt(parts[1]);
                        int iTanggal = Integer.parseInt(parts[2]);

                        String pWaktu = sWaktu;
                        String[] part = pWaktu.split(":");
                        int iJam = Integer.parseInt(part[0]);
                        int iMenit = Integer.parseInt(part[1]);

                        Calendar beginTime = Calendar.getInstance();
                        beginTime.set(iTahun, iBulan-1, iTanggal, iJam, iMenit);
//                        beginTime.set
                        Calendar endTime = Calendar.getInstance();
                        endTime.set(iTahun, iBulan-1, iTanggal, iJam+2, iMenit);
                        Intent intent = new Intent(Intent.ACTION_INSERT)
                                .setData(CalendarContract.Events.CONTENT_URI)
                                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                                .putExtra(CalendarContract.Events.TITLE, sJdlKajian)
                                .putExtra(CalendarContract.Events.DESCRIPTION, "Pengisi : "+sPengisi)
                                .putExtra(CalendarContract.Events.EVENT_LOCATION, sAlamat)
                                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                                .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
                        context.startActivity(intent);
                    }
            }));
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}