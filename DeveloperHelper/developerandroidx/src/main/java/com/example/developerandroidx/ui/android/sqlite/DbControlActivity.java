package com.example.developerandroidx.ui.android.sqlite;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.quickAdapter.MessageRcvAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.db.entity.Message;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.example.developerandroidx.utils.StringUtils;
import com.example.developerandroidx.view.swipeAction.QMUIRVItemSwipeAction;
import com.example.developerandroidx.view.swipeAction.QMUISwipeAction;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class DbControlActivity extends BaseActivity {

    @BindView(R.id.rcv_message)
    RecyclerView rcvMessage;
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
        rcvMessage.setAdapter(adapter);
        viewModel = (DbControlViewModel) getViewModel(this, DbControlViewModel.class);
        viewModel.getData().observe(this, adapter::setList);

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

            @Override
            public void onClickAction(QMUIRVItemSwipeAction swipeAction, RecyclerView.ViewHolder selected, QMUISwipeAction action) {
                super.onClickAction(swipeAction, selected, action);
                Toast.makeText(context,
                        "你点击了第 " + selected.getAdapterPosition() + " 个 item 的" + action.getText(),
                        Toast.LENGTH_SHORT).show();
                if (action == adapter.mDeleteAction) {
                    adapter.removeAt(selected.getAdapterPosition());
                } else {
                    swipeAction.clear();
                }
            }
        });
        swipeAction.attachToRecyclerView(rcvMessage);
    }

    @OnClick({R.id.iv_add})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_add_message, new DialogUtils.OnFullScreenDialogBindView() {
                    @Override
                    public void onBind(FullScreenDialog dialog, View rootView) {
                        iv_header_image = (ImageView) rootView.findViewById(R.id.iv_header_image);
                        EditText et_contact = rootView.findViewById(R.id.et_contact);
                        EditText et_contact_id = rootView.findViewById(R.id.et_contact_id);
                        EditText et_msg = rootView.findViewById(R.id.et_msg);
                        Button btn_cancel = rootView.findViewById(R.id.btn_cancel);
                        Button btn_save = rootView.findViewById(R.id.btn_save);
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
                                viewModel.insert(new Message(contactId, msg, headerImageUri, contactName, new Date().getTime()));
                                dialog.doDismiss();
                            } else {
                                btn_save.startAnimation(AnimUtil.getInstance().getShakeAnim());
                            }
                        });
                    }
                });
                break;
        }
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