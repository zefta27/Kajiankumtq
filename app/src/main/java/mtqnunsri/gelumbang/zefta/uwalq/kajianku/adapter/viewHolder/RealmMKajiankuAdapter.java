package mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter.viewHolder;

/**
 * Created by root on 04/07/17.
 */
import android.content.Context;

import io.realm.MKajianKuRealmProxy;
import io.realm.RealmResults;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.adapter.RealmModelAdapter;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.model.MKajianKu;

public class RealmMKajiankuAdapter extends RealmModelAdapter<MKajianKu> {

    public RealmMKajiankuAdapter(Context context, RealmResults<MKajianKu> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}