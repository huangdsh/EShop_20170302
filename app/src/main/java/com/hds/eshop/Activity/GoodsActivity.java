package com.hds.eshop.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hds.eshop.Adater.GoodsPagerAdapter;
import com.hds.eshop.Api.ApiGoodsInfo;
import com.hds.eshop.Api.ApiPath;
import com.hds.eshop.Entity.GoodsInfo;
import com.hds.eshop.Entity.GoodsInfoRsp;
import com.hds.eshop.Manager.GoodsSpecPopupWindow;
import com.hds.eshop.Manager.ResponseEntity;
import com.hds.eshop.Manager.ToastWrapper;
import com.hds.eshop.Manager.ToolbarWrapper;
import com.hds.eshop.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by gqq on 2017/3/2.
 */
// 商品页面
public class GoodsActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private static final String EXTRA_GOODS_ID = "EXTRA_GOODS_ID";
    private GoodsPagerAdapter mAdapter;
    private GoodsInfo mGoodsInfo;
    private GoodsSpecPopupWindow mGoodsSpecPopupWindow;

    /**
     * @param context 上下文
     * @param goodsId 用于查询的商品Id
     * @return 返回的Intent，用于跳转页面
     */
    public static Intent getStartIntent(Context context, int goodsId) {
        Intent intent = new Intent(context, GoodsActivity.class);
        intent.putExtra(EXTRA_GOODS_ID, goodsId);
        return intent;
    }

    @BindViews({R.id.text_tab_goods, R.id.text_tab_details, R.id.text_tab_comments})
    List<TextView> tvTabList;
    @BindView(R.id.pager_goods)
    ViewPager goodsPager;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_goods;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_goods, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_share) {
            ToastWrapper.show(R.string.action_share);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {

        new ToolbarWrapper(this);

//        chooseTab(0);

        goodsPager.addOnPageChangeListener(this);

        int goodsId = getIntent().getIntExtra(EXTRA_GOODS_ID, 0);
        enqueue(new ApiGoodsInfo(goodsId));

    }

    // 得到数据处理数据
    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity responseEntity) {
        switch (apiPath) {
            case ApiPath.GOODS_INFO:
                if (success) {
                    GoodsInfoRsp goodsInfoRsp = (GoodsInfoRsp) responseEntity;
                    mGoodsInfo = goodsInfoRsp.getData();
                    goodsPager.setAdapter(new GoodsPagerAdapter(getSupportFragmentManager(), mGoodsInfo));
                    chooseTab(0);
                }
                break;
            default:
                throw new UnsupportedOperationException(apiPath);
        }
    }

    // ViewPager的滑动监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 改变文本显示
        chooseTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void selectPage(int position) {
        goodsPager.setCurrentItem(position, false);
        chooseTab(position);
    }

    @OnClick({R.id.text_tab_goods, R.id.text_tab_details, R.id.text_tab_comments})
    public void selectTab(TextView textView) {
        int position = tvTabList.indexOf(textView);
        goodsPager.setCurrentItem(position);
        chooseTab(position);

    }

    @OnClick({R.id.button_show_cart, R.id.button_add_cart, R.id.button_buy})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_show_cart:
                break;
            case R.id.button_add_cart:
            case R.id.button_buy:
                if (mGoodsInfo == null) return;
                // 弹出一个选择框
                if (mGoodsInfo == null) return;
                if (mGoodsSpecPopupWindow == null) {
                    mGoodsSpecPopupWindow = new GoodsSpecPopupWindow(this, mGoodsInfo);
                }
                mGoodsSpecPopupWindow.show(new GoodsSpecPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(int number) {
                        ToastWrapper.show("Confirm" + number);
                        mGoodsSpecPopupWindow.dismiss();
                    }
                });
                break;
            default:
                throw new UnsupportedOperationException("Unsupported View Id");
        }
    }


    private void chooseTab(int position) {
        Resources res = getResources();
        for (int i = 0; i < tvTabList.size(); i++) {
            tvTabList.get(i).setSelected(i == position);
            float textSize = i == position ? res.getDimension(R.dimen.font_large) :
                    res.getDimension(R.dimen.font_normal);
            tvTabList.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
    }
}
