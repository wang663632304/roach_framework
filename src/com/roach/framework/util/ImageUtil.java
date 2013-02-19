package com.roach.framework.util;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;

public class ImageUtil {
	
	public static Bitmap decodeImgFileDescriptor(FileDescriptor fileDescriptor) throws FileNotFoundException, IOException{
		BitmapFactory.Options optns=new BitmapFactory.Options();
		optns.inJustDecodeBounds=true;
		Bitmap tmp=null;
		try{
			tmp=BitmapFactory.decodeFileDescriptor(fileDescriptor,null,optns);
		}catch(OutOfMemoryError e){
			e.printStackTrace();
		}
		if(tmp!=null){
			if(!tmp.isRecycled()){
				tmp.recycle();
				System.gc();
			}
			tmp=null;
		}
		optns.inPreferredConfig=Config.RGB_565;
		optns.inJustDecodeBounds=false;
		optns.inPurgeable=true;
		optns.inInputShareable=true;
        FileInputStream fis=new FileInputStream(fileDescriptor);
        Bitmap bmp=null;
        try{
        	bmp=BitmapFactory.decodeStream(fis, null, optns);
//        	bmp=BitmapFactory.decodeStream(fis);
        }catch(OutOfMemoryError e){
			e.printStackTrace();
		}
        fis.close();
        return bmp;
	}

}
