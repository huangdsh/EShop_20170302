package com.hds.eshop.Api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hds.eshop.Entity.HomeCategoryRsp;
import com.hds.eshop.Manager.RequestParam;
import com.hds.eshop.Manager.ResponseEntity;
import com.hds.eshop.Api.ApiInterface;
import com.hds.eshop.Api.ApiPath;


/**
 * Created by gqq on 2017/3/3.
 */
// 服务器接口：首页的分类和推荐商品
public class ApiHomeCategory implements ApiInterface {

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.HOME_CATEGORY;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return null;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseEntity() {
        return HomeCategoryRsp.class;
    }
}
