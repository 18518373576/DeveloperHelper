package com.example.developerandroidx.adapter.quickAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.example.developerandroidx.R;
import com.example.developerandroidx.databinding.ItemSportHistoryBinding;
import com.example.developerandroidx.db.entity.SportHistory;
import com.example.developerandroidx.model.MySportHistory;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.StringUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 作者： zjf 2020/7/3 9:55 AM
 * 参考：
 * 描述：
 */
public class SportHistoryRcvAdapter extends BaseQuickAdapter<SportHistory, BaseDataBindingHolder<ItemSportHistoryBinding>> {

    public SportHistoryRcvAdapter() {
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
