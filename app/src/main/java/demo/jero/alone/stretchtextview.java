package demo.jero.alone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import demo.jero.R;

/**
 * 创建日期: 16/3/2 下午11:29
 * 作者:wanghao
 * 描述:
 * 让字体根据 显示的宽度 高度 来让字体大小缩小,如果可以展示开不用缩小
 * 1,先限定宽度,如果字体宽度大于限定宽度,让字体换行
 * 2,换行后获取他的高度,让换行的高度和 限定的高度对比,不断地轮训让字体减1
 * 3,随着字体不断的减小,当宽度,高度都小于限定的宽高的时候 获取该字体大小并显示
 */
@SuppressLint("AppCompatCustomView")
public class stretchtextview extends TextView {
    private static final String TAG = "stretchtextview";
    private CharSequence textContent;//textview的内容
    private float mSpacingMult = 1.0f;//StaticLayout
    private float mSpacingAdd = 0.0f;//StaticLayout
    private int LayoutAlign = 0;//StaticLayout 的LayoutAlign
    private Layout.Alignment alignment;//StaticLayout 的LayoutAlign  设置
    private float minSize = 1.0f;//默认textsize的最小值
    private int widthL;//限制字体的宽度
    private int heightL;//限制字体的高度
    private float cacheTargetsize;//目标字体大小 缓存
    private float newSize = 0;//目标字体大小 最终版

    public stretchtextview(Context context) {
        this(context, null);
    }

    public stretchtextview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public stretchtextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.stretchtextview);//获取StaticLayout的三个值
        LayoutAlign = a.getInteger(R.styleable.stretchtextview_layoutalign, 0);
        mSpacingMult = a.getInteger(R.styleable.stretchtextview_mSpacingMult, 1);
        mSpacingAdd = a.getInteger(R.styleable.stretchtextview_mSpacingAdd, 0);
        switch (LayoutAlign) {
            case 0:
                alignment = Layout.Alignment.ALIGN_NORMAL;
                break;
            case 1:
                alignment = Layout.Alignment.ALIGN_CENTER;
                break;
            case 2:
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
                break;
            default:
                break;
        }
        a.recycle();
        textContent = getText();
    }

    /**
     * 获取xml里面字体宽高
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        textContent = getText();
        //获取字体打算展示的宽高
        widthL = (w - oldw) - getCompoundPaddingLeft() - getCompoundPaddingRight();
        heightL = (h - oldh) - getCompoundPaddingBottom() - getCompoundPaddingTop();
        setNewText(widthL, heightL);
        Log.i(TAG, "onSizeChanged: " + w + "//" + h + "//old " + oldw + "//" + oldh);
    }

    /**
     * 更改字体大小
     *
     * @param newTextSize
     */
    public void changeTextSize(float newTextSize) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);
    }

    /**
     * 根据这些参数来配置字体大小
     * 自定义view的时候直接继承 stretchtextview ，直接调用该方法。不用再写 drawtext了。
     * canvas是必须写上的不然不管用
     *
     * @param text
     * @param width
     * @param height
     * @param paint
     * @param alignment
     * @param canvas
     * @return
     */
    public float setStretchTextView(String text, int width, int height, TextPaint paint, Layout.Alignment alignment, Canvas canvas) {
        cacheTargetsize = paint.getTextSize();
        int cacheHeight = getHeightByWidth(text, alignment, paint, width, cacheTargetsize).getHeight();
        while (cacheHeight > height && cacheTargetsize > minSize) {
            cacheTargetsize = Math.max(cacheTargetsize - 1, minSize);
            cacheHeight = getHeightByWidth(text, alignment, paint, width, cacheTargetsize).getHeight();
        }
        if (canvas != null) {
            getHeightByWidth(text, alignment, paint, width, cacheTargetsize).draw(canvas);
        }
        return cacheTargetsize;
    }

    /**
     * 根据宽高 设置字体大小
     *
     * @param widthL
     * @param heightL
     */
    private void setNewText(int widthL, int heightL) {
        this.widthL = widthL;
        this.heightL = heightL;
        if (widthL >= 0 && heightL >= 0 && textContent != null && textContent.length() > 0) {
            TextPaint tPaint = new TextPaint(getPaint());
            newSize = setNewSize(textContent.toString().trim(), widthL, heightL, tPaint, alignment);
        }
        changeTextSize(newSize);
    }


    private float setNewSize(String text, int width, int height, TextPaint paint, Layout.Alignment alignment) {
        cacheTargetsize = setStretchTextView(text, width, height, paint, alignment, null);
        return cacheTargetsize;
    }

    /**
     * 宽度一定的时候,让文字换行 获取他的StaticLayout  (为了让他在自定义view的时候画出字体,获取换行之后的高度)
     *
     * @param source
     * @param align
     * @param paint
     * @param width
     * @param textSize
     * @return
     */
    private StaticLayout getHeightByWidth(CharSequence source, Layout.Alignment align, TextPaint paint, int width, float textSize) {
        paint.setTextSize(textSize);
        mSpacingMult = mSpacingMult < 1.0 ? 1.0f : mSpacingMult;
        StaticLayout layout = new StaticLayout(source, paint, width, align, mSpacingMult, mSpacingAdd, true);
        return layout;
    }

}