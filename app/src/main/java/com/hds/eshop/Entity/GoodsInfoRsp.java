package com.hds.eshop.Entity;


import com.google.gson.annotations.SerializedName;
import com.hds.eshop.Manager.ResponseEntity;

// 商品详情的响应体
public class GoodsInfoRsp extends ResponseEntity {

    @SerializedName("data")
    private GoodsInfo mData;

    public GoodsInfo getData() {
        return mData;
    }

}
