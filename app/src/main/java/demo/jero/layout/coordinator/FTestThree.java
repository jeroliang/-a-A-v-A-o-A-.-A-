package demo.jero.layout.coordinator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.jero.R;

/**
 * Created by Jero on 2018/1/18 0018.
 */

public class FTestThree extends Fragment {
    private static final String TAG = "FTestTwo";
    TabLayout tabLayout;
    AppBarLayout appBarLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_test_three, null);
        tabLayout = view.findViewById(R.id.toolbar_tab);
        appBarLayout = view.findViewById(R.id.app_bar_layout);
        tabLayout.addTab(tabLayout.newTab().setText("asd"));
        tabLayout.addTab(tabLayout.newTab().setText("asd"));
        tabLayout.addTab(tabLayout.newTab().setText("asd"));
        tabLayout.addTab(tabLayout.newTab().setText("asd"));
        tabLayout.addTab(tabLayout.newTab().setText("asd"));
        tabLayout.addTab(tabLayout.newTab().setText("asd"));
        tabLayout.addTab(tabLayout.newTab().setText("asd"));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                appBarLayout.setExpanded(false);
                Log.i(TAG, "onTabSelected: "+tab.toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i(TAG, "onTabUnselected: ");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i(TAG, "onTabReselected: ");
            }
        });

        return view;
    }
}
