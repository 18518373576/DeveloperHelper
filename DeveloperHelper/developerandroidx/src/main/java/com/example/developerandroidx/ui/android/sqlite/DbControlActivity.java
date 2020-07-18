package com.example.developerandroidx.ui.android.sqlite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.quickAdapter.MessageRcvAdapter;
import com.example.developerandroidx.base.BaseActivityWithButterKnife;
import com.example.developerandroidx.db.entity.Message;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.StringUtils;
import com.example.developerandroidx.view.loadingView.LoadingPage;
import com.example.developerandroidx.view.swipeAction.QMUIRVItemSwipeAction;
import com.example.developerandroidx.view.swipeAction.QMUISwipeAction;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class DbControlActivity extends BaseActivityWithButterKnife {

    @BindView(R.id.rcv_message)
    RecyclerView rcvMessage;
    @BindView(R.id.lp_loading)
    LoadingPage lp_loading;
    private final int REQUEST_IMAGE_GALLERY = 100;
    private ImageView iv_header_image;
    private DbControlViewModel viewModel;

    @Override
    protected int bindLayout() {
        return R.layout.activity_db_control;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Database Control");
        rcvMessage.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initData() {
        super.initData();
        MessageRcvAdapter adapter = new MessageRcvAdapter();
        adapter.setAnimationEnable(true);
        rcvMessage.setAdapter(adapter);
        viewModel = (DbControlViewModel) getViewModel(this, DbControlViewModel.class);


        //添加测试数据
        Message[] messages = new Message[100];
        for (int i = 0; i < 100; i++) {
            messages[i] = new Message(i + "", "测试添加" + i, "", "测试" + i, new Date().getTime());
        }
        viewModel.insert(messages);


        viewModel.getData().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messageList) {
                if (messageList.size() == 0) {
                    lp_loading.noData("暂无数据", true, rcvMessage);
                } else {
                    lp_loading.noData(null, false, rcvMessage);
                    adapter.setList(messageList);
                }
            }
        });
        //item滑动action
        QMUIRVItemSwipeAction swipeAction = new QMUIRVItemSwipeAction(true, new QMUIRVItemSwipeAction.Callback() {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.removeAt(viewHolder.getAdapterPosition());
            }

            @Override
            public int getSwipeDirection(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return QMUIRVItemSwipeAction.SWIPE_LEFT;
            }

            @SuppressLint("CheckResult")
            @Override
            public void onClickAction(QMUIRVItemSwipeAction swipeAction, RecyclerView.ViewHolder selected, QMUISwipeAction action) {
                super.onClickAction(swipeAction, selected, action);
                if (action == adapter.mDeleteAction) {
                    swipeAction.clear();

                    Observable.just(1).delay(200, TimeUnit.MILLISECONDS).subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            DialogUtils.getInstance().showMessageDialog(context, "提示", "是否要删除此条记录", "删除", "取消", new DialogUtils.OnButtonClickedListener() {
                                @Override
                                public void onClick(String msg, boolean isOkButton) {
                                    if (isOkButton) {
                                        int position = selected.getAdapterPosition();
                                        viewModel.delete(adapter.getData().get(position));
                                    }
                                }
                            });
                        }
                    });
                } else {
                    swipeAction.clear();
                    showAddMsgDialog(adapter.getData().get(selected.getAdapterPosition()));
                }
            }
        });
        swipeAction.attachToRecyclerView(rcvMessage);
    }

    @OnClick({R.id.iv_add})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                showAddMsgDialog(null);
                break;
        }
    }

    /**
     * 添加或修改数据
     *
     * @param message
     */
    private void showAddMsgDialog(Message message) {
        //添加数据弹框
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_add_message, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                iv_header_image = (ImageView) rootView.findViewById(R.id.iv_header_image);
                EditText et_contact = rootView.findViewById(R.id.et_contact);
                EditText et_contact_id = rootView.findViewById(R.id.et_contact_id);
                EditText et_msg = rootView.findViewById(R.id.et_msg);
                Button btn_cancel = rootView.findViewById(R.id.btn_cancel);
                Button btn_save = rootView.findViewById(R.id.btn_save);
                if (message != null) {
                    Glide
                            .with(context)
                            .load(Uri.parse(message.contactHeaderImage))
                            .override(iv_header_image.getWidth(), iv_header_image.getHeight())
                            .centerCrop()
                            .error(R.mipmap.icon_launcher)
                            .placeholder(R.mipmap.icon_launcher)
                            .fallback(R.mipmap.icon_launcher)
                            .into(iv_header_image);
                    et_contact.setText(message.contactName);
                    et_contact_id.setText(message.contactId);
                    et_contact_id.setTextColor(getResources().getColor(R.color.lightGrayColor));
                    et_contact_id.setEnabled(false);
                    et_msg.setText(message.lastMsg);
                }
                iv_header_image.setOnClickListener(v12 -> {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
                });

                btn_cancel.setOnClickListener(v1 -> dialog.doDismiss());
                btn_save.setOnClickListener(v13 -> {
                    String contactName = et_contact.getText().toString().trim();
                    String contactId = et_contact_id.getText().toString().trim();
                    String headerImageUri = (String) iv_header_image.getTag();
                    String msg = et_msg.getText().toString().trim();
                    if (!StringUtils.getInstance().isHasNull(contactName, contactId, headerImageUri, msg)) {
                        Message tempMsg = new Message(contactId, msg, headerImageUri, contactName, new Date().getTime());
                        if (message == null) {
                            viewModel.insert(tempMsg);
                        } else {
                            viewModel.upDate(tempMsg);
                        }
                        dialog.doDismiss();
                    } else {
                        btn_save.startAnimation(AnimUtil.getInstance().getShakeAnim());
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            Uri returnUri = data.getData();
            iv_header_image.setTag(returnUri.toString());
            Glide
                    .with(context)
                    .load(returnUri)
                    .override(iv_header_image.getWidth(), iv_header_image.getHeight())
                    .centerCrop()
                    .error(R.mipmap.icon_camera)
                    .placeholder(R.mipmap.icon_camera)
                    .fallback(R.mipmap.icon_camera)
                    .into(iv_header_image);
        }
    }
}