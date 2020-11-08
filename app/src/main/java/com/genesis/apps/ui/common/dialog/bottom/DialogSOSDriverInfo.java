package com.genesis.apps.ui.common.dialog.bottom;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.gra.api.SOS_1006;
import com.genesis.apps.comm.util.DateUtil;
import com.genesis.apps.comm.util.PhoneUtil;
import com.genesis.apps.databinding.DialogBottomSosDriverInfoBinding;
import com.genesis.apps.ui.common.view.listener.OnSingleClickListener;

import java.util.Locale;

import androidx.annotation.NonNull;

import static com.genesis.apps.comm.model.constants.VariableType.SERVICE_SOS_AREA_CLS_CODE_H;
import static com.genesis.apps.comm.model.constants.VariableType.SERVICE_SOS_AREA_CLS_CODE_R;

public class DialogSOSDriverInfo extends BaseBottomDialog<DialogBottomSosDriverInfoBinding> {

    private SOS_1006.Response data;

    public DialogSOSDriverInfo(@NonNull Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom_sos_driver_info);
        setAllowOutTouch(true);

        if(data!=null){
            ui.tvControlTel.setText(PhoneNumberUtils.formatNumber(data.getSosDriverVO().getControlTel(), Locale.getDefault().getCountry()));
            ui.tvCarNo.setText(data.getSosDriverVO().getCarNo()+" "+data.getSosDriverVO().getCarTypeNm());
            ui.tvSAllocNm.setText(data.getSosDriverVO().getSAllocNm());
            ui.tvTmpAcptDtm.setText((!TextUtils.isEmpty(data.getTmpAcptDtm()) ? (DateUtil.getDate(DateUtil.getDefaultDateFormat(DateUtil.getYyyyMMddHHmmss(data.getTmpAcptDtm()), DateUtil.DATE_FORMAT_yyyyMMddHHmmss), DateUtil.DATE_FORMAT_yyyy_MM_dd_HH_mm)) : "--"));
            ui.tvAreaClsCd.setText(data.getAreaClsCd().equalsIgnoreCase("R") ? SERVICE_SOS_AREA_CLS_CODE_R : SERVICE_SOS_AREA_CLS_CODE_H);
            ui.tvAddr.setText(data.getAddr());
            ui.tvCarRegNo.setText(data.getCarRegNo());
            ui.tvMemo.setText(data.getMemo());
            ui.btnCall.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    if(!TextUtils.isEmpty(data.getSosDriverVO().getControlTel())) {
                        getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(WebView.SCHEME_TEL + data.getSosDriverVO().getControlTel())));
                    }
                }
            });
        }
    }

    public SOS_1006.Response getData() {
        return data;
    }

    public void setData(SOS_1006.Response data) {
        this.data = data;
    }
}