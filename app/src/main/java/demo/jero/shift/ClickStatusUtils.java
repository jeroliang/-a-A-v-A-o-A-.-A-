package demo.jero.shift;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import demo.jero.R;

/**
 * Created by Jero on 2017/9/29 0029.
 */

public class ClickStatusUtils {

    public static void ChangeStatus(@NonNull Activity context) {
        ChangeStatus(context, getRootView(context));
    }

    /**
     * 从Activity 获取 rootView 根节点
     *
     * @param context
     * @return 当前activity布局的根节点
     */
    public static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    private static void ChangeStatus(Activity context, @NonNull View root) {
        if (root == null) {
            return;
        }
        if (root instanceof TextView) {
            Log.i("ClickStatusUtils", "ChangeStatus: Text:" + root);
            TextView textView = (TextView) root;
            setStatus(context, textView);
        } else if (root instanceof Button) {
            Log.i("ClickStatusUtils", "ChangeStatus: Btn:" + root);
            Button btnView = (Button) root;
            setStatus(context, btnView);
        } else if (root instanceof ViewGroup) {
            Log.i("ClickStatusUtils", "ChangeStatus: Lay:" + root);
            ViewGroup viewGroup = (ViewGroup) root;
            setStatus(context, viewGroup);
            for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                ChangeStatus(context, viewGroup.getChildAt(i));
            }
        }
    }

    private static void setStatus(Activity context, View v) {
        StateListDrawable bg = new StateListDrawable();
        int[] drawableState = v.getDrawableState();
        if (drawableState.length != 0) {
            bg.addState(new int[]{android.R.attr.state_pressed}, context.getResources().getDrawable(R.drawable.click_pressed));
            bg.addState(new int[]{}, v.getBackground());
        }
        v.setBackgroundDrawable(bg);
    }

    /**
     * 代码生成选择器
     *
     * @param context   当前上下文
     * @param idNormal  默认图片id
     * @param idPressed 触摸时图片id
     * @param idFocused 获得焦点时图片id
     * @param idUnable  没有选中时图片id
     * @return
     */
    public static StateListDrawable newSelector(Context context, int idNormal, int idPressed, int idFocused, int idUnable) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = idNormal == -1 ? null : context.getResources().getDrawable(idNormal);
        Drawable pressed = idPressed == -1 ? null : context.getResources().getDrawable(idPressed);
        Drawable focused = idFocused == -1 ? null : context.getResources().getDrawable(idFocused);
        Drawable unable = idUnable == -1 ? null : context.getResources().getDrawable(idUnable);
        // View.PRESSED_ENABLED_STATE_SET
        bg.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        // View.ENABLED_FOCUSED_STATE_SET
        bg.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, focused);
        // View.ENABLED_STATE_SET
        bg.addState(new int[]{android.R.attr.state_enabled}, normal);
        // View.FOCUSED_STATE_SET
        bg.addState(new int[]{android.R.attr.state_focused}, focused);
        // View.WINDOW_FOCUSED_STATE_SET
        bg.addState(new int[]{android.R.attr.state_window_focused}, unable);
        // View.EMPTY_STATE_SET
        bg.addState(new int[]{}, normal);
        return bg;
    }
}
