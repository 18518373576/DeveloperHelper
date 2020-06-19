package com.example.developerandroidx.ui.android.contentProvider;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.FileProvider;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.android.contentProvider.dialog.MediaListDialog;
import com.example.developerandroidx.ui.android.contentProvider.provider.Media;
import com.example.developerandroidx.ui.widget.webView.TechnologyWebviewActivity;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.RouteUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

public class ContentProviderActivity extends BaseActivity {

    @BindView(R.id.tv_desc)
    TextView tv_desc;

    private Uri logUri;

    @Override
    protected int bindLayout() {
        return R.layout.activity_content_provider;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("App data & files");
        iv_right.setVisibility(View.VISIBLE);
        //内容提供者官方文档
        iv_right.setOnClickListener(v ->
                RouteUtil.goTo(context, RouteUtil.getDestination(TechnologyWebviewActivity.class), "https://developer.android.google.cn/guide/topics/providers/content-providers"));
    }

    @Override
    protected void initData() {
        super.initData();
        //在内部存储空间,创建一个log文件
        try {
//            new File(context.getFilesDir(), "log/appLog.log");
            tv_desc.setText("创建appLog.log文件\n");
            String filePath = context.getFilesDir().getAbsolutePath() + "/log";
            String fileName = "appLog.log";
            File path = new File(filePath);
            File file = new File(filePath, fileName);
            if (!path.exists()) {
                path.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            tv_desc.append("文件位置:\n" + file.getAbsolutePath() + "\n");
            logUri = FileProvider.getUriForFile(context, "com.example.developerandroidx.fileprovider", file);
            tv_desc.append("文件URI:\n" + logUri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_inner_store, R.id.btn_External_specific_store, R.id.btn_External_public_store
            , R.id.btn_show_video, R.id.btn_show_pics, R.id.btn_show_music, R.id.btn_share_data
            , R.id.btn_share_file, R.id.btn_request_file, R.id.btn_write_log, R.id.btn_read_log
    })
    public void click(View v) {
        tv_desc.setText("");
        switch (v.getId()) {
            //获取内部存储路径,并获取files文件夹下的文件
            case R.id.btn_inner_store:
                function_01();
                break;
            //获取外部存储的应用私有存储空间
            case R.id.btn_External_specific_store:
                function_02();
                break;
            //获取外部公共存储空间
            case R.id.btn_External_public_store:
                function_03();
                break;
            //获取手机视频列表
            case R.id.btn_show_video:
                function_04();
                break;
            //获取照片
            case R.id.btn_show_pics:
                function_05();
                break;
            //展示音频
            case R.id.btn_show_music:
                function_06();
                break;
            //分享简单数据
            case R.id.btn_share_data:
                function_07();
                break;
            //文件分享
            case R.id.btn_share_file:
                function_08();
                break;
            //请求别的应用的分享文件
            case R.id.btn_request_file:
                function_09();
                break;
            //写入log文件数据
            case R.id.btn_write_log:
                function_10();
                break;
            //读log数据
            case R.id.btn_read_log:
                function_11();
                break;
        }
    }

    private void function_11() {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = getContentResolver().openInputStream(logUri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            DialogUtils.getInstance().showMessageDialog(context, "读取log数据", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void function_10() {
        DialogUtils.getInstance().showInputDialog(context, "写入log数据", "写入", new DialogUtils.OnButtonClickedListener() {
            @Override
            public void onClick(String msg, boolean isOkButton) {
                try (ParcelFileDescriptor descriptor = getContentResolver().openFileDescriptor(logUri, "w"); FileOutputStream outputStream = new FileOutputStream(descriptor.getFileDescriptor())) {
                    outputStream.write(("时间:" + System.currentTimeMillis() + " log:" + msg).getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 请求文件共享的回调
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent returnIntent) {
        super.onActivityResult(requestCode, resultCode, returnIntent);
        if (resultCode != RESULT_OK) {
            return;
        } else {
            Uri returnUri = returnIntent.getData();
            //Glide.with(context).load(returnUri).into(iv_right);
            DialogUtils.getInstance().showMessageDialog(context, "返回URI", returnUri.toString());
        }
    }

    private void function_09() {
        DialogUtils.getInstance().showMessageDialog(context, "请求文件", "文件类型:image/*", "请求", "取消", new DialogUtils.OnButtonClickedListener() {
            @Override
            public void onClick(String msg, boolean isOkButton) {
                Intent requestFileIntent = new Intent(Intent.ACTION_PICK);
                requestFileIntent.setType("image/*");
                startActivityForResult(requestFileIntent, 0);
            }
        });
    }

    private void function_08() {
        tv_desc.append("FileProvider以URI的形式对外共享文件\n");
        tv_desc.append("URI:" + logUri.toString());
    }

    private void function_07() {
        DialogUtils.getInstance().showMessageDialog(context, "发送消息", "明天有🌧,记得带伞!!!",
                "发送", "取消", new DialogUtils.OnButtonClickedListener() {
                    @Override
                    public void onClick(String msg, boolean isOkButton) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, msg);
//                        intent.setPackage("com.tencent.mm");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "发送消息");
                        intent.setType("text/plain");
                        Intent shareIntent = Intent.createChooser(intent, "发送消息");
                        startActivity(shareIntent);
                    }
                });
    }

    private void function_06() {
        List<Media> mediaList = new ArrayList<>();
        //要查询的Audio数据的列
        String[] projection = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE
        };
        //查询条件,这里根据音频长度过滤
        String selection = MediaStore.Audio.Media.DURATION + " >= ?";
        //过滤条件的值,这里是过滤掉时长小于1分钟的视频
        String[] selectionArgs = new String[]{String.valueOf(TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS))};
        //根据音频名称进行排序
        //asc：指定列按升序排列 desc：指定列按降序排列
        String sortOrder = MediaStore.Audio.Media.DISPLAY_NAME + " ASC";

        //try (){ }语句,会在执行完自动执行继承Closeable类的close方法
        try (Cursor cursor = getApplicationContext().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder
        )) {
            if (cursor != null) {
                //获取各个字段所在的获取到的数据列中的位置
                int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
                int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
                int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE);

                while (cursor.moveToNext()) {
                    long id = cursor.getLong(idColumn);
                    String name = cursor.getString(nameColumn);
                    int duration = cursor.getInt(durationColumn);
                    int size = cursor.getInt(sizeColumn);

                    //获取视频文件的URI
                    //content://media/external/video/media/163272
                    //作为对比获取本应用的一张图片的URI
                    //file:///storage/emulated/0/Android/data/com.example.developerandroidx/files/Pictures/JPEG_20200615_135412_8637409846958515960.jpg
                    Uri contentUri = ContentUris.withAppendedId(
                            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);

                    mediaList.add(new Media(contentUri, name, duration, size, Media.MediaType.AUDIO));
                }
                new MediaListDialog(mediaList).show(context);
            }
        }
    }

    private void function_05() {
        List<Media> mediaList = new ArrayList<>();
        //要查询的Images数据的列
        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN,
                MediaStore.Images.Media.SIZE
        };
        //查询条件,这里根据照片大小
        String selection = MediaStore.Images.Media.SIZE + " >= ?";
        //过滤条件的值,这里是过滤掉大小小于1M的照片
        String[] selectionArgs = new String[]{String.valueOf(1024 * 50)};
        //根据拍摄时间进行排序,降序排序
        String sortOrder = MediaStore.Images.Media.DATE_TAKEN + " DESC";
        //try (){ }语句,会在执行完自动执行继承Closeable类的close方法
        try (Cursor cursor = getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder
        )) {
            if (cursor != null) {
                //获取各个字段所在的获取到的数据列中的位置
                int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
                int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
                int dateTakenColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN);
                int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);

                while (cursor.moveToNext()) {
                    long id = cursor.getLong(idColumn);
                    String name = cursor.getString(nameColumn);
                    long dateTaken = cursor.getLong(dateTakenColumn);
                    int size = cursor.getInt(sizeColumn);

                    //获取照片文件的URI
                    Uri contentUri = ContentUris.withAppendedId(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

                    mediaList.add(new Media(contentUri, name, dateTaken, size, Media.MediaType.PIC));
                }
                new MediaListDialog(mediaList).show(context);
            }
        }
    }

    private void function_04() {
        List<Media> mediaList = new ArrayList<>();
        //要查询的video数据的列
        String[] projection = new String[]{
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.SIZE
        };
        //查询条件,这里根据视频长度过滤
        String selection = MediaStore.Video.Media.DURATION + " >= ?";
        //过滤条件的值,这里是过滤掉时长小于1分钟的视频
        String[] selectionArgs = new String[]{String.valueOf(TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS))};
        //根据视频名称进行排序
        //asc：指定列按升序排列 desc：指定列按降序排列
        String sortOrder = MediaStore.Video.Media.DISPLAY_NAME + " ASC";

        //try (){ }语句,会在执行完自动执行继承Closeable类的close方法
        try (Cursor cursor = getApplicationContext().getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder
        )) {
            if (cursor != null) {
                //获取各个字段所在的获取到的数据列中的位置
                int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
                int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);
                int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);

