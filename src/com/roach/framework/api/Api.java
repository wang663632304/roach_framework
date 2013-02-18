package com.roach.framework.api;

import org.apache.http.client.CookieStore;

import android.content.Context;

import com.roach.framework.http.AsyncHttpClient;
import com.roach.framework.http.AsyncHttpResponseHandler;
import com.roach.framework.http.PersistentCookieStore;
import com.roach.framework.http.RequestParams;

public class Api {

protected static AsyncHttpClient client;
    
    public static Api getInstance(Context context){
    	if(client==null){
    		client = new AsyncHttpClient();
    		System.out.println("client is:"+client);
    		client.addHeader("Connection", "Close");
        	client.addHeader("Accept", "*/*"); 
        	CookieStore cs = new PersistentCookieStore(context);
        	client.setCookieStore(cs);
        	client.setUserAgent("Anatta 1.0 (Android; Android os 4.0.3; zh_CN)"); 
    	}
    	return new Api();
    }
    
    public void getData(Context context,String url,RequestParams request,AsyncHttpResponseHandler responseHandler){
    	client.get(context,url, null, responseHandler);
    }
    
    public void postData(Context context,String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
    	client.post(context, url, params, responseHandler);
    }
    
    /**
     * login
     */
    public void login(Context context,String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
    	client.post(url, params, responseHandler);
    }
    
    public void profile(Context context,String url,AsyncHttpResponseHandler responseHandler){
    	client.get(url, responseHandler);
    }
}
