package com.example.developerandroidx.adapter.quickAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.example.developerandroidx.R;
import com.example.developerandroidx.databinding.ItemCityBinding;
import com.example.developerandroidx.db.entity.City;

import org.jetbrains.annotations.NotNull;

/**
 * 作者： zjf 2020/6/24 3:42 PM
 * 参考：
 * 描述：
 */
public class CityRcvAdapter extends BaseQuickAdapter<City, BaseDataBindingHolder<ItemCityBinding>> {

    public CityRcvAdapter() {
        super(R.layout.item_city);
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder<ItemCityBinding> holder, City item) {
        ItemCityBinding binding = holder.getDataBinding();
        if (binding != null) {
            binding.setCity(item);
        }
    }
}
