package com.genesis.apps.ui.main.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.CHB_1007;
import com.genesis.apps.comm.model.api.roadwin.ServiceAreaCheck;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.vo.AddressVO;
import com.genesis.apps.comm.model.vo.map.AroundPOIReqVO;
import com.genesis.apps.comm.model.vo.map.ReverseGeocodingReqVO;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.viewmodel.CHBViewModel;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.comm.viewmodel.MapViewModel;
import com.genesis.apps.comm.viewmodel.RoadWinViewModel;
import com.genesis.apps.databinding.ActivityMap2Binding;
import com.genesis.apps.databinding.LayoutMapOverlayUiBottomAddressBinding;
import com.genesis.apps.ui.common.activity.GpsBaseActivity;
import com.genesis.apps.ui.common.fragment.SubFragment;
import com.google.gson.Gson;
import com.hmns.playmap.PlayMapPoint;
import com.hmns.playmap.extension.PlayMapGeoItem;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MapSearchMyPositionActivity extends GpsBaseActivity<ActivityMap2Binding> {
    private MapViewModel mapViewModel;
    private LGNViewModel lgnViewModel;
    private RoadWinViewModel roadWinViewModel;
    private CHBViewModel chbViewModel;
    private LayoutMapOverlayUiBottomAddressBinding bottomSelectBinding;
    private AddressVO selectAddressVO;
    private Double[] requestPosition = new Double[2];

    private int titleId;
    private int msgId;
    //true??? ?????? ?????? ?????? ?????? ??????
    // ???????????? ????????? ?????? true??? ?????? ???????????? ????????? (2020.11.30.)
    // TODO ?????? ?????? ?????? ????????????????????? ??? ????????? isDirect??? ???????????????
//    private boolean isDirect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        ui.pmvMapView.initMap();
        getDataFromIntent();
        setViewModel();
        setObserver();
        checkEnableGPS(() -> initLocation(), () -> reqMyLocation());

    }

    private void initLocation(){
        if (selectAddressVO == null) {
            //??? ????????? ?????? ????????? ????????? map??? ??? ????????? ?????????
            initView(37.463936, 127.042953);
            lgnViewModel.setPosition(37.463936, 127.042953);
        } else {
            initView(selectAddressVO.getCenterLat(), selectAddressVO.getCenterLon());
            lgnViewModel.setPosition(selectAddressVO.getCenterLat(), selectAddressVO.getCenterLon());
        }
        //???????????? ?????? ??????
        lgnViewModel.setMyPosition(37.463936, 127.042953);
    }

    //???????????? ????????? ?????? ????????????
//    private void checkIsDirect() {
//        if (isDirect) {
//            isDirect = false;
//            openSearchAddress();
//        }
//    }

    private void initView(final double latitude, final double longitude) {
        ui.ivCenterMaker.setVisibility(View.VISIBLE);
        ui.ivCenterMaker.setImageResource(getPinMarker());
        //???????????? ?????? ??? ??? ?????????
        ui.pmvMapView.initMap(latitude, longitude, DEFAULT_ZOOM);

        ui.lMapOverlayTitle.tvMapTitleText.setVisibility(View.VISIBLE);
        ui.lMapOverlayTitle.tvMapTitleText.setOnClickListener(onSingleClickListener);
        ui.lMapOverlayTitle.tvMapTitleAddress.setVisibility(View.GONE);

//        ui.lMapOverlayTitle.tvMapTitleText.setVisibility(View.GONE);
//        ui.lMapOverlayTitle.tvMapTitleAddress.setVisibility(View.VISIBLE);
//        ui.lMapOverlayTitle.tvMapTitleAddress.setText(getTitleAddressMsg());
//        ui.lMapOverlayTitle.tvMapTitleAddress.setOnClickListener(onSingleClickListener);
        ui.btnMyPosition.setOnClickListener(onSingleClickListener);
        ui.pmvMapView.onMapTouchUpListener((motionEvent, makerList) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    lgnViewModel.setPosition(ui.pmvMapView.getMapCenterPoint().getLatitude(), ui.pmvMapView.getMapCenterPoint().getLongitude());

//                    mapViewModel.reqPlayMapGeoItem(new ReverseGeocodingReqVO(ui.pmvMapView.getMapCenterPoint().getLatitude(),ui.pmvMapView.getMapCenterPoint().getLongitude(),1));
                    break;
                default:
                    break;
            }

        });

