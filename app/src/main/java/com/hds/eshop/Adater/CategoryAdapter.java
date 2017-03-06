package com.hds.eshop.Adater;

import android.view.View;
import android.widget.TextView;

import com.hds.eshop.Entity.CategoryPrimary;
import com.hds.eshop.R;

import butterknife.BindView;

/**
 * Created by gqq on 2017/2/24.
 */

// 一级分类的适配器
public class CategoryAdapter extends BaseListAdapter<CategoryPrimary,CategoryAdapter.ViewHolder> {

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_primary_category;
    }

    @Override
    protected ViewHolder getItemViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseListAdapter.ViewHolder{

        @BindView(R.id.text_category)
        TextView mTextCategory;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position) {
            // 数据的展示
            mTextCategory.setText(getItem(position).getName());
        }
    }
}
