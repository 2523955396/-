package com.HUANSHI.HQW;

import android.content.Context;

import com.HUANSHI.HQW.File.CCXML;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.player.SystemPlayerManager;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;

public class NEIHE {
    Context context;
    public NEIHE(Context context){
        this.context=context;
    }

    public void neihe(){
        CCXML ccxml=new CCXML();
        if (ccxml.inout_ryj(context).equals("0")){
            GSYVideoType.disableMediaCodec();
        }
        else if (ccxml.inout_ryj(context).equals("1")){
            GSYVideoType.enableMediaCodec();
        }

        if (ccxml.inout_neihe(context).equals("0")){
            PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        }else if (ccxml.inout_neihe(context).equals("1")){
            PlayerFactory.setPlayManager(SystemPlayerManager.class);
        }else if (ccxml.inout_neihe(context).equals("2")){
            PlayerFactory.setPlayManager(IjkPlayerManager.class);
        }

        if (ccxml.inout_xuanran(context).equals("0")){

        }else if (ccxml.inout_xuanran(context).equals("1")){
            GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL);
        }

        if (ccxml.inout_huizhi(context).equals("0")){

        }else if (ccxml.inout_huizhi(context).equals("1")){
            GSYVideoType.setRenderType(GSYVideoType.SUFRACE);
        }else if (ccxml.inout_huizhi(context).equals("2")){
            GSYVideoType.setRenderType(GSYVideoType.GLSURFACE);
        }else if (ccxml.inout_huizhi(context).equals("3")){
            GSYVideoType.setRenderType(GSYVideoType.TEXTURE);

        }

    }
}