//        checkIsDirect();
    }

    private int getTitleAddressMsg() {
        switch (titleId) {
            case R.string.service_drive_address_search_from_title://???????????? ?????????
                return R.string.service_drive_address_search_from_title;
            case R.string.service_drive_address_search_to_title://???????????? ?????????
                return R.string.service_drive_address_search_to_title;
            case 0:
            default://??? ???
                return R.string.map_title_3;
        }
    }

    private int getPinMarker() {
        switch (titleId) {
            case R.string.service_drive_address_search_from_title://???????????? ?????????
                return R.drawable.ic_pin_from;
            case R.string.service_drive_address_search_to_title://???????????? ?????????
                return R.drawable.ic_pin_to;
            case 0:
            default://??? ???
                return R.drawable.ico_map_pin_active_b;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void getDataFromIntent() {
        try {
            selectAddressVO = (AddressVO) getIntent().getSerializableExtra(KeyNames.KEY_NAME_ADDR);
            titleId = getIntent().getIntExtra(KeyNames.KEY_NAME_MAP_SEARCH_TITLE_ID, 0);
            msgId = getIntent().getIntExtra(KeyNames.KEY_NAME_MAP_SEARCH_MSG_ID, 0);
//            isDirect = getIntent().getBooleanExtra(KeyNames.KEY_NAME_MAP_SEARCH_DIRECT_OPEN, false);
        } catch (Exception e) {
            e.printStackTrace();
            selectAddressVO = null;
        }
    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        lgnViewModel = new ViewModelProvider(this).get(LGNViewModel.class);
        mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        roadWinViewModel = new ViewModelProvider(this).get(RoadWinViewModel.class);
        chbViewModel = new ViewModelProvider(this).get(CHBViewModel.class);
    }

    @Override
    public void setObserver() {

        lgnViewModel.getPosition().observe(this, doubles -> {
            requestPosition[0] = doubles.get(0);
            requestPosition[1] = doubles.get(1);
            //????????? ?????? ??????????????? ??????
            mapViewModel.reqPlayMapGeoItem(new ReverseGeocodingReqVO(requestPosition[0], requestPosition[1], 1));
        });

        mapViewModel.getPlayMapGeoItem().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if (result.data != null) {
                        mapViewModel.reqPlayMapPoiItemList(new AroundPOIReqVO("??????", requestPosition[0], requestPosition[1], 30, 3, 1, 0, 20));
                    }
                    break;
                default:
                    showProgressDialog(false);
                    break;
            }
        });

        mapViewModel.getPlayMapPoiItem().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    //??????????????? ?????? ????????????
                    if (result.data != null && result.data.size() > 0) {
                        updateAddressInfo(new Gson().fromJson(new Gson().toJson(result.data.get(0)), AddressVO.class));
                        break;
                    }
                default:
                    showProgressDialog(false);
                    //??????????????? ?????? ???????????????
                    PlayMapGeoItem item = mapViewModel.getPlayMapGeoItem().getValue().data;
                    if (item != null) {
                        updateAddressInfo(new Gson().fromJson(new Gson().toJson(item), AddressVO.class));
                    }
                    break;
            }
        });

        //????????? ?????? ?????? ?????? (???????????? ????????????) ??????
        roadWinViewModel.getRES_SERVICE_AREA_CHECK().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;

                case SUCCESS:
                    if (result.data != null && result.data.getRspCode() != null) {
                        //????????? ?????? ??????
                        if (result.data.getRspCode().equals(ServiceAreaCheck.RSP_CODE_POSSIBLE)) {
                            exitPageWithAddress();
                        }
                        // ????????? ?????? ??????
                        else {
                            SnackBarUtil.show(this, getString(R.string.sd_cant_service));
                        }

                        showProgressDialog(false);
                        return;
                    }
                    //not break; ????????? ???????????? default??? ????????????

                default:
                    showProgressDialog(false);
                    String serverMsg = "";
                    try {
                        serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        SnackBarUtil.show(this, (TextUtils.isEmpty(serverMsg)) ? getString(R.string.r_flaw06_p02_snackbar_1) : serverMsg);
                    }
                    break;
            }
        });

        // ??????????????? ????????? ?????? ?????? ??????
        chbViewModel.getRES_CHB_1007().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;

                case SUCCESS:
                    if (result.data != null && result.data.getUseYn() != null) {
                        //????????? ?????? ??????
                        if (result.data.getUseYn().equals("Y")) {
                            exitPageWithAddress();
                        }
                        // ????????? ?????? ??????
                        else {
                            SnackBarUtil.show(this, getString(R.string.service_charge_btr_err_13));
                        }

                        showProgressDialog(false);
                        break;
                    }
                default:
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
                        showProgressDialog(false);
                    }
                    break;
            }
        });


