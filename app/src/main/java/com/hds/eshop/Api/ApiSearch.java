package com.hds.eshop.Api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hds.eshop.Entity.Filter;
import com.hds.eshop.Entity.Pagination;
import com.hds.eshop.Entity.SearchReq;
import com.hds.eshop.Entity.SearchRsp;
import com.hds.eshop.Manager.RequestParam;
import com.hds.eshop.Manager.ResponseEntity;


/**
 * Created by gqq on 2017/3/3.
 */
// 服务器接口：搜索商品，POST请求，有请求体
public class ApiSearch implements ApiInterface{

    private SearchReq mSearchReq;

    // 根据传入的参数构建请求体数据
    public ApiSearch(Filter filter, Pagination pagination) {
        mSearchReq = new SearchReq();
        mSearchReq.setFilter(filter);
        mSearchReq.setPagination(pagination);
    }

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.SEARCH;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return mSearchReq;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseEntity() {
        return SearchRsp.class;
    }
}
