package com.genesis.apps.ui.main.service;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.REQ_1001;
import com.genesis.apps.comm.model.api.gra.SOS_1001;
import com.genesis.apps.comm.model.api.gra.SOS_1006;
import com.genesis.apps.comm.model.vo.AddressVO;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.comm.viewmodel.REQViewModel;
import com.genesis.apps.comm.viewmodel.SOSViewModel;
import com.genesis.apps.databinding.FragmentServiceMaintenanceBinding;
import com.genesis.apps.ui.common.activity.BaseActivity;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.dialog.middle.MiddleDialog;
import com.genesis.apps.ui.common.fragment.SubFragment;
import com.genesis.apps.ui.main.MainActivity;
import com.genesis.apps.ui.main.ServiceNetworkActivity;

import static com.genesis.apps.comm.model.api.BaseResponse.RETURN_CODE_SUCC;

public class FragmentMaintenance extends SubFragment<FragmentServiceMaintenanceBinding> {
    private static final String TAG = FragmentMaintenance.class.getSimpleName();
    private REQViewModel reqViewModel;
    private SOSViewModel sosViewModel;
    private LGNViewModel lgnViewModel;
    private String tmpAcptNo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView(): start");
        View view = super.setContentView(inflater, R.layout.fragment_service_maintenance);
        me.setFragment(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        me.setLifecycleOwner(this);
        sosViewModel = new ViewModelProvider(this).get(SOSViewModel.class);
        reqViewModel = new ViewModelProvider(this).get(REQViewModel.class);
        lgnViewModel = new ViewModelProvider(getActivity()).get(LGNViewModel.class);

        sosViewModel.getRES_SOS_1001().observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case LOADING:
                    ((SubActivity) getActivity()).showProgressDialog(true);
                    break;
                case SUCCESS:
                    ((SubActivity) getActivity()).showProgressDialog(false);
                    if(result.data!=null&&StringUtil.isValidString(result.data.getRtCd()).equalsIgnoreCase(RETURN_CODE_SUCC)){
                        tmpAcptNo = result.data.getTmpAcptNo();
                        if(!TextUtils.isEmpty(tmpAcptNo)) sosViewModel.reqSOS1006(new SOS_1006.Request(APPIAInfo.SM01.getId(), tmpAcptNo));
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
                        SnackBarUtil.show(getActivity(), TextUtils.isEmpty(serverMsg) ? getString(R.string.r_flaw06_p02_snackbar_1) : serverMsg);
                    }
                    break;
            }
        });
        sosViewModel.getRES_SOS_1006().observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case LOADING:
                    ((SubActivity) getActivity()).showProgressDialog(true);
                    break;
                case SUCCESS:
                    ((SubActivity) getActivity()).showProgressDialog(false);
                    if (result.data != null && result.data.getSosDriverVO() != null && !TextUtils.isEmpty(tmpAcptNo)) {
                        ((BaseActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceSOSRouteInfoActivity.class).putExtra(KeyNames.KEY_NAME_SOS_DRIVER_VO, result.data), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
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
                        SnackBarUtil.show(getActivity(), TextUtils.isEmpty(serverMsg) ? getString(R.string.r_flaw06_p02_snackbar_1) : serverMsg);
                    }
                    break;
            }
        });

        reqViewModel.getRES_REQ_1001().observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case LOADING:
                    ((SubActivity) getActivity()).showProgressDialog(true);
                    break;
                case SUCCESS:
                    ((SubActivity) getActivity()).showProgressDialog(false);
                    if (result.data != null) {
                        setViewSOSStatus(result.data.getPgrsStusCd());
//                        setViewMaintenanceStatus(result.data.getStusCd());
                    }
                    break;
                default:
                    ((SubActivity) getActivity()).showProgressDialog(false);
                    break;
            }
        });

    }

    private void setViewMaintenanceStatus(String result) {
        try {
            if (!TextUtils.isEmpty(result)) {
                int stusCd = Integer.parseInt(result);
                if (stusCd > 4609 && stusCd < 4850) {
                    //????????? ??????
                    me.lServiceMaintenanceHistoryBtn.tvMovingNow.setVisibility(View.VISIBLE);
                    me.lServiceMaintenanceHistoryBtn.tvMovingNow.setText(R.string.sm01_maintenance_14);
                } else {
                    me.lServiceMaintenanceHistoryBtn.tvMovingNow.setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            me.lServiceMaintenanceHistoryBtn.tvMovingNow.setVisibility(View.GONE);
        }
    }

    private void setViewSOSStatus(String pgrsStusCd) {
        if (!TextUtils.isEmpty(pgrsStusCd)) {
            switch (pgrsStusCd) {
                case VariableType.SERVICE_SOS_STATUS_CODE_R://??????
                    me.lServiceMaintenanceEmergencyBtn.tvMovingNow.setVisibility(View.VISIBLE);
                    me.lServiceMaintenanceEmergencyBtn.tvMovingNow.setText(R.string.sm01_maintenance_41);
                    me.lServiceMaintenanceEmergencyBtn.tvServiceMaintenanceBtnBlack.setText(R.string.sm01_maintenance_42);
                    break;
                case VariableType.SERVICE_SOS_STATUS_CODE_W://??????
                    me.lServiceMaintenanceEmergencyBtn.tvMovingNow.setVisibility(View.VISIBLE);
                    me.lServiceMaintenanceEmergencyBtn.tvMovingNow.setText(R.string.sm01_maintenance_13);
                    me.lServiceMaintenanceEmergencyBtn.tvServiceMaintenanceBtnBlack.setText(R.string.sm01_maintenance_42);
                    break;
                case VariableType.SERVICE_SOS_STATUS_CODE_S://??????
                    me.lServiceMaintenanceEmergencyBtn.tvMovingNow.setVisibility(View.VISIBLE);
                    me.lServiceMaintenanceEmergencyBtn.tvMovingNow.setText(R.string.sm01_maintenance_7);
                    me.lServiceMaintenanceEmergencyBtn.tvServiceMaintenanceBtnBlack.setText(R.string.sm01_maintenance_42);
                    break;
                case VariableType.SERVICE_SOS_STATUS_CODE_E://??????
                case VariableType.SERVICE_SOS_STATUS_CODE_C://??????
                default:
                    me.lServiceMaintenanceEmergencyBtn.tvMovingNow.setVisibility(View.GONE);
                    me.lServiceMaintenanceEmergencyBtn.tvServiceMaintenanceBtnBlack.setText(R.string.sm01_maintenance_40);
                    break;
            }
        }
    }

    private void startServiceNetworkReservation(){
        //????????????
        try {
            String avlRsrVn = reqViewModel.getRES_REQ_1001().getValue().data.getAvlRsrYn();
            if (!TextUtils.isEmpty(avlRsrVn) && avlRsrVn.equalsIgnoreCase(VariableType.COMMON_MEANS_YES))
                ((BaseActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), MaintenanceReserveActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
            else
                MiddleDialog.dialogServiceInfo(getActivity(), null);
        } catch (Exception ignore) {

        }
    }


    private void startSOSActivity() {

        if (!((MainActivity) getActivity()).isGpsEnable()) {
            MiddleDialog.dialogGPS(getActivity(), () -> ((MainActivity) getActivity()).turnGPSOn(isGPSEnable -> {
                Log.v("test","value:"+isGPSEnable);
            }), () -> {
                //????????????????????????
            });
        } else {
            String pgrsStusCd = "";
            try {
                pgrsStusCd = reqViewModel.getRES_REQ_1001().getValue().data.getPgrsStusCd();
            } catch (Exception e) {
                pgrsStusCd = "";
            }

            if (!TextUtils.isEmpty(pgrsStusCd)) {
                switch (pgrsStusCd) {
                    case VariableType.SERVICE_SOS_STATUS_CODE_R://??????
                    case VariableType.SERVICE_SOS_STATUS_CODE_W://??????
                        ((BaseActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceSOSApplyInfoActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                        break;
                    case VariableType.SERVICE_SOS_STATUS_CODE_S://??????
                        sosViewModel.reqSOS1001(new SOS_1001.Request(APPIAInfo.SM01.getId()));
                        break;
                    case VariableType.SERVICE_SOS_STATUS_CODE_E://??????
                    case VariableType.SERVICE_SOS_STATUS_CODE_C://??????
                    default:
                        ((BaseActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceSOSApplyActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                        break;
                }
            }
        }
    }

    @Override
    public void onRefresh() {
        reqServiceInfoToServer();
    }

    /**
     * @brief ????????? ?????? ????????? ????????? ??????
     */
    private void reqServiceInfoToServer() {
        VehicleVO mainVehicle = null;
        String custGbCd = "";
        try {
            mainVehicle = reqViewModel.getMainVehicle();
            custGbCd = lgnViewModel.getUserInfoFromDB().getCustGbCd();
        } catch (Exception e) {
            mainVehicle = null;
        } finally {
            if (mainVehicle != null && !TextUtils.isEmpty(custGbCd) && custGbCd.equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_OV)) {
                reqViewModel.reqREQ1001(new REQ_1001.Request(APPIAInfo.SM01.getId(), mainVehicle.getVin()));
            }
        }
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
//        if(!checkCustGbCd(id))
//            return;

        switch (id) {
            //????????? ???????????? ??????
            case R.id.tv_service_maintenance_find_network_btn:
                ((BaseActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceNetworkActivity.class).putExtra(KeyNames.KEY_NAME_PAGE_TYPE, ServiceNetworkActivity.PAGE_TYPE_SERVICE), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            //?????? ??????
            case R.id.l_service_maintenance_reservation_btn:
                startServiceNetworkReservation();
                break;
            case R.id.tv_service_maintenance_btn_black:

                String title = v.getTag().toString();

                if(StringUtil.isValidString(title).equalsIgnoreCase(getString(R.string.sm01_maintenance_2))){
                    //????????????
                    startServiceNetworkReservation();
                }else if(StringUtil.isValidString(title).equalsIgnoreCase(getString(R.string.sm01_maintenance_6))){
                    //????????????
                    startSOSActivity();
                }
                break;
            //?????? ??????
            case R.id.tv_service_maintenance_btn_white:
                VehicleVO mainVehicle = null;
                try {
                    mainVehicle = reqViewModel.getMainVehicle();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(mainVehicle!=null&&!TextUtils.isEmpty(mainVehicle.getMdlNm())){
                    String tel=mainVehicle.getMdlNm().equalsIgnoreCase("G90")||mainVehicle.getMdlNm().equalsIgnoreCase("EQ900") ? "080-900-6000" : "080-700-6000";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(WebView.SCHEME_TEL + tel)));
                }
                break;
            //?????? ??????/?????? ??????
            case R.id.l_service_maintenance_history_btn:
                ((BaseActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceRepairReserveHistoryActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;

            //????????????
            case R.id.l_service_maintenance_emergency_btn:
                startSOSActivity();
                break;

            //???????????? ??????
            case R.id.l_service_maintenance_customercenter_btn:
                ((BaseActivity) getActivity()).startActivitySingleTop(
                        new Intent(getActivity(), ServiceRemoteRegisterActivity.class),
                        RequestCodes.REQ_CODE_ACTIVITY.getCode(),
                        VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE
                );
                break;
            // ?????? ?????? ?????? ??????
            case R.id.l_service_maintenance_remote_servie_list_btn:
                if (!TextUtils.isEmpty(getMainVehicleVin())) {
                    ((BaseActivity) getActivity()).startActivitySingleTop(
                            new Intent(getActivity(), ServiceRemoteListActivity.class).putExtra(KeyNames.KEY_NAME_VIN, getMainVehicleVin()),
                            RequestCodes.REQ_CODE_ACTIVITY.getCode(),
                            VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE
                    );
                }
                break;

            //??????????????????
            case R.id.l_service_maintenance_defect_btn:
                //?????? ?????? ??? ???????????? ?????? ??? ?????? ?????? ??????
                AddressVO addressVO = new AddressVO();
                try {
                    addressVO.setCenterLat(lgnViewModel.getPosition().getValue().get(0));
                    addressVO.setCenterLon(lgnViewModel.getPosition().getValue().get(1));
                } catch (Exception e) {
                    addressVO.setCenterLat(37.463936);
                    addressVO.setCenterLon(127.042953);
                }

                if (!TextUtils.isEmpty(getMainVehicleVin())) {
                    ((BaseActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), ServiceRelapseHistoryActivity.class)
                                    .putExtra(KeyNames.KEY_NAME_ADDR, addressVO)
                                    .putExtra(KeyNames.KEY_NAME_MDL_NM, getMainVehicleMdlNm())
                                    .putExtra(KeyNames.KEY_NAME_VIN, getMainVehicleVin())
                            , RequestCodes.REQ_CODE_ACTIVITY.getCode()
                            , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                }
                break;

            default:
                //do nothing
                break;
        }
    }

    private String getMainVehicleVin() {
        VehicleVO mainVehicle = null;
        try {
            mainVehicle = reqViewModel.getMainVehicle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mainVehicle == null ? "" : mainVehicle.getVin();
    }

    private String getMainVehicleMdlNm() {
        VehicleVO mainVehicle = null;
        try {
            mainVehicle = reqViewModel.getMainVehicle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mainVehicle == null ? "" : mainVehicle.getMdlNm();
    }
}
