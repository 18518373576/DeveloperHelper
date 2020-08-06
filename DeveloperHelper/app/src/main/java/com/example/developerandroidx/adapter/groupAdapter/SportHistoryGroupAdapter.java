package com.example.developerandroidx.adapter.groupAdapter;

import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.example.developerandroidx.R;
import com.example.developerandroidx.databinding.ItemSportHistoryBinding;
import com.example.developerandroidx.db.entity.SportHistory;
import com.example.developerandroidx.model.MySportHistory;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.StringUtils;
import com.example.developerandroidx.view.groupRecyclerView.GroupRecyclerAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * 作者： zjf 2020/7/7 11:53 AM
 * 参考：
 * 描述：
 */
public class SportHistoryGroupAdapter extends GroupRecyclerAdapter<String, SportHistory> {

    public SportHistoryGroupAdapter() {
        super(R.layout.item_sport_history);
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder<ItemSportHistoryBinding> holder, SportHistory item) {
        ItemSportHistoryBinding binding = holder.getDataBinding();
        MySportHistory mySportHistory = new MySportHistory();

        mySportHistory.iconId = item.sportType == Constant.Common.RIDING ? R.mipmap.icon_riding : R.mipmap.icon_step;
        mySportHistory.dateStr = item.dateStr;
        mySportHistory.timeSpace = StringUtils.getInstance().getFormatTime(item.startTime, "HH:mm") + "-" +
                StringUtils.getInstance().getFormatTime(item.endTime, "HH:mm");
        mySportHistory.distanceOrSteps = item.sportType == Constant.Common.RIDING ? StringUtils.getInstance().getDistance(item.distance) + "KM" : item.steps + "步";
        if (binding != null) {
            binding.setSportHistory(mySportHistory);
        }
    }
}
