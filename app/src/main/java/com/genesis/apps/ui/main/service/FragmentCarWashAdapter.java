package com.genesis.apps.ui.main.service;

import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.genesis.apps.R;
import com.genesis.apps.comm.model.vo.WashGoodsVO;
import com.genesis.apps.databinding.ItemServiceCarWashBinding;
import com.genesis.apps.ui.common.view.listener.OnSingleClickListener;
import com.genesis.apps.ui.common.view.listview.BaseRecyclerViewAdapter2;
import com.genesis.apps.ui.common.view.viewholder.BaseViewHolder;

public class FragmentCarWashAdapter extends BaseRecyclerViewAdapter2<WashGoodsVO> {
    private static OnSingleClickListener singleClickListener;

    public FragmentCarWashAdapter(OnSingleClickListener listener) {
        singleClickListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarWashTicketViewHolder(getView(parent, R.layout.item_service_car_wash));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindView(getItem(position), position);
    }

    //세차 예약 내역 뷰홀더
    public static class CarWashTicketViewHolder extends BaseViewHolder<WashGoodsVO, ItemServiceCarWashBinding> {

        public CarWashTicketViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(WashGoodsVO item) {
            //do nothing
        }

        @Override
        public void onBindView(WashGoodsVO item, int pos) {
            if("Y".equals(item.getRsvtAblYn())) {
                getBinding().llCheck.setVisibility(View.GONE);

                getBinding().lServiceCarWashItem.setEnabled(true);
                getBinding().lServiceCarWashItem.setClickable(true);
            } else {
                getBinding().llCheck.setVisibility(View.VISIBLE);

                getBinding().lServiceCarWashItem.setEnabled(false);
                getBinding().lServiceCarWashItem.setClickable(false);
            }

            //상품명
            getBinding().tvCarWashItemName.setText(item.getGodsNm());

            //할인정보
            getBinding().tvCarWashItemCurrPrice.setText(item.getDsctNm());

            //버튼 클릭 리스너 및 해당 버튼 처리에 필요한 데이터 세팅
            setSingleClickListenerAndData(item);

            Glide.with(getContext())
                    .load(item.getGodsImgUri())
                    .format(DecodeFormat.PREFER_RGB_565)
                    .error(R.drawable.img_car_339_2) //todo 대체 이미지 필요
                    .placeholder(R.drawable.img_car_339_2) //todo 에러시 대체 이미지 필요
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(getBinding().ivCarWashItemImg);
        }

        @Override
        public void onBindView(WashGoodsVO item, int pos, SparseBooleanArray selectedItems) {
            //do nothing
        }

        //리스너를 연결하고, 이를 처리하는데 필요한 데이터도 저장
        private void setSingleClickListenerAndData(WashGoodsVO item) {
            getBinding().lServiceCarWashItem.setOnClickListener(singleClickListener);
            getBinding().lServiceCarWashItem.setTag(R.id.tag_wash_item, item);
        }
    }
}
