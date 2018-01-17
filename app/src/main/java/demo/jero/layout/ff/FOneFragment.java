package demo.jero.layout.ff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.jero.R;
import demo.jero.layout.BaseFragment;

/**
 * Created by Jero on 2018/1/15 0015.
 */

public class FOneFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_one_fragment, null);
    }
}
