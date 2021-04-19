package com.HUANSHI.HQW.File;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.HUANSHI.HQW.SHEZHI;
import com.HUANSHI.HQW.SHUAXIN;
import com.HUANSHI.HQW.home;

import static android.content.Context.MODE_PRIVATE;

public class CCXML extends Activity {
    public void input_ryj(Context context,String i){
        SharedPreferences sp = context.getSharedPreferences("bfq", MODE_PRIVATE);//创建的文件loginInfo.xml
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        editor.putString("软硬解", i);//写入用户名密码
        editor.commit();//提交修改
    }
    public String inout_ryj(Context context){
        SharedPreferences sp = context.getSharedPreferences("bfq", MODE_PRIVATE);//获取文件
        String spPsw =sp.getString("软硬解", "");//获取用户为ad123的密码
        return spPsw;
    }

    public void input_neihe(Context context,String i){
        SharedPreferences sp = context.getSharedPreferences("bfq", MODE_PRIVATE);//创建的文件loginInfo.xml
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        editor.putString("内核", i);//写入用户名密码
        editor.commit();//提交修改
    }
    public String inout_neihe(Context context){
        SharedPreferences sp = context.getSharedPreferences("bfq", MODE_PRIVATE);//获取文件
        String spPsw =sp.getString("内核", "");//获取用户为ad123的密码
        return spPsw;
    }

    public void input_xuanran(Context context,String i){
        SharedPreferences sp = context.getSharedPreferences("bfq", MODE_PRIVATE);//创建的文件loginInfo.xml
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        editor.putString("渲染", i);//写入用户名密码
        editor.commit();//提交修改
    }
    public String inout_xuanran(Context context){
        SharedPreferences sp = context.getSharedPreferences("bfq", MODE_PRIVATE);//获取文件
        String spPsw =sp.getString("渲染", "");//获取用户为ad123的密码
        return spPsw;
    }

    public void input_huizhi(Context context,String i){
        SharedPreferences sp = context.getSharedPreferences("bfq", MODE_PRIVATE);//创建的文件loginInfo.xml
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        editor.putString("绘制", i);//写入用户名密码
        editor.commit();//提交修改
    }
    public String inout_huizhi(Context context){
        SharedPreferences sp = context.getSharedPreferences("bfq", MODE_PRIVATE);//获取文件
        String spPsw =sp.getString("绘制", "");//获取用户为ad123的密码
        return spPsw;
    }
}
