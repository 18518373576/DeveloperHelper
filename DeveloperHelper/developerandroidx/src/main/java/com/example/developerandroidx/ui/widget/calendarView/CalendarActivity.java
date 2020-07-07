package com.example.developerandroidx.ui.widget.calendarView;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.groupAdapter.SportHistoryGroupAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.db.DB_utils;
import com.example.developerandroidx.db.entity.SportHistory;
import com.example.developerandroidx.projectInterface.CallBack;
import com.example.developerandroidx.ui.android.map.BaiDuMapActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.view.groupRecyclerView.GroupItemDecoration;
import com.example.developerandroidx.view.groupRecyclerView.GroupRecyclerView;
import com.google.gson.Gson;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者： zjf 2020/7/6 3:44 PM
 * 参考：
 * 描述：展示运动历史记录,与地图界面联动
 */
public class CalendarActivity extends BaseActivity {
    @BindView(R.id.tv_month_day)
    TextView tvMonthDay;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_lunar)
    TextView tvLunar;
    @BindView(R.id.ib_calendar)
    ImageView ibCalendar;
    @BindView(R.id.tv_current_day)
    TextView tvCurrentDay;
    @BindView(R.id.fl_current)
    FrameLayout flCurrent;
    @BindView(R.id.rl_tool)
    RelativeLayout rlTool;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.calendarLayout)
    CalendarLayout calendarLayout;
    @BindView(R.id.rcv_sport_history)
    GroupRecyclerView rcvSportHistory;
    private int mYear;

    @Override
    protected int bindLayout() {
        return R.layout.activity_calendar_view;
    }

    @Override
    protected void initView() {
        super.initView();
        calendarView.setAllMode();
        mYear = calendarView.getCurYear();
        //展示年份选择
        tvMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!calendarLayout.isExpand()) {
                    calendarLayout.expand();
                    return;
                }
                calendarView.showYearSelectLayout(mYear);
                tvLunar.setVisibility(View.GONE);
                tvYear.setVisibility(View.GONE);
                tvMonthDay.setText(String.valueOf(mYear));
            }
        });
        //回到当前日期
        flCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.scrollToCurrent();
            }
        });
        tvMonthDay.setText(calendarView.getCurMonth() + "月" + calendarView.getCurDay() + "日");
        tvYear.setText(String.valueOf(calendarView.getCurYear()));
        tvLunar.setText("今日");
        tvCurrentDay.setText(String.valueOf(calendarView.getCurDay()));

        //日期选择监听
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                tvLunar.setVisibility(View.VISIBLE);
                tvYear.setVisibility(View.VISIBLE);
                tvMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
                tvYear.setText(String.valueOf(calendar.getYear()));
                tvLunar.setText(calendar.getLunar());
                mYear = calendar.getYear();
            }
        });
        //年份选择监听
        calendarView.setOnYearChangeListener(new CalendarView.OnYearChangeListener() {
            @Override
            public void onYearChange(int year) {
                tvMonthDay.setText(String.valueOf(year));
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        DB_utils.getInstance().getAllSportData(new CallBack<List<SportHistory>>() {
            @Override
            public void onFail(String msg) {
                LogUtils.e(getPackageName(), msg);
            }

            @Override
            public void onSuc(List<SportHistory> sportHistoryList) {
                initCalendar(sportHistoryList);
                //默认收缩日历
                calendarLayout.shrink();
            }
        });
    }

    //初始化日历,把运动数据拿出来,进行日历初始化
    private void initCalendar(List<SportHistory> sportHistoryList) {
        //添加日历事件,在有运动记录的地方做标记
        Map<String, Calendar> map = new HashMap<>();
        LinkedHashMap<String, List<SportHistory>> titles = new LinkedHashMap<>();
        List<SportHistory> list = new ArrayList<>();
        for (SportHistory sportHistory : sportHistoryList) {
            int year = Integer.parseInt(sportHistory.dateStr.split("-")[0]);
            int month = Integer.parseInt(sportHistory.dateStr.split("-")[1]);
            int day = Integer.parseInt(sportHistory.dateStr.split("-")[2]);
            map.put(getSchemeCalendar(year, month, day, 0xFFFF7256, "运动").toString(),
                    getSchemeCalendar(year, month, day, 0xFFFF7256, "运动"));

            //初始化分组recyclerView的数据
            //如果map中不包含分组,建立一个新的分组
            if (!titles.containsKey(sportHistory.dateStr)) {
                list = new ArrayList<>();
            }
            list.add(sportHistory);
            titles.put(sportHistory.dateStr, list);
        }
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        calendarView.setSchemeDate(map);

        //填充recyclerView
        rcvSportHistory.setLayoutManager(new LinearLayoutManager(this));
        rcvSportHistory.addItemDecoration(new GroupItemDecoration<String, SportHistory>());
        SportHistoryGroupAdapter adapter = new SportHistoryGroupAdapter();
        adapter.resetGroups(titles);
        rcvSportHistory.setAdapter(adapter);
        rcvSportHistory.notifyDataSetChanged();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                //跳转到地图界面展示数据
                if (TextUtils.isEmpty(getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM))) {
                    return;
                }
                String param = new Gson().toJson(sportHistoryList.get(position));
                Intent intent = new Intent(context, BaiDuMapActivity.class);
                intent.putExtra(Constant.IntentParams.INTENT_PARAM, param);
                setResult(RESULT_OK, intent);
                CalendarActivity.this.finish();
            }
        });
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
//        calendar.addScheme(new Calendar.Scheme());
//        calendar.addScheme(0xFF008800, "假");
//        calendar.addScheme(0xFF008800, "节");
        return calendar;
    }
}
