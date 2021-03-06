package com.genesis.apps.ui.main.service;

import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.gra.WSH_1004;
import com.genesis.apps.comm.model.vo.WashReserveVO;
import com.genesis.apps.comm.util.DateUtil;
import com.genesis.apps.databinding.ItemCarWashHistoryBinding;
import com.genesis.apps.ui.common.view.listener.OnSingleClickListener;
import com.genesis.apps.ui.common.view.listview.BaseRecyclerViewAdapter2;
import com.genesis.apps.ui.common.view.viewholder.BaseViewHolder;

import java.util.Date;

public class CarWashHistoryAdapter extends BaseRecyclerViewAdapter2<WashReserveVO> {
    private static OnSingleClickListener singleClickListener;

    public CarWashHistoryAdapter(OnSingleClickListener listener) {
        singleClickListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarWashHistoryViewHolder(getView(parent, R.layout.item_car_wash_history));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindView(getItem(position), position);
    }

    //세차 예약 내역 뷰홀더
    public static class CarWashHistoryViewHolder extends BaseViewHolder<WashReserveVO, ItemCarWashHistoryBinding> {

        public CarWashHistoryViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(WashReserveVO item) {
            //do nothing
        }

        @Override
        public void onBindView(WashReserveVO item, int pos) {
            //예약 중인지 이용완료 또는 취소인지에 따라 뷰 모양 다름.
            //결제 방식 표기 여부도 이를 따름.
            setViewType(item.getRsvtStusCd(), item.getPaymtWayCd());

            //날짜
            setDate(item.isFirst(), item.getRsvtDtm());

            //지점명
            setBranchName(item.getBrnhNm());

            //상품명
            getBinding().tvCarWashHistoryName.setText(item.getGodsNm());

            //결제금액 -> 할인명으로 변경 표기
//            getBinding().tvCarWashHistoryPrice.setText(StringUtil.getPriceString(item.getPaymtCost()));
            getBinding().tvCarWashHistoryPrice.setText(TextUtils.isEmpty(item.getDsctNm()) ? "0% 할인" : item.getDsctNm());

            //버튼 클릭 리스너 및 해당 버튼 처리에 필요한 데이터 세팅
            setSingleClickListenerAndData(item, pos);

            Glide.with(getContext())
                    .load(item.getGodsImgUri())
                    .format(DecodeFormat.PREFER_RGB_565)
                    .error(R.drawable.img_car_339_2) //todo 대체 이미지 필요
                    .placeholder(R.drawable.img_car_339_2) //todo 에러시 대체 이미지 필요
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(getBinding().ivCarWashHistoryImg);
        }

        @Override
        public void onBindView(WashReserveVO item, int pos, SparseBooleanArray selectedItems) {
            //do nothing
        }

        //예약 중인지 이용완료 또는 취소인지에 따라 뷰 모양 다름
        //공통 부분 비중이 커서 viewType을 아예 나누지는 않고 visibility로 처리함
        private void setViewType(String status, String payType) {
            switch (status) {
                case WSH_1004.RESERVE_VALID:
                    //상단 결제방식, 하단 세부정보 visible
//                    getBinding().tvCarWashHistoryPaytype.setVisibility(View.VISIBLE);
                    getBinding().lCarWashHistoryBottom.setVisibility(View.VISIBLE);

                    //결제방식 표시
//                    setPayType(payType);

                    //상단 이용완료or취소, 하단 지점명 gone
                    getBinding().tvCarWashHistoryServiceEnd.setVisibility(View.GONE);
                    getBinding().tvCarWashHistoryBranchNameEnd.setVisibility(View.GONE);
                    break;

                case WSH_1004.RESERVE_COMPLETED:
                case WSH_1004.RESERVE_CANCELED:
//                    //상단 결제방식, 하단 세부정보 gone
//                    getBinding().tvCarWashHistoryPaytype.setVisibility(View.GONE);
                    getBinding().lCarWashHistoryBottom.setVisibility(View.GONE);

                    //상단 이용완료or취소, 하단 지점명 visible
                    getBinding().tvCarWashHistoryServiceEnd.setVisibility(View.VISIBLE);
                    getBinding().tvCarWashHistoryBranchNameEnd.setVisibility(View.VISIBLE);

                    getBinding().tvCarWashHistoryServiceEnd.setText(
                            status.equals(WSH_1004.RESERVE_COMPLETED) ? R.string.sm_cw_history_07 : R.string.sm_cw_history_06
                    );
                    break;
            }
        }

