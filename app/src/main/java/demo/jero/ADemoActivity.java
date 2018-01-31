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

import demo.jero.alone.DAutoSizeAct;
import demo.jero.alone.DStaticLayout;
import demo.jero.alone.SaveActivity;

/**
 * Created by Jero on 2017/12/19 0019.
 */

public class ADemoActivity extends ListActivity {
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
                        startActivity(new Intent(ADemoActivity.this, DStaticLayout.class));
                        break;
                    case 1:
                        startActivity(new Intent(ADemoActivity.this, DAutoSizeAct.class));
                        break;
                    case 2:
                        startActivity(new Intent(ADemoActivity.this, SaveActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private List<String> initData() {
        List<String> list = new ArrayList<>();
        list.add(" /// StaticLayout");
        list.add(" /// AutoSizeText");
        list.add(" /// SaveActivity");

        return list;
    }
}
