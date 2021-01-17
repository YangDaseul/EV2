package com.genesis.apps.ui.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.GNS_1001;
import com.genesis.apps.comm.model.api.gra.GNS_1002;
import com.genesis.apps.comm.model.api.gra.GNS_1003;
import com.genesis.apps.comm.model.api.gra.GNS_1004;
import com.genesis.apps.comm.model.api.gra.GNS_1005;
import com.genesis.apps.comm.model.api.gra.GNS_1010;
import com.genesis.apps.comm.model.api.gra.MYP_1005;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.CouponVO;
import com.genesis.apps.comm.model.vo.PrivilegeVO;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.comm.viewmodel.GNSViewModel;
import com.genesis.apps.comm.viewmodel.MYPViewModel;
import com.genesis.apps.databinding.ActivityMyCarNewBinding;
import com.genesis.apps.room.ResultCallback;
import com.genesis.apps.ui.common.activity.GAWebActivity;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.dialog.bottom.BottomListDialog;
import com.genesis.apps.ui.common.dialog.bottom.DialogCarRgstNo;
import com.genesis.apps.ui.main.home.view.CarHorizontalAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

public class MyCarActivity extends SubActivity<ActivityMyCarNewBinding> {

    private MYPViewModel mypViewModel;
    private GNSViewModel gnsViewModel;
    private CarHorizontalAdapter adapter;
    private String tmpCarRgstNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car_new);
        getDataFromIntent();
        setViewModel();
        setObserver();
        initView();
        gnsViewModel.reqGNS1001(new GNS_1001.Request(APPIAInfo.GM_CARLST01.getId()));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void getDataFromIntent() {

    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        ui.setActivity(this);
        gnsViewModel = new ViewModelProvider(this).get(GNSViewModel.class);
        mypViewModel = new ViewModelProvider(this).get(MYPViewModel.class);
    }

    @Override
    public void setObserver() {
        //차량 조회에 대한 결과
        gnsViewModel.getRES_GNS_1001().observe(this, result -> {

            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    if (result.data != null) {
                        gnsViewModel.setGNS1001ToDB(result.data, new ResultCallback() {
                            @Override
                            public void onSuccess(Object object) {
                                try {
                                    if (Boolean.parseBoolean(object.toString())) {
                                        List<VehicleVO> list = new ArrayList<>();
                                        list.addAll(gnsViewModel.getVehicleList());
                                        ui.vpCar.setOffscreenPageLimit(list.size());
                                        adapter.setRows(list);
                                        adapter.notifyDataSetChanged();
                                        setVehicleInfo(list.get(0), false);
                                        setBtnIndicator(0);
                                        //이동효과를 주는데 노티파이체인지와 딜레이없이 콜하면 효과가 중첩되어 사라저서 100ms 후 처리 진행
                                        new Handler().postDelayed(() -> {
                                            ui.vpCar.setCurrentItem(0, true);
                                            ui.indicator.createIndicators(list.size(), 0);
                                        }, 100);
                                    }
                                } catch (Exception e) {
                                    //TODO 예외처리 필요
                                }
                                showProgressDialog(false);
                                setEmptyView();
                            }

                            @Override
                            public void onError(Object e) {
                                showProgressDialog(false);
                                setEmptyView();
                            }
                        });
                        break;
                    }
                default:
                    showProgressDialog(false);
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (TextUtils.isEmpty(serverMsg)) {
                            serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                        }
                        SnackBarUtil.show(this, serverMsg);
                        setEmptyView();
                    }
                    break;
            }
        });
        //차량 번호 수정
        gnsViewModel.getRES_GNS_1002().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if (StringUtil.isValidString(result.data.getRtCd()).equalsIgnoreCase("0000")) {
                        try {
                            int pos = ui.vpCar.getCurrentItem();
                            VehicleVO vehicleVO = ((VehicleVO) adapter.getItem(pos));
                            vehicleVO.setCarRgstNo(tmpCarRgstNo);
                            tmpCarRgstNo = "";
                            updateDataToAdapter(vehicleVO, pos);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        SnackBarUtil.show(this, getString(R.string.gm_carlst01_snackbar_2));
                        break;
                    }
                default:
                    showProgressDialog(false);
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (TextUtils.isEmpty(serverMsg)) {
                            serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                        }
                        SnackBarUtil.show(this, serverMsg);
                    }
                    break;
            }
        });

        //삭제
        gnsViewModel.getRES_GNS_1003().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if (StringUtil.isValidString(result.data.getRtCd()).equalsIgnoreCase("0000")) {
                        try {
                            //삭제가 완료되면 로컬 데이터의 주 이용 차량 정보를 제거
                            int pos = ui.vpCar.getCurrentItem();
                            VehicleVO vehicleVO = ((VehicleVO) adapter.getItem(pos));
//                        vehicleVO.setMainVhclYn(VariableType.MAIN_VEHICLE_N);
                            vehicleVO.setDelExpDay("7");
                            vehicleVO.setDelExpYn(VariableType.DELETE_EXPIRE_Y);
                            gnsViewModel.updateVehicleToDB(vehicleVO);
                            updateDataToAdapter(vehicleVO, pos);
                            SnackBarUtil.show(this, getString(R.string.gm_carlst01_snackbar_4));
                        }catch (Exception e){

                        }
                        break;
                    }
                default:
                    showProgressDialog(false);
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (TextUtils.isEmpty(serverMsg)) {
                            serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                        }
                        SnackBarUtil.show(this, serverMsg);
                    }
                    break;
            }
        });


        //주 차량 설정
        gnsViewModel.getRES_GNS_1004().observe(this, result -> {

            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if (StringUtil.isValidString(result.data.getRtCd()).equalsIgnoreCase("0000")) {
                        gnsViewModel.reqGNS1001(new GNS_1001.Request(APPIAInfo.GM_CARLST01.getId()));
                        SnackBarUtil.show(this, getString(R.string.gm_carlst01_snackbar_1));
                        break;
                    }
                default:
                    showProgressDialog(false);
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (TextUtils.isEmpty(serverMsg)) {
                            serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                        }
                        SnackBarUtil.show(this, serverMsg);
                    }
                    break;
            }


        });

        //차량 복구
        gnsViewModel.getRES_GNS_1005().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if (StringUtil.isValidString(result.data.getRtCd()).equalsIgnoreCase("0000")) {
                        try {
                            int pos = ui.vpCar.getCurrentItem();
                            VehicleVO vehicleVO = ((VehicleVO) adapter.getItem(pos));
                            vehicleVO.setDelExpDay("");
                            vehicleVO.setDelExpYn(VariableType.DELETE_EXPIRE_N);
//                        if (adapter.getItemCount() == 1) {
//                            vehicleVO.setMainVhclYn(VariableType.MAIN_VEHICLE_Y);
//                        }
                            gnsViewModel.updateVehicleToDB(vehicleVO);
                            updateDataToAdapter(vehicleVO, pos);
                            SnackBarUtil.show(this, getString(R.string.gm_carlst01_snackbar_3));
                        }catch (Exception e){

                        }
                        break;
                    }
                default:
                    showProgressDialog(false);
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (TextUtils.isEmpty(serverMsg)) {
                            serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                        }
                        SnackBarUtil.show(this, serverMsg);
                    }
                    break;
            }
        });


        gnsViewModel.getRES_GNS_1010().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    if (result.data.getCpnList() != null && result.data.getCpnList().size() > 0) {
                        setViewCoupon(result.data.getCpnList());
                        break;
                    }
                default:
                    setViewCoupon(null);
