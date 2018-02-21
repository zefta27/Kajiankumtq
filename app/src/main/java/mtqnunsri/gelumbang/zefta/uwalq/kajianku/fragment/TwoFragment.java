package mtqnunsri.gelumbang.zefta.uwalq.kajianku.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mtqnunsri.gelumbang.zefta.uwalq.kajianku.R;

/**
 * Created by root on 03/06/17.
 */

public class TwoFragment extends Fragment {
    public TwoFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }
}
