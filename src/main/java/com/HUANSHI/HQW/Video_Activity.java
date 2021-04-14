package com.HUANSHI.HQW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.MAX_PRIORITY;

public class Video_Activity extends AppCompatActivity implements View.OnClickListener {
    StandardGSYVideoPlayer videoPlayer;
    OrientationUtils orientationUtils;
    List<juji> jujiList;
    String id;
    String name;
    String daoyan;
    String yanyuan;
    String jianjie;
    String year;
    String guojia;
    String leibie;
    String logo;
    String jiesao;

    GridView gridView;
    JUJIGridview jujiGridview;

    TextView video_name;
    TextView video_fenlei;
    TextView video_daoyan;
    TextView video_zhuyan;
    TextView video_juqing;

    Boolean juqingi=false;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            videoPlayer.setUpLazy(jujiList.get(0).href, true, null, null, name);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));//设置要显示的颜色（Color.TRANSPARENT为透明）
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

       find();
        url(id);
        urll(id);
        qp();

    }

    public void find(){
        id=getIntent().getStringExtra("userid");
        videoPlayer=findViewById(R.id.videonew);
        gridView=findViewById(R.id.gridview_juji);
        jujiList=new ArrayList<juji>();
        jujiGridview=new JUJIGridview(this,jujiList,videoPlayer);

        video_name=findViewById(R.id.video_name);
        video_fenlei=findViewById(R.id.video_fenlei);
        video_daoyan=findViewById(R.id.video_daoyan);
        video_zhuyan=findViewById(R.id.video_zhuyan);
        video_juqing=findViewById(R.id.video_juqing);
        video_juqing.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video_juqing:
                if (juqingi==false){
                    video_juqing.setLines(3);
                    juqingi=true;
                }else {
                    video_juqing.setLines(2);
                    juqingi=false;
                }
                break;
        }
    }

    public void url(String id){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/sousuo.php?userid="+id);
                    URLConnection urlConnection=url.openConnection();
                    InputStream inputStream=urlConnection.getInputStream();
                    String jsoni="";
                    int json;
                    while ((json=inputStream.read())!=-1){
                        jsoni+=(char)json;
                    }
                    JSONArray jsonArray=new JSONArray(jsoni);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        int id=jsonObject.getInt("ID");
                        name=jsonObject.getString("影视名称");
                        daoyan=jsonObject.getString("导演");
                        yanyuan=jsonObject.getString("演员");
                        jianjie=jsonObject.getString("简介");
                        year=jsonObject.getString("年份");
                        guojia =jsonObject.getString("国家");
                        leibie=jsonObject.getString("类别");
                        logo=jsonObject.getString("logo");
                        jiesao=jsonObject.getString("介绍");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            video_name.setText(name);
                            video_fenlei.setText(year+"|"+guojia+"|"+leibie);
                            video_daoyan.setText("导演:"+daoyan);
                            video_zhuyan.setText("主演"+yanyuan);
                            video_juqing.setText("剧情:"+jianjie);
                            video_name.setSelected(true);
                            video_daoyan.setSelected(true);
                            video_zhuyan.setSelected(true);
                        }
                    });

                }catch (Exception v){

                }
            }
        }.start();
    }

    public void urll(String id){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/juji.php?userid="+id);
                    URLConnection urlConnection=url.openConnection();
                    InputStream inputStream=urlConnection.getInputStream();
                    String jsoni="";
                    int json;
                    while ((json=inputStream.read())!=-1){
                        jsoni+=(char)json;
                    }
                    System.out.println(jsoni);
                    JSONArray jsonArray=new JSONArray(jsoni);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        int id=jsonObject.getInt("ID");
                        String jujiname=jsonObject.getString("剧集");
                        String href=jsonObject.getString("超链接");
                        juji juji=new juji();
                        juji.jujiname=jujiname;
                        juji.href=href;
                        jujiList.add(juji);
                        System.out.println(href);
                    }
                    handler.sendEmptyMessage(0);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gridView.setAdapter(jujiGridview);
                        }
                    });

                }catch (Exception v){

                }
            }
        }.start();
    }
public void qp(){
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GSYVideoManager.releaseAllVideos();
                finish();
            }
        });
    videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            orientationUtils = new OrientationUtils(Video_Activity.this, videoPlayer);
            orientationUtils.setEnable(true);
            orientationUtils.resolveByClick();

            videoPlayer.setAutoFullWithSize(true);
            videoPlayer.startWindowFullscreen(Video_Activity.this, true, true);
        }
    });
}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK &&event.getRepeatCount()==0){
            GSYVideoManager.releaseAllVideos();
            finish();
//            videoPlayer.clearCurrentCache();//清理缓存
        }
        return super.onKeyDown(keyCode, event);
    }
}