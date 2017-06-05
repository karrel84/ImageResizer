package com.kerrel.imageresizer;

import android.Manifest;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.kerrel.imageresizer.databinding.ActivityMainBinding;
import com.kerrel.imageresizer_lib.ImageResizer;
import com.kerrel.imageresizer_lib.operations.ImageResize;
import com.kerrel.imageresizer_lib.operations.ResizeMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import gun0912.tedbottompicker.TedBottomPicker;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding mBinding;
    private Context mContext;
    private RequestManager mGlideRequestManager;

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mContext = this;
        mGlideRequestManager = Glide.with(this);
        mBinding.button.setOnClickListener(onGetImageListener);
        mBinding.button3.setOnClickListener(onResizeListener);
    }

    private View.OnClickListener onGetImageListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    Log.d(TAG, "onPermissionGranted");
                    TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(mContext)
                            .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                                @Override
                                public void onImageSelected(Uri uri) {
                                    mUri = uri;
                                    mGlideRequestManager
                                            .load(uri)
                                            .into(mBinding.imageView);
                                }
                            })
                            .create();

                    tedBottomPicker.show(getSupportFragmentManager());
                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(mContext, "권한이 거부되었습니다.", Toast.LENGTH_LONG).show();
                }
            };

            new TedPermission(mContext)
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                    .check();
        }
    };

    // 리사이즈
    private View.OnClickListener onResizeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            File file = new File(mUri.getPath());
            Bitmap bitmap = ImageResizer.resize(file, 24, 16);
            mBinding.imageView.setImageBitmap(bitmap);

            ImageResizer.saveToFile(bitmap, writeImage("test.png"));
        }
    };

    /**
     * @param fileName test.png
     * @return
     */
    private File writeImage(String fileName) {
        File pathfile = Environment.getExternalStorageDirectory();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(System.currentTimeMillis());
        String seq = "1234";
        pathfile = new File(pathfile.getPath() + "/test1/test2/" + date + "/" + seq);

        if (!pathfile.exists()) pathfile.mkdirs();

        pathfile = new File(pathfile.getPath() + "/" + fileName);
        return pathfile;
    }
}
