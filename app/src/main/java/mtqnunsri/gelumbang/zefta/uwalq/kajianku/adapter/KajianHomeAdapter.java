package mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mtqnunsri.gelumbang.zefta.uwalq.kajianku.Activity.CobaRecycle;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.Activity.MainActivity;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.R;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter.viewHolder.KajianViewHolder;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.fragment.OneFragment;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.setget.Kajian;


public class KajianHomeAdapter extends RecyclerView.Adapter<KajianViewHolder> {

    private List<Kajian> agt;
    private Context mContext;

    public KajianHomeAdapter(OneFragment context, List<Kajian> agt) {
        this.agt = agt;
        this.mContext = context.getActivity();
    }

    @Override
    public KajianViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_fragment, false);
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_home_fragment,viewGroup, false);
        ;
//        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_fragment,viewGroup,true);
        KajianViewHolder mh = new KajianViewHolder(v, mContext);
        return mh;
    }
    @Override
    public void onBindViewHolder(KajianViewHolder feedListRowHolder, int i) {

        Kajian item = agt.get(i);

        feedListRowHolder.jdl_kajian.setText(item.getJdl_kajian());
        feedListRowHolder.alamat.setText(item.getAlamat());
        feedListRowHolder.latitude.setText(item.getLatitude());
        feedListRowHolder.longitude.setText(item.getLongitude());
        feedListRowHolder.pengisi.setText(item.getPengisi());
        feedListRowHolder.tempat.setText(item.getTempat());
        feedListRowHolder.tanggal.setText(item.getTanggal());
        feedListRowHolder.waktu.setText(item.getWaktu());

        if (item.getImageBackground() != null) {
            Glide.with(mContext)
                    .load("http://api.androidhive.info/images/realm/1.png")
                    .asBitmap()
                    .fitCenter()
                    .into(feedListRowHolder.imageBackground);
        }
    }

    @Override
    public int getItemCount() {
        return (null != agt ? agt.size() : 0);
    }

   }