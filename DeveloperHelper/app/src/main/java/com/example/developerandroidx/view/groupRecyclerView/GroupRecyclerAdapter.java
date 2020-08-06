package com.example.developerandroidx.view.groupRecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.example.developerandroidx.databinding.ItemSportHistoryBinding;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * 分组的RecyclerAdapter
 * Created by haibin on 2017/5/15.
 */
@SuppressWarnings("unused")
public abstract class GroupRecyclerAdapter<Parent, Child> extends BaseQuickAdapter<Child, BaseDataBindingHolder<ItemSportHistoryBinding>> {
    private LinkedHashMap<Parent, List<Child>> mGroups;
    private List<Parent> mGroupTitles;

    public GroupRecyclerAdapter(int layoutId) {
        super(layoutId);
        mGroups = new LinkedHashMap<>();
        mGroupTitles = new ArrayList<>();
    }

    /**
     * 返回特定的标题
     */
    Parent getGroup(int groupPosition) {
        return mGroupTitles.get(groupPosition);
    }

    /**
     * 获得分组的数量
     *
     * @return 组的数量
     */
    int getGroupCount() {
        return mGroupTitles.size();
    }

    /**
     * 获取某一组的数量
     *
     * @param groupPosition groupPosition
     * @return 某一组的数量
     */
    int getChildCount(int groupPosition) {
        if (mGroupTitles == null || mGroups.size() == 0)
            return 0;
        if (mGroups.get(mGroupTitles.get(groupPosition)) == null)
            return 0;
        return mGroups.get(mGroupTitles.get(groupPosition)).size();
    }

    /**
     * 重置分组数据
     *
     * @param groups groups
     */
    public void resetGroups(LinkedHashMap<Parent, List<Child>> groups) {
        if (groups == null) {
            return;
        }
        mGroups.clear();
        mGroupTitles.clear();
        mGroups.putAll(groups);

        //重置数据
        List<Child> list = new ArrayList<>();
        for (Parent key : mGroups.keySet()) {
            mGroupTitles.add(key);
            list.addAll(mGroups.get(key));
        }
        setList(list);
    }

    /**
     * 清除分组数据
     */
    public final void clearGroup() {
        mGroupTitles.clear();
        mGroups.clear();
        setList(null);
    }

    /**
     * 从分组移除数据
     *
     * @param position 下标
     * @return 分组是否为空，要移除分组
     */
    public boolean removeGroupItem(int position) {
        int group = getGroupIndex(position);
        removeGroupChildren(group);
        int count = getChildCount(group);
        removeAt(position);
        if (count <= 0) {
            mGroupTitles.remove(group);
            return true;
        }
        return false;
    }

    /**
     * 获取所在分组
     *
     * @param position 下标
     * @return 获取所在分组
     */
    private int getGroupIndex(int position) {
        int count = 0;
        if (position <= count)
            return 0;
        int i = 0;
        for (Parent parent : mGroups.keySet()) {
            count += mGroups.get(parent).size();
            if (position < count) {
                return i;
            }
            i++;
        }
        return 0;
    }

    private void removeGroupChildren(int groupPosition) {
        if (groupPosition >= mGroupTitles.size())
            return;
        List<Child> childList = mGroups.get(mGroupTitles.get(groupPosition));
        if (childList != null && childList.size() != 0) {
            childList.remove(childList.size() - 1);
        }
    }
}