//                    String serverMsg = "";
//                    try {
//                        serverMsg = result.data.getRtMsg();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (TextUtils.isEmpty(serverMsg)) {
//                            serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
//                        }
//                        SnackBarUtil.show(this, serverMsg);
//                    }
                    break;
            }
        });

        //프리빌리지 상태 observer
        mypViewModel.getRES_MYP_1005().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    if (result.data != null) {
                        showProgressDialog(false);
                        setPrivilegeLayout(result.data);
                        break;
                    }
                default:
                    showProgressDialog(false);
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (TextUtils.isEmpty(serverMsg)) {
                            serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                        }
                        SnackBarUtil.show(this, serverMsg);
                    }
                    break;
            }
        });
    }


    private void setPrivilegeLayout(MYP_1005.Response data) {
        if (!StringUtil.isValidString(data.getMbrshJoinYn()).equalsIgnoreCase("Y") || data.getPvilList()==null || data.getPvilList().size() < 1) {
            ui.lPrivilege.setVisibility(View.GONE);
        } else {
            ui.lPrivilege.setVisibility(View.VISIBLE);
            if (data.getPvilList().size() > 0) {
                switch (data.getPvilList().get(0).getJoinPsblCd()) {
                    case PrivilegeVO.JOIN_CODE_APPLY_POSSIBLE:
                        ui.btnStatus.setVisibility(View.INVISIBLE);
                        ui.btnBenefit.setVisibility(View.INVISIBLE);
                        ui.btnApply.setVisibility(View.VISIBLE);
                        ui.btnApply.setTag(R.id.url, data.getPvilList().get(0).getServiceUrl());
                        break;
                    case PrivilegeVO.JOIN_CODE_APPLYED:
                        ui.btnStatus.setVisibility(View.VISIBLE);
                        ui.btnBenefit.setVisibility(View.VISIBLE);
                        ui.btnApply.setVisibility(View.INVISIBLE);

                        ui.btnStatus.setTag(R.id.url, data.getPvilList().get(0).getServiceUrl());
                        ui.btnBenefit.setTag(R.id.url, data.getPvilList().get(0).getServiceDetailUrl());
                        break;
                    default:
                        ui.lPrivilege.setVisibility(View.GONE);
                        break;
                }
            }
        }
    }

    private void setViewCoupon(List<CouponVO> list) {

        int totalCnt = 0;//추후 잔여횟수가 없습니다. 등을 표시할 때 사용 가능

        if (list != null && list.size() > 0) {
            for (CouponVO couponVO : list) {
                try {
                    totalCnt += Integer.parseInt(couponVO.getRemCnt());
                } catch (Exception e) {

                }

                String cnt = (TextUtils.isEmpty(couponVO.getRemCnt()) ? "0" : couponVO.getRemCnt()) + " " + getString(R.string.gm_carlst_04_16);
                switch (couponVO.getItemDivCd()) {
                    case VariableType.COUPON_CODE_ENGINE:
                        ui.tvPartEngineOilCnt.setText(cnt);
                        ui.tvTitlePartEngine.setText((TextUtils.isEmpty(couponVO.getItemNm()) ? getString(R.string.gm_carlst_04_7) : couponVO.getItemNm()));
                        break;
                    case VariableType.COUPON_CODE_FILTER:
                        ui.tvPartAirConditionerFilterCnt.setText(cnt);
                        ui.tvTitlePartAirConditionerFilter.setText((TextUtils.isEmpty(couponVO.getItemNm()) ? getString(R.string.gm_carlst_04_11) : couponVO.getItemNm()));
                        break;
                    case VariableType.COUPON_CODE_BREAK_PAD:
                        ui.tvPartBreakPadCnt.setText(cnt);
                        ui.tvTitlePartBreakPad.setText((TextUtils.isEmpty(couponVO.getItemNm()) ? getString(R.string.gm_carlst_04_12) : couponVO.getItemNm()));
                        break;
                    case VariableType.COUPON_CODE_WIPER:
                        ui.tvPartWiperCnt.setText(cnt);
                        ui.tvTitlePartWiper.setText((TextUtils.isEmpty(couponVO.getItemNm()) ? getString(R.string.gm_carlst_04_13) : couponVO.getItemNm()));
                        break;
                    case VariableType.COUPON_CODE_BREAK_OIL:
                        ui.tvPartBreakCnt.setText(cnt);
                        ui.tvTitlePartBreak.setText((TextUtils.isEmpty(couponVO.getItemNm()) ? getString(R.string.gm_carlst_04_14) : couponVO.getItemNm()));
                        break;
                    case VariableType.COUPON_CODE_PICKUP_DELIVERY:
                        ui.tvPartHomeCnt.setText(cnt);
                        ui.tvTitlePartHome.setText((TextUtils.isEmpty(couponVO.getItemNm()) ? getString(R.string.gm_carlst_04_15) : couponVO.getItemNm()));
                        break;
                    case VariableType.COUPON_CODE_SONAKS:
                    default:
                        //처리안함
                        break;
                }
            }
        } else {
            String cnt = "0 " + getString(R.string.gm_carlst_04_16);
            ui.tvPartEngineOilCnt.setText(cnt);
            ui.tvPartAirConditionerFilterCnt.setText(cnt);
            ui.tvPartBreakPadCnt.setText(cnt);
            ui.tvPartWiperCnt.setText(cnt);
            ui.tvPartBreakCnt.setText(cnt);
            ui.tvPartHomeCnt.setText(cnt);
        }

        reqPrivilegeDataToServer();
    }

    private void reqPrivilegeDataToServer() {
        try {
            VehicleVO vehicleVO = ((VehicleVO) adapter.getItem(ui.vpCar.getCurrentItem()));
            mypViewModel.reqMYP1005(new MYP_1005.Request(APPIAInfo.GM_CARLST_04.getId(), vehicleVO.getVin()));
        }catch (Exception e){
            e.printStackTrace();
            //do nothing
        }
    }

    private void updateDataToAdapter(VehicleVO vehicleVO, int pos) {
        adapter.setRow(pos, vehicleVO);
        setVehicleInfo(vehicleVO, true);
    }

    private void setEmptyView() {
        ui.tvEmpty.setVisibility(adapter == null || adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    private void setEmptyCoupon(int totalCnt) {
        ui.tvEmptyCoupon.setVisibility(totalCnt == 0 ? View.VISIBLE : View.GONE);
    }

    private int beforePostion = -1;

    private void initView() {
        adapter = new CarHorizontalAdapter(onSingleClickListener);
        ui.vpCar.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        ui.vpCar.setAdapter(adapter);
        ui.indicator.setViewPager(ui.vpCar);
        ui.vpCar.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0 && beforePostion != 0 && beforePostion != -1) {
                    VehicleVO vehicleVO = ((VehicleVO) adapter.getItem(position));
                    setVehicleInfo(vehicleVO, false);
                    setBtnIndicator(position);
                }
                beforePostion = positionOffsetPixels;
            }
        });