        //날짜. 서버 값은 YYYYMMDDHH24MISS / UI 출력은 YYYY.MM.DD
        private void setDate(boolean isFirst, String dateOriginal) {
            if(!isFirst||TextUtils.isEmpty(dateOriginal)){
                getBinding().tvCarWashHistoryDate.setVisibility(View.GONE);
            }else {
                getBinding().tvCarWashHistoryDate.setVisibility(View.VISIBLE);
                Date date = DateUtil.getDefaultDateFormat(dateOriginal, DateUtil.DATE_FORMAT_yyyyMMddHHmmss);
                String dateStr = DateUtil.getDate(date, DateUtil.DATE_FORMAT_yyyy_mm_dd_dot);
                getBinding().tvCarWashHistoryDate.setText(dateStr);
            }
        }

//        //날짜. 서버 값은 YYYYMMDDHH24MISS / UI 출력은 YYYY.MM.DD
//        private void setDate(WashReserveVO item, String rsvtStusCd) {
//
//            String dateOriginal="";
//            switch (rsvtStusCd) {
//                case WSH_1004.RESERVE_COMPLETED:
//                    dateOriginal = item.getRsvtDtm()
//                    break;
//                default:
//                    dateOriginal = item.getRsvtDtm();
//                    break;
//            }
//            Date date = DateUtil.getDefaultDateFormat(dateOriginal, DateUtil.DATE_FORMAT_yyyyMMddHHmmss);
//            String dateStr = DateUtil.getDate(date, DateUtil.DATE_FORMAT_yyyy_mm_dd_dot);
//
//            getBinding().tvCarWashHistoryDate.setText(dateStr);
//        }


        //지점명. 예약중/이용완료또는 취소 두 뷰에 일괄 처리
        private void setBranchName(String name) {
            getBinding().tvCarWashHistoryBranchNameReserved.setText(name);
            getBinding().tvCarWashHistoryBranchNameEnd.setText(name);
        }

        //결제 수단 코드에 따라 내용 표시
        //todo : api에 결제 수단 정보 갱신되면 반영
        private void setPayType(String type) {
            int payTypeId;

            switch (type) {
                case WSH_1004.PAY_CASH:
                    payTypeId = R.string.sm_cw_pay_type_01;
                    break;

                default:
                    //결제수단 정보 누락된 상태
                    payTypeId = R.string.sm_cw_pay_type_xx;
                    break;
            }

            getBinding().tvCarWashHistoryPaytype.setText(payTypeId);
        }

        //리스너를 연결하고, 이를 처리하는데 필요한 데이터도 저장
        private void setSingleClickListenerAndData(WashReserveVO item, int pos) {
            //통화하기
            getBinding().tvCarWashHistoryCall.lWhole.setOnClickListener(singleClickListener);
            getBinding().tvCarWashHistoryCall.lWhole.setTag(R.id.tag_wash_history, item);

            //직원에게확인
            getBinding().tvCarWashHistoryConfirm.setOnClickListener(singleClickListener);
            getBinding().tvCarWashHistoryConfirm.setTag(R.id.tag_wash_history, item);
            getBinding().tvCarWashHistoryConfirm.setTag(R.id.item_position, pos);

            //예약취소
            getBinding().tvCarWashHistoryCancel.setOnClickListener(singleClickListener);
            getBinding().tvCarWashHistoryCancel.setTag(R.id.tag_wash_history, item);
            getBinding().tvCarWashHistoryCancel.setTag(R.id.item_position, pos);
        }
    }

    public void setRsvtStusCd(int pos, String status) {
        ((WashReserveVO) getItem(pos)).setRsvtStusCd(status);
        notifyItemChanged(pos);
    }

}
