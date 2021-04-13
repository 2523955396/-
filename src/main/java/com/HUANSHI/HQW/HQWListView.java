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

class HQWListView  extends BaseAdapter {
    Context context;
    List<hqwlist> hqwlists;
    public HQWListView(Context context,List<hqwlist> hqwlists){
    this.context=context;
    this.hqwlists=hqwlists;

    }

    @Override
    public int getCount() {
        return hqwlists.size();
    }

    @Override
    public hqwlist getItem(int position) {
        return hqwlists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view view=null;
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.homeone, null);
            view = new view();
            view.name = convertView.findViewById(R.id.homeone_name);
            view.daoyan = convertView.findViewById(R.id.homeone_daoyan);
            view.biglogo = convertView.findViewById(R.id.homeone_biglogo);
            convertView.setTag(view);
        }
        else {
            view=(view)convertView.getTag();
        }

        view.name.setText(hqwlists.get(position).name);
        view.daoyan.setText(hqwlists.get(position).jiesao);
        view.biglogo.setBackground(hqwlists.get(position).biglogo);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Video_Activity.class);
                intent.putExtra("userid",""+hqwlists.get(position).id);
                context.startActivity(intent);
            }
        });



        return convertView;
    }



    class view{
        TextView name;
        TextView daoyan;
        LinearLayout biglogo;
    }

}
