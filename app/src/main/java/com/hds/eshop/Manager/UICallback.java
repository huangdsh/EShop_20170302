package com.hds.eshop.Manager;

import android.os.Handler;
import android.os.Looper;

import com.hds.eshop.R;
import com.hds.eshop.Utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * UiCallback:
 * 1. 处理后台线程与主线程的交互：Handler处理
 * 2. 统一处理各种错误情况
 * 3. 将所有请求得到的响应转换为之前定义的响应体基类
 *
 * 这里直接处理错误情况，所以可以直接对外提供一个请求成功的方法，方便处理请求的数据
 */

// 为了统一处理OkHttp的Callback不能更新UI的问题
public abstract class UICallback implements Callback{
    /**
     * 泛型
     * 如果T是String类型，List也必须是String的
     *
     * 自定义泛型：泛型类、泛型接口、泛型方法
     * 泛型通配符：?
     */
//    List<Integer> mStringList = new ArrayList<>();
//    List<T> mTList;
//    Class<T> t;// 代表的是泛型所代表的类，比如是一个Banner，他其实代表的Banner.class
//
//    List<? extends Banner> mList;
//    Class<? extends Banner> mClass;
//
//    public <T extends Banner>T setData(T t){
//
//        new Gson().fromJson(,t);
//        new Gson().fromJson(,mClass);
//
//        return t;
//    }

    // 转换的类型
    private Class<? extends ResponseEntity> mResponseType;


    public UICallback() {
    }

    // 创建一个运行在主线程的Handler
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(final Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // 添加到消息队列里，和handler运行在同一个线程
                onFailureInUI(call, e);
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    onResponseInUI(call, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 对成功和失败的处理
    public void onFailureInUI(Call call, IOException e){
        // 对于请求失败的时候处理
        ToastWrapper.show(R.string.error_network);
        LogUtils.error("onFailureInUi",e);
        onBusinessResponse(false,null);
    }


    public void onResponseInUI(Call call, Response response) throws IOException {
        if (response.isSuccessful()){
            // 要转换成真正的实体类
            ResponseEntity responseEntity = EShopClient.getInstance().getResponseEntity(response, mResponseType);

            // 判断类为null
            if (responseEntity==null || responseEntity.getStatus()==null){
                throw new RuntimeException("Fatal Api Error");
            }

            // 判断是不是真正的拿到数据了
            if (responseEntity.getStatus().isSucceed()){
                // 成功，数据也有
                onBusinessResponse(true,responseEntity);
            }else {
                ToastWrapper.show(responseEntity.getStatus().getErrorDesc());
                onBusinessResponse(false,responseEntity);
            }
        }
    }

    // 告诉我们要转换的实际的实体类型
    public void setResponseType(Class<? extends ResponseEntity> responseType){
        mResponseType = responseType;
    }

    // 给使用者实现的一个方法：处理拿到数据
    public abstract void onBusinessResponse(boolean isSucces,ResponseEntity responseEntity);

}
