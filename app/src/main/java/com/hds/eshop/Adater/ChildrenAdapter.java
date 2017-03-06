package com.hds.eshop.Adater;

import android.view.View;
import android.widget.TextView;

import com.hds.eshop.Entity.CategoryBase;
import com.hds.eshop.R;

import butterknife.BindView;

/**
 * Created by gqq on 2017/2/24.
 */

// 子分类的适配器
public class ChildrenAdapter extends BaseListAdapter<CategoryBase,ChildrenAdapter.ViewHolder> {

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_children_category;
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
            mTextCategory.setText(getItem(position).getName());
        }
    }
}
