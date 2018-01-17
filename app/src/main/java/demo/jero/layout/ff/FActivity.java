package demo.jero.layout.ff;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import demo.jero.R;

/**
 * Created by Jero on 2018/1/15 0015.
 */

public class FActivity extends AppCompatActivity {
    private static final String TAG = "FActivity";

    Fragment one, two, three, four, five, six, seven, eight, nine, ten;
    int oldSelect = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.fragment_act);
        showFragment(oldSelect);
    }

    private void showFragment(int tag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        ten = new FTenFragment();
        six = new FSixFragment();

        transaction.add(R.id.aa, ten);
        transaction.add(R.id.cc, new FTenFragment());
        transaction.commit();

        one = new FOneFragment();

        transaction = getFragmentManager().beginTransaction();
        transaction.remove(ten);
        transaction.commit();
//        transaction.add(R.id.aa, one, "aa");
    }

    private Fragment getFragmentByTag(int tag) {
        Fragment fragment = null;
        FragmentManager manager = getFragmentManager();
        if (tag > 2)
            fragment = manager.findFragmentByTag("" + tag);
        else if (tag == 1)
            fragment = manager.findFragmentById(R.id.ee);
        else if (tag == 2)
            fragment = manager.findFragmentById(R.id.bb);

        if (null == fragment) {
            fragment = newFragment(tag);
            manager.beginTransaction().add(fragment, "" + tag);
        }
        return fragment;
    }

    private Fragment newFragment(int tag) {
        Fragment fragment = null;
        switch (tag) {
            case 1:
                fragment = new FOneFragment();
                break;
            case 2:
                fragment = new FTwoFragment();
                break;
            case 3:
                fragment = new FThreeFragment();
                break;
            case 4:
                fragment = new FFourFragment();
                break;
            case 5:
                fragment = new FFiveFragment();
                break;
            case 6:
                fragment = new FSixFragment();
                break;
            case 7:
                fragment = new FSevenFragment();
                break;
            case 8:
                fragment = new FEightFragment();
                break;
            case 9:
                fragment = new FNineFragment();
                break;
            case 10:
                fragment = new FTenFragment();
                break;
        }
        return fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fragment_lay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

}
