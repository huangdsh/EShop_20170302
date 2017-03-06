package com.hds.eshop.Api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hds.eshop.Entity.GoodsInfoReq;
import com.hds.eshop.Entity.GoodsInfoRsp;
import com.hds.eshop.Manager.RequestParam;
import com.hds.eshop.Manager.ResponseEntity;


/**
 * Created by gqq on 2017/3/3.
 */
// 服务器接口：商品详情的请求
public class ApiGoodsInfo implements ApiInterface{

    // 构建请求体数据
    GoodsInfoReq mGoodsInfoReq;

    public ApiGoodsInfo(int goodsId) {
        mGoodsInfoReq = new GoodsInfoReq();
        mGoodsInfoReq.setGoodsId(goodsId);
    }

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.GOODS_INFO;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return mGoodsInfoReq;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseEntity() {
        return GoodsInfoRsp.class;
    }
}
