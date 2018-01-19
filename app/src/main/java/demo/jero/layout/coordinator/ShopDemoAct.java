package demo.jero.layout.coordinator;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;

import demo.jero.R;

/**
 * Created by Jero on 2018/1/19 0019.
 */

public class ShopDemoAct extends Activity {
    TabLayout tabLayout;
    AppBarLayout appBarLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.f_test_three);
        tabLayout = findViewById(R.id.toolbar_tab);
        appBarLayout = findViewById(R.id.app_bar_layout);
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
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // double click
            }
        });
    }
}
