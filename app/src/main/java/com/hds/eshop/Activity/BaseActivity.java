package com.hds.eshop.Activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.hds.eshop.Api.ApiInterface;
import com.hds.eshop.Manager.EShopClient;
import com.hds.eshop.Manager.ResponseEntity;
import com.hds.eshop.Manager.UICallback;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by gqq on 2017/2/24.
 */

// 通用的Activity的基类
public abstract class BaseActivity extends TransitionActivity{

    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());

        mUnbinder = ButterKnife.bind(this);

        initView();
    }

    protected abstract void initView();

    // 让子类来告诉我们布局
    @LayoutRes
    protected abstract int getContentViewLayout();

    // 基类中提供一个请求的方法
    protected Call enqueue(final ApiInterface apiInterface){

        UICallback uiCallback = new UICallback() {
            @Override
            public void onBusinessResponse(boolean isSucces, ResponseEntity responseEntity) {
                // 处理数据：提供给使用者处理
                BaseActivity.this.onBusinessResponse(apiInterface.getPath(),isSucces,responseEntity);
            }
        };

        return EShopClient.getInstance().enqueue(apiInterface,uiCallback,getClass().getSimpleName());
    }

    protected abstract void onBusinessResponse(String path, boolean isSucces, ResponseEntity responseEntity);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消请求
        EShopClient.getInstance().CancelByTag(getClass().getSimpleName());
        mUnbinder.unbind();
        mUnbinder = null;
    }
}
