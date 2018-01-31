package demo.jero.layout.coordinator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import demo.jero.R;

public class CoordinatorLayActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int oldSelect = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("ccc", "onSaveInstanceState: ");
        outState.putInt("tag", oldSelect);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        oldSelect = savedInstanceState.getInt("tag");
        Log.i("ccc", "onRestoreInstanceState: " + oldSelect);
        restoreFragment();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        //添加时候调用
        Log.i("ccc", "onAttachFragment: ");
    }

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        //v4 添加时候调用
        Log.i("ccc", "onAttachFragment: v4");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_lay);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            checkFragment(3);
        }
    }

    private void restoreFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(getFragmentManager().findFragmentByTag("" + oldSelect));
        transaction.show(getFragmentManager().findFragmentByTag("" + oldSelect));
        transaction.commit();
    }

    private void checkFragment(int tag) {
        if (tag == oldSelect)
            return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.show(getFragmentByTag(transaction, tag));
        if (oldSelect > 0)
            transaction.hide(getFragmentByTag(transaction, oldSelect));
        transaction.commit();
        oldSelect = tag;
    }

    private Fragment getFragmentByTag(FragmentTransaction transaction, int tag) {
        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentByTag("" + tag);
        if (null == fragment) {
            fragment = newFragment(tag);
            transaction.add(R.id.fragment, fragment, "" + tag);
        }
        transaction.addToBackStack(null);//回退栈  记录本次操作  back按键（onBackPressed（）方法）执行
        return fragment;
    }

    private Fragment newFragment(int tag) {
        Fragment fragment = null;
        switch (tag) {
            case 1:
                fragment = new FTestOne();
                break;
            case 2:
                fragment = new FTestTwo();
                break;
            case 3:
                fragment = new FTestThree();
                break;
            case 4:
                break;
            case 5:
                break;
        }
        return fragment;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.coordinator_lay, menu);
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
            Toast.makeText(this, "activity_settings", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_1) {
            // Handle the camera action
            checkFragment(1);
        } else if (id == R.id.nav_2) {
            checkFragment(2);
        } else if (id == R.id.nav_3) {
            checkFragment(3);
        } else if (id == R.id.nav_4) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
