package com.genesis.apps.ui.main.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.BtrVO;
import com.genesis.apps.comm.model.vo.RepairReserveVO;
import com.genesis.apps.comm.model.vo.RepairTypeVO;
import com.genesis.apps.comm.model.vo.ReserveInfo;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.databinding.FragmentServiceBinding;
import com.genesis.apps.databinding.ItemTabServiceBinding;
import com.genesis.apps.ui.common.activity.BaseActivity;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.dialog.middle.MiddleDialog;
import com.genesis.apps.ui.common.fragment.SubFragment;
import com.genesis.apps.ui.main.MainActivity;
import com.genesis.apps.ui.myg.MyGEntranceActivity;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;


public class FragmentService extends SubFragment<FragmentServiceBinding> {
    private static final String TAG = FragmentService.class.getSimpleName();
    private final int[] TAB_ID_LIST_EV = {R.string.sm01_header_4, R.string.sm01_header_1, R.string.sm01_header_2, R.string.sm01_header_3};
    private final int[] TAB_ID_LIST_GN = {R.string.sm01_header_1, R.string.sm01_header_2, R.string.sm01_header_3};

    public ServiceViewpagerAdapter serviceTabAdapter;
    private LGNViewModel lgnViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.setContentView(inflater, R.layout.fragment_service);
    }

    private void initView() {
        serviceTabAdapter = new ServiceViewpagerAdapter(this);
        me.vpServiceContentsViewPager.setAdapter(serviceTabAdapter);
        initTabView();
        //ViewPager Setting
        me.vpServiceContentsViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        me.vpServiceContentsViewPager.setCurrentItem(0);
        me.vpServiceContentsViewPager.setOffscreenPageLimit(TAB_ID_LIST_EV.length);
    }

    //??? ?????? ??????
    private void initTabView() {
        new TabLayoutMediator(me.tlServiceTabs, me.vpServiceContentsViewPager, (tab, position) -> { }).attach();

        for(int i=0 ; i<TAB_ID_LIST_EV.length; i++) {
            final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ItemTabServiceBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_tab_service, null, false);
            final View view = binding.getRoot();
            binding.tvTab.setText(TAB_ID_LIST_EV[i]);
            me.tlServiceTabs.getTabAt(i).setCustomView(view);
        }
    }

    //??? ?????? ??????
    private void refreshTabView(final int[] tabList) {
        if(me.tlServiceTabs.getTabCount()>0)
            me.tlServiceTabs.removeAllTabs();

        new TabLayoutMediator(me.tlServiceTabs, me.vpServiceContentsViewPager, (tab, position) -> { }).attach();

        for(int i=0 ; i<tabList.length; i++) {
            final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ItemTabServiceBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_tab_service, null, false);
            final View view = binding.getRoot();
            binding.tvTab.setText(tabList[i]);
            me.tlServiceTabs.getTabAt(i).setCustomView(view);
        }
        //?????? ???????????? ?????? ????????? ???????????? ?????????
        if(me.vpServiceContentsViewPager.getCurrentItem()>0) me.vpServiceContentsViewPager.setCurrentItem(0);
    }

    private void refreshTab(){
        VehicleVO vehicleVO = null;
        try{
            vehicleVO = lgnViewModel.getMainVehicleSimplyFromDB();
        }catch (Exception e){

        }
        if(vehicleVO!=null&&vehicleVO.isEV()){
            if(serviceTabAdapter.getItemCount()<4){
                serviceTabAdapter.addFragmentCharge();
                refreshTabView(TAB_ID_LIST_EV);
            }
        }else{
            if(serviceTabAdapter.getItemCount()>3) {
                serviceTabAdapter.removeFragmentCharge();
                refreshTabView(TAB_ID_LIST_GN);
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        me.setLifecycleOwner(getViewLifecycleOwner());
        me.setFragment(this);
        initViewPager();
        lgnViewModel = new ViewModelProvider(getActivity()).get(LGNViewModel.class);
    }

    private void initViewPager() {
        //ViewPager Setting

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClickCommon(View v) {

        switch (v.getId()) {

        }
    }

    @Override
    public void onRefresh() {
        Log.e("onResume", "onReusme contents");
        SubActivity.setStatusBarColor(getActivity(), R.color.x_ffffff);
        ((MainActivity) getActivity()).setGNB(getString(R.string.main_word_3), View.VISIBLE, false, true);
        refreshTab();
        clearKeyPad();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == ResultCodes.REQ_CODE_SERVICE_RESERVE_AUTOCARE.getCode()&&data!=null) {
            //???????????? ????????? ?????? ?????? ??? ????????? ??????
            RepairReserveVO repairReserveVO = (RepairReserveVO) data.getSerializableExtra(KeyNames.KEY_NAME_SERVICE_RESERVE_INFO);
            if (repairReserveVO != null) {
                ((SubActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceAutocare4ResultActivity.class).putExtra(KeyNames.KEY_NAME_SERVICE_RESERVE_INFO, repairReserveVO)
                        , RequestCodes.REQ_CODE_ACTIVITY.getCode()
                        , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
            }
        } else if (resultCode == ResultCodes.REQ_CODE_SERVICE_RESERVE_HOMETOHOME.getCode()&&data!=null) {
            //????????? ????????? ?????? ?????? ??? ????????? ??????
            RepairReserveVO repairReserveVO = (RepairReserveVO) data.getSerializableExtra(KeyNames.KEY_NAME_SERVICE_RESERVE_INFO);
            if (repairReserveVO != null) {
                ((SubActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceHomeToHome4ResultActivity.class).putExtra(KeyNames.KEY_NAME_SERVICE_RESERVE_INFO, repairReserveVO)
                        , RequestCodes.REQ_CODE_ACTIVITY.getCode()
                        , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
            }
        } else if (resultCode == ResultCodes.REQ_CODE_SERVICE_RESERVE_REPAIR.getCode()&&data!=null) {
            //?????? ????????? ?????? ?????? ??? ????????? ??????
            RepairReserveVO repairReserveVO = (RepairReserveVO) data.getSerializableExtra(KeyNames.KEY_NAME_SERVICE_RESERVE_INFO);
            if (repairReserveVO != null) {
                ((SubActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceRepair4ResultActivity.class).putExtra(KeyNames.KEY_NAME_SERVICE_RESERVE_INFO, repairReserveVO)
                        , RequestCodes.REQ_CODE_ACTIVITY.getCode()
                        , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
            }

        } else if (resultCode == ResultCodes.REQ_CODE_CHARGE_RESERVATION_FINISH.getCode()&&data!=null) {
            //????????? ?????? ?????? ??? ????????? ??????
            ReserveInfo reserveInfo = (ReserveInfo) data.getSerializableExtra(KeyNames.KEY_NAME_CHARGE_RESERVE_INFO);
            if (reserveInfo != null) {
                ((SubActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ChargeResultActivity.class).putExtra(KeyNames.KEY_NAME_CHARGE_RESERVE_INFO, reserveInfo)
                        , RequestCodes.REQ_CODE_ACTIVITY.getCode()
                        , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
            }
        } else if (resultCode == ResultCodes.REQ_CODE_SERVICE_RESERVE_REMOTE.getCode()&&data!=null) {
            //???????????? ????????? ?????? ?????? ??? ????????? ??????
            ((SubActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceRemoteListActivity.class)
                            .putExtras(data)
                    , RequestCodes.REQ_CODE_ACTIVITY.getCode()
                    , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
        } else if (resultCode == ResultCodes.REQ_CODE_SERVICE_NETWORK_RESERVE.getCode()&&data!=null) {
            //??????????????????????????? ?????? ?????? ???
            RepairTypeVO repairTypeVO = (RepairTypeVO) data.getSerializableExtra(KeyNames.KEY_NAME_SERVICE_REPAIR_TYPE_CODE);
            BtrVO btrVO = (BtrVO) data.getSerializableExtra(KeyNames.KEY_NAME_BTR);
            ((SubActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceRepair2ApplyActivity.class)
                            .putExtra(KeyNames.KEY_NAME_SERVICE_REPAIR_TYPE_CODE, repairTypeVO)
                            .putExtra(KeyNames.KEY_NAME_BTR, btrVO)
                    , RequestCodes.REQ_CODE_ACTIVITY.getCode()
                    , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
        } else if(requestCode == RequestCodes.REQ_CODE_SERVICE_DRIVE_REQ.getCode()) {
            // ???????????? ?????? ??????
            for (Fragment fragment : getChildFragmentManager().getFragments()) {
                if (fragment instanceof FragmentServiceDrive) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                    return;
                }
            }
        } else if (resultCode == ResultCodes.REQ_CODE_SERVICE_WASH_RESERVATION_FINISH.getCode()) {
            //?????? ?????? ???????????? ??????
            ((BaseActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), CarWashHistoryActivity.class), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
        } else if (resultCode == ResultCodes.REQ_CODE_SERVICE_CHARGE_BTR_RESERVATION_FINISH.getCode()) {
            ((BaseActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceChargeBtrReserveHistoryActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    public boolean checkCustGbCd(int viewId, String custGbCd){
        boolean isAllow=true;

        try {
            switch (viewId) {
                case R.id.tv_service_maintenance_btn_black://??????, ???????????? ??????
                case R.id.tv_service_maintenance_btn_white://????????????
                case R.id.l_service_maintenance_history_btn: //?????? ??????/?????? ??????
                case R.id.l_service_maintenance_reservation_btn: //????????????
                case R.id.l_service_maintenance_emergency_btn: //????????????
                case R.id.l_service_maintenance_customercenter_btn://???????????? ??????
                case R.id.l_service_maintenance_remote_servie_list_btn:// ?????? ?????? ??????
                case R.id.l_service_maintenance_defect_btn: //??????????????????
                case R.id.l_service_car_wash_history_btn: //?????? ????????? ???????????? ??????
                case R.id.l_service_car_wash_request_btn: //?????? ????????? ?????? ??????
                case R.id.l_service_car_wash_item: // ?????? ?????? ??????
                case R.id.l_service_drive_req_btn: //???????????? ?????? ??????
                case R.id.l_service_drive_list_btn: //???????????? ?????? ?????? ??????
                case R.id.l_service_charge_reservation_list://????????? ?????? ??????
                case R.id.l_service_charge_btr_service: //????????? ????????? ?????????
                case R.id.l_service_charge_service: //???????????? ?????? ?????????
                    switch (custGbCd) {
                        //??????????????? ?????? ?????? ?????? ?????? ??????
                        case VariableType.MAIN_VEHICLE_TYPE_OV:
                            isAllow=true;
                            break;
                        case VariableType.MAIN_VEHICLE_TYPE_CV:
                        case VariableType.MAIN_VEHICLE_TYPE_NV:
                            //?????? ?????? ?????? ??? ?????? ????????? ?????? ????????? ?????? ????????? ??????
                            isAllow=false;
                            SnackBarUtil.show(getActivity(), getString(R.string.sm01_snack_bar));
                            break;
                        case VariableType.MAIN_VEHICLE_TYPE_0000:
                        default:
                            //???????????? ????????? ?????? ????????? ??????
                            isAllow=false;
                            dialogLogin();
                            break;
                    }
                    break;
                case R.id.btn_service_charge_search: //????????? ??????
                case R.id.l_service_charge_recommend_trip://?????? ?????? ??????
                    switch (custGbCd) {
                        //???????????? ??? ??????????????? ?????? ?????? ?????? ?????? ??????
                        case VariableType.MAIN_VEHICLE_TYPE_OV:
                        case VariableType.MAIN_VEHICLE_TYPE_CV:
                            isAllow=true;
                            break;
                        case VariableType.MAIN_VEHICLE_TYPE_NV:
                            //??? ?????? ????????? ?????? ????????? ?????? ????????? ??????
                            isAllow=false;
                            SnackBarUtil.show(getActivity(), getString(R.string.sm01_snack_bar));
                            break;
                        case VariableType.MAIN_VEHICLE_TYPE_0000:
                        default:
                            //???????????? ????????? ?????? ????????? ??????
                            isAllow=false;
                            dialogLogin();
                            break;
                    }

                    break;
                default:
                    //??? ??? ??????
                    isAllow=true;
                    break;
            }
        }catch (Exception ignore){

        }

        return isAllow;
    }

    private void dialogLogin(){
        MiddleDialog.dialogLogin(getActivity(), () -> {
            ((SubActivity)getActivity()).startActivitySingleTop(new Intent(getActivity(), MyGEntranceActivity.class), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
        }, () -> {

        });
    }


}
