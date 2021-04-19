package com.HUANSHI.HQW.BaseAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.HUANSHI.HQW.List.juji;
import com.HUANSHI.HQW.R;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class JUJIGridview extends BaseAdapter {
    public StandardGSYVideoPlayer videoPlayer;
    public List<juji> jujiList;
    public List<view> viewList=new ArrayList<view>();
    public Context context;
    public JUJIGridview(Context context,List<juji> jujiList,StandardGSYVideoPlayer videoPlayer){
        this.jujiList=jujiList;
        this.context=context;
        this.videoPlayer=videoPlayer;

    }

    @Override
    public int getCount() {
        return jujiList.size();
    }

    @Override
    public juji getItem(int position) {
        return jujiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view view;
        if (convertView==null){
            view=new view();
            convertView= LayoutInflater.from(context).inflate(R.layout.juji,null);
            view.juji_name=convertView.findViewById(R.id.juji_name);
            viewList.add(view);
            convertView.setTag(view);
        }
        else {
            view=(view)convertView.getTag();
        }

        view.juji_name.setText(jujiList.get(position).jujiname);
        view.juji_name.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         for (int i=0;i<getCount();i++){
                             viewList.get(i).juji_name.setTextColor(Color.WHITE);
                             viewList.get(i).juji_name.setBackgroundColor(Color.BLACK);
                         }

                         view.juji_name.setTextColor(Color.BLACK);
                         view.juji_name.setBackgroundColor(Color.WHITE);


                         videoPlayer.setNeedShowWifiTip(false);
                         videoPlayer.release();
                         videoPlayer.setUpLazy(jujiList.get(position).href, true, null, null, jujiList.get(position).jujiname);
                         videoPlayer.startPlayLogic();
                     }
                 });
        return convertView;
    }

class view{
        TextView juji_name;
}
}
