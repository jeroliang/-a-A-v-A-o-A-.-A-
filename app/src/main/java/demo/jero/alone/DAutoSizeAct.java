package demo.jero.alone;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.jero.R;

/**
 * Created by Jero on 2017/12/27 0027.
 */

public class DAutoSizeAct extends Activity {
    private Button btn1, btn2;
    private AppCompatTextView tv1, tv2;
    private EditText et, et1, et2;


    private Button jia, jian;
    private AppCompatTextView vvv;
    private LinearLayout.LayoutParams vvvParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_auto_size_act);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        et1 = findViewById(R.id.et_1);
        et2 = findViewById(R.id.et_2);
        et = findViewById(R.id.et);

        jia = findViewById(R.id.jia);
        jian = findViewById(R.id.jian);
        vvv = findViewById(R.id.vvv);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btn1.setText(s);
                btn2.setText(s);
                tv1.setText(s);
                tv2.setText(s);
                et1.setText(s);
                et2.setText(s);

                vvv.setText(s);
            }
        });
        vvvParams = (LinearLayout.LayoutParams) vvv.getLayoutParams();
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvvParams.width += 10;
                vvvParams.height += 10;
                vvv.setLayoutParams(vvvParams);
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvvParams.width -= 10;
                vvvParams.height -= 10;
                vvv.setLayoutParams(vvvParams);
            }
        });


//        TextViewCompat.setAutoSizeTextTypeWithDefaults(btn2, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
//        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv2, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
//        TextViewCompat.setAutoSizeTextTypeWithDefaults(et2, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
//        TextViewCompat.setAutoSizeTextTypeWithDefaults(vvv, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);


//        int[] sizeList = new int[]{1, 9, 3, 5, 20, 18, 14, 16, 21};
//        TextViewCompat.setAutoSizeTextTypeUniformWithPresetSizes(btn2, sizeList, TypedValue.COMPLEX_UNIT_DIP);
//        TextViewCompat.setAutoSizeTextTypeUniformWithPresetSizes(tv2, sizeList, TypedValue.COMPLEX_UNIT_DIP);
//        TextViewCompat.setAutoSizeTextTypeUniformWithPresetSizes(et2, sizeList, TypedValue.COMPLEX_UNIT_DIP);
//        TextViewCompat.setAutoSizeTextTypeUniformWithPresetSizes(vvv, sizeList, TypedValue.COMPLEX_UNIT_DIP);

        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(btn2, 6, 30, 1,TypedValue.COMPLEX_UNIT_DIP);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(tv2, 6, 30, 1, TypedValue.COMPLEX_UNIT_DIP);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(et2, 6, 30, 1, TypedValue.COMPLEX_UNIT_DIP);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(vvv, 6, 30, 1, TypedValue.COMPLEX_UNIT_DIP);
    }
}