//
//        btrViewModel.getRES_BTR_1008().observe(this, result -> {
//
//            switch (result.status) {
//
//                case LOADING:
//                    showProgressDialog(true);
//
//                    break;
//                case SUCCESS:
//                    showProgressDialog(false);
//
//                    if (result.data != null && result.data.getAsnList() != null) {
//                        setPosition(result.data.getAsnList(), result.data.getAsnList().get(0));
//                    }
//
//                    break;
//
//                default:
//                    showProgressDialog(false);
//                    break;
//            }
//        });
//
//        btrViewModel.getRES_BTR_1009().observe(this, result -> {
//            switch (result.status){
//                case LOADING:
//                    showProgressDialog(true);
//                    break;
//                case SUCCESS:
//                    showProgressDialog(false);
//                    if(result.data!=null&&result.data.getRtCd().equalsIgnoreCase("0000")){
//                        setResult(ResultCodes.REQ_CODE_BTR.getCode(), new Intent().putExtra(KeyNames.KEY_NAME_BTR, btrVO));
//                        finish();
//                        break;
//                    }
//                default:
//                    showProgressDialog(false);
//                    SnackBarUtil.show(this, getString(R.string.gm_bt06_snackbar_2));
//                    break;
//            }
//        });
    }


    @Override
    public void onClickCommon(View v) {

        switch (v.getId()) {
            case R.id.tv_map_address_btn://??????
                if (selectAddressVO != null) {
                    //???????????? ???????????? ????????? ?????? ??????
                    if (titleId == R.string.service_drive_address_search_from_title) {
                        roadWinViewModel.reqServiceAreaCheck(
                                new ServiceAreaCheck.Request(
                                        "" + selectAddressVO.getCenterLon(),
                                        "" + selectAddressVO.getCenterLat()));
                    } else if(titleId == R.string.service_charge_btr_01){
                        String[] addressInfo = getAddress(selectAddressVO);
                        chbViewModel.reqCHB1007(new CHB_1007.Request(APPIAInfo.SM_CGRV01_01.getId(), addressInfo[0], null, selectAddressVO.getCenterLat(), selectAddressVO.getCenterLon(), addressInfo[1]));
                    } else {
                        exitPageWithAddress();
                    }
                }
                break;
            case R.id.btn_my_position:
                checkEnableGPS(() -> initLocation(), () -> {
                    lgnViewModel.setPosition(lgnViewModel.getMyPosition().get(0), lgnViewModel.getMyPosition().get(1));
                    try {
                        ui.pmvMapView.setMapCenterPoint(new PlayMapPoint(lgnViewModel.getMyPosition().get(0), lgnViewModel.getMyPosition().get(1)), 500);
                    }catch (Exception e){

                    }
                });
                break;
            case R.id.tv_map_title_text:
            case R.id.tv_map_title_address:
                openSearchAddress();
                break;
            case R.id.btn_my_addr_position:
                List<SubFragment> fragments = getFragments();
                if (fragments != null && fragments.size() > 0) {
                    hideFragment(fragments.get(0));
                }

                lgnViewModel.setPosition(lgnViewModel.getMyPosition().get(0), lgnViewModel.getMyPosition().get(1));
                try {
                    ui.pmvMapView.setMapCenterPoint(new PlayMapPoint(lgnViewModel.getMyPosition().get(0), lgnViewModel.getMyPosition().get(1)), 500);
                }catch (Exception e){

                }

                break;
        }

    }

    private void exitPageWithAddress() {
        exitPage(new Intent().putExtra(KeyNames.KEY_NAME_ADDR, selectAddressVO), ResultCodes.REQ_CODE_SERVICE_SOS_MAP.getCode());
    }

    private void openSearchAddress() {
        Bundle bundle = new Bundle();
        bundle.putInt(KeyNames.KEY_NAME_MAP_SEARCH_TITLE_ID, titleId);
        bundle.putInt(KeyNames.KEY_NAME_MAP_SEARCH_MSG_ID, msgId);
        showFragment(new SearchAddressHMNFragment(), bundle);
    }

    private void reqMyLocation() {
        showProgressDialog(true);
        findMyLocation(location -> {
            showProgressDialog(false);
            if (location == null) {
                exitPage("?????? ????????? ????????? ??? ????????????. GPS ????????? ?????? ??? ?????? ????????? ?????????.", ResultCodes.REQ_CODE_EMPTY_INTENT.getCode());
                return;
            }

            runOnUiThread(() -> {
                if (selectAddressVO == null) {
                    //??? ????????? ?????? ????????? ????????? map??? ??? ????????? ?????????
                    initView(location.getLatitude(), location.getLongitude());
                    lgnViewModel.setPosition(location.getLatitude(), location.getLongitude());
                } else {
                    initView(selectAddressVO.getCenterLat(), selectAddressVO.getCenterLon());
                    lgnViewModel.setPosition(selectAddressVO.getCenterLat(), selectAddressVO.getCenterLon());
                }
                //???????????? ?????? ??????
                lgnViewModel.setMyPosition(location.getLatitude(), location.getLongitude());
            });

        }, 5000, GpsRetType.GPS_RETURN_HIGH, false);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodes.REQ_CODE_GPS.getCode()) {
            if(resultCode == RESULT_OK)
                reqMyLocation();
            else{
                initLocation();
            }
        }
