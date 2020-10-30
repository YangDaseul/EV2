package com.genesis.apps.ui.main.service;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.gra.APPIAInfo;
import com.genesis.apps.comm.model.gra.BaseResponse;
import com.genesis.apps.comm.model.gra.api.WSH_1004;
import com.genesis.apps.comm.model.gra.api.WSH_1005;
import com.genesis.apps.comm.model.gra.api.WSH_1006;
import com.genesis.apps.comm.model.vo.WashReserveVO;
import com.genesis.apps.comm.util.PhoneUtil;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.viewmodel.WSHViewModel;
import com.genesis.apps.databinding.ActivityCarWashHistoryBinding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.dialog.bottom.BottomDialogInputBranchCode;
import com.genesis.apps.ui.common.dialog.middle.MiddleDialog;

import java.util.List;

public class CarWashHistoryActivity extends SubActivity<ActivityCarWashHistoryBinding> {
    private static final String TAG = CarWashHistoryActivity.class.getSimpleName();

    private WSHViewModel viewModel;
    private CarWashHistoryAdapter adapter;

    private int cancelPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash_history);

        getDataFromIntent();
        setViewModel();
        setObserver();

        setAdapter();

        //TODO 저거 ("SONAX") 하드코딩하는 게 맞나
        viewModel.reqWSH1004(new WSH_1004.Request(APPIAInfo.SM_CW01.getId(), "SONAX"));
    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(WSHViewModel.class);
    }

    @Override
    public void onClickCommon(View v) {
        Log.d(TAG, "onClickCommon()");
        int id = v.getId();

        WashReserveVO tag = (WashReserveVO) v.getTag(R.id.tag_wash_history);
        String rsvtSeqNo;

        switch (id) {
            //통화하기
            case R.id.tv_car_wash_history_call:
                String phoneNumber = tag.getTelNo();
                PhoneUtil.phoneDial(this, phoneNumber);
                break;

            //직원에게 확인
            case R.id.tv_car_wash_history_confirm:
                rsvtSeqNo = tag.getRsvtSeqNo();
                String oldBrnhCd = tag.getBrnhCd();

                final BottomDialogInputBranchCode inputBranchCodeDialog = new BottomDialogInputBranchCode(this, oldBrnhCd, R.style.BottomSheetDialogTheme);
                inputBranchCodeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (inputBranchCodeDialog.isInputConfirmed()) {
                            String newBrnhCd = inputBranchCodeDialog.getBranchCode();
                            viewModel.reqWSH1005(new WSH_1005.Request(APPIAInfo.SM_CW01_P02.getId(), rsvtSeqNo, newBrnhCd));
                        }
                    }
                });
                inputBranchCodeDialog.show();
                break;

            case R.id.tv_car_wash_history_cancel:
                rsvtSeqNo = tag.getRsvtSeqNo();
                String brnhCd = tag.getBrnhCd();
                cancelPosition = (int) v.getTag(R.id.item_position);

                MiddleDialog.dialogCarWashCancel(
                        this,
                        () -> viewModel.reqWSH1006(new WSH_1006.Request(APPIAInfo.SM_CW01_P01.getId(), rsvtSeqNo, brnhCd)),
                        null);

                break;

            default:
                //do nothing
                break;
        }

    }

    @Override
    public void setObserver() {
        //예약 내역 옵저버
        viewModel.getRES_WSH_1004().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;

                case SUCCESS:
                    if (result.data != null && result.data.getRsvtList() != null) {
                        List<WashReserveVO> list = result.data.getRsvtList();
                        adapter.setRows(list);
                        adapter.notifyDataSetChanged();

                        //성공 후 데이터 로딩까지 다 되면 로딩 치우고 break;
                        showProgressDialog(false);
                        break;
                    }
                    //not break; 데이터 이상하면 default로 진입시킴

                default:
                    showProgressDialog(false);
                    SnackBarUtil.show(this, getString(result.message));
                    //todo : 구체적인 예외처리
                    break;
            }
        });

        //직원에게 확인 옵저버
        viewModel.getRES_WSH_1005().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;

                case SUCCESS:
                    if (result.data != null && result.data.getRtCd().equals(BaseResponse.RETURN_CODE_SUCC)) {
                        SnackBarUtil.show(this, getString(R.string.cw_confirm_staff));

                        //성공 후 데이터 로딩까지 다 하고 로딩 치우고 break;
                        showProgressDialog(false);
                        break;
                    }
                    //not break; 데이터 이상하면 default로 진입시킴

                default:
                    showProgressDialog(false);
                    SnackBarUtil.show(this, getString(result.message));
                    //todo : 구체적인 예외처리
                    break;
            }
        });

        //예약 취소 옵저버
        viewModel.getRES_WSH_1006().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;

                case SUCCESS:
                    if (result.data != null && result.data.getRtCd().equals(BaseResponse.RETURN_CODE_SUCC)) {
                        adapter.setRsvtStusCd(cancelPosition, WSH_1004.RESERVE_CANCELED);
                        SnackBarUtil.show(this, getString(R.string.cw_cancel_reserve));

                        //성공 후 데이터 로딩까지 다 하고 로딩 치우고 break;
                        showProgressDialog(false);
                        break;
                    }
                    //not break; 데이터 이상하면 default로 진입시킴

                default:
                    showProgressDialog(false);
                    SnackBarUtil.show(this, getString(result.message));
                    //todo : 구체적인 예외처리
                    break;
            }
        });

    }

    @Override
    public void getDataFromIntent() {
        //do nothing
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void setAdapter() {
        //세차 예약 내역 어댑터 (인스턴스 타입 맞나 확인)
        adapter = new CarWashHistoryAdapter(onSingleClickListener);
        ui.rvCarWashHistoryList.setLayoutManager(new LinearLayoutManager(this));
        ui.rvCarWashHistoryList.setHasFixedSize(true);
        ui.rvCarWashHistoryList.setAdapter(adapter);
    }

}
