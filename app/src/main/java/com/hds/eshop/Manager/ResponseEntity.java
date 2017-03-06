package com.hds.eshop.Manager;

import com.google.gson.annotations.SerializedName;
import com.hds.eshop.Entity.Status;

/**
 * Created by gqq on 2017/3/1.
 */

// 响应的实体基类：为了防止直接实例化，所以做成抽象类
public abstract class ResponseEntity {

    @SerializedName("status")
    private Status mStatus;

    public Status getStatus() {
        return mStatus;
    }
}
