package com.example.developerandroidx.adapter.quickAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.example.developerandroidx.R;
import com.example.developerandroidx.databinding.ItemWeatherListBinding;
import com.example.developerandroidx.model.WeatherModel;
import com.example.developerandroidx.utils.Constant;

import org.jetbrains.annotations.NotNull;

/**
 * 作者： zjf 2020/6/24 10:24 AM
 * 参考：
 * 描述：
 */
public class WeatherRcvAdapter extends BaseQuickAdapter<WeatherModel.DataBean, BaseDataBindingHolder<ItemWeatherListBinding>> {

    public WeatherRcvAdapter() {
        super(R.layout.item_weather_list);
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder<ItemWeatherListBinding> holder, WeatherModel.DataBean item) {
        ItemWeatherListBinding binding = holder.getDataBinding();
        if (binding != null) {
            binding.setWeather(item);
            binding.setWeatherIcon(Constant.getWeatherIconMap());
            binding.setKey(item.getWea_img());
        }
    }
}