//        final float pageMargin = getResources().getDimensionPixelOffset(R.dimen.offset2);
//        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset2);
//        ui.vpCar.setPageTransformer((page, position) -> {
//            float myOffset = position * -(2 * pageOffset + pageMargin);
//            if (position < -1) {
//                page.setTranslationX(-myOffset);
//
//                Log.v("viewpager bug1","offset:"+myOffset);
//            } else if (position <= 1) {
//                float scaleFactor = Math.max(1f, 1 - Math.abs(position - 0.14285715f));
//                page.setTranslationX(myOffset);
//                page.setScaleY(scaleFactor);
//                page.setScaleX(scaleFactor);
//                page.setAlpha(scaleFactor);
//
//                Log.v("viewpager bug2","offset:"+myOffset+ " Scale factor:"+scaleFactor);
//            } else {
//                page.setAlpha(0f);
//                page.setTranslationX(myOffset);
//                Log.v("viewpager bug3","offset:"+myOffset);
//            }
//
//        });
    }

    private void setBtnIndicator(int position) {
        final int maxSize = adapter.getItemCount();
        ui.btnPre.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
        ui.btnNext.setVisibility((maxSize-1)==position? View.GONE : View.VISIBLE);
    }

    private void setVehicleInfo(VehicleVO vehicleVO, boolean isUpdate) {
        ui.tvCarCode.setText(StringUtil.isValidString(vehicleVO.getMdlNm()));
        ui.tvCarModel.setText(StringUtil.isValidString(vehicleVO.getSaleMdlNm()));
        //UI초기화
        ui.tvCarVrn.setVisibility(View.GONE);
        ui.btnRegVrn.lWhole.setVisibility(View.GONE);
        ui.btnRecovery.lWhole.setVisibility(View.GONE);
        ui.btnDelete.lWhole.setVisibility(View.GONE);
        ui.btnSimilar.lWhole.setVisibility(View.GONE);
        ui.btnContract.lWhole.setVisibility(View.GONE);
        ui.lPrivilege.setVisibility(View.GONE);
        ui.ivFavorite.setVisibility(View.GONE);
        ui.ivFavorite.setTag(R.id.vehicle, vehicleVO);

        switch (StringUtil.isValidString(vehicleVO.getCustGbCd())) {
            case VariableType.MAIN_VEHICLE_TYPE_OV:
                if (StringUtil.isValidString(vehicleVO.getDelExpYn()).equalsIgnoreCase(VariableType.DELETE_EXPIRE_Y)) {
                    //삭제 예정 차량
                    //차량번호 셋팅
                    ui.tvCarVrn.setVisibility(View.VISIBLE);
                    ui.tvCarVrn.setText(StringUtil.isValidString(vehicleVO.getCarRgstNo()));
                    ui.tvCarVrn.setOnClickListener(null);
                    ui.tvCarVrn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                    //삭졔예정표기
                    ui.tvCarStatus.setText(String.format(getString(R.string.gm_carlst_04_4), (((TextUtils.isEmpty(vehicleVO.getDelExpDay())) ? "0" : vehicleVO.getDelExpDay()))));
                    //복구버튼 활성화
                    ui.btnRecovery.lWhole.setVisibility(View.VISIBLE);
                } else {
                    //정상차량
                    //차량번호 셋팅
                    if (!TextUtils.isEmpty(vehicleVO.getCarRgstNo())) {
                        //차량번호가 있을 경우
                        ui.tvCarVrn.setVisibility(View.VISIBLE);
                        ui.tvCarVrn.setText(vehicleVO.getCarRgstNo());
                        ui.tvCarVrn.setOnClickListener(onSingleClickListener);
                        ui.tvCarVrn.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.car_m_edit), null);
                    } else {
                        //차량번호가 없을 경우
                        ui.btnRegVrn.lWhole.setVisibility(View.VISIBLE);
                    }

                    //주 이용 차량 셋팅
                    ui.ivFavorite.setVisibility(View.VISIBLE);
                    ui.ivFavorite.setImageResource(StringUtil.isValidString(vehicleVO.getMainVhclYn()).equalsIgnoreCase(VariableType.MAIN_VEHICLE_N) ? R.drawable.ic_star_l_s_d : R.drawable.ic_star_l_s);
                    //주 차량 표기
                    ui.tvCarStatus.setText(StringUtil.isValidString(vehicleVO.getMainVhclYn()).equalsIgnoreCase(VariableType.MAIN_VEHICLE_N) ? "" : getString(R.string.gm_carlst_01_4));
                    //삭제버튼 활성화
                    ui.btnDelete.lWhole.setVisibility(View.VISIBLE);
                }

                if (!isUpdate) {
                    gnsViewModel.reqGNS1010(new GNS_1010.Request(APPIAInfo.GM_CARLST_04.getId(), vehicleVO.getVin(), vehicleVO.getMdlNm()));
                }
                break;

            case VariableType.MAIN_VEHICLE_TYPE_CV://계약차량
                //계약차량 표기
                ui.tvCarStatus.setText(R.string.gm_carlst_04_26);
                //유사재고조회
                ui.btnSimilar.lWhole.setVisibility(View.VISIBLE);
                //계약서조회
                ui.btnContract.lWhole.setVisibility(View.VISIBLE);
                setEmptyCoupon(0);
                break;
            case VariableType.MAIN_VEHICLE_TYPE_NV:
            default:
                break;
        }
    }


    @Override
    public void onClickCommon(View v) {

        try {
            VehicleVO vehicleVO = getCurrentVehicleVO();
            switch (v.getId()) {
                case R.id.btn_contract://계약서 조회
                    if (vehicleVO != null) {
                        startActivitySingleTop(new Intent(this, APPIAInfo.GM02_CTR01.getActivity())
                                        .putExtra(KeyNames.KEY_NAME_CTRCT_NO, vehicleVO.getCtrctNo())
                                , RequestCodes.REQ_CODE_ACTIVITY.getCode()
                                , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                    }
                    break;
                case R.id.btn_similar://유사 재고 조회
                    startActivitySingleTop(new Intent(this, APPIAInfo.GM02_INV01.getActivity()), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                    break;
                //이전 버튼과 다음버튼은 정책 변경으로 제거됨
                case R.id.btn_pre://이전 버튼
//                    ui.vpCar.setCurrentItem(ui.vpCar.getCurrentItem() - 1, true);
                    break;
                case R.id.btn_next://다음 버튼
//                    ui.vpCar.setCurrentItem(ui.vpCar.getCurrentItem() + 1, true);
                    break;
                case R.id.iv_favorite://주 이용 차량 설정
                    try {
                        if (vehicleVO != null
                                && !StringUtil.isValidString(vehicleVO.getDelExpYn()).equalsIgnoreCase(VariableType.DELETE_EXPIRE_Y) //삭제 예정 차량이 아니고
                                && StringUtil.isValidString(vehicleVO.getCustGbCd()).equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_OV) //소유 차량이고
                                && !StringUtil.isValidString(vehicleVO.getMainVhclYn()).equalsIgnoreCase(VariableType.MAIN_VEHICLE_Y)) { //현재 주 이용 차량이 아니면
                            //주 이용 차량 설정 가능
                            gnsViewModel.reqGNS1004(new GNS_1004.Request(APPIAInfo.GM_CARLST01.getId(), StringUtil.isValidString(vehicleVO.getVin())));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btn_recovery:// 차량 복구
                    if (vehicleVO != null
                            && StringUtil.isValidString(vehicleVO.getDelExpYn()).equalsIgnoreCase(VariableType.DELETE_EXPIRE_Y) //삭제 예정 차량이고
                            && StringUtil.isValidString(vehicleVO.getCustGbCd()).equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_OV) //소유 차량이고
                    ) {
                        //복구 요청 가능
                        gnsViewModel.reqGNS1005(new GNS_1005.Request(APPIAInfo.GM_CARLST_04.getId(), StringUtil.isValidString(vehicleVO.getVin())));
                    }
                    break;
                case R.id.tv_car_vrn:// 차량번호 수정
                case R.id.btn_reg_vrn:
                    if(vehicleVO!=null) {
                        final DialogCarRgstNo dialogCarRgstNo = new DialogCarRgstNo(this, R.style.BottomSheetDialogTheme);
                        dialogCarRgstNo.setOnDismissListener(dialogInterface -> {
                            if (!TextUtils.isEmpty(dialogCarRgstNo.getCarRgstNo())) {
                                tmpCarRgstNo = dialogCarRgstNo.getCarRgstNo();
                                gnsViewModel.reqGNS1002(new GNS_1002.Request(APPIAInfo.GM_CARLST_04.getId(), StringUtil.isValidString(vehicleVO.getVin()), dialogCarRgstNo.getCarRgstNo()));
                            }
                        });
                        dialogCarRgstNo.show();
                    }
                    break;
                case R.id.btn_delete:// 차량 삭제
                    if(vehicleVO!=null) {
                        final List<String> deletionList = Arrays.asList(getResources().getStringArray(R.array.vehicle_deletion_reason));
                        final BottomListDialog bottomListDialog = new BottomListDialog(this, R.style.BottomSheetDialogTheme);
                        bottomListDialog.setOnDismissListener(dialogInterface -> {
                            String result = bottomListDialog.getSelectItem();
                            if (!TextUtils.isEmpty(result)) {
                                //TODO 삭제 요청
                                String delRsnCd = "";

                                if (result.equalsIgnoreCase(deletionList.get(0))) {
                                    delRsnCd = VariableType.MY_CAR_DELETION_REASON_SELL;
                                } else if (result.equalsIgnoreCase(deletionList.get(1))) {
                                    delRsnCd = VariableType.MY_CAR_DELETION_REASON_SCRAPPED;
                                } else if (result.equalsIgnoreCase(deletionList.get(2))) {
                                    delRsnCd = VariableType.MY_CAR_DELETION_REASON_TRANSFER;
                                } else {
                                    delRsnCd = VariableType.MY_CAR_DELETION_REASON_ETC;
                                }
                                gnsViewModel.reqGNS1003(new GNS_1003.Request(APPIAInfo.GM_CARLST_04.getId(), StringUtil.isValidString(vehicleVO.getVin()), delRsnCd));
                            }
                        });
                        bottomListDialog.setDatas(deletionList);
                        bottomListDialog.setTitle(getString(R.string.gm_carlst_p01_7));
                        bottomListDialog.show();
                    }
                    //삭제제요청후
                    //성공이면 gns 001 요청 및 db갱신 후
                    //해당 차대번호의 차량으로 vehicleVO를 덮어쓴 다음에
                    //INIT..호출
                    break;
                case R.id.btn_benefit://프리빌리지 혜택
                case R.id.btn_status://프리빌리지 현황
                case R.id.btn_apply://프리빌리지 신청
                    String url = v.getTag(R.id.url).toString();
                    goPrivilege(v.getId(), url);
                    break;
                case R.id.tv_titlebar_text_btn:
                    final List<String> list = Arrays.asList(getResources().getStringArray(!isApplyRentRis() ? R.array.vehicle_reg : R.array.vehicle_list));
                    final BottomListDialog bottomListDialog2 = new BottomListDialog(this, R.style.BottomSheetDialogTheme);
                    bottomListDialog2.setOnDismissListener(dialogInterface -> {
                        String result = bottomListDialog2.getSelectItem();
                        if (!TextUtils.isEmpty(result)) {
                            if (result.equalsIgnoreCase(list.get(0))) {
                                //렌트/리스 실 운행자 등록
                                openRentRis();
                            } else {
                                //중고차 등록
                                openRent();
                            }
                        }
                    });
                    bottomListDialog2.setDatas(list);
                    bottomListDialog2.setTitle(getString(R.string.gm_carlst_p01_11));
                    bottomListDialog2.show();
                    break;
            }
        } catch (Exception e) {

        }
    }

    private void openRent() {
        startActivitySingleTop(new Intent(this, RegisterUsedCarActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
    }

    private void openRentRis() {
        if (!isApplyRentRis()) {
            //등록확인메뉴로 이동
            startActivitySingleTop(new Intent(this, LeasingCarVinRegisterActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
        } else {
            //내역으로 이동
            startActivitySingleTop(new Intent(this, LeasingCarHistActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
        }
    }

    private boolean isApplyRentRis(){
        String actoprRgstYn = "N";
        try {
            actoprRgstYn = gnsViewModel.getRES_GNS_1001().getValue().data.getActoprRgstYn();
        } catch (Exception e) {
            actoprRgstYn = "N";
        }
        return StringUtil.isValidString(actoprRgstYn).equalsIgnoreCase(VariableType.COMMON_MEANS_YES);
    }

    private void goPrivilege(int id, String url) {
        if (!TextUtils.isEmpty(url.trim())) {
            int titleId = 0;
            switch (id) {
                case R.id.btn_status:
                    titleId = R.string.mg_prvi01_word_1_2;
                    break;
                case R.id.btn_benefit:
                    titleId = R.string.mg_prvi01_word_1_3;
                    break;
                case R.id.btn_apply:
                    titleId = R.string.mg_prvi01_word_1;
                    break;
            }
            startActivitySingleTop(new Intent(this, GAWebActivity.class).putExtra(KeyNames.KEY_NAME_URL, url).putExtra(KeyNames.KEY_NAME_MAP_SEARCH_TITLE_ID, titleId), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
        } else {
            SnackBarUtil.show(this, "이동 가능한 URL이 없습니다.");
        }
    }

    private VehicleVO getCurrentVehicleVO() {
        VehicleVO vehicleVO = null;
        int pos = ui.vpCar.getCurrentItem();
        try {
            vehicleVO = ((VehicleVO) adapter.getItem(pos));
        } catch (Exception e) {
            vehicleVO = null;
        }
        return vehicleVO;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == ResultCodes.REQ_CODE_TS_AUTH.getCode()){
           gnsViewModel.reqGNS1001(new GNS_1001.Request(APPIAInfo.GM_CARLST01.getId()));
        }
    }
}