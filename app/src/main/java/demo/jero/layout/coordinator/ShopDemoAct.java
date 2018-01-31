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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
