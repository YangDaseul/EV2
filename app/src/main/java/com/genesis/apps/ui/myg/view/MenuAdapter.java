package com.genesis.apps.ui.myg.view;

import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.vo.MenuVO;
import com.genesis.apps.databinding.ItemMenuBinding;
import com.genesis.apps.ui.common.view.listener.OnSingleClickListener;
import com.genesis.apps.ui.common.view.listener.ViewPressEffectHelper;
import com.genesis.apps.ui.common.view.listview.BaseRecyclerViewAdapter2;
import com.genesis.apps.ui.common.view.viewholder.BaseViewHolder;


public class MenuAdapter extends BaseRecyclerViewAdapter2<MenuVO> {

    private static boolean isRecently=false;
    private static OnPositionClickListener onItemClickListener;
    private static boolean isSearch=false;

    public MenuAdapter(OnPositionClickListener onItemClickListener) {
        MenuAdapter.onItemClickListener = onItemClickListener;
    }

    public boolean isIsSearch() {
        return isSearch;
    }

    public void setIsSearch(boolean isSearch) {
        MenuAdapter.isSearch = isSearch;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemMenu(getView(parent, R.layout.item_menu));
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
//                super.onBindViewHolder(holder, position);
        holder.onBindView(getItem(position), position);
    }

    public boolean isRecently() {
        return isRecently;
    }

    public void setRecently(boolean recently) {
        isRecently = recently;
    }

    public void deleteItem(int position){
        if(isRecently()){
            remove(position);
        }
    }

    private static class ItemMenu extends BaseViewHolder<MenuVO, ItemMenuBinding> {
        public ItemMenu(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(MenuVO item) {

        }

        @Override
        public void onBindView(MenuVO item, int pos) {
            ViewPressEffectHelper.attach(getBinding().btnDel);
            getBinding().tvName.setText(item.getName());
            getBinding().btnDel.setVisibility(isRecently ? View.VISIBLE : View.GONE);
            getBinding().btnDel.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    onItemClickListener.onClick(v,pos);
                }
            });

            getBinding().lWhole.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    onItemClickListener.onClick(v,pos);
                }
            });

            if(!isSearch&&!TextUtils.isEmpty(item.getCategory())){
                getBinding().tvCategory.setVisibility(View.VISIBLE);
                getBinding().tvCategory.setText(item.getCategory());
            }else{
                getBinding().tvCategory.setVisibility(View.GONE);
            }
        }

        @Override
        public void onBindView(MenuVO item, int pos, SparseBooleanArray selectedItems) {

        }

    }

    public interface OnPositionClickListener {
        void onClick(View v, int position);
    }
}