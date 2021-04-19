package com.HUANSHI.HQW.DOWN;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class downloadx {
    String xiazaii;
    String xiazaix;
    int longi;
    int longx;
    public void download(){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL("https://alpan.51huanqi.cn/d/%E7%84%95%E5%A5%87%E8%B5%84%E6%BA%90%E7%AB%99/%E7%A7%BB%E5%8A%A8%E7%AB%AF%E8%BD%AF%E4%BB%B6/%E9%85%B7%E6%88%91%E9%9F%B3%E4%B9%90_9.3.7.7.apk");
                    URLConnection connection=url.openConnection();
                    int long1= Integer.valueOf(connection.getContentLength());
                    xiazaix=String.format("%.2f",long1/1024.00/1024.00)+"MB";
                    longx=long1;
                    InputStream inputStream=connection.getInputStream();
                    File file=new File("/sdcard/焕视/");
                    if (!file.exists()){
                        file.mkdirs();
                    }
                    FileOutputStream fileOutputStream=new FileOutputStream( file.getPath()+"/焕视.apk");
                    int xiazai;
                    byte[] by=new byte[1024];
                    while ((xiazai=inputStream.read(by))!=-1){
                        fileOutputStream.write(by,0,xiazai);
                        File file1=new File(file.getPath()+"/焕视.apk");
                       longi=(int)file1.length();
                        xiazaii=String.format("%.2f",longi/1024.00/1024.00)+"MB";
                        System.out.println("总数"+xiazaix+longx+"下载中"+xiazaii+longi);
                    }



                }catch (Exception v){

                }
            }
        }.start();





    }
}
