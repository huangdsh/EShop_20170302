package com.hds.eshop.Entity;

import com.google.gson.annotations.SerializedName;
import com.hds.eshop.Manager.RequestParam;

// 商品详情请求体
public class GoodsInfoReq extends RequestParam {

    @SerializedName("goods_id")
    private int mGoodsId;

    public int getGoodsId() {
        return mGoodsId;
    }

    public void setGoodsId(int goodsId) {
        mGoodsId = goodsId;
    }
}
