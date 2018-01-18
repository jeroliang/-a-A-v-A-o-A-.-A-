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

        // 正常显示  add就显示
//        transaction.add(R.id.aa, ten);
//        transaction.add(R.id.cc, six);
//        transaction.commit();

        // remove 回销毁
//        transaction.add(R.id.aa, six);
//        transaction.add(R.id.aa, ten);
//        transaction.remove(ten);
//        transaction.commit();

        // replace 等于 remove + add  就是替换了  旧的会销毁
//        transaction.add(R.id.aa, six);
//        transaction.replace(R.id.aa, ten);
//        transaction.commit();

        //<editor-fold desc="隐藏  显示 ">
        // 如果在on create 方法里面发现不需要显示某个fragment  会不走create view 方法  所以不显示
        // hide 并不会隐藏  只是到了最下层
//        transaction.add(R.id.aa, new FSevenFragment());
//        transaction.add(R.id.aa, ten);
//        transaction.add(R.id.aa, six);
//        transaction.hide(six);
//        transaction.commit();

        // 显示  跟hide 成对
//        transaction = getFragmentManager().beginTransaction();
////        transaction.show(six);
//        transaction.remove(ten);
//        transaction.commit();
        //</editor-fold>


        //<editor-fold desc="拆分（解绑） 附上（绑定<如果销毁会新建，但是没有绑定的目标，所以只新建>）">
        // detach 是 拆分效果就是隐藏了  不会销毁
//        transaction.add(R.id.aa, six);
//        transaction.add(R.id.aa, ten);
//        transaction.detach(ten);
//        transaction.commit();

        // attach 可以跟detach 使用
//        transaction = getFragmentManager().beginTransaction();
//        transaction.attach(ten);
//        transaction.commit();
        //</editor-fold>
    }

    private void showFragment(FragmentTransaction transaction, int tag) {
        if (getFragmentByTag(tag).isHidden())
            transaction.hide(getFragmentByTag(tag));
        transaction.show(getFragmentByTag(tag));
    }

    private Fragment getFragmentByTag(int tag) {
        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentByTag("" + tag);
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
