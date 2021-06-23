package com.genesis.apps.ui.myg.view;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.vo.HSWCouponVO;
import com.genesis.apps.comm.util.DateUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.databinding.ItemHswCouponBinding;
import com.genesis.apps.ui.common.view.listview.BaseRecyclerViewAdapter2;
import com.genesis.apps.ui.common.view.viewholder.BaseViewHolder;


public class HswCouponAdapter extends BaseRecyclerViewAdapter2<HSWCouponVO> {

    public HswCouponAdapter() {

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("recyclerview test2", "create :");
        return new ItemHswCoupon(getView(parent, R.layout.item_hsw_coupon));
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        Log.v("recyclerview onBindViewHolder", "position pos:" + position);
//                super.onBindViewHolder(holder, position);
        holder.onBindView(getItem(position), position);

    }

    private static class ItemHswCoupon extends BaseViewHolder<HSWCouponVO, ItemHswCouponBinding> {
        public ItemHswCoupon(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(HSWCouponVO item) {

        }

        @Override
        public void onBindView(HSWCouponVO item, int pos) {
            getBinding().setData(item);
        }

        @Override
        public void onBindView(HSWCouponVO item, int pos, SparseBooleanArray selectedItems) {

        }

    }
}