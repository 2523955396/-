package com.HUANSHI.HQW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListView;


import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
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

    int ifo=0;

    int urlsl=0;
    int urlsl_two=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        create_viewpager();
        url();

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

        RefreshLayout refreshLayout= view1.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hqwListView.hqwlists.removeAll(hqwListView.hqwlists);
                        hqwListView.notifyDataSetChanged();
                    }
                });

                        urlsl=0;
                        url_one();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                url_one();
            }
        });

        RefreshLayout refreshLayout_home_two= view2.findViewById(R.id.refreshLayout_home_two);
        refreshLayout_home_two.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hqwGridview.gridviewlists.removeAll(hqwGridview.gridviewlists);
                        hqwGridview.notifyDataSetChanged();
                    }
                });
                urlsl_two=0;
                url1_two();
            }
        });
        refreshLayout_home_two.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                url1_two();
            }
        });

        listView=view1.findViewById(R.id.HQWListview);
        hqwlists=new ArrayList<hqwlist>();
        hqwListView=new HQWListView(this,hqwlists);

        gridView= view2.findViewById(R.id.gridview);
        gridviewlists=new ArrayList<gridviewlist>();
        hqwGridview=new HQWGridview(this,gridviewlists);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==1){
                    if (ifo==0){
                        url1();
                        ifo=1;
                    }
                }
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void url(){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/home_one.php?sl="+urlsl);
                    URLConnection urlConnection=url.openConnection();
                    InputStream inputStream=urlConnection.getInputStream();
                    String jsoni="";
                    int json;
                    while ((json=inputStream.read())!=-1){
                        jsoni+=(char)json;
                    }
                    if (!jsoni.equals("[]")) {
                        JSONArray jsonArray = new JSONArray(jsoni);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int id = jsonObject.getInt("ID");
                            String name = jsonObject.getString("影视名称");
                            String jiesao = jsonObject.getString("介绍");
                            String biglogo = jsonObject.getString("biglogo");
                            hqwlist hqwlist = new hqwlist();
                            hqwlist.id = id;
                            hqwlist.name = name;
                            hqwlist.jiesao = jiesao;
                            hqwlist.biglogo = drawable(biglogo);
                            hqwlists.add(hqwlist);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listView.setAdapter(hqwListView);
                                urlsl += 10;
                            }
                        });
                    }
                }catch (Exception v){
                }
            }
        }.start();
    }
    public void url_one(){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/home_one.php?sl="+urlsl);
                    URLConnection urlConnection=url.openConnection();
                    InputStream inputStream=urlConnection.getInputStream();
                    String jsoni="";
                    int json;
                    while ((json=inputStream.read())!=-1){
                        jsoni+=(char)json;
                    }
                    if (!jsoni.equals("[]")) {
                        JSONArray jsonArray = new JSONArray(jsoni);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int id = jsonObject.getInt("ID");
                            String name = jsonObject.getString("影视名称");
                            String jiesao = jsonObject.getString("介绍");
                            String biglogo = jsonObject.getString("biglogo");
                            hqwlist hqwlist = new hqwlist();
                            hqwlist.id = id;
                            hqwlist.name = name;
                            hqwlist.jiesao = jiesao;
                            hqwlist.biglogo = drawable(biglogo);
                            hqwListView.hqwlists.add(hqwlist);


                        }
                        urlsl+=10;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hqwListView.notifyDataSetChanged();
                            }
                        });


                    }
                }catch (Exception v){
                }
            }
        }.start();
    }




    public void url1(){

        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/home_two.php?sl="+urlsl_two);
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
                        String name=jsonObject.getString("影视名称");
                        String logo=jsonObject.getString("logo");
                        String huazhi=jsonObject.getString("画质");
                        gridviewlist gridviewlist=new gridviewlist();
                        gridviewlist.id=id;
                        gridviewlist.name=name;
                        gridviewlist.logo=drawable(logo);
                        gridviewlist.huazhi=huazhi;
                        gridviewlists.add(gridviewlist);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gridView.setAdapter(hqwGridview);
                            urlsl_two += 20;
                        }
                    });
                }catch (Exception v){

                }
            }
        }.start();
    }
    public void url1_two(){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/home_two.php?sl="+urlsl_two);
                    URLConnection urlConnection=url.openConnection();
                    InputStream inputStream=urlConnection.getInputStream();
                    String jsoni="";
                    int json;
                    while ((json=inputStream.read())!=-1){
                        jsoni+=(char)json;
                    }
                    if (!jsoni.equals("[]")) {
                        JSONArray jsonArray = new JSONArray(jsoni);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int id = jsonObject.getInt("ID");
                            String name = jsonObject.getString("影视名称");
                            String logo = jsonObject.getString("logo");
                            String huazhi = jsonObject.getString("画质");
                            gridviewlist gridviewlist = new gridviewlist();
                            gridviewlist.id = id;
                            gridviewlist.name = name;
                            gridviewlist.logo = drawable(logo);
                            gridviewlist.huazhi = huazhi;
                            hqwGridview.gridviewlists.add(gridviewlist);

                        }
                        urlsl_two += 20;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hqwGridview.notifyDataSetChanged();
                            }
                        });
                    }
                }catch (Exception v){

                }
            }
        }.start();


    }









    public Bitmap bitmap(String img) {

        Bitmap bitmap = null;

        try {
            URL url = new URL(img);
            URLConnection urlConnection = url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (Exception v) {
        }
        return bitmap;
    }

    public Drawable drawable(String img){
        Drawable drawable = null;

        try {
            URL url = new URL(img);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            drawable=Drawable.createFromStream(inputStream,null);
            return drawable;
        } catch (Exception v) {
        }
        return drawable;
    }
}