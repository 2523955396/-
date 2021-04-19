package com.HUANSHI.HQW;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.HUANSHI.HQW.BaseAdapter.HQWGridview;
import com.HUANSHI.HQW.BaseAdapter.HQWListView;
import com.HUANSHI.HQW.DOWN.Drawable;
import com.HUANSHI.HQW.List.gridviewlist;
import com.HUANSHI.HQW.List.hqwlist;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Handler;

public class url_one_two_shree extends SHUAXIN{
    int url_one;
    int url_two;
    String url_twoss;

    public void url_one(HQWListView hqwListView, ListView listView, List<hqwlist> hqwlists, int urlone,View view1){
       LinearLayout jiazai=view1.findViewById(R.id.home_one_jiazai);
        TextView jiazaii=view1.findViewById(R.id.home_one_jiazaii);
        ProgressBar jiazaiix=view1.findViewById(R.id.home_one_jiazaiix);
        url_one=urlone;
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/home_one.php?sl="+url_one);
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
                            hqwlist.biglogo =new Drawable().drawable(biglogo);
                            hqwlists.add(hqwlist);
                            jiazaii.setText("灵动载入中:"+(i+1)+"/"+jsonArray.length());
                            jiazaiix.setProgress(i+1);
                            jiazaiix.setMax(jsonArray.length());
                            if ((i+1)==jsonArray.length()){
                                jiazai.setVisibility(View.INVISIBLE);
                            }


                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listView.setAdapter(hqwListView);
                            }
                        });
                    }
                }catch (Exception v){
                }
            }
        }.start();
    }


    public void  url_onei(HQWListView hqwListView,int urlone,View view1){
        LinearLayout jiazai=view1.findViewById(R.id.home_one_jiazai);
        TextView jiazaii=view1.findViewById(R.id.home_one_jiazaii);
        ProgressBar jiazaiix=view1.findViewById(R.id.home_one_jiazaiix);
        jiazai.setVisibility(View.VISIBLE);
        jiazaii.setText("");
        jiazaiix.setProgress(0);
        url_one=urlone;
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/home_one.php?sl="+url_one);
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
                            hqwlist.biglogo = new Drawable().drawable(biglogo);
                            hqwListView.hqwlists.add(hqwlist);
                            jiazaii.setText("资源载入中:"+(i+1)+"/"+jsonArray.length());
                            jiazaiix.setProgress(i+1);
                            jiazaiix.setMax(jsonArray.length());
                            if ((i+1)==jsonArray.length()){
                                jiazai.setVisibility(View.INVISIBLE);
                            }
                        }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                hqwListView.notifyDataSetChanged();
                            }
                        });
                    }
                    else {
                        jiazai.setVisibility(View.INVISIBLE);
                    }

                }catch (Exception v){
                }
            }
        }.start();
    }

    public void url_two(GridView gridView, List<gridviewlist> gridviewlists, HQWGridview hqwGridview,int urlsl_two,View view2){
        LinearLayout jiazai=view2.findViewById(R.id.home_two_jiazai);
        TextView jiazaii=view2.findViewById(R.id.home_two_jiazaii);
        ProgressBar jiazaiix=view2.findViewById(R.id.home_two_jiazaiix);
        url_two=urlsl_two;
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/home_two.php?sl="+url_two);
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
                        gridviewlist.logo=new Drawable().drawable(logo);
                        gridviewlist.huazhi=huazhi;
                        gridviewlists.add(gridviewlist);
                        jiazaii.setText("灵动载入中:"+(i+1)+"/"+jsonArray.length());
                        jiazaiix.setProgress(i+1);
                        jiazaiix.setMax(jsonArray.length());
                        if ((i+1)==jsonArray.length()){
                            jiazai.setVisibility(View.INVISIBLE);
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gridView.setAdapter(hqwGridview);
                        }
                    });
                }catch (Exception v){

                }
            }
        }.start();
    }
    public void url_twoi(HQWGridview hqwGridview,int urlsl_two,View view2){
        LinearLayout jiazai=view2.findViewById(R.id.home_two_jiazai);
        TextView jiazaii=view2.findViewById(R.id.home_two_jiazaii);
        ProgressBar jiazaiix=view2.findViewById(R.id.home_two_jiazaiix);
        jiazai.setVisibility(View.VISIBLE);
        jiazaii.setText("");
        jiazaiix.setProgress(0);
        url_two=urlsl_two;
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/home_two.php?sl="+url_two);
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
                            gridviewlist.logo =new Drawable().drawable(logo);;
                            gridviewlist.huazhi = huazhi;
                            hqwGridview.gridviewlists.add(gridviewlist);

                            jiazaii.setText("资源载入中:"+(i+1)+"/"+jsonArray.length());
                            jiazaiix.setProgress(i+1);
                            jiazaiix.setMax(jsonArray.length());
                            if ((i+1)==jsonArray.length()){
                                jiazai.setVisibility(View.INVISIBLE);
                            }

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hqwGridview.notifyDataSetChanged();
                            }
                        });
                    }
                    else {
                        jiazai.setVisibility(View.INVISIBLE);
                    }
                }catch (Exception v){

                }
            }
        }.start();


    }


    public void url_twoss(HQWGridview hqwGridview, String urlsl_two, View view2, Context context){
        LinearLayout jiazai=view2.findViewById(R.id.home_two_jiazai);
        TextView jiazaii=view2.findViewById(R.id.home_two_jiazaii);
        ProgressBar jiazaiix=view2.findViewById(R.id.home_two_jiazaiix);
        jiazai.setVisibility(View.VISIBLE);
        jiazaii.setText("");
        jiazaiix.setProgress(0);
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://hs.51huanqi.cn/sousuo.php?userid="+urlsl_two);
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
                            gridviewlist.logo =new Drawable().drawable(logo);;
                            gridviewlist.huazhi = huazhi;
                            hqwGridview.gridviewlists.add(gridviewlist);

                            jiazaii.setText("资源载入中:"+(i+1)+"/"+jsonArray.length());
                            jiazaiix.setProgress(i+1);
                            jiazaiix.setMax(jsonArray.length());
                            if ((i+1)==jsonArray.length()){
                                jiazai.setVisibility(View.INVISIBLE);
                            }

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hqwGridview.notifyDataSetChanged();
                            }
                        });
                    }
                    else {
                        jiazai.setVisibility(View.INVISIBLE);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context,"资源不存在,请下拉刷新或重置",Toast.LENGTH_LONG).show();
                            }
                        });


                    }
                }catch (Exception v){

                }
            }
        }.start();


    }
}
