package com.HUANSHI.HQW;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.HUANSHI.HQW.File.CCXML;

public class SHEZHI extends  SHUAXIN implements View.OnClickListener {
    Context context;
    View view3;
    Switch ryj;
    Switch neihe1;
    Switch neihe2;
    Switch neihe3;
    Switch xuanran1;
    Switch xuanran2;
    Switch huizhi1;
    Switch huizhi2;
    Switch huizhi3;
    Switch huizhi4;
    TextView hexing;
    TextView shengming;

    CCXML ccxml;
   public SHEZHI(Context context, View view3){
        this.context=context;
        this.view3=view3;
        find();
    }
    public void find(){
      ryj=view3.findViewById(R.id.ryj);
      ryj.setOnClickListener(this);

      neihe1=view3.findViewById(R.id.neihe1);
      neihe2=view3.findViewById(R.id.neihe2);
      neihe3=view3.findViewById(R.id.neihe3);
      neihe1.setOnClickListener(this);
      neihe2.setOnClickListener(this);
      neihe3.setOnClickListener(this);

      xuanran1=view3.findViewById(R.id.xuanran1);
      xuanran2=view3.findViewById(R.id.xuanran2);
      xuanran1.setOnClickListener(this);
      xuanran2.setOnClickListener(this);

      huizhi1=view3.findViewById(R.id.huizhi1);
      huizhi2=view3.findViewById(R.id.huizhi2);
      huizhi3=view3.findViewById(R.id.huizhi3);
      huizhi4=view3.findViewById(R.id.huizhi4);
      huizhi1.setOnClickListener(this);
      huizhi2.setOnClickListener(this);
      huizhi3.setOnClickListener(this);
      huizhi4.setOnClickListener(this);

      hexing=view3.findViewById(R.id.hexing);
      hexing.setOnClickListener(this);
        shengming=view3.findViewById(R.id.shengming);
        shengming.setOnClickListener(this);



      ccxml= new CCXML();
        bfqszif();
    }

public void bfqszif(){
    if (ccxml.inout_ryj(context).equals(""))
    {
        ccxml.input_ryj(context,"0");
    }else if (ccxml.inout_ryj(context).equals("0"))
    {

        ryj.setChecked(false);
    }else if (ccxml.inout_ryj(context).equals("1")){
        ryj.setChecked(true);
    }

    if (ccxml.inout_neihe(context).equals(""))
    {
        ccxml.input_neihe(context,"0");
        neihe1.setChecked(true);
    }else if (ccxml.inout_neihe(context).equals("0"))
    {
        neihe1.setChecked(true);
    }else if (ccxml.inout_neihe(context).equals("1")){
        neihe2.setChecked(true);
    }else if (ccxml.inout_neihe(context).equals("2")){
        neihe3.setChecked(true);
    }

    if (ccxml.inout_xuanran(context).equals(""))
    {
        ccxml.input_xuanran(context,"0");
        xuanran1.setChecked(true);
    }else if (ccxml.inout_xuanran(context).equals("0"))
    {
        xuanran1.setChecked(true);
    }else if (ccxml.inout_xuanran(context).equals("1")){
        xuanran2.setChecked(true);
    }

    if (ccxml.inout_huizhi(context).equals(""))
    {
        ccxml.input_huizhi(context,"0");
        huizhi1.setChecked(true);
    }else if (ccxml.inout_huizhi(context).equals("0"))
    {
        huizhi1.setChecked(true);
    }else if (ccxml.inout_huizhi(context).equals("1")){
        huizhi2.setChecked(true);
    }else if (ccxml.inout_huizhi(context).equals("2")){
        huizhi3.setChecked(true);
    }else if (ccxml.inout_huizhi(context).equals("3")){
        huizhi4.setChecked(true);
    }


}
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ryj:
                if (ccxml.inout_ryj(context).equals("0"))
                {
                ccxml.input_ryj(context,"1");
                ryj.setChecked(true);
                    Toast.makeText(context,"开启硬解模式",Toast.LENGTH_SHORT).show();
                }else if (ccxml.inout_ryj(context).equals("1")){
                    ccxml.input_ryj(context,"0");
                    ryj.setChecked(false);
                    Toast.makeText(context,"开启软解模式",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.neihe1:
                    ccxml.input_neihe(context,"0");
                    neihe1.setChecked(true);
                    neihe2.setChecked(false);
                    neihe3.setChecked(false);

                break;
            case R.id.neihe2:
                ccxml.input_neihe(context,"1");
                neihe1.setChecked(false);
                neihe2.setChecked(true);
                neihe3.setChecked(false);
                break;
            case R.id.neihe3:
                ccxml.input_neihe(context,"2");
                neihe1.setChecked(false);
                neihe2.setChecked(false);
                neihe3.setChecked(true);
                break;
            case R.id.xuanran1:
                ccxml.input_xuanran(context,"0");
                xuanran1.setChecked(true);
                xuanran2.setChecked(false);
                break;
            case R.id.xuanran2:
                ccxml.input_xuanran(context,"1");
                xuanran1.setChecked(false);
                xuanran2.setChecked(true);
                break;
            case R.id.huizhi1:
                ccxml.input_huizhi(context,"0");
                huizhi1.setChecked(true);
                huizhi2.setChecked(false);
                huizhi3.setChecked(false);
                huizhi4.setChecked(false);
                break;
            case R.id.huizhi2:
                ccxml.input_huizhi(context,"1");
                huizhi1.setChecked(false);
                huizhi2.setChecked(true);
                huizhi3.setChecked(false);
                huizhi4.setChecked(false);
                break;
            case R.id.huizhi3:
                ccxml.input_huizhi(context,"2");
                huizhi1.setChecked(false);
                huizhi2.setChecked(false);
                huizhi3.setChecked(true);
                huizhi4.setChecked(false);
                break;
            case R.id.huizhi4:
                ccxml.input_huizhi(context,"3");
                huizhi1.setChecked(false);
                huizhi2.setChecked(false);
                huizhi3.setChecked(false);
                huizhi4.setChecked(true);
                break;
            case R.id.hexing:
                AlertDialog alertDialog=new AlertDialog.Builder(context).setMessage(
                        "焕视内核调节说明:\n软硬解:\n软解是用CPU解析视频,硬解是用GPU解析视频" +
                                "，CPU解析相对于GPU解析而言CPU占用内存更多，" +
                                "但是画质更好,而GPU解析占用内存相对少但是画质不佳\n" +
                                "播放器内核:\n播放器的话我们采用3种方式，默认使用最强大的exo(支持的解析模式最多)，再然后推荐用IJK，" +
                                "这个具体看使用场景，最后使用原生，各有各的优缺点，目前exo内核并未发现问题\n" +
                                "渲染模式:" +
                                "渲染模式顾名思义就是在视频播放的时候进行视频渲染，让视频更加光鲜亮丽\n" +
                                "绘制：\n" +
                                "三种绘制模式，每个模式体验不一样，喜欢捣鼓的可以试着用用\n" +
                                "焕视v2.0项目播放器采用的是GSYVideoPlayer(项目地址https://github.com/CarGuo/GSYVideoPlayer),播放器很强大，但是因为时间原因，我得在" +
                                "4月底开发出焕视v2.0的最后一版，然后进行未来语言Flutter的学习了。" +
                                "所以焕视第二次见就会停版状态,但是这个软件会运维下去。"
                ).create();
                alertDialog.show();
                break;
            case R.id.shengming:
                AlertDialog alertDialog2=new AlertDialog.Builder(context).setMessage("" +
                        "本软件仅作为个人学术研究\n本软件未具备任何传播分享功能\n本软件资源均来自互联网\n本软件不得进行非法售卖,如因本软件触犯法律,用户自行承担法律责任").create();
                alertDialog2.show();
                break;
        }
    }
}
