package com.example.developerandroidx.ui.widget;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.developerandroidx.R;
import com.example.developerandroidx.bean.FunctionItemBean;
import com.example.developerandroidx.ui.widget.actionBar.ActionBarActivity;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.ArrayList;
import java.util.List;

public class WidgetViewModel extends ViewModel {

    private MutableLiveData<List<FunctionItemBean>> mList;
    private List<FunctionItemBean> functionList;

    public WidgetViewModel() {
        mList = new MutableLiveData<>();
        initData();
        mList.setValue(functionList);
    }

    private void initData() {
        functionList = new ArrayList<>();
        functionList.add(new FunctionItemBean("ActionBar", R.mipmap.icon_action, RouteUtil.getDestination(ActionBarActivity.class)));
        functionList.add(new FunctionItemBean("ToolBar", R.mipmap.icon_tool_bar, RouteUtil.getDestination(ActionBarActivity.class)));
        functionList.add(new FunctionItemBean("RecyclerView", R.mipmap.icon_recycler, ""));
        functionList.add(new FunctionItemBean("CardView", R.mipmap.icon_card_view, ""));
        functionList.add(new FunctionItemBean("WebView", R.mipmap.icon_web_view, ""));
    }

    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return mList;
    }
}