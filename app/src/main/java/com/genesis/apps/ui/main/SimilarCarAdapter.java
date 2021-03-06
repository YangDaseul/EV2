package com.genesis.apps.ui.main;

import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.genesis.apps.R;
import com.genesis.apps.comm.model.vo.SimilarVehicleVO;
import com.genesis.apps.comm.util.DateUtil;
import com.genesis.apps.databinding.ItemSimilarCarBinding;
import com.genesis.apps.databinding.ItemSimilarCarHeaderBinding;
import com.genesis.apps.ui.common.view.listview.BaseRecyclerViewAdapter2;
import com.genesis.apps.ui.common.view.viewholder.BaseViewHolder;

import java.util.Calendar;
import java.util.Locale;


public class SimilarCarAdapter extends BaseRecyclerViewAdapter2<SimilarVehicleVO> {

    private static final int HEADER=0;
    private static final int BODY=1;
    private static View.OnClickListener onClickListener;
    public SimilarCarAdapter(View.OnClickListener onClickListener) {
        SimilarCarAdapter.onClickListener = onClickListener;
    }

    private boolean isCelPhNo(){
        if(!TextUtils.isEmpty(((SimilarVehicleVO)getItem(0)).getCelphNo())){
            int HHmm = Integer.parseInt(DateUtil.getDate(Calendar.getInstance(Locale.getDefault()).getTime(), DateUtil.DATE_FORMAT_HHmm));
            if(HHmm < 830 || HHmm > 1730){
                ((SimilarVehicleVO)getItem(0)).setCelphNo("");
            }else{
                return true;
            }
        }
        return false;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==HEADER)
            return new ItemSimilarCarHeader(getView(parent, R.layout.item_similar_car_header), getItemCount());
        else
            return new ItemSimilarCar(getView(parent, R.layout.item_similar_car), isCelPhNo());
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        holder.onBindView(getItem(position), position);

    }

    @Override
    public int getItemViewType(int position) {
        try {
            if (position==0) {
                return HEADER;
            } else {
                return BODY;
            }
        }catch (Exception e){
            return HEADER;
        }
    }

    public void selectItem(int pos, boolean isInit) {
        for (int i = 0; i < getItemCount(); i++) {
            ((SimilarVehicleVO) getItem(i)).setSelect(i==pos&&!((SimilarVehicleVO) getItem(i)).isSelect()&&!isInit);
        }
        notifyDataSetChanged();
    }

    public SimilarVehicleVO getSelectItem(){
        SimilarVehicleVO similarVehicleVO = null;

        for(SimilarVehicleVO item : getItems()){
            if(item.isSelect()){
                similarVehicleVO = item;
                break;
            }
        }

        return similarVehicleVO;
    }



    private static class ItemSimilarCarHeader extends BaseViewHolder<SimilarVehicleVO, ItemSimilarCarHeaderBinding> {

        int totalCnt;

        public ItemSimilarCarHeader(View itemView, int totalCnt) {
            super(itemView);
            this.totalCnt = totalCnt;
        }

        @Override
        public void onBindView(SimilarVehicleVO item) {

        }

        @Override
        public void onBindView(SimilarVehicleVO item, int pos) {
            getBinding().setData(item);
            getBinding().setTotalCnt(totalCnt);
            Glide
                    .with(getContext())
                    .load(item.getVhclImgUri())
                    .format(DecodeFormat.PREFER_RGB_565)
                    .error(R.drawable.img_car) //todo ?????? ????????? ??????
//                    .placeholder(R.drawable.img_car) //todo ????????? ?????? ????????? ??????
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(getBinding().ivCar);
        }

        @Override
        public void onBindView(SimilarVehicleVO item, int pos, SparseBooleanArray selectedItems) {

        }

    }



    private static class ItemSimilarCar extends BaseViewHolder<SimilarVehicleVO, ItemSimilarCarBinding> {

        private boolean allowCheck=false;

        public ItemSimilarCar(View itemView, boolean allowCheck) {
            super(itemView);
            this.allowCheck = allowCheck;
            if(allowCheck) getBinding().lWhole.setOnClickListener(onClickListener);
        }

        @Override
        public void onBindView(SimilarVehicleVO item) {


        }

        @Override
        public void onBindView(SimilarVehicleVO item, int pos) {
            getBinding().lWhole.setTag(R.id.item, item);
            getBinding().lWhole.setTag(R.id.position, pos);
            getBinding().setAllowCheck(allowCheck);
            getBinding().setData(item);
            Glide
                    .with(getContext())
                    .load(item.getVhclImgUri())
                    .format(DecodeFormat.PREFER_RGB_565)
                    .error(R.drawable.img_car) //todo ?????? ????????? ??????
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(getBinding().ivCar);

//            getBinding().tvModel.setText(item.getVhclNm());
//            getBinding().tvCarRgstNo.setText(item.getMdlNm());
//            Glide
//                    .with(getContext())
//                    .load(item.getVhclImgUri())
//                    .format(DecodeFormat.PREFER_RGB_565)
//                    .error(R.drawable.img_car) //todo ?????? ????????? ??????
//                    .placeholder(R.drawable.img_car) //todo ????????? ?????? ????????? ??????
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(getBinding().ivCar);
//
//
//            getBinding().tag.setTagTextSize(DeviceUtil.dip2Pixel(getContext(),12));
//            getBinding().tag.setTagTypeface(typeface);
//
//            getBinding().tag.setGravity(Gravity.CENTER);
//            getBinding().tag.addTag(item.getEtrrClrNm());
//            getBinding().tag.addTag(item.getItrrClrNm());
//            getBinding().tag.addTag(item.getOtpnNm());
//            getBinding().tvSmlrRto.setVisibility(View.VISIBLE);
//            getBinding().tvSmlrRto.setText(String.format(getContext().getString(R.string.gm02_inv01_2), item.getSmlrRto()==null ? "0" : item.getSmlrRto()));
        }

        @Override
        public void onBindView(SimilarVehicleVO item, int pos, SparseBooleanArray selectedItems) {

        }

    }

}