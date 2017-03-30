package com.example.hasee.myapp.fragment.lookFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasee.myapp.R;

/**
 * Created by hasee on 2017/3/22.
 */
public class FilmFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.look_fra_film_fra,container,false);

        return ret;

    }

    @Override
    public String getFragmentTitle() {
        return "影视";
    }
}
