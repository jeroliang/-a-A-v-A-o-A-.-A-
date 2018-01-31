package demo.jero.layout.coordinator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import demo.jero.R;

/**
 * Created by Jero on 2018/1/18 0018.
 */

public class FTestOne extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Fragment可以添加自己的MenuItem到Activity的ActionBar或者可选菜单中
        // 会在原有后面添加  id不可以一样 （无法处理点击，可以显示）
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_test_one, null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_lay, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getActivity(), "FragmentONE_" + item.getItemId(), Toast.LENGTH_SHORT).show();
        return true;
    }

}
