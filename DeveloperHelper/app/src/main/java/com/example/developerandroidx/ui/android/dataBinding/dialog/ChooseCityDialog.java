package com.example.developerandroidx.ui.android.dataBinding.dialog;

import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.quickAdapter.CityRcvAdapter;
import com.example.developerandroidx.databinding.DialogChooseCityBinding;
import com.example.developerandroidx.db.entity.City;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.ui.android.dataBinding.DataBindingActivity;
import com.example.developerandroidx.ui.android.dataBinding.DataBindingViewModel;
import com.example.developerandroidx.utils.DialogUtils;

import java.util.List;

/**
 * 作者： zjf 2020/6/24 2:25 PM
 * 参考：
 * 描述：
 */
public class ChooseCityDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_choose_city, (dialog, rootView) -> {
            CityRcvAdapter adapter = new CityRcvAdapter();
            adapter.setAnimationEnable(true);
            DialogChooseCityBinding binding = DataBindingUtil.bind(rootView);
            if (binding != null) {
                //获取依赖的activity的viewModel
                DataBindingViewModel viewModel = new ViewModelProvider((DataBindingActivity) context).get(DataBindingViewModel.class);
                //viewModel里面监听搜索框数据变化,查询数据库返回的数据
                viewModel.searchCities.observe((DataBindingActivity) context, new Observer<List<City>>() {
                    @Override
                    public void onChanged(List<City> cities) {
                        adapter.setList(cities);
                    }
                });

                binding.setLinearLayoutManager(new LinearLayoutManager(context));
                binding.setDataModel(viewModel);
                binding.setAdapter(adapter);
                adapter.setOnItemClickListener((adapter1, view, position) -> {
                    //点击搜索到的城市,重新加载数据
                    dialog.doDismiss();
                    City city = (City) adapter1.getData().get(position);
                    viewModel.reLoadWeatherData(city.id);
                });
            }
        });
    }
}
