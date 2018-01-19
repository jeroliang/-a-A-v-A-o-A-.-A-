package demo.jero;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import demo.jero.layout.coordinator.CoordinatorLayActivity;
import demo.jero.layout.coordinator.ScrollingActivity;
import demo.jero.layout.coordinator.ShopDemoAct;
import demo.jero.layout.ff.FActivity;

/**
 * Created by Jero on 2017/12/19 0019.
 */

public class LayDemoActivity extends ListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> list = initData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(LayDemoActivity.this, FActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(LayDemoActivity.this, CoordinatorLayActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(LayDemoActivity.this, ScrollingActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(LayDemoActivity.this, ShopDemoAct.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private List<String> initData() {
        List<String> list = new ArrayList<>();
        list.add(" /// FDemo");
        list.add(" /// CoordinatorLayAll");
        list.add(" /// ScrollingLay");
        list.add(" /// ShopHomeDemo");

        return list;
    }
}
