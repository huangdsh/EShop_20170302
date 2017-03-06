package com.hds.eshop.Entity;

import com.google.gson.annotations.SerializedName;
import com.hds.eshop.Manager.ResponseEntity;

import java.util.List;

/**
 * Created by gqq on 2017/2/10.
 */

// 暂时使用的商品分类的响应体
public class CategoryRsp extends ResponseEntity {

    @SerializedName("data")
    private List<CategoryPrimary> mData;

    public List<CategoryPrimary> getData() {
        return mData;
    }
}
