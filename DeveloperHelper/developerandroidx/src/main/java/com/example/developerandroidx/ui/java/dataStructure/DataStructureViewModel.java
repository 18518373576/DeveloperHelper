package com.example.developerandroidx.ui.java.dataStructure;

import com.example.developerandroidx.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2020/5/28 14:33
 * 参考:
 * 描述: 数据结构列表数据
 */
public class DataStructureViewModel extends BaseViewModel<List<String>> {

    @Override
    protected List<String> initData(Object dataType) {
        List<String> list = new ArrayList<>();
        list.add("单向链表");
        list.add("双向链表");
        return list;
    }
}
