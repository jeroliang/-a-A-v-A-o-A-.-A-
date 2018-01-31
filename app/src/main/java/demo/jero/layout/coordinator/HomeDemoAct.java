package demo.jero.layout.coordinator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;

import demo.jero.R;

/**
 * Created by Jero on 2018/1/19 0019.
 */

public class HomeDemoAct extends Activity {
    TabLayout tabLayout;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_test_four);
    }
}
