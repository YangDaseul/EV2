package com.genesis.apps.ui.main.service;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.CHB_1021;
import com.genesis.apps.comm.model.constants.ChargeBtrStatus;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.comm.viewmodel.CHBViewModel;
import com.genesis.apps.databinding.FragmentServiceChargeBtrStatusBinding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.fragment.SubFragment;
import com.genesis.apps.ui.main.service.view.ServiceChargeBtrStatusViewpagerAdapter;

public class FragmentServiceChargeBtrStatus extends SubFragment<FragmentServiceChargeBtrStatusBinding> {

    private final int PAGE_NUM = 2;
    private CHBViewModel chbViewModel;

    private boolean init = true;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.setContentView(inflater, R.layout.fragment_service_charge_btr_status);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setViewModel();
        setObserver();
        initView();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClickCommon(View v) {
    }

    @Override
    public void onRefresh() {
        VehicleVO vehicleVO = null;
        try {
            vehicleVO = chbViewModel.getMainVehicleFromDB();
        } catch (Exception e) {
            vehicleVO = null;
        } finally {
            //??????????????? ??????
            if (vehicleVO != null)
                reqAplctStatusGet(vehicleVO);
        }
    }

    private void setViewModel() {
        chbViewModel = new ViewModelProvider(this).get(CHBViewModel.class);
    }

    private void setObserver() {
        chbViewModel.getRES_CHB_1021().observe(getViewLifecycleOwner(), result -> {

            switch (result.status) {
                case LOADING:
                    ((SubActivity) getActivity()).showProgressDialog(true);
                    break;
                case SUCCESS:
                    ((SubActivity) getActivity()).showProgressDialog(false);
                    if (result.data != null && result.data.getRtCd().equalsIgnoreCase("0000")) {
                        me.vpStatus.setAdapter(new ServiceChargeBtrStatusViewpagerAdapter(this, PAGE_NUM, result.data));
                        setFragment(StringUtil.isValidString(result.data.getStatus()));
                        break;
                    }
                default:
                    ((SubActivity) getActivity()).showProgressDialog(false);
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        me.lEmpty.lWhole.setVisibility(View.VISIBLE);
                    }

                    if (result.data != null && StringUtil.isValidString(result.data.getRtCd()).equalsIgnoreCase("2005")){
                        setFragment(null);
                        //????????? ????????? ?????? ?????? ??????????????? ???????????? ??????
                        return;
                    }

                    if (TextUtils.isEmpty(serverMsg)) {
                        serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                    }
                    SnackBarUtil.show(getActivity(), serverMsg);

                    break;
            }
        });
    }

    public void reqAplctStatusGet(VehicleVO vehicleVO) {
        chbViewModel.reqCHB1021(new CHB_1021.Request(APPIAInfo.SM_CGRV04_02.getId(), vehicleVO.getVin()));
    }

    private void setFragment(String stusCd) {

        if (ChargeBtrStatus.STATUS_1000.getStusCd().equalsIgnoreCase(stusCd)) {
            setStatusFragment(0);
        } else if (ChargeBtrStatus.STATUS_1500.getStusCd().equalsIgnoreCase(stusCd) ||
                ChargeBtrStatus.STATUS_2000.getStusCd().equalsIgnoreCase(stusCd) ||
                ChargeBtrStatus.STATUS_3000.getStusCd().equalsIgnoreCase(stusCd) ||
                ChargeBtrStatus.STATUS_4000.getStusCd().equalsIgnoreCase(stusCd)) {
            setStatusFragment(1);
        } else {
            if(init)
                ((ServiceChargeBtrReserveHistoryActivity) getActivity()).moveChargeBtrTab(VariableType.SERVICE_CHARGE_BTR_HIST_TAB_ID);
            me.lEmpty.lWhole.setVisibility(View.VISIBLE);
        }

        this.init = false;
    }

    private void setStatusFragment(int index) {
        me.vpStatus.setCurrentItem(index);

        me.vpStatus.setUserInputEnabled(false);
        me.vpStatus.setOffscreenPageLimit(PAGE_NUM);
        me.lEmpty.lWhole.setVisibility(View.GONE);
    }

    private void initView() {

    }

}
