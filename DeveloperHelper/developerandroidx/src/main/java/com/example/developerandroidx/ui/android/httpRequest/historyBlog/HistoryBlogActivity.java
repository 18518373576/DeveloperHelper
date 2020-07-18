package com.example.developerandroidx.ui.android.httpRequest.historyBlog;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.quickAdapter.BlogHistoryRcvAdapter;
import com.example.developerandroidx.base.BaseActivityWithButterKnife;
import com.example.developerandroidx.base.BaseModel;
import com.example.developerandroidx.model.HistoryBlogBean;
import com.example.developerandroidx.ui.widget.webView.TechnologyWebViewActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.PreferenceUtils;
import com.example.developerandroidx.utils.RouteUtil;
import com.example.developerandroidx.view.loadingView.LoadingPage;

import butterknife.BindView;

public class HistoryBlogActivity extends BaseActivityWithButterKnife implements OnItemClickListener, OnLoadMoreListener, Observer<BaseModel> {

    @BindView(R.id.rcv_blog_history)
    RecyclerView rcv_blog_history;
    @BindView(R.id.lv_loading)
    LoadingPage lv_loading;

    private BlogHistoryRcvAdapter adapter;
    private int page = 1;
    private HistoryBlogViewModel viewModel;
    private String requestLibrary;
    private String id;

    @Override
    protected int bindLayout() {
        return R.layout.activity_history_blog;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("历史文章");
        rcv_blog_history.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BlogHistoryRcvAdapter();
        adapter.setOnItemClickListener(this);
        adapter.getLoadMoreModule().setOnLoadMoreListener(this);
        adapter.getLoadMoreModule().setAutoLoadMore(true);
        adapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
        adapter.setAnimationEnable(true);
        rcv_blog_history.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        id = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        requestLibrary = PreferenceUtils.getInstance().getStringValue(Constant.PreferenceKeys.HTTP_REQUEST_LIBRARY);
        viewModel = getViewModel(HistoryBlogViewModel.class);

        lv_loading.onLoading(rcv_blog_history);
        loadData(Constant.Internet.FIRST_LOAD);
    }

    /**
     * 加载数据
     */
    private void loadData(String loadType) {
        viewModel.getData(requestLibrary, loadType, id, String.valueOf(page)).observe(this, this);
    }

    /**
     * 列表条目点金事件
     *
     * @param adapter  the adapter
     * @param view     The itemView within the RecyclerView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     */
    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        HistoryBlogBean.Model model = (HistoryBlogBean.Model) adapter.getData().get(position);
        RouteUtil.goTo(context, RouteUtil.getDestination(TechnologyWebViewActivity.class), model.link);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
        loadData(Constant.Internet.LOAD_MORE);
    }

    /**
     * 监听数据变化
     *
     * @param baseModel
     */
    @Override
    public void onChanged(BaseModel baseModel) {
        HistoryBlogBean historyBlogBean = (HistoryBlogBean) baseModel;
        if (page == 1) {
            //数据错误
            if (historyBlogBean == null) {
                lv_loading.loadingFail(rcv_blog_history, R.mipmap.icon_404);
                return;
            }
            adapter.setList(historyBlogBean.data.datas);
            int pageSize = 20;
            if (historyBlogBean.data.datas.size() < pageSize) {
                //方法必须在 adapter.setList(之后调用)
                adapter.getLoadMoreModule().loadMoreEnd();
            }
            lv_loading.loadingSuc(rcv_blog_history);
        } else {
            if (historyBlogBean == null) {
                return;
            }
            if (page == historyBlogBean.data.pageCount) {
                adapter.getLoadMoreModule().loadMoreEnd();
            } else {
                //执行完上滑加载后，记得Complete
                adapter.getLoadMoreModule().loadMoreComplete();
            }
            adapter.addData(historyBlogBean.data.datas);
        }
        page = historyBlogBean.data.curPage;
        page++;
    }
}