//        if (resultCode == ResultCodes.REQ_CODE_BTR.getCode()) {
//            btrVO = (BtrVO) data.getSerializableExtra(KeyNames.KEY_NAME_BTR);
//            List<BtrVO> list = btrViewModel.getRES_BTR_1008().getValue().data.getAsnList();
//            setPosition(list, btrVO);
//        } else if (resultCode == ResultCodes.REQ_CODE_ADDR_FILTER.getCode()) {
//            try {
//                fillerCd = data.getStringExtra(KeyNames.KEY_NAME_MAP_FILTER);
//            } catch (Exception e) {
//                fillerCd = "";
//            }
//            try {
//                addr = data.getStringExtra(KeyNames.KEY_NAME_MAP_CITY);
//            } catch (Exception e) {
//                addr = "";
//            }
//            try {
//                addrDtl = data.getStringExtra(KeyNames.KEY_NAME_MAP_GU);
//            } catch (Exception e) {
//                addrDtl = "";
//            }
//
//            if (!TextUtils.isEmpty(fillerCd) || !TextUtils.isEmpty(addr) || !TextUtils.isEmpty(addrDtl)) {
//                btrViewModel.reqBTR1008(new BTR_1008.Request(APPIAInfo.GM_CARLST_01_B01.getId(), String.valueOf(btrVO.getMapXcooNm()), String.valueOf(btrVO.getMapYcooNm()), "", "", ""));
//            }
//        }
    }
