package mtqnunsri.gelumbang.zefta.uwalq.kajianku.realm;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;
import mtqnunsri.gelumbang.zefta.uwalq.kajianku.model.MKajianKu;

/**
 * Created by root on 04/07/17.
 */

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();

    }
    public static RealmController with (Fragment fragment)
    {
        if(instance == null)
        {
            instance = new RealmController(fragment.getActivity().getApplication());

        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }
    public static RealmController getInstance(){
        return instance;
    }

    public Realm getRealm()
    {
        return realm;
    }

    public void refresh()
    {
        realm.refresh();
    }
    public void clearAll(){
        realm.beginTransaction();
        realm.clear(MKajianKu.class);
        realm.commitTransaction();
    }

    public RealmResults<MKajianKu> getKajian(){
        return realm.where(MKajianKu.class).findAll();
    }

    public MKajianKu getKajian(String id)
    {
       return realm.where(MKajianKu.class).equalTo("id",id).findFirst();
    }

    public RealmResults<MKajianKu> queryedKajianku(){
        return realm.where(MKajianKu.class)
                .contains("jdlKajian", "JdlKajian 0")
                .or()
                .contains("pengisi","Pengisi")
                .findAll();
    }




}
