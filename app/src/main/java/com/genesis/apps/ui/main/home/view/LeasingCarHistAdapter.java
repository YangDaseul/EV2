package com.genesis.apps.ui.main.home.view;

import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.RentStatusVO;
import com.genesis.apps.databinding.ItemLeasingHistBinding;
import com.genesis.apps.databinding.ItemLeasingHistMoreBinding;
import com.genesis.apps.ui.common.view.listener.OnSingleClickListener;
import com.genesis.apps.ui.common.view.listview.BaseRecyclerViewAdapter2;
import com.genesis.apps.ui.common.view.viewholder.BaseViewHolder;


public class LeasingCarHistAdapter extends BaseRecyclerViewAdapter2<RentStatusVO> {

    private static final int BOTTOM=0;
    private static final int BODY=1;
    private static OnSingleClickListener onSingleClickListener;
    private int pageLimit=2;
    private boolean isMore=false;
    public LeasingCarHistAdapter(OnSingleClickListener onSingleClickListener) {
        LeasingCarHistAdapter.onSingleClickListener = onSingleClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==BODY)
            return new ItemLeasingHist(getView(parent, R.layout.item_leasing_hist));
        else
            return new ItemLeasingHistMore(getView(parent, R.layout.item_leasing_hist_more));
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        holder.onBindView(getItem(position), position);
    }


    @Override
    public int getItemViewType(int position) {
        try {
            if (isMore&&getItemCount()>2&&position==(getItemCount()-1)) {
                return BOTTOM;
            } else {
                return BODY;
            }
        }catch (Exception e){
            return BODY;
        }
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }


    public class ItemLeasingHist extends BaseViewHolder<RentStatusVO, ItemLeasingHistBinding> {
        public ItemLeasingHist(View itemView) {
            super(itemView);
            getBinding().setListener(onSingleClickListener);
            getBinding().setViewHolder(this);
        }

        @Override
        public void onBindView(RentStatusVO item) {

        }

        @Override
        public void onBindView(RentStatusVO item, int pos) {
            getBinding().setData(item);
            getBinding().btnStatus.setTag(R.id.leasing_car_hist, item);



//            getBinding().btnStatus.setVisibility(View.GONE);
//            getBinding().btnStatus.setOnClickListener(null);
//
//            getBinding().tvVin.setText(item.getVin());
//            getBinding().tvRentPeriod.setText(item.getRentPeriod()+"??????");
//            getBinding().tvDate.setText(DateUtil.getDate(DateUtil.getDefaultDateFormat(item.getSubspDtm(), DateUtil.DATE_FORMAT_yyyyMMddHHmmss), DateUtil.DATE_FORMAT_yyyy_mm_dd_dot));
//
//            switch (item.getAprvStusCd()){
//                case VariableType.LEASING_CAR_APRV_STATUS_CODE_AGREE:
//                    getBinding().tvContents.setText(R.string.gm_carlst_02_10);
//                    getBinding().tvResult.setText(R.string.gm_carlst_02_9);
//                    getBinding().tvResult.setTextAppearance(R.style.LeasingCarTextViewAllow);
//                    break;
//                case VariableType.LEASING_CAR_APRV_STATUS_CODE_REJECT:
//                    getBinding().tvContents.setText(R.string.gm_carlst_02_11);
//                    getBinding().tvResult.setText(R.string.gm_carlst_02_8);
//                    getBinding().tvResult.setTextAppearance(R.style.LeasingCarTextViewReject);
//
//                    getBinding().btnStatus.setVisibility(View.VISIBLE);
//                    getBinding().btnStatus.setOnClickListener(onSingleClickListener);
//                    getBinding().btnStatus.setText(R.string.gm_carlst_02_14);
//                    getBinding().btnStatus.setTag(R.id.leasing_car_hist, item);
//                    break;
//                case VariableType.LEASING_CAR_APRV_STATUS_CODE_WAIT:
//                default:
//                    getBinding().tvContents.setText(R.string.gm_carlst_02_12);
//                    getBinding().tvResult.setText(R.string.gm_carlst_02_7);
//                    getBinding().tvResult.setTextAppearance(R.style.LeasingCarTextViewWait);
//
//                    getBinding().btnStatus.setVisibility(View.VISIBLE);
//                    getBinding().btnStatus.setOnClickListener(onSingleClickListener);
//                    getBinding().btnStatus.setText(R.string.gm_carlst_02_13);
//                    getBinding().btnStatus.setTag(R.id.leasing_car_hist, item);
//                    break;
//            }



        }

        @Override
        public void onBindView(RentStatusVO item, int pos, SparseBooleanArray selectedItems) {

        }

        public String getContents(String aprvStusCd){
            if(TextUtils.isEmpty(aprvStusCd)){
                return getContext().getString(R.string.gm_carlst_02_12);
            }else {
                switch (aprvStusCd) {
                    case VariableType.LEASING_CAR_APRV_STATUS_CODE_AGREE:
                        return getContext().getString(R.string.gm_carlst_02_10);
                    case VariableType.LEASING_CAR_APRV_STATUS_CODE_REJECT:
                        return getContext().getString(R.string.gm_carlst_02_11);
                    case VariableType.LEASING_CAR_APRV_STATUS_CODE_WAIT:
                    default:
                        return getContext().getString(R.string.gm_carlst_02_12);
                }
            }
        }

        public String getResult(String aprvStusCd){
            if(TextUtils.isEmpty(aprvStusCd)){
                return getContext().getString(R.string.gm_carlst_02_7);
            }else {
                switch (aprvStusCd) {
                    case VariableType.LEASING_CAR_APRV_STATUS_CODE_AGREE:
                        return getContext().getString(R.string.gm_carlst_02_9);
                    case VariableType.LEASING_CAR_APRV_STATUS_CODE_REJECT:
                        return getContext().getString(R.string.gm_carlst_02_8);
                    case VariableType.LEASING_CAR_APRV_STATUS_CODE_WAIT:
                    default:
                        return getContext().getString(R.string.gm_carlst_02_7);
                }
            }
        }



//        public String getResultColor(String aprvStusCd){
//            if(TextUtils.isEmpty(aprvStusCd)){
//                return "@color/x_a2a2a2";
//            }else {
//                switch (aprvStusCd) {
//                    case VariableType.LEASING_CAR_APRV_STATUS_CODE_AGREE:
//                        return "@color/x_996449";
//                    case VariableType.LEASING_CAR_APRV_STATUS_CODE_REJECT:
//                        return "@color/x_ce2d2d";
//                    case VariableType.LEASING_CAR_APRV_STATUS_CODE_WAIT:
//                    default:
//                        return "@color/x_a2a2a2";
//                }
//            }
//        }

    }



    private static class ItemLeasingHistMore extends BaseViewHolder<RentStatusVO, ItemLeasingHistMoreBinding> {
        public ItemLeasingHistMore(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(RentStatusVO item) {

        }

        @Override
        public void onBindView(RentStatusVO item, int pos) {
            getBinding().btnMore.setOnClickListener(onSingleClickListener);
        }

        @Override
        public void onBindView(RentStatusVO item, int pos, SparseBooleanArray selectedItems) {

        }

    }

}