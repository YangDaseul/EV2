package com.genesis.apps.ui.main.service;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.DDS_1001;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.viewmodel.DDSViewModel;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.databinding.FragmentServiceDriveBinding;
import com.genesis.apps.ui.common.activity.BaseActivity;
import com.genesis.apps.ui.common.fragment.SubFragment;
import com.genesis.apps.ui.main.MainActivity;

import java.util.concurrent.ExecutionException;

public class FragmentServiceDrive extends SubFragment<FragmentServiceDriveBinding> {
    private static final String TAG = FragmentServiceDrive.class.getSimpleName();

    private DDSViewModel ddsViewModel;
    private LGNViewModel lgnViewModel;
    private VehicleVO mainVehicle;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);

        setViewModel();
        setObserver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView(): start");
        return super.setContentView(inflater, R.layout.fragment_service_drive);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //?????? ??????
        if (resultCode == ResultCodes.REQ_CODE_PAYMENT_SUCC.getCode()) {
            onClickReqBtn();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRefresh() {
        initMainVehicle();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClickCommon(View v) {
        int id = v.getId();
        Log.d(TAG, "onClickCommon: view id :" + id);

        try {
            if (!((FragmentService) getParentFragment()).checkCustGbCd(id, lgnViewModel.getUserInfoFromDB().getCustGbCd()))
                return;
        } catch (Exception e) {

        }


        switch (id) {
            //???????????? ?????? ?????? (?????? ????????? ???????????? ??? ????????? ?????????)
            case R.id.l_service_drive_req_btn:
                onClickReqBtn();
                break;

            case R.id.l_service_drive_list_btn:
                ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceDriveHistoryActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;

            default:
                //do nothing
                break;
        }
    }

    public void setViewModel() {
        me.setLifecycleOwner(getViewLifecycleOwner());
        me.setFragment(this);
        ddsViewModel = new ViewModelProvider(this).get(DDSViewModel.class);
        lgnViewModel = new ViewModelProvider(this).get(LGNViewModel.class);
    }

    public void setObserver() {
        //?????? ?????? ??????
        ddsViewModel.getRES_DDS_1001().observe(getViewLifecycleOwner(), result -> {
            Log.d(TAG, "getRES_DDS_1001 check req obs: " + result.status);

            Intent intent;

            switch (result.status) {
                case LOADING:
                    ((MainActivity) getActivity()).showProgressDialog(true);
                    break;

                case SUCCESS:
                    if (result.data != null) {
                        ((MainActivity) getActivity()).showProgressDialog(false);

                        //???????????? ?????? ?????? ?????? ?????? ???????????? ??????
                        startServiceDriveActivity(result.data);
                        break;
                    }
                    //not break; ????????? ???????????? default??? ????????????

                default:
                    ((MainActivity) getActivity()).showProgressDialog(false);
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (TextUtils.isEmpty(serverMsg)) {
                            serverMsg = getString(R.string.instability_network);
                        }
                        SnackBarUtil.show(getActivity(), serverMsg);
                    }
                    break;
            }
        });

    }

    private void initMainVehicle() {
        try {
            mainVehicle = lgnViewModel.getMainVehicleSimplyFromDB();
        } catch (ExecutionException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            Log.d(TAG, "InterruptedException");
            Thread.currentThread().interrupt();
        }
    }

    //???????????? ?????? ??????
    //?????? ????????? ???????????? ?????? ?????? ??????????????? ??????(?????? ?????? ?????? or ???????????? ???????????? ??????)
    private void onClickReqBtn() {
        //?????? ?????? ??????
        ddsViewModel.reqDDS1001(
                new DDS_1001.Request(
                        APPIAInfo.SM_DRV02.getId(),
                        mainVehicle.getVin()));
    }

    //???????????? ?????? ?????? ?????? ?????? ???????????? ??????
    private void startServiceDriveActivity(@NonNull DDS_1001.Response data) {
        Log.d(TAG, "startServiceDriveActivity: ");

        if (data.getSvcStusCd() == null) {
            //???????????? ?????? ???????????? ??????
            startReqActivity();
            return;
        }

        switch (data.getSvcStusCd()) {
            //?????? ?????? ???????????? ??????
            case DDS_1001.STATUS_DRIVER_MATCH_WAIT:
            case DDS_1001.STATUS_RESERVED:
            case DDS_1001.STATUS_DRIVER_MATCHED:
            case DDS_1001.STATUS_DRIVER_REMATCHED:
            case DDS_1001.STATUS_DRIVE_NOW:
            case DDS_1001.STATUS_NO_DRIVER:
                //?????? ???????????? ?????? ??????????????? ?????????
                startReqResultActivity(data);
                break;

            //???????????? ?????? ???????????? ??????
            case DDS_1001.STATUS_REQ:
            case DDS_1001.STATUS_SERVICE_FINISHED:
            case DDS_1001.STATUS_CANCEL_BY_USER:
            case DDS_1001.STATUS_CANCEL_CAUSE_NO_DRIVER:
                startReqActivity();
                break;

            default:
                Log.d(TAG, "getRES_DDS_1001: unknown svcStusCd ");
                break;
        }
    }

    //?????? ?????? ???????????? ??????
    private void startReqResultActivity(DDS_1001.Response data) {
        //?????? ?????? ???????????? ??? ?????? ????????? ???????????? ??????????????? ?????????
        Intent intent = new Intent(getActivity(), ServiceDriveReqResultActivity.class)
                .putExtra(KeyNames.KEY_NAME_SERVICE_DRIVE_STATUS, data);

        ((BaseActivity) getActivity()).startActivitySingleTop(intent, RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
    }

    //?????? ???????????? ??????
    private void startReqActivity() {
        //??? ?????? ????????? ?????????
        Intent intent = new Intent(getActivity(), ServiceDriveReqActivity.class);
//                .putExtra(KeyNames.KEY_NAME_VEHICLE_VO, mainVehicle);

        ((BaseActivity) getActivity()).startActivitySingleTop(intent, RequestCodes.REQ_CODE_SERVICE_DRIVE_REQ.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
    }
}
