package com.example.developerandroidx.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public abstract class BaseFragmentWithDataBinding<T extends ViewDataBinding> extends Fragment {

    protected Context context;
    protected T binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        binding = DataBindingUtil.inflate(inflater, bindLayout(), container, false);
        initView();
        initData();
        return binding.getRoot();
    }

    /**
     * 获取ViewModel
     *
     * @param owner      ViewModel库拥有者，可是是fragment或者activity
     * @param modelClass 自己定义的viewModel类
     * @return
     */
    public <V extends ViewModel> V getViewModel(ViewModelStoreOwner owner, Class<V> modelClass) {
        return new ViewModelProvider(owner).get(modelClass);
    }

    /**
     * 绑定layout
     *
     * @return layout文件id
     */
    protected abstract int bindLayout();

    /**
     * view的一些操作
     */
    protected abstract void initView();

    /**
     * 数据回调的一些操作
     */
    protected abstract void initData();
}
