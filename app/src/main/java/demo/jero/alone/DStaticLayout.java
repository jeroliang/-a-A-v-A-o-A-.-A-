package demo.jero.alone;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;

/**
 * Created by Jero on 2017/12/19 0019.
 */

public class DStaticLayout extends Activity {
    String content = "爱情不停站 想开往地老天荒 需要多勇敢。把一个人的温暖 转移到另一个的胸膛 " +
            "才拒绝做爱清代罪的羔羊 每一次 系爱哦始于庆阳 阳光再生上流转 让所有孽障被原谅 ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new StaticDemoView(this));
    }

    private class StaticDemoView extends View {

        public StaticDemoView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.MAGENTA);
            textPaint.setTextSize(60);
            textPaint.setAlpha(199);
            StaticLayout staticLayout;
            /*
            可以设置
            1. 开始位置 结束长度
            2. 画笔 和范围宽度
            3. 几倍行距 和行距附加值
            4. 对齐方式
            5. 省略标记开始位置
             */
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                StaticLayout.Builder builder = StaticLayout.Builder.obtain(content, 0, 20, textPaint,
                        canvas.getWidth() - 100);
                staticLayout = builder.build();
            } else {
                staticLayout = new StaticLayout(
                        content,
                        textPaint,
                        canvas.getWidth() - 100,
                        Layout.Alignment.ALIGN_CENTER,
                        1,
                        6,
                        false);
            }
            staticLayout.draw(canvas);
        }
    }
}
