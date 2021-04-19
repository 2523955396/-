package com.HUANSHI.HQW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class startActivity extends AppCompatActivity implements View.OnClickListener {
TextView text;
LinearLayout update;
ProgressBar jindu;
TextView update1;
TextView update2;
    TextView banben;


    String name;
    String V;
    int vv;
    String texti;
    String down;
    String datetime;

    String xiazaii="00.00MB";
    String xiazaix="00.00MB";
    int longi=0;
    int longx=1;
    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                     int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    @PermissionSuccess(requestCode = 100)
    public void doSomething(){
        //授权时执行
//        Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
    }
    @PermissionFail(requestCode = 100)
    public void doFailSomething(){
        //取消授权时执行
//        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.setStatusBarColor(Color.WHITE);
        //状态栏中的文字颜色和图标颜色，需要android系统6.0以上，而且目前只有一种可以修改（一种是深色，一种是浅色即白色）
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_start);

        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE
                )
                .request();


        update=findViewById(R.id.start_update);
        banben=findViewById(R.id.start_banben);
        text=findViewById(R.id.start_text);
        jindu=findViewById(R.id.start_jindu);
        update1=findViewById(R.id.start_update1);
        update2=findViewById(R.id.start_update2);
        update1.setOnClickListener(this);
        update2.setOnClickListener(this);




    }





    public void update() {
        new Thread(){
            @Override
            public void run() {
        try {
            URL url=new URL("https://hs.51huanqi.cn/update.php");
            URLConnection urlConnection=url.openConnection();
            InputStream inputStream=urlConnection.getInputStream();
            String urll="";
            int scurl;
            while ((scurl=inputStream.read())!=-1){
                urll+=(char)scurl;
            }

            JSONArray jsonArray=new JSONArray(urll);
            System.out.println(jsonArray);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                name=jsonObject.getString("软件名");
                V=jsonObject.getString("版本号");
                vv=jsonObject.getInt("版本次数");
                texti=jsonObject.getString("更新内容");
                down=jsonObject.getString("下载地址");
                datetime=jsonObject.getString("更新时间");
                System.out.println(name+V+vv+texti+down+datetime);
            }
            PackageInfo packageInfo= startActivity.this.getApplicationContext().getPackageManager().getPackageInfo(startActivity.this.getPackageName(),0);
//                    System.out.println("当前版本名称:"+packageInfo.versionName);
            System.out.println(vv);
            if (vv>packageInfo.versionCode){
                System.out.println("版本该升级了");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        update.setVisibility(View.VISIBLE);
                        text.setText(texti);
                        banben.setText("版本更新:"+V);

                    }
                });


            }
            else {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(startActivity.this,home.class);
                        startActivity(intent);
                        finish();
                    }
                },1000);

            }

        }catch (Exception v){

        }
            }
        }.start();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_update1:
                Intent intent1=new Intent(startActivity.this,home.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.start_update2:
                File file=new File(getExternalFilesDir("焕视")+"/焕视.apk");

                    download();

                    new Thread() {
                        @Override
                        public void run() {
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            jindu.setProgress(longi);
                                            update2.setText(xiazaii + "/" + xiazaix);
                                            if (longx == longi) {
                                                update2.setText("安装");
                                                System.out.println("下载完毕");
                                                cancel();
                                                interrupt();
                                                installApk(startActivity.this,file);
                                            }

                                        }
                                    });
                                }
                            }, 0, 1000);
                        }
                    }.start();
                break;

        }
    }

    public void download(){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL(down);
                    URLConnection connection=url.openConnection();
                    int long1= Integer.valueOf(connection.getContentLength());
                    xiazaix=String.format("%.2f",long1/1024.00/1024.00)+"MB";
                    longx=long1;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jindu.setMax(longx);
                        }
                    });
                    InputStream inputStream=connection.getInputStream();
                    File file=new File(getExternalFilesDir("焕视")+"");
                    if (!file.exists()){
                        file.mkdirs();
                    }
                    System.out.println(getExternalFilesDir(null));
                    FileOutputStream fileOutputStream=new FileOutputStream( getExternalFilesDir("焕视")+"/焕视.apk");
                    int xiazai;
                    byte[] by=new byte[1024];
                    while ((xiazai=inputStream.read(by))!=-1){
                        fileOutputStream.write(by,0,xiazai);
                        File file1=new File(file.getPath()+"/焕视.apk");
                        longi=(int)file1.length();
                        xiazaii=String.format("%.2f",longi/1024.00/1024.00)+"MB";
                        System.out.println("总数"+xiazaix+longx+"下载中"+xiazaii+longi);
                    }
                    System.out.println(file.getPath());


                }catch (Exception v){

                }
            }
        }.start();





    }

    public void installApk(Context context, File file) {
        if (context == null) {
            return;
        }
        String authority = getApplicationContext().getPackageName() + ".fileProvider";
        //确保authority 与AndroidManifest.xml中android:authorities="包名.fileProvider"所有字符一致
        Uri apkUri = FileProvider.getUriForFile(context, authority, file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        Log.i("DOWNLOAD", "installApk() startActivity(intent)");
        context.startActivity(intent);
        finish();
    }

}