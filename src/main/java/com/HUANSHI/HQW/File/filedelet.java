package com.HUANSHI.HQW.File;

import com.HUANSHI.HQW.home;

import java.io.File;

public class filedelet extends home {
    public void filedelect(File file){

                if (file.exists()){
                    file.delete();
                }
    }
}
