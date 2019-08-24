package ysn.com.view.roundrect;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

/**
 * @Author yangsanning
 * @ClassName IView
 * @Description 一句话概括作用
 * @Date 2019/8/24
 * @History 2019/8/24 author: description:
 */
public interface IView  {

    void initAttrs(Context context, @Nullable AttributeSet attrs);

    void initPaint();

    void initData();
}
