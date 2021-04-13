package com.HUANSHI.HQW;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Handler;

class HQWGridview  extends BaseAdapter {
    Context context;
    List<gridviewlist> gridviewlists;
    public HQWGridview(Context context,List<gridviewlist> gridviewlists){
        this.context=context;
        this.gridviewlists=gridviewlists;

    }

    @Override
    public int getCount() {
        return gridviewlists.size();
    }

    @Override
    public gridviewlist getItem(int position) {
        return gridviewlists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            view view=null;
            if (convertView==null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.gridview, null);
                view = new view();
                view.mc = convertView.findViewById(R.id.gridview_mc);
                view.hz = convertView.findViewById(R.id.gridview_hz);
                view.hb = convertView.findViewById(R.id.gridview_hb);
                convertView.setTag(view);
            }
            else {
                view=(view)convertView.getTag();
            }
            view.mc.setText(gridviewlists.get(position).name);
            view.mc.setSelected(true);//字体滚动
            view.hz.setText(gridviewlists.get(position).huazhi);
            view.hb.setBackground(gridviewlists.get(position).logo);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Video_Activity.class);
                intent.putExtra("userid",""+gridviewlists.get(position).id);
                context.startActivity(intent);
            }
        });



        return convertView;
    }



    class view{
        TextView mc;
        TextView hz;
        LinearLayout hb;
    }

}