                while (cursor.moveToNext()) {
                    long id = cursor.getLong(idColumn);
                    String name = cursor.getString(nameColumn);
                    int duration = cursor.getInt(durationColumn);
                    int size = cursor.getInt(sizeColumn);

                    //获取视频文件的URI
                    //content://media/external/video/media/163272
                    //作为对比获取本应用的一张图片的URI
                    //file:///storage/emulated/0/Android/data/com.example.developerandroidx/files/Pictures/JPEG_20200615_135412_8637409846958515960.jpg
                    Uri contentUri = ContentUris.withAppendedId(
                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);

                    mediaList.add(new Media(contentUri, name, duration, size, Media.MediaType.VIDEO));
                }
                new MediaListDialog(mediaList).show(context);
            }
        }
    }

    private void function_03() {
        tv_desc.append(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    public void function_02() {
        try {
            tv_desc.append(getExternalFilesDir(null).getAbsolutePath() + "\n\n");
            tv_desc.append(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "\n\n");
            tv_desc.append(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "\n\n");
            tv_desc.append(getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() + "\n\n");
            tv_desc.append(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "\n\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("CheckResult")
    public void function_01() {
        try {
            tv_desc.append("CanonicalPath:" + context.getFilesDir().getCanonicalPath() + "\n\n");
            tv_desc.append("AbsolutePath:" + context.getFilesDir().getAbsolutePath() + "\n\n");
            listFiles(context.getFilesDir());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出内部存储files文件夹下的文件
     */
    private void listFiles(File fileDir) {
        for (File file : fileDir.listFiles()) {
            if (file.isDirectory()) {
                listFiles(file);
            } else {
                tv_desc.append("Files:" + file.getAbsolutePath() + "\n");
            }
        }
    }
}