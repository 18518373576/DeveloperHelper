package com.example.developerandroidx.ui.widget;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.FunctionItemBean;
import com.example.developerandroidx.ui.widget.actionBar.ActionBarActivity;
import com.example.developerandroidx.ui.widget.calendarView.CalendarActivity;
import com.example.developerandroidx.ui.widget.canvas.CanvasActivity;
import com.example.developerandroidx.ui.widget.gesturePassword.GesturePasswordActivity;
import com.example.developerandroidx.ui.widget.mathematicalCurve.MathematicalCurveActivity;
import com.example.developerandroidx.ui.widget.navigationView.NavigationViewActivity;
import com.example.developerandroidx.ui.widget.waterDrop.WaterDropActivity;
import com.example.developerandroidx.ui.widget.webView.WebViewActivity;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.ArrayList;
import java.util.List;

public class WidgetViewModel extends BaseViewModel<List<FunctionItemBean>> {

    private List<FunctionItemBean> functionList;

    @Override
    protected void initData(@Nullable String... param) {
        //无数据的时候,设置数据,有数据的时候直接取数据,不再进行设置
        //主要作用就是屏幕切换保存数据,无屏幕切换可忽略此判断
        if (functionList == null)
            setData(initData());
    }

    private List<FunctionItemBean> initData() {
        functionList = new ArrayList<>();
        functionList.add(new FunctionItemBean("ActionBar", R.mipmap.icon_action, RouteUtil.getDestination(ActionBarActivity.class)));
        functionList.add(new FunctionItemBean("RecyclerView", R.mipmap.icon_recycler, ""));
        functionList.add(new FunctionItemBean("CardView", R.mipmap.icon_card_view, ""));
        functionList.add(new FunctionItemBean("WebView", R.mipmap.icon_web_view, RouteUtil.getDestination(WebViewActivity.class)));
        functionList.add(new FunctionItemBean("CalendarView", R.mipmap.icon_calendar, RouteUtil.getDestination(CalendarActivity.class)));
        functionList.add(new FunctionItemBean("Custom Toast", R.mipmap.icon_toast, ""));
        functionList.add(new FunctionItemBean("JSoup", R.mipmap.icon_html, ""));
        functionList.add(new FunctionItemBean("Canvas", R.mipmap.icon_canvas, RouteUtil.getDestination(CanvasActivity.class)));
        functionList.add(new FunctionItemBean("Mathematical Curve", R.mipmap.icon_curve, RouteUtil.getDestination(MathematicalCurveActivity.class)));
        functionList.add(new FunctionItemBean("Navigation", R.mipmap.icon_navigation, RouteUtil.getDestination(NavigationViewActivity.class)));
        functionList.add(new FunctionItemBean("手势密码", R.mipmap.icon_gesture, RouteUtil.getDestination(GesturePasswordActivity.class)));
        functionList.add(new FunctionItemBean("CoordinatorLayout", R.mipmap.icon_coordinator, ""));
        functionList.add(new FunctionItemBean("水滴", R.mipmap.icon_water_drop, RouteUtil.getDestination(WaterDropActivity.class)));
        functionList.add(new FunctionItemBean("上升的气球", R.mipmap.icon_balloon, ""));
        functionList.add(new FunctionItemBean("滑块进度条", R.mipmap.icon_sliding_block, ""));
        functionList.add(new FunctionItemBean("LoadingView", R.mipmap.icon_loading, ""));
        functionList.add(new FunctionItemBean("圆形进度条", R.mipmap.icon_progress, ""));
        functionList.add(new FunctionItemBean("Flutter", R.mipmap.icon_flutter, ""));

        return functionList;
    }

    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return getData();
    }
}