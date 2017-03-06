package com.hds.eshop.Api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hds.eshop.Entity.CategoryRsp;
import com.hds.eshop.Manager.RequestParam;
import com.hds.eshop.Manager.ResponseEntity;


/**
 * Created by gqq on 2017/3/3.
 */

// 服务器接口：获取商品的分类信息
public class ApiCategory implements ApiInterface{

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.CATEGORY;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return null;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseEntity() {
        return CategoryRsp.class;
    }
}
