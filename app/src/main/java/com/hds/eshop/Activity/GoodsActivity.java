package com.hds.eshop.Activity;

import android.content.Context;
import android.content.Intent;

import com.hds.eshop.Manager.ResponseEntity;
import com.hds.eshop.R;

/**
 * Created by gqq on 2017/3/2.
 */
// 商品页面
public class GoodsActivity extends BaseActivity{

    private static final String EXTRA_GOODS_ID = "EXTRA_GOOD_ID";

    // 提供一个跳转的方法
    public static Intent getStartIntent(Context context, int goodsId){
        Intent intent = new Intent(context,GoodsActivity.class);
        intent.putExtra(EXTRA_GOODS_ID,goodsId);
        return intent;
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_goods;
    }


    @Override
    protected void initView() {

    }

    // 拿到数据处理
    @Override
    protected void onBusinessResponse(String path, boolean isSucces, ResponseEntity responseEntity) {

    }
}
