package com.example.developerandroidx.ui;

import android.content.Intent;
import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.widget.webView.TechnologyWebviewActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;

import butterknife.BindView;

public class AboutActivity extends BaseActivity {
    @BindView(R.id.esv_content)
    ExtensibleScrollView esv_content;
    @BindView(R.id.cv_image)
    View cv_image;
    @BindView(R.id.tv_new_version)
    View tv_new_version;

    @Override
    protected int bindLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("About");
        cv_image.setOnClickListener(v -> Beta.checkAppUpgrade());
    }

    @Override
    protected void initData() {
        super.initData();
        //查看有无本地更新策略
        loadUpgradeInfo();
        esv_content.addBodyWithIntent("DevelopHelper(github)", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/18518373576/DeveloperHelper"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("DevelopHelper(gitee)", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://gitee.com/ZhangQQ_123/DeveloperHelper"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("图标库：https://www.iconfont.cn", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://www.iconfont.cn"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("butterknife", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/JakeWharton/butterknife"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("CodeView", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/Thereisnospon/CodeView"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("kongzue.dialog", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/kongzue/DialogV3"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("eventbus", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/greenrobot/EventBus"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("BaseRecyclerViewAdapterHelper", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/CymChad/BaseRecyclerViewAdapterHelper"));
    }

    private void loadUpgradeInfo() {

        /***** 获取升级信息 *****/
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();

        if (upgradeInfo == null) {
            return;
        }
        tv_new_version.setVisibility(View.VISIBLE);
        StringBuilder info = new StringBuilder();
        info.append("id: ").append(upgradeInfo.id).append("\n");
        info.append("标题: ").append(upgradeInfo.title).append("\n");
        info.append("升级说明: ").append(upgradeInfo.newFeature).append("\n");
        info.append("versionCode: ").append(upgradeInfo.versionCode).append("\n");
        info.append("versionName: ").append(upgradeInfo.versionName).append("\n");
        info.append("发布时间: ").append(upgradeInfo.publishTime).append("\n");
        info.append("安装包Md5: ").append(upgradeInfo.apkMd5).append("\n");
        info.append("安装包下载地址: ").append(upgradeInfo.apkUrl).append("\n");
        info.append("安装包大小: ").append(upgradeInfo.fileSize).append("\n");
        info.append("弹窗间隔（ms）: ").append(upgradeInfo.popInterval).append("\n");
        info.append("弹窗次数: ").append(upgradeInfo.popTimes).append("\n");
        info.append("发布类型（0:测试 1:正式）: ").append(upgradeInfo.publishType).append("\n");
        info.append("弹窗类型（1:建议 2:强制 3:手工）: ").append(upgradeInfo.upgradeType).append("\n");
        info.append("图片地址：").append(upgradeInfo.imageUrl);

        LogUtils.e("app更新", info.toString());
    }
}
