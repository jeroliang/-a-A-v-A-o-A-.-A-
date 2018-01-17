package demo.jero.layout.ff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.jero.layout.BaseFragment;

/**
 * Created by Jero on 2018/1/15 0015.
 */

public class FSixFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("FSixFragment");
        textView.setBackgroundColor(0x78456166);
        return textView;
    }
}