//
//    private void setPosition(PlayMapGeoItem item) {
//        if (bottomSelectBinding == null) {
//            setViewStub(R.id.vs_map_overlay_bottom_box, R.layout.layout_map_overlay_ui_bottom_address, new ViewStub.OnInflateListener() {
//                @Override
//                public void onInflate(ViewStub viewStub, View inflated) {
//                    bottomSelectBinding = DataBindingUtil.bind(inflated);
//                    bottomSelectBinding.tvMapAddressBtn.setOnClickListener(onSingleClickListener);
//                    bottomSelectBinding.tvMapAddressTitle.setText(item.title);
//                    bottomSelectBinding.tvMapAddressAddress.setText(item.addrRoad);
//                }
//            });
//        } else {
//            bottomSelectBinding.tvMapAddressTitle.setText(item.title);
//            bottomSelectBinding.tvMapAddressAddress.setText(item.addrRoad);
//        }
//    }


    private void updateAddressInfo(final AddressVO addressVO) {
        selectAddressVO = addressVO;
        if (bottomSelectBinding == null) {
            setViewStub(R.id.vs_map_overlay_bottom_box, R.layout.layout_map_overlay_ui_bottom_address, (viewStub, inflated) -> {
                bottomSelectBinding = DataBindingUtil.bind(inflated);
                bottomSelectBinding.tvMapAddressBtn.setOnClickListener(onSingleClickListener);
//                    setViewAddresInfo(addressVO.getTitle(), addressVO.getCname(), addressVO.getAddrRoad());
                setViewAddresInfo(addressVO);
            });
        } else {
            setViewAddresInfo(addressVO);
        }
    }


    private void setViewAddresInfo(AddressVO addressVO) {
        String[] addressInfo = getAddress(addressVO);
        bottomSelectBinding.tvMapAddressTitle.setText(addressInfo[1]);
        bottomSelectBinding.tvMapAddressTitle.setVisibility(TextUtils.isEmpty(addressInfo[1]) ? View.GONE : View.VISIBLE);
        bottomSelectBinding.tvMapAddressAddress.setText(addressInfo[0]);
        bottomSelectBinding.tvMapAddressAddress.setVisibility(TextUtils.isEmpty(addressInfo[0]) ? View.GONE : View.VISIBLE);
//        bottomSelectBinding.tvMapAddressTitle.setText(buildName+ (TextUtils.isEmpty(subBuildName) ? "" : " "+subBuildName));
//        bottomSelectBinding.tvMapAddressTitle.setVisibility(TextUtils.isEmpty(buildName) ? View.GONE : View.VISIBLE);
//        bottomSelectBinding.tvMapAddressAddress.setText(address);
//        bottomSelectBinding.tvMapAddressAddress.setVisibility(TextUtils.isEmpty(address) ? View.GONE : View.VISIBLE);
    }

    public void setAddressInfo(AddressVO addressVO) {
        new Handler().postDelayed(() -> {
            if (addressVO != null) {
                updateAddressInfo(addressVO);
                try {
                    ui.pmvMapView.setMapCenterPoint(new PlayMapPoint(addressVO.getCenterLat(), addressVO.getCenterLon()), 500);
                }catch (Exception e){

                }
            }
        }, 500);
    }

    @Override
    public void onBackPressed() {
        List<SubFragment> fragments = getFragments();
        if (fragments != null && fragments.size() > 0) {
            hideFragment(fragments.get(0));
        } else {
            super.onBackPressed();
        }
    }



}
