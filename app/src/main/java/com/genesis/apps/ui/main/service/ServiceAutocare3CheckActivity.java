package com.genesis.apps.ui.main.service;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.ChangeBounds;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.gra.api.REQ_1007;
import com.genesis.apps.comm.model.vo.AddressVO;
import com.genesis.apps.comm.model.vo.CouponVO;
import com.genesis.apps.comm.model.vo.RepairReserveVO;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.util.DateUtil;
import com.genesis.apps.comm.util.DeviceUtil;
import com.genesis.apps.comm.util.SoftKeyboardUtil;
import com.genesis.apps.comm.util.StringRe2j;
import com.genesis.apps.comm.viewmodel.REQViewModel;
import com.genesis.apps.databinding.ActivityServiceAutocare2Apply1Binding;
import com.genesis.apps.databinding.ActivityServiceAutocare3CheckBinding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.dialog.bottom.DialogAutocareService;
import com.genesis.apps.ui.common.dialog.bottom.DialogCalendar;
import com.genesis.apps.ui.common.dialog.middle.MiddleDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @author hjpark
 * @brief 오토케어 3단계
 */
public class ServiceAutocare3CheckActivity extends SubActivity<ActivityServiceAutocare3CheckBinding> {
    private RepairReserveVO repairReserveVO;
    private REQViewModel reqViewModel;
    private View[] edits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResizeScreen();
        setContentView(R.layout.activity_service_autocare_3_check);
        getDataFromIntent();
        setViewModel();
        setObserver();
        initEditView();
//        reqViewModel.reqREQ1007(new REQ_1007.Request());
    }

    private void initEditView() {
        edits = new View[]{ui.etVrn, ui.etTel};
        ui.etTel.setOnEditorActionListener(editorActionListener);
        ui.etTel.setOnFocusChangeListener(focusChangeListener);
        ui.etVrn.setOnEditorActionListener(editorActionListener);
        ui.etVrn.setOnFocusChangeListener(focusChangeListener);
    }

    @Override
    public void onClickCommon(View v) {
        switch (v.getId()) {
            case R.id.btn_next://다음
                doNext();
                break;
        }
    }

    private void clearKeypad() {
        for (View view : edits) {
            view.clearFocus();
        }
        SoftKeyboardUtil.hideKeyboard(this, getWindow().getDecorView().getWindowToken());
    }

    private void doNext() {
        if (isValid()) {
            clearKeypad();
//            sosViewModel.reqSOS1002(new SOS_1002.Request(APPIAInfo.SM_EMGC01.getId(),
//                    mainVehicle.getVin(),
//                    ui.etCarRegNo.getText().toString().trim(),
//                    mainVehicle.getMdlCd(),
//                    fltCd,
//                    areaClsCd,
//                    ui.tvAddrInfo1.getText().toString().trim() +" "+ui.tvAddrInfo2.getText().toString().trim()+" "+ui.etAddrDtl.getText().toString().trim(),
//                    addressVO.getCenterLat()+"",addressVO.getCenterLon()+"",ui.etCelPhNo.getText().toString().trim(),ui.etMemo.getText().toString().trim()));
        }
    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        ui.setActivity(this);
        ui.setData(repairReserveVO);
        reqViewModel = new ViewModelProvider(this).get(REQViewModel.class);
    }

    @Override
    public void setObserver() {
//        sosViewModel.getRES_SOS_1002().observe(this, result -> {
//            switch (result.status){
//                case LOADING:
//                    showProgressDialog(true);
//                    break;
//                case SUCCESS:
//                    showProgressDialog(false);
//                    if(result.data!=null&&!TextUtils.isEmpty(result.data.getRtCd())&&result.data.getRtCd().equalsIgnoreCase("0000")&&!TextUtils.isEmpty(result.data.getTmpAcptNo())){
//                        startActivitySingleTop(new Intent(this, ServiceSOSApplyInfoActivity.class).putExtra(KeyNames.KEY_NAME_SOS_TMP_NO, result.data.getTmpAcptNo()).addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT),0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
//                        finish();
//                        break;
//                    }
//                default:
//                    String serverMsg="";
//                    try {
//                        serverMsg = result.data.getRtMsg();
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }finally{
//                        if (TextUtils.isEmpty(serverMsg)){
//                            serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
//                        }
//                        SnackBarUtil.show(this, serverMsg);
//                        showProgressDialog(false);
//                    }
//                    break;
//            }
//        });

    }

    @Override
    public void getDataFromIntent() {
        try {
            repairReserveVO = (RepairReserveVO) getIntent().getSerializableExtra(KeyNames.KEY_NAME_SERVICE_RESERVE_INFO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (repairReserveVO == null) {
                exitPage("신청 정보가 존재하지 않습니다.\n잠시후 다시 시도해 주십시오.", ResultCodes.REQ_CODE_EMPTY_INTENT.getCode());
            }
        }
    }


    private boolean checkValidPhoneNumber() {
        String celPhoneNo = ui.etTel.getText().toString().replaceAll("-", "").trim();

        if (TextUtils.isEmpty(celPhoneNo)) {
            ui.etTel.requestFocus();
            ui.lTel.setError(getString(R.string.sm_emgc01_5));
            return false;
        } else if (!StringRe2j.matches(celPhoneNo, getString(R.string.check_phone_number))) {
            ui.etTel.requestFocus();
            ui.lTel.setError(getString(R.string.sm_emgc01_26));
            return false;
        } else {
            ui.etTel.setText(PhoneNumberUtils.formatNumber(celPhoneNo, Locale.getDefault().getCountry()));
            ui.etTel.setSelection(ui.etTel.length());
            ui.etTel.clearFocus();
            ui.lTel.setError(null);
            return true;
        }
    }

    private boolean checkValidCarRegNo() {
        String carRegNo = ui.etVrn.getText().toString().trim();

        if (TextUtils.isEmpty(carRegNo)) {
            ui.etVrn.requestFocus();
            ui.lVrn.setError(getString(R.string.sm_emgc01_18));
            return false;
        } else if (!StringRe2j.matches(carRegNo, getString(R.string.check_car_vrn))) {
            ui.etVrn.requestFocus();
            ui.lVrn.setError(getString(R.string.sm_emgc01_27));
            return false;
        } else {
            ui.lVrn.setError(null);
            return true;
        }
    }

    private boolean isValid() {
        return checkValidCarRegNo() && checkValidPhoneNumber();
    }


    EditText.OnEditorActionListener editorActionListener = (textView, actionId, keyEvent) -> {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            doNext();
        }
        return false;
    };

    EditText.OnFocusChangeListener focusChangeListener = (view, hasFocus) -> {
        if (hasFocus) {
            SoftKeyboardUtil.showKeyboard(getApplicationContext());
        }
    };

    @Override
    public void onBackPressed() {
        dialogExit();
    }

    @Override
    public void onBackButton() {
        dialogExit();
    }

    private void dialogExit() {
        MiddleDialog.dialogServiceBack(this, () -> {
            finish();
            closeTransition();
        }, () -> {

        });
    }

}