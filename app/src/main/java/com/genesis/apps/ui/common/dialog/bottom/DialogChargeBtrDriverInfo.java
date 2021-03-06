package com.genesis.apps.ui.common.dialog.bottom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.gra.CHB_1021;
import com.genesis.apps.comm.model.constants.ChargeBtrStatus;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.carlife.MembershipVO;
import com.genesis.apps.comm.model.vo.carlife.OptionVO;
import com.genesis.apps.comm.model.vo.carlife.WorkerVO;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.databinding.DialogBottomChargeBtrDriverInfoBinding;
import com.genesis.apps.ui.common.view.listener.OnSingleClickListener;

import java.util.List;
import java.util.Locale;

/**
 * 픽업앤충전 현황/예약_서비스 중 예약 정보 view
 * @author ljeun
 * @since 2021. 5. 10.
 */
public class DialogChargeBtrDriverInfo extends BaseBottomDialog<DialogBottomChargeBtrDriverInfoBinding> {

    private String stusMsg;
    private CHB_1021.Response data;

    public DialogChargeBtrDriverInfo(@NonNull Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom_charge_btr_driver_info);
        setAllowOutTouch(true);
        ui.back.lWhole.setElevation(0);

        if (data != null) {
            ui.setStusMsg(stusMsg);
            ui.setData(data);

            setLayoutReserveInfo();

            ui.btnCall.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    if (ui.lControlTel.lWhole.getTag() != null && ui.lControlTel.lWhole.getTag() instanceof String)
                        getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(WebView.SCHEME_TEL + ui.lControlTel.lWhole.getTag())));
                }
            });
        }
    }

    private void setLayoutReserveInfo() {
        /**
         * 기사 정보
         */
        String targetType = null;
        switch (ChargeBtrStatus.findCode(StringUtil.isValidString(data.getStatus()))) {
            case STATUS_1500:
            case STATUS_2000:
                targetType = VariableType.SERVICE_CHARGE_BTR_WORKER_TYPE_PICKUP;
                break;
            case STATUS_3000:
                targetType = VariableType.SERVICE_CHARGE_BTR_WORKER_TYPE_SERVICE;
                break;
            case STATUS_4000:
                targetType = VariableType.SERVICE_CHARGE_BTR_WORKER_TYPE_DELIVERY;
                break;
        }

        WorkerVO workerVO = getWorkerInfo(data.getWorkerList(), targetType);
        if(workerVO != null) {
            // 기사님 이름 표시
            //ui.lWorkerNm.setMsg(StringUtil.isValidString(workerVO.getWorkerName()));
            ui.lWorkerNm.setMsg(StringUtil.getNameMask(StringUtil.isValidString(workerVO.getWorkerName())));

            // 기사님 전화번호 표시
            //ui.lControlTel.setMsg(PhoneNumberUtils.formatNumber(StringUtil.isValidString(workerVO.getWorkerHpNo()), Locale.getDefault().getCountry()));
            ui.lControlTel.setMsg(StringUtil.getPhoneMask(StringUtil.isValidString(workerVO.getWorkerHpNo())));
            ui.lControlTel.lWhole.setTag(workerVO.getWorkerHpNo());
        }

        // 업체 정보 표시
        if (data.getVendorInfo() != null)
            ui.lAllocNm.setMsg(StringUtil.isValidString(data.getVendorInfo().getVendorName()));

        /**
         * 예약 내역
         */
        // 픽업 주소 표시
        String address = null;
        if (data.getLocationList() != null && data.getLocationList().size() > 0)
            address = StringUtil.isValidString(data.getLocationList().get(0).getAddress()) + " " + StringUtil.isValidString(data.getLocationList().get(0).getAddressDetail()) + " " + StringUtil.isValidString(data.getLocationList().get(0).getBuildingName());

        ui.lPickupAddr.setMsg(address);

        // 예약 상품 표시 (충전 or 충전, 세차)
        String serviceNm = getContext().getString(R.string.service_charge_btr_word_05);

        OptionVO deliverVO = null;
        OptionVO carwashVO = null;
        for (OptionVO optVo : data.getOrderInfo().getOptionList()) {
            if (optVo.getOptionCode().equalsIgnoreCase(VariableType.SERVICE_CHARGE_BTR_OPT_CD_1)) {
                deliverVO = optVo;
                continue;
            } else {
                carwashVO = optVo;
                continue;
            }
        }

        if (carwashVO != null)
            serviceNm += ", " + getContext().getString(R.string.service_charge_btr_word_06);

        ui.lReserveInfo.setMsg(serviceNm);


        /**
         * 서비스 내역(가격 정보)
         */
        // 충전 금액 표시
        ui.lAdvancePaymt.setMsg(StringUtil.getPriceString(data.getOrderInfo().getProductPrice()));
        // 충전 크레딧 포인트 정보 표시
        String useCreditPoint = null;
        if(data.getOrderInfo().getMembershipList() != null && data.getOrderInfo().getMembershipList().size() > 0) {
            for(MembershipVO vo : data.getOrderInfo().getMembershipList()) {
                if(vo.getMembershipCode().equalsIgnoreCase(VariableType.SERVICE_CHARGE_BTR_MEMBERSHIP_CODE_STRFF)) {
                    useCreditPoint = StringUtil.getDiscountString(vo.getMembershipUsePoint());
                    break;
                }
            }
        }

        if(TextUtils.isEmpty(useCreditPoint)) {
            ui.lCreditPoint.lWhole.setVisibility(View.GONE);
            ui.txtCreditPoint.setVisibility(View.GONE);
        } else {
            ui.lCreditPoint.lWhole.setVisibility(View.VISIBLE);
            ui.txtCreditPoint.setVisibility(View.VISIBLE);
            ui.lCreditPoint.setMsg(useCreditPoint);
        }

        // 탁송금액 표시
        int deliverPrice = deliverVO.getOptionPrice();
        ui.lDeliveryPaymt.setMsg(StringUtil.getPriceString(deliverPrice));
        // 세차금액 표시
        if (carwashVO != null) {
            ui.lCarWashPaymt.lWhole.setVisibility(View.VISIBLE);
            int carwashPrice = carwashVO.getOptionPrice();
            ui.lCarWashPaymt.setMsg(StringUtil.getPriceString(carwashPrice));
        } else {
            ui.lCarWashPaymt.lWhole.setVisibility(View.GONE);
        }

        // 결제 금액 표시
        ui.lPaymtAmt.setMsg(StringUtil.getPriceString(data.getOrderInfo().getEstimatedPaymentAmount()));
    }

    /**
     * 기사 정보 가져오기
     *
     * @param list
     * @param type
     * @return
     */
    private WorkerVO getWorkerInfo(List<WorkerVO> list, String type) {
        WorkerVO result = null;

        if (list != null && list.size() > 0) {
            if(!TextUtils.isEmpty(type)) {
                for (WorkerVO vo : list) {
                    if (StringUtil.isValidString(vo.getWorkerType()).equalsIgnoreCase(type)) {
                        result = vo;
                        break;
                    }
                }
            }

            if (result == null)
                result = list.get(0);
        }

        return result;
    }

    public CHB_1021.Response getData() {
        return data;
    }

    public void setData(CHB_1021.Response data) {
        this.data = data;
    }

    public void setStusMsg(String stuMsg) {
        this.stusMsg = stuMsg;
    }
}
