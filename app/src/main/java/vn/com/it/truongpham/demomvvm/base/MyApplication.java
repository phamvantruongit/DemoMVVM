package vn.com.it.truongpham.demomvvm.base;

import android.app.Application;

import vn.com.it.truongpham.demomvvm.data.rest.ApiClient;
import vn.com.it.truongpham.demomvvm.data.rest.RestApi;

public class MyApplication extends Application {
    public static RestApi restApi;
    @Override
    public void onCreate() {
        super.onCreate();
        restApi=ApiClient.getInstance(this).create(RestApi.class);
    }
}
