package com.HUANSHI.HQW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.HUANSHI.HQW.BaseAdapter.HQWGridview;
import com.HUANSHI.HQW.BaseAdapter.HQWListView;
import com.HUANSHI.HQW.File.filedelet;
import com.HUANSHI.HQW.HQW.HQWTWO;
import com.HUANSHI.HQW.List.gridviewlist;
import com.HUANSHI.HQW.List.hqwlist;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class home extends AppCompatActivity  {
    View view1;
    View view2;
    View view3;
    ViewPager viewPager;

    List<hqwlist> hqwlists;
    ListView listView;
    HQWListView hqwListView;

    GridView gridView;
    List<gridviewlist> gridviewlists;
    HQWGridview hqwGridview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.WHITE);
        //状态栏中的文字颜色和图标颜色，需要android系统6.0以上，而且目前只有一种可以修改（一种是深色，一种是浅色即白色）
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_home);

        new filedelet().filedelect(new File(getExternalFilesDir("焕视")+"/焕视.apk"));
        create_viewpager();
    }



    public void create_viewpager(){
        viewPager=findViewById(R.id.HQWViewpager);
        view1= LayoutInflater.from(this).inflate(R.layout.home_one,null);
        view2= LayoutInflater.from(this).inflate(R.layout.home_two,null);
        view3= LayoutInflater.from(this).inflate(R.layout.home_three,null);
        List<View> list=new ArrayList<View>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        HQWViwpager hqwViwpager=new HQWViwpager(this,list);
        viewPager.setAdapter(hqwViwpager);
        viewPager.setPageTransformer(true,new HQWTWO());

        listView=view1.findViewById(R.id.HQWListview);
        hqwlists=new ArrayList<hqwlist>();
        hqwListView=new HQWListView(this,hqwlists);

        gridView= view2.findViewById(R.id.gridview);
        gridviewlists=new ArrayList<gridviewlist>();
        hqwGridview=new HQWGridview(this,gridviewlists);


        RefreshLayout refreshLayout= view1.findViewById(R.id.refreshLayout);
        RefreshLayout refreshLayout_home_two= view2.findViewById(R.id.refreshLayout_home_two);

        new SHUAXIN().shuaxin(this,refreshLayout,refreshLayout_home_two,viewPager,hqwlists,listView,hqwListView,gridView,gridviewlists,hqwGridview,view1,view2,view3);




    }



}