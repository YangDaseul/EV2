package com.genesis.apps.ui.main;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.BTR_1008;
import com.genesis.apps.comm.model.api.gra.BTR_1009;
import com.genesis.apps.comm.model.api.gra.EPT_1001;
import com.genesis.apps.comm.model.api.gra.REQ_1002;
import com.genesis.apps.comm.model.api.gra.REQ_1003;
import com.genesis.apps.comm.model.api.gra.STC_1001;
import com.genesis.apps.comm.model.constants.ChargeSearchCategorytype;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.constants.SpidCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.AddressVO;
import com.genesis.apps.comm.model.vo.BtrVO;
import com.genesis.apps.comm.model.vo.ChargeEptInfoVO;
import com.genesis.apps.comm.model.vo.ChargeSearchCategoryVO;
import com.genesis.apps.comm.model.vo.RepairTypeVO;
import com.genesis.apps.comm.model.vo.ReserveVo;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.model.vo.map.ReverseGeocodingReqVO;
import com.genesis.apps.comm.util.PackageUtil;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.comm.viewmodel.BTRViewModel;
import com.genesis.apps.comm.viewmodel.EPTViewModel;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.comm.viewmodel.MapViewModel;
import com.genesis.apps.comm.viewmodel.PUBViewModel;
import com.genesis.apps.comm.viewmodel.REQViewModel;
import com.genesis.apps.comm.viewmodel.STCViewModel;
import com.genesis.apps.databinding.ActivityMap2Binding;
import com.genesis.apps.databinding.LayoutMapOverlayUiBottomEvChargeBinding;
import com.genesis.apps.databinding.LayoutMapOverlayUiBottomSelectNewBinding;
import com.genesis.apps.ui.common.activity.GpsBaseActivity;
import com.genesis.apps.ui.common.dialog.bottom.BottomListDialog;
import com.genesis.apps.ui.common.dialog.bottom.BottomRecyclerDialog;
import com.genesis.apps.ui.common.fragment.SubFragment;
import com.genesis.apps.ui.main.home.BluehandsFilterFragment;
import com.genesis.apps.ui.main.home.BtrBluehandsListActivity;
import com.genesis.apps.ui.main.service.ChargeFindActivity;
import com.genesis.apps.ui.main.service.ChargeReserveActivity;
import com.genesis.apps.ui.main.service.ChargeStationDetailActivity;
import com.genesis.apps.ui.main.service.SearchAddressHMNFragment;
import com.google.gson.Gson;
import com.hmns.playmap.PlayMapPoint;
import com.hmns.playmap.shape.PlayMapMarker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.genesis.apps.comm.model.api.BaseResponse.RETURN_CODE_SUCC;

/**
 * @brief ????????? ????????????
 * ????????? ??????, ???????????? ?????? ??????, ????????? ???????????? ?????? ????????? ?????? ????????????
 * ??? ???????????? ????????? ?????? ?????????
 * @see #PAGE_TYPE_BTR ????????? ??????
 * ?????? ?????? ?????? : ??????
 * @see #PAGE_TYPE_RENT ????????????
 * ?????? ?????? ?????? : ??????
 * @see #PAGE_TYPE_SERVICE ????????? ???????????? ??????
 * ?????? ?????? ?????? : ??????
 */
public class ServiceNetworkActivity extends GpsBaseActivity<ActivityMap2Binding> implements SearchAddressHMNFragment.AddressSelectListener {

    private MapViewModel mapViewModel;
    private BTRViewModel btrViewModel;
    private LGNViewModel lgnViewModel;
    private PUBViewModel pubViewModel;
    private REQViewModel reqViewModel;
    private EPTViewModel eptViewModel;
    private STCViewModel stcViewModel;
    private BtrVO btrVO = null;
    private ChargeEptInfoVO selectedChargeEptInfo;
    private ReserveVo selectChargeStcInfo;
    private LayoutMapOverlayUiBottomSelectNewBinding bottomSelectBinding;
    private LayoutMapOverlayUiBottomEvChargeBinding evBottomSelectBinding;
    private String fillerCd = "";
    private String rparTypCd = ""; //????????? ?????? ??? ?????? ????????? ????????? REQ-1002 ??????
    private String addr = "";
    private String addrDtl = "";
    private VehicleVO mainVehicle = null;

    private List<ChargeSearchCategoryVO> searchCategoryList;
    private AddressVO initAddressVO;
    private Intent evReturnData=new Intent();

    public final static int PAGE_TYPE_BTR = 0;//????????? ??????
    public final static int PAGE_TYPE_RENT = 1;//???????????? ??????
    public final static int PAGE_TYPE_SERVICE = 2;//????????? ????????????
    public final static int PAGE_TYPE_REPAIR = 3;//???????????????
    public final static int PAGE_TYPE_EVCHARGE = 4;//????????? ??????
    public final static int PAGE_TYPE_EVCHARGE_STC = 5; // ????????? ??????(???????????????)
    private int pageType = 0;
    private boolean isReSearchCharge=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        getDataFromIntent();
        setViewModel();
        setObserver();
        initView();

        if(initAddressVO!=null&&initAddressVO.getCenterLon()>0&&initAddressVO.getCenterLat()>0){
            lgnViewModel.setPosition(initAddressVO.getCenterLat(), initAddressVO.getCenterLon());
        }else {
            checkEnableGPS(() -> {
                //????????????????????????
                lgnViewModel.setPosition(37.463936, 127.042953);
                lgnViewModel.setMyPosition(37.463936, 127.042953);
            }, () -> {
                reqMyLocation();
            });
        }
    }

    private void initView() {
        // pageType ?????? ?????? ?????? ?????? UI ?????? ??????.
        switch (pageType) {
            case PAGE_TYPE_EVCHARGE_STC:
            case PAGE_TYPE_EVCHARGE: {
                // ????????? ??????
                ui.rgLocation.setVisibility(View.GONE);
                ui.ivBtnFilter.setVisibility(View.VISIBLE);
                ui.ivBtnFilter.setOnClickListener(onSingleClickListener);
                ui.btnMyPosition.setVisibility(View.GONE);
                ui.btnMyPosition2.setOnClickListener(onSingleClickListener);
                ui.btnCarPosition.setOnClickListener(onSingleClickListener);
                ui.btnResearch.setVisibility(View.VISIBLE);
                ui.btnResearch.setOnClickListener(onSingleClickListener);
                ui.lMapOverlayTitle.btnSearch.setVisibility(View.GONE);
                ui.lMapOverlayTitle.btnSearchList.setVisibility(View.GONE);
                break;
            }
            case PAGE_TYPE_BTR:
            case PAGE_TYPE_RENT:
            case PAGE_TYPE_SERVICE:
            case PAGE_TYPE_REPAIR:
            default: {
                ui.btnResearch.setVisibility(View.GONE);
                ui.rgLocation.setVisibility(View.GONE);
                ui.ivBtnFilter.setVisibility(View.GONE);
                ui.btnMyPosition.setVisibility(View.VISIBLE);
                ui.btnMyPosition.setOnClickListener(onSingleClickListener);
                break;
            }
        }
        ui.lMapOverlayTitle.tvMapTitleText.setVisibility(View.GONE);
        ui.lMapOverlayTitle.lServiceNetworkTitle.setVisibility(View.VISIBLE);
        ui.lMapOverlayTitle.btnSearch.setOnClickListener(onSingleClickListener);
        ui.lMapOverlayTitle.btnSearchList.setOnClickListener(onSingleClickListener);
        ui.pmvMapView.onMapTouchUpListener((motionEvent, makerList) -> {

            //????????? ??????????????? ????????? ?????? ?????? ?????? ??????
            if (makerList != null && makerList.size() > 0) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        BtrVO btrVO = null;
                        List<BtrVO> list = new ArrayList<>();
                        try {
                            switch (pageType) {
                                case PAGE_TYPE_BTR:
                                case PAGE_TYPE_RENT:
                                    btrVO = btrViewModel.getBtrVO(makerList.get(0).getId());
                                    list = btrViewModel.getRES_BTR_1008().getValue().data.getAsnList();
                                    break;
                                case PAGE_TYPE_EVCHARGE_STC:
                                    setPositionChargeStationStc(stcViewModel.getRES_STC_1001().getValue().data.getSearchList(),
                                            (stcViewModel.getChargeStcInfoVO(makerList.get(0).getId())), true);
                                    break;
                                case PAGE_TYPE_EVCHARGE: {
                                    setPositionChargeStation(eptViewModel.getRES_EPT_1001().getValue().data.getChgList()
                                            , (eptViewModel.getChargeEptInfoVO(makerList.get(0).getId())), true);
                                    break;
                                }
                                case PAGE_TYPE_SERVICE:
                                case PAGE_TYPE_REPAIR:
                                default:
                                    btrVO = reqViewModel.getBtrVO(makerList.get(0).getId());
                                    list = reqViewModel.getRES_REQ_1002().getValue().data.getAsnList();
                                    break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (btrVO != null) {
                                setPosition(list, btrVO, true);
                            }
                        }
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void getDataFromIntent() {
        try {
            btrVO = (BtrVO) getIntent().getSerializableExtra(KeyNames.KEY_NAME_BTR);
            pageType = getIntent().getIntExtra(KeyNames.KEY_NAME_PAGE_TYPE, PAGE_TYPE_SERVICE);
            rparTypCd = getIntent().getStringExtra(KeyNames.KEY_NAME_SERVICE_REPAIR_TYPE_CODE);
            initAddressVO = (AddressVO) getIntent().getSerializableExtra(KeyNames.KEY_NAME_ADDR);

            if(pageType == PAGE_TYPE_EVCHARGE || pageType == PAGE_TYPE_EVCHARGE_STC) {
                searchCategoryList = getIntent().getParcelableArrayListExtra(KeyNames.KEY_NAME_FILTER_INFO);
                if (searchCategoryList == null) {
                    // ????????? ?????? ??????
                    searchCategoryList = getDefaultCategoryList(pageType);
                }else{
                    if(pageType == PAGE_TYPE_EVCHARGE) {
                        ChargeSearchCategoryVO chargeSearchCategoryVO = searchCategoryList.stream().filter(data -> data.getTitleResId() == R.string.sm_evss01_16).findFirst().orElse(null);
                        if (chargeSearchCategoryVO == null) {
                            searchCategoryList.add(getChargeTypeAll());
                        } else if (chargeSearchCategoryVO.getSelectedItem() == null || chargeSearchCategoryVO.getSelectedItem().size() < 1) {
                            chargeSearchCategoryVO.addSelectedItem(ChargeSearchCategorytype.ALL);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<ChargeSearchCategoryVO> getDefaultCategoryList(int pageType) {
        if (pageType == PAGE_TYPE_EVCHARGE) {
            return new ArrayList<>(Arrays.asList(
                    new ChargeSearchCategoryVO(R.string.sm_evss01_15, ChargeSearchCategoryVO.COMPONENT_TYPE.ONLY_ONE, null),
                    getChargeTypeAll(),// ?????? ?????? ??? ??????.
                    new ChargeSearchCategoryVO(R.string.sm_evss01_21, ChargeSearchCategoryVO.COMPONENT_TYPE.CHECK, Arrays.asList(ChargeSearchCategorytype.SUPER_SPEED, ChargeSearchCategorytype.HIGH_SPEED, ChargeSearchCategorytype.SLOW_SPEED)),
                    new ChargeSearchCategoryVO(R.string.sm_evss01_25, ChargeSearchCategoryVO.COMPONENT_TYPE.CHECK, Arrays.asList(ChargeSearchCategorytype.S_TRAFFIC_CRADIT_PAY, ChargeSearchCategorytype.CAR_PAY))
            ));
        } else if (pageType == PAGE_TYPE_EVCHARGE_STC) {
            return new ArrayList<>(Arrays.asList(
                    new ChargeSearchCategoryVO(R.string.sm_evss01_15, ChargeSearchCategoryVO.COMPONENT_TYPE.ONLY_ONE, null),
                    new ChargeSearchCategoryVO(R.string.sm_evss01_21, ChargeSearchCategoryVO.COMPONENT_TYPE.CHECK, Arrays.asList(ChargeSearchCategorytype.SUPER_SPEED, ChargeSearchCategorytype.HIGH_SPEED, ChargeSearchCategorytype.SLOW_SPEED))
            ));
        } else {
            return new ArrayList<>();
        }
    }

    private ChargeSearchCategoryVO getChargeTypeAll(){
        return new ChargeSearchCategoryVO(R.string.sm_evss01_16, ChargeSearchCategoryVO.COMPONENT_TYPE.RADIO, Arrays.asList(ChargeSearchCategorytype.ALL, ChargeSearchCategorytype.GENESIS, ChargeSearchCategorytype.E_PIT, ChargeSearchCategorytype.HI_CHARGER))
                .addSelectedItem(ChargeSearchCategorytype.ALL);
    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        btrViewModel = new ViewModelProvider(this).get(BTRViewModel.class);
        lgnViewModel = new ViewModelProvider(this).get(LGNViewModel.class);
        pubViewModel = new ViewModelProvider(this).get(PUBViewModel.class);
        reqViewModel = new ViewModelProvider(this).get(REQViewModel.class);
        eptViewModel = new ViewModelProvider(this).get(EPTViewModel.class);
        stcViewModel = new ViewModelProvider(this).get(STCViewModel.class);
    }

    @Override
    public void setObserver() {

        mapViewModel.getPlayMapGeoItem().observe(this, result -> {

            switch (result.status){
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if(result.data!=null){
                        initAddressVO.setAddrRoad(result.data.addrRoad);
                        initAddressVO.setAddr(result.data.addr);
                        initAddressVO.setCenterLat(ui.pmvMapView.getMapCenterLatitude());
                        initAddressVO.setCenterLon(ui.pmvMapView.getMapCenterLongitude());
                        lgnViewModel.setPosition(initAddressVO.getCenterLat(), initAddressVO.getCenterLon());
                    }
                    break;
                default:
                    showProgressDialog(false);
                    break;
            }
        });


        lgnViewModel.getPosition().observe(this, doubles -> {
            if(!isReSearchCharge) {
                ui.pmvMapView.initMap(doubles.get(0), doubles.get(1), DEFAULT_ZOOM_WIDE);
            }else{
                isReSearchCharge=false;
            }

            switch (pageType) {
                case PAGE_TYPE_BTR:
                case PAGE_TYPE_RENT:
                    btrViewModel.reqBTR1008(new BTR_1008.Request(APPIAInfo.GM_CARLST_01_B01.getId(), String.valueOf(doubles.get(1)), String.valueOf(doubles.get(0)), "", "", fillerCd));
                    break;
                case PAGE_TYPE_EVCHARGE_STC:
                case PAGE_TYPE_EVCHARGE:
                    try {
                        mainVehicle = reqViewModel.getMainVehicle();
                        reqSearchChargeStation(doubles.get(0), doubles.get(1), searchCategoryList);
                    } catch (Exception e) {

                    }
                    break;
                case PAGE_TYPE_REPAIR:
                    if (btrVO != null) {
                        try {
                            mainVehicle = reqViewModel.getMainVehicle();
                            reqViewModel.reqREQ1002(new REQ_1002.Request(APPIAInfo.SM_SNFIND01.getId(), mainVehicle.getVin(), mainVehicle.getMdlCd(), btrVO.getMapXcooNm(), btrVO.getMapYcooNm(), "", "", "", rparTypCd));
                        } catch (Exception e) {

                        }
                        break;
                    }
                case PAGE_TYPE_SERVICE:
                default:
                    try {
                        mainVehicle = reqViewModel.getMainVehicle();
                        reqViewModel.reqREQ1002(new REQ_1002.Request(APPIAInfo.SM_SNFIND01.getId(), mainVehicle.getVin(), mainVehicle.getMdlCd(), String.valueOf(doubles.get(1)), String.valueOf(doubles.get(0)), "", "", "", rparTypCd));
                    } catch (Exception e) {

                    }
                    break;
            }

        });

        btrViewModel.getRES_BTR_1008().observe(this, result -> {

            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if (result.data != null && result.data.getAsnList() != null) {

                        try {
                            if (result.data.getAsnList().size() > 0) {
                                result.data.setAsnList(result.data.getAsnList().stream().sorted((o1, o2) -> Float.compare(Float.parseFloat(o1.getDist()), Float.parseFloat(o2.getDist()))).collect(Collectors.toList()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        setPosition(result.data.getAsnList(), result.data.getAsnList().get(0), false);
                        break;
                    }
                default:
                    String serverMsg = "";
                    try {
//                        serverMsg = result.data.getRtMsg();
                        serverMsg = getString(R.string.snackbar_etc_3);
                        //?????? ???????????? ?????? ????????? ???????????? ??? ??????????????? ??????
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        setPosition(null, null, false);
                        showProgressDialog(false);
                        SnackBarUtil.show(this, (TextUtils.isEmpty(serverMsg)) ? getString(R.string.r_flaw06_p02_snackbar_1) : serverMsg);
                    }
                    break;
            }
        });

        btrViewModel.getRES_BTR_1009().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if (result.data != null && result.data.getRtCd().equalsIgnoreCase("0000")) {
                        setResult(ResultCodes.REQ_CODE_BTR.getCode(), new Intent().putExtra(KeyNames.KEY_NAME_BTR, btrVO));
                        finish();
                        break;
                    }
                default:
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        showProgressDialog(false);
                        SnackBarUtil.show(this, (TextUtils.isEmpty(serverMsg)) ? getString(R.string.gm_bt06_snackbar_2) : serverMsg);
                    }
                    break;
            }
        });


        pubViewModel.getFilterInfo().observe(this, filterInfo -> {
            try {
                fillerCd = filterInfo.get(0);
            } catch (Exception e) {
                fillerCd = "";
            }
            try {
                addr = filterInfo.get(1);
            } catch (Exception e) {
                addr = "";
            }
            try {
                addrDtl = filterInfo.get(2);
            } catch (Exception e) {
                addrDtl = "";
            }

//            if (!TextUtils.isEmpty(fillerCd) || !TextUtils.isEmpty(addr) || !TextUtils.isEmpty(addrDtl)) {
            try {
                switch (pageType) {
                    case PAGE_TYPE_BTR:
                    case PAGE_TYPE_RENT:
                        btrViewModel.reqBTR1008(new BTR_1008.Request(APPIAInfo.GM_CARLST_01_B01.getId(), String.valueOf(lgnViewModel.getPosition().getValue().get(1)), String.valueOf(lgnViewModel.getPosition().getValue().get(0)), addr, addrDtl, fillerCd));
                        break;
                    case PAGE_TYPE_EVCHARGE_STC:
                    case PAGE_TYPE_EVCHARGE: {
                        // ????????? ??????
                        // Nothing
                        break;
                    }
                    case PAGE_TYPE_REPAIR:
                    case PAGE_TYPE_SERVICE:
                    default:
                        reqViewModel.reqREQ1002(new REQ_1002.Request(APPIAInfo.SM_SNFIND01.getId(), reqViewModel.getMainVehicle().getVin(), reqViewModel.getMainVehicle().getMdlCd(), String.valueOf(lgnViewModel.getPosition().getValue().get(1)), String.valueOf(lgnViewModel.getPosition().getValue().get(0)), addr, addrDtl, fillerCd, rparTypCd));
                        break;
                }
            } catch (Exception e) {

            }
//            }
        });


        reqViewModel.getRES_REQ_1002().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if (result.data != null && result.data.getAsnList() != null) {

                        try {
                            if (result.data.getAsnList().size() > 0) {
                                result.data.setAsnList(result.data.getAsnList().stream().sorted((o1, o2) -> Float.compare(Float.parseFloat(o1.getDist()), Float.parseFloat(o2.getDist()))).collect(Collectors.toList()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        setPosition(result.data.getAsnList(), result.data.getAsnList().get(0), false);
                        break;
                    }
                default:
                    String serverMsg = "";
                    try {
//                        serverMsg = result.data.getRtMsg();
                        serverMsg = getString(R.string.snackbar_etc_3);
                        //?????? ???????????? ?????? ????????? ???????????? ??? ??????????????? ??????
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        setPosition(null, null, false);
                        showProgressDialog(false);
                        SnackBarUtil.show(this, (TextUtils.isEmpty(serverMsg)) ? getString(R.string.r_flaw06_p02_snackbar_1) : serverMsg);
                    }
                    break;
            }
        });


        reqViewModel.getRES_REQ_1003().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    List<RepairTypeVO> list = new ArrayList<>();
                    if (result.data != null && result.data.getRtCd().equalsIgnoreCase(RETURN_CODE_SUCC)) {
                        if (StringUtil.isValidString(result.data.getPrctYn()).equalsIgnoreCase(VariableType.COMMON_MEANS_YES) && result.data.getRparTypList() != null && result.data.getRparTypList().size() > 0) {
                            list.addAll(result.data.getRparTypList());
                            showDialogRepairType(list);
                        } else {
                            SnackBarUtil.show(this, getString(R.string.sm_snfind01_snackbar_2));
                        }
                        break;
                    }
                default:
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        showProgressDialog(false);
                        SnackBarUtil.show(this, (TextUtils.isEmpty(serverMsg)) ? "?????? ????????? ??????????????????.\n?????? ????????? ????????? ?????????." : serverMsg);
                    }
                    break;
            }
        });

        eptViewModel.getRES_EPT_1001().observe(this, result -> {
            switch (result.status) {
                case LOADING: {
                    showProgressDialog(true);
                    break;
                }
                case SUCCESS: {
                    showProgressDialog(false);
                    try {
                        if (result.data != null&&(RETURN_CODE_SUCC.equalsIgnoreCase(result.data.getRtCd()))) {
                            setPositionChargeStation(result.data.getChgList()
                                    , (result.data.getChgList()!=null&&result.data.getChgList().size()>0) ? result.data.getChgList().get(0) : null, false
                            );
                            break;
                        }
                    } catch (Exception e) {

                    }
                }
                default: {
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        showProgressDialog(false);
                        setPositionChargeStation(null, null, false);
                        SnackBarUtil.show(this, (TextUtils.isEmpty(serverMsg)) ? "?????? ????????? ??????????????????.\n?????? ????????? ????????? ?????????." : serverMsg);
                    }
                }
            }
        });

        stcViewModel.getRES_STC_1001().observe(this, result -> {
            switch (result.status) {
                case LOADING: {
                    showProgressDialog(true);
                    break;
                }
                case SUCCESS: {
                    showProgressDialog(false);
                    try {
                        if (result.data != null&&(RETURN_CODE_SUCC.equalsIgnoreCase(result.data.getRtCd()))) {
                            setPositionChargeStationStc(result.data.getSearchList()
                                    , (result.data.getSearchList()!=null&&result.data.getSearchList().size()>0) ? result.data.getSearchList().get(0) : null, false
                            );
                            break;
                        }
                    } catch (Exception e) {

                    }
                }
                default: {
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        showProgressDialog(false);
                        setPositionChargeStationStc(null, null, false);
                        SnackBarUtil.show(this, (TextUtils.isEmpty(serverMsg)) ? "?????? ????????? ??????????????????.\n?????? ????????? ????????? ?????????." : serverMsg);
                    }
                }
            }
        });
    }

    private void showDialogRepairType(final List<RepairTypeVO> list) {
        try {
            final BottomListDialog bottomListDialog = new BottomListDialog(this, R.style.BottomSheetDialogTheme);
            bottomListDialog.setOnDismissListener(dialogInterface -> {
                String result = bottomListDialog.getSelectItem();
                if (!TextUtils.isEmpty(result)) {
                    RepairTypeVO repairTypeVO = null;
                    try {
                        repairTypeVO = reqViewModel.getRepairTypeCd(result, list);
                        exitPage(new Intent().putExtra(KeyNames.KEY_NAME_SERVICE_REPAIR_TYPE_CODE, repairTypeVO)
                                .putExtra(KeyNames.KEY_NAME_BTR, btrVO), ResultCodes.REQ_CODE_SERVICE_NETWORK_RESERVE.getCode());
                    } catch (Exception e) {

                    }
                }
            });
            bottomListDialog.setDatas(reqViewModel.getRepairTypeNm(list));
            bottomListDialog.setTitle(getString(R.string.sm_snfind01_p01_1));
            bottomListDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDialogFilter(List<ChargeSearchCategoryVO> filterList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(ServiceNetworkActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ChargerFilterListAdapter adapter = new ChargerFilterListAdapter();
        adapter.setRows(filterList);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(ServiceNetworkActivity.this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(new ColorDrawable(getColor(R.color.x_e5e5e5)));
        BottomRecyclerDialog dialog = new BottomRecyclerDialog.Builder(ServiceNetworkActivity.this)
                .setTitle(R.string.sm_evss01_36)
                .setBottomButtonTitle(R.string.sm_evss01_29)
                .setAdapter(adapter)
                .addDecoration(dividerItemDecoration)
                .setLayoutManager(layoutManager)
                .setButtonClickListener((view) -> {
                    searchCategoryList = adapter.getItems();
                    ArrayList<ChargeSearchCategoryVO> list = new ArrayList<>();
                    list.addAll(searchCategoryList);
                    evReturnData.putParcelableArrayListExtra(KeyNames.KEY_NAME_FILTER_INFO, list);
                    setResult(ResultCodes.REQ_CODE_CHARGE_FILTER_APPLY.getCode(), evReturnData);
                    reqSearchChargeStation(lgnViewModel.getPosition().getValue().get(0), lgnViewModel.getPosition().getValue().get(1), adapter.getItems());
                })
                .build();
        dialog.show();
    }


    @Override
    public void onClickCommon(View v) {

        switch (v.getId()) {
            case R.id.tv_map_select_phone://????????????

                String tel = ((TextView) v).getText().toString().trim().replaceAll("-", "");
                if (!TextUtils.isEmpty(tel))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(WebView.SCHEME_TEL + tel)));

                break;

            case R.id.tv_auth_1:
            case R.id.tv_auth_2:
            case R.id.tv_auth_3:
            case R.id.tv_auth_4:
                if (btrVO == null || StringUtil.isValidString(btrVO.getAcps1Cd()).equalsIgnoreCase("2"))
                    return;

                int authId = Integer.parseInt(v.getTag(R.id.item).toString());
                String msg;
                switch (authId) {
                    case R.string.bt06_17://????????????
                        msg = btrVO.getPntgXclSvcSbc();
                        break;
                    case R.string.bt06_18://???????????????
                        msg = btrVO.getPrimTechSvcSbc();
                        break;
                    case R.string.bt06_23://cs????????????
                        msg = btrVO.getPrimCsSvcSbc();
                        break;
                    case R.string.bt06_23_2://????????? ??????
                        msg = btrVO.getEvSvcSbc();
                        break;
                    default:
                        return;
                }
                if (!TextUtils.isEmpty(msg)) {
                    SnackBarUtil.show(this, msg);
                }
                break;
            case R.id.btn_left_white: //??????????????????
                switch (pageType) {
                    case PAGE_TYPE_REPAIR:
                    case PAGE_TYPE_SERVICE:
                        if (btrVO != null) {
                            startActivitySingleTop(new Intent(this, ServiceNetworkPriceActivity.class).putExtra(KeyNames.KEY_NAME_ASNCD, btrVO.getAsnCd()), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_VERTICAL_SLIDE);
                        }
                        break;
                    case PAGE_TYPE_EVCHARGE_STC:
                    case PAGE_TYPE_EVCHARGE: {
                        // ????????? ??????
                        // Nothing
                        break;
                    }
                }
                break;
            case R.id.btn_right_black://??????
                switch (pageType) {
                    case PAGE_TYPE_BTR:
                        btrViewModel.reqBTR1009(new BTR_1009.Request(APPIAInfo.GM_BT06.getId(), btrVO.getVin(), btrVO.getAsnCd()));
                        break;
                    case PAGE_TYPE_RENT:
                        //?????? ????????????.
                        exitPage(new Intent().putExtra(KeyNames.KEY_NAME_BTR, btrVO), ResultCodes.REQ_CODE_BTR.getCode());
                        break;
                    case PAGE_TYPE_REPAIR:
                        //????????? ??????2?????? ??????
                        exitPage(new Intent().putExtra(KeyNames.KEY_NAME_BTR, btrVO), ResultCodes.REQ_CODE_BTR.getCode());
                        break;
                    case PAGE_TYPE_EVCHARGE_STC:
                        if(selectChargeStcInfo != null && initAddressVO != null) {
                            startActivitySingleTop(new Intent(ServiceNetworkActivity.this, ChargeStationDetailActivity.class)
                                            .putExtra(KeyNames.KEY_STATION_TYPE, VariableType.CHARGE_STATION_TYPE_STC)
                                            .putExtra(KeyNames.KEY_NAME_CHARGE_STATION_CSID, selectChargeStcInfo.getSid())
                                            .putExtra(KeyNames.KEY_NAME_LAT, initAddressVO.getCenterLat()+"")
                                            .putExtra(KeyNames.KEY_NAME_LOT, initAddressVO.getCenterLon()+""),
                                    RequestCodes.REQ_CODE_ACTIVITY.getCode(),
                                    VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                        }
                        break;
                    case PAGE_TYPE_EVCHARGE: {
                        // ????????? ??????
                        if(selectedChargeEptInfo != null && initAddressVO != null) {
                            startActivitySingleTop(new Intent(ServiceNetworkActivity.this, ChargeStationDetailActivity.class)
                                            .putExtra(KeyNames.KEY_NAME_CHARGE_STATION_SPID, selectedChargeEptInfo.getSpid())
                                            .putExtra(KeyNames.KEY_NAME_CHARGE_STATION_CSID, selectedChargeEptInfo.getCsid())
                                            .putExtra(KeyNames.KEY_NAME_LAT, initAddressVO.getCenterLat()+"")
                                            .putExtra(KeyNames.KEY_NAME_LOT, initAddressVO.getCenterLon()+""),
                                    RequestCodes.REQ_CODE_ACTIVITY.getCode(),
                                    VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                        }
                        break;
                    }
                    case PAGE_TYPE_SERVICE:
                    default:
                        reqViewModel.reqREQ1003(new REQ_1003.Request(APPIAInfo.SM_SNFIND01.getId(), mainVehicle.getVin(), mainVehicle.getMdlCd(), btrVO.getAsnCd(), btrVO.getAcps1Cd(), btrVO.getFirmScnCd()));
                        break;
                }
                break;
            case R.id.btn_search_list://??????
                List<BtrVO> list = new ArrayList<>();

                try {
                    switch (pageType) {
                        case PAGE_TYPE_BTR:
                        case PAGE_TYPE_RENT:
                            list = btrViewModel.getRES_BTR_1008().getValue().data.getAsnList();
                            break;
                        case PAGE_TYPE_EVCHARGE_STC: {
                            startActivitySingleTop(new Intent(ServiceNetworkActivity.this, ChargeReserveActivity.class),
                                    RequestCodes.REQ_CODE_ACTIVITY.getCode(),
                                    VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                            break;
                        }
                        case PAGE_TYPE_EVCHARGE: {
                            /*
                             ?????? ????????? ?????? ???????????? ???????????? ????????? EV ????????? ????????? ?????? ????????? ??????
                             ??? ???????????? ?????? ?????? ??? return ??????
                             */
                            startActivitySingleTop(new Intent(ServiceNetworkActivity.this, ChargeFindActivity.class),
                                    RequestCodes.REQ_CODE_ACTIVITY.getCode(),
                                    VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                            return;
                        }
                        case PAGE_TYPE_REPAIR:
                        case PAGE_TYPE_SERVICE:
                        default:
                            list = reqViewModel.getRES_REQ_1002().getValue().data.getAsnList();
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (list != null && list.size() > 0) {
                    int titleId = 0;
                    switch (pageType) {
                        case PAGE_TYPE_BTR://????????? ??????
                        case PAGE_TYPE_RENT://???????????? ??????
                            titleId = R.string.sm01_maintenance_0;
                            break;
                        case PAGE_TYPE_SERVICE://????????? ????????????
                        case PAGE_TYPE_REPAIR://???????????????
                        default:
                            titleId = R.string.sm01_maintenance_1;
                            break;
                    }

                    startActivitySingleTop(new Intent(this, BtrBluehandsListActivity.class).putExtra(KeyNames.KEY_NAME_MAP_SEARCH_TITLE_ID, titleId).putExtra(KeyNames.KEY_NAME_BTR, new Gson().toJson(list)), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                }
                break;
            case R.id.btn_my_position:

                checkEnableGPS(() -> {

                }, () -> {
                    pubViewModel.setFilterInfo("", "", "");
//                    List<BtrVO> btrVOList = new ArrayList<>();
//
//                    try {
//                        switch (pageType) {
//                            case PAGE_TYPE_BTR:
//                            case PAGE_TYPE_RENT:
//                                btrVOList = btrViewModel.getRES_BTR_1008().getValue().data.getAsnList();
//                                break;
//                            case PAGE_TYPE_REPAIR:
//                            case PAGE_TYPE_SERVICE:
//                            default:
//                                btrVOList = reqViewModel.getRES_REQ_1002().getValue().data.getAsnList();
//                                break;
//                        }
//                    }catch (Exception e){
//
//                    }
//
//                    if(btrVOList!=null&&btrVOList.size()>0){
//                        setPosition(btrVOList, btrVOList.get(0),false);
//                    }else{
//                        SnackBarUtil.show(this, "??????????????? ????????? ???????????? ????????????.");
//                    }
                });

                break;
            case R.id.btn_research:
                //??? ?????? ?????????
                isReSearchCharge=true;
                mapViewModel.reqPlayMapGeoItem(new ReverseGeocodingReqVO(ui.pmvMapView.getMapCenterLatitude(),ui.pmvMapView.getMapCenterLongitude(),0));
                break;

//            case R.id.btn_my_position_2: {
//                // EV????????? ?????? > ??? ?????? ?????? ??????.
//                reqMyLocation();
//                break;
//            }
//            case R.id.btn_car_position: {
//                // EV ????????? ?????? > ??? ?????? ?????? ?????? ??????.
//                Log.d("FID", "test :: onClickCommon :: btn_car_position");
//                // TODO ?????? ????????? ?????? ??????.
//                break;
//            }
            case R.id.iv_btn_filter: {
                // EV ????????? ?????? > ?????? - ?????? ??????.
                try {
                    // ?????? ???????????? ???????????? ?????? ??????.
                    ArrayList<ChargeSearchCategoryVO> filterList = new ArrayList<>();
                    for (ChargeSearchCategoryVO item : searchCategoryList) {
                        filterList.add((ChargeSearchCategoryVO) item.clone());
                    }
                    showDialogFilter(filterList);
                } catch (CloneNotSupportedException e) {

                }
                break;
            }
            case R.id.tv_btn_route_detail: {
                // ?????? ?????? ??????. ???????????? ???????????? ??? ??????
                // ????????? ?????? ????????? - ?????? ?????? ?????? ?????? > ???????????? ???????????? ??? ??????
                if(pageType == PAGE_TYPE_EVCHARGE) {
                    ChargeEptInfoVO item = (ChargeEptInfoVO) v.getTag(R.id.item);
                    if(item!=null&&!TextUtils.isEmpty(item.getLat())&&!TextUtils.isEmpty(item.getLot())){
                        PackageUtil.runAppWithScheme(this, PackageUtil.PACKAGE_CONNECTED_CAR, VariableType.getGCSScheme(item.getLat(), item.getLot()));
                    }
                } else if(pageType == PAGE_TYPE_EVCHARGE_STC) {
                    ReserveVo item = (ReserveVo) v.getTag(R.id.item);
                    if(item!=null&&!TextUtils.isEmpty(item.getLat())&&!TextUtils.isEmpty(item.getLot())){
                        PackageUtil.runAppWithScheme(this, PackageUtil.PACKAGE_CONNECTED_CAR, VariableType.getGCSScheme(item.getLat(), item.getLot()));
                    }
                }

                break;
            }
            case R.id.btn_search:
                // pageType ?????? ?????? ?????? ??????.
                switch (pageType) {
                    case PAGE_TYPE_EVCHARGE_STC:
                    case PAGE_TYPE_EVCHARGE: {
                        // ????????? ??????
                        // ?????? ??????
                        Bundle bundle = new Bundle();
                        bundle.putInt(KeyNames.KEY_NAME_MAP_SEARCH_TITLE_ID, R.string.sm_evss01_34);
                        bundle.putInt(KeyNames.KEY_NAME_MAP_SEARCH_MSG_ID, R.string.sm_evss01_35);
                        SearchAddressHMNFragment fragment = new SearchAddressHMNFragment();
                        fragment.setAddressSelectListener(this);
                        showFragment(fragment, bundle);
                        break;
                    }
                    case PAGE_TYPE_BTR:
                    case PAGE_TYPE_RENT:
                    case PAGE_TYPE_SERVICE:
                    case PAGE_TYPE_REPAIR:
                    default: {
                        showFragment(new BluehandsFilterFragment());
                        break;
                    }
                }
                break;
        }

    }

    @Override
    public void onAddressSelected(AddressVO selectedAddr) {
        lgnViewModel.setPosition(selectedAddr.getCenterLat(), selectedAddr.getCenterLon());
        reqSearchChargeStation(selectedAddr.getCenterLat(), selectedAddr.getCenterLon(), searchCategoryList);
    }

    /**
     * drawMarkerItem ????????? ????????? ?????????.
     */
    public void drawMarkerItem(BtrVO btrVO, int iconId) {
        PlayMapMarker markerItem = new PlayMapMarker();
//        PlayMapPoint point = mapView.getMapCenterPoint();
        PlayMapPoint point = new PlayMapPoint(Double.parseDouble(btrVO.getMapYcooNm()), Double.parseDouble(btrVO.getMapXcooNm()));
        markerItem.setMapPoint(point);
//        markerItem.setCalloutTitle("??????");
//        markerItem.setCalloutSubTitle("??????");
        markerItem.setCanShowCallout(false);
        markerItem.setAutoCalloutVisible(false);
        markerItem.setIcon(((BitmapDrawable) getResources().getDrawable(iconId, null)).getBitmap());


        String strId = btrVO.getAsnCd();
        ui.pmvMapView.addMarkerItem(strId, markerItem);
    }


    public void modifyMarkerItem(BtrVO btrVO, int iconId) {
        PlayMapMarker markerItem = new PlayMapMarker();
//        PlayMapPoint point = mapView.getMapCenterPoint();
        PlayMapPoint point = new PlayMapPoint(Double.parseDouble(btrVO.getMapYcooNm()), Double.parseDouble(btrVO.getMapXcooNm()));
        markerItem.setMapPoint(point);
//        markerItem.setCalloutTitle("??????");
//        markerItem.setCalloutSubTitle("??????");
        markerItem.setCanShowCallout(false);
        markerItem.setAutoCalloutVisible(false);
        markerItem.setIcon(((BitmapDrawable) getResources().getDrawable(iconId, null)).getBitmap());


        String strId = btrVO.getAsnCd();
        ui.pmvMapView.removeMarkerItem(strId);
        ui.pmvMapView.addMarkerItem(strId, markerItem);
    }

    public void modifyMarkerItemCharge(ChargeEptInfoVO chargeEptInfoVO, int iconId) {
        PlayMapMarker markerItem = new PlayMapMarker();
//        PlayMapPoint point = mapView.getMapCenterPoint();
        PlayMapPoint point = new PlayMapPoint(Double.parseDouble(chargeEptInfoVO.getLat()), Double.parseDouble(chargeEptInfoVO.getLot()));
        markerItem.setMapPoint(point);
//        markerItem.setCalloutTitle("??????");
//        markerItem.setCalloutSubTitle("??????");
        markerItem.setCanShowCallout(false);
        markerItem.setAutoCalloutVisible(false);
        markerItem.setIcon(((BitmapDrawable) getResources().getDrawable(iconId, null)).getBitmap());


        String strId = chargeEptInfoVO.getCsid();
        ui.pmvMapView.removeMarkerItem(strId);
        ui.pmvMapView.addMarkerItem(strId, markerItem);
    }

    public void modifyMarkerItemCharge(ReserveVo reserveVo, int iconId) {
        PlayMapMarker markerItem = new PlayMapMarker();
//        PlayMapPoint point = mapView.getMapCenterPoint();
        PlayMapPoint point = new PlayMapPoint(Double.parseDouble(reserveVo.getLat()), Double.parseDouble(reserveVo.getLot()));
        markerItem.setMapPoint(point);
//        markerItem.setCalloutTitle("??????");
//        markerItem.setCalloutSubTitle("??????");
        markerItem.setCanShowCallout(false);
        markerItem.setAutoCalloutVisible(false);
        markerItem.setIcon(((BitmapDrawable) getResources().getDrawable(iconId, null)).getBitmap());


        String strId = reserveVo.getSid();
        ui.pmvMapView.removeMarkerItem(strId);
        ui.pmvMapView.addMarkerItem(strId, markerItem);
    }

    /**
     * ????????? ???????????? ??????.
     *
     * @param markerId ????????? ????????? ????????? ID
     * @param x        ????????? ?????? ??????
     * @param y        ????????? ?????? ??????
     * @param iconId   ????????? ?????? ????????? Resource ID
     */
    private void drawMarkerItem(String markerId, double x, double y, int iconId) {
        PlayMapMarker markerItem = new PlayMapMarker();
        PlayMapPoint point = new PlayMapPoint(x, y);
        markerItem.setMapPoint(point);
        markerItem.setCanShowCallout(false);
        markerItem.setAutoCalloutVisible(false);
        markerItem.setIcon(((BitmapDrawable) getResources().getDrawable(iconId, null)).getBitmap());
        ui.pmvMapView.addMarkerItem(markerId, markerItem);
    }

//    /**
//     * ?????? ????????? ???????????? ??????.
//     *
//     * @param markerId ????????? ?????? ID
//     * @param x        ????????? ??????
//     * @param y        ????????? ??????
//     * @param iconId   ????????? ?????? ????????? Resource ID
//     */
//    private void modifyMarkerItem(String markerId, double x, double y, int iconId) {
//        ui.pmvMapView.removeMarkerItem(markerId);
//        drawMarkerItem(markerId, x, y, iconId);
//    }


    private void reqMyLocation() {
        showProgressDialog(true);
        findMyLocation(location -> {
            showProgressDialog(false);
            if (location == null) {
                exitPage("?????? ????????? ????????? ??? ????????????. GPS ????????? ?????? ??? ?????? ????????? ?????????.", ResultCodes.REQ_CODE_EMPTY_INTENT.getCode());
                return;
            }
//            location.setLatitude(37.5133402);
//            location.setLongitude(126.9333508);
            runOnUiThread(() -> {
                switch (pageType) {
                    case PAGE_TYPE_EVCHARGE_STC:
                    case PAGE_TYPE_EVCHARGE: {
                        // ????????? ??????
                        ui.btnMyPosition2.setChecked(true);
                        lgnViewModel.setPosition(location.getLatitude(), location.getLongitude());
                        break;
                    }
                    case PAGE_TYPE_BTR:
                    case PAGE_TYPE_RENT:
                    case PAGE_TYPE_SERVICE:
                    case PAGE_TYPE_REPAIR:
                    default: {
                        if (btrVO == null) {
                            //????????? ????????? ????????? ??? ????????? ??????????????? ??????
                            lgnViewModel.setPosition(location.getLatitude(), location.getLongitude());
                        } else {
                            //????????? ????????? ????????? ????????? ???????????? ????????? ??????????????? ??????
                            lgnViewModel.setPosition(Double.parseDouble(btrVO.getMapYcooNm()), Double.parseDouble(btrVO.getMapXcooNm()));
                        }
                        break;
                    }
                }
                //???????????? ?????? ??????
                lgnViewModel.setMyPosition(location.getLatitude(), location.getLongitude());
            });

        }, 5000, GpsRetType.GPS_RETURN_FIRST, false);
    }

    private void reqSearchChargeStation(double lat, double lot, List<ChargeSearchCategoryVO> filterList) {
        String reservYn = null;
        String chgCd = null;
        List<String> chgSpeed = new ArrayList<>();
        List<String> payType = new ArrayList<>();
        if (filterList != null && filterList.size() > 0) {
            for (ChargeSearchCategoryVO item : filterList) {
                if (item.isSelected()) {
                    switch (item.getTitleResId()) {
                        case R.string.sm_evss01_15: {
                            // ?????? ?????? ????????? ?????? ??????.
                            reservYn = "Y";
                            break;
                        }
                        case R.string.sm_evss01_16: {
                            // ????????? ?????? ??????.
                            if (item.getSelectedItem().size() > 0) {
                                chgCd = item.getSelectedItem().get(0).getCode();
                            }
                            break;
                        }
                        case R.string.sm_evss01_21: {
                            // ?????? ?????? ??????.
//                            if (item.getSelectedItem().size() > 0) {
//                                chgSpeed = item.getSelectedItem().stream().map(it -> "\"" + it.getCode() + "\"").collect(Collectors.joining(",", "[", "]"));
//                            }

                            if (item.getSelectedItem().size() > 0) {
                                chgSpeed.addAll(item.getSelectedItem().stream().map(it -> it.getCode()).collect(Collectors.toList()));
                            }

                            break;
                        }
                        case R.string.sm_evss01_25: {
                            // ?????? ?????? ??????.
                            if (item.getSelectedItem().size() > 0) {
//                                payType = item.getSelectedItem().stream().map(it -> "\"" + it.getCode() + "\"").collect(Collectors.joining(",", "[", "]"));
                                payType.addAll(item.getSelectedItem().stream().map(it -> it.getCode()).collect(Collectors.toList()));
                            }
                            break;
                        }
                    }
                }
            }
        }

        if(pageType == PAGE_TYPE_EVCHARGE) {
            eptViewModel.reqEPT1001(new EPT_1001.Request(
                    APPIAInfo.SM_EVSS02.getId(),
                    mainVehicle.getVin(),
                    String.valueOf(lat),
                    String.valueOf(lot),
                    reservYn,
                    chgCd,
                    chgSpeed.size() < 1 ? null : chgSpeed,
                    payType.size() < 1 ? null : payType
            ));
        } else if(pageType == PAGE_TYPE_EVCHARGE_STC){
            stcViewModel.reqSTC1001(new STC_1001.Request(
                    APPIAInfo.SM_EVSS02.getId(),
                    mainVehicle.getVin(),
                    lat,
                    lot,
                    reservYn,
                    chgSpeed.contains(ChargeSearchCategorytype.SUPER_SPEED.getCode()) ? "Y" : null,
                    chgSpeed.contains(ChargeSearchCategorytype.HIGH_SPEED.getCode()) ? "Y" : null,
                    chgSpeed.contains(ChargeSearchCategorytype.SLOW_SPEED.getCode()) ? "Y" : null,
                    "1",
                    "50"
            ));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ResultCodes.REQ_CODE_BTR.getCode() && data != null) {
            btrVO = (BtrVO) data.getSerializableExtra(KeyNames.KEY_NAME_BTR);
            List<BtrVO> list = null;

            try {
                switch (pageType) {
                    case PAGE_TYPE_BTR:
                    case PAGE_TYPE_RENT:
                        list = btrViewModel.getRES_BTR_1008().getValue().data.getAsnList();
                        break;
                    case PAGE_TYPE_EVCHARGE_STC:
                    case PAGE_TYPE_EVCHARGE: {
                        // TODO ????????? ??????
                        break;
                    }
                    case PAGE_TYPE_REPAIR:
                    case PAGE_TYPE_SERVICE:
                    default:
                        list = reqViewModel.getRES_REQ_1002().getValue().data.getAsnList();
                        break;
                }
            } catch (Exception e) {

            }


            setPosition(list, btrVO, false);
        } else if (resultCode == ResultCodes.REQ_CODE_ADDR_FILTER.getCode()) {
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
        } else if (requestCode == RequestCodes.REQ_CODE_GPS.getCode()) {
            if (resultCode == RESULT_OK)
                reqMyLocation();
            else {
                //????????????????????????
                lgnViewModel.setPosition(37.463936, 127.042953);
                lgnViewModel.setMyPosition(37.463936, 127.042953);
            }
        } else if (resultCode == ResultCodes.REQ_CODE_CHARGE_RESERVATION_FINISH.getCode()) {
            // ????????? ?????? ??????
            exitPage(data, resultCode);
        }


    }

    private void setPosition(List<BtrVO> list, BtrVO btrVO, boolean isSelect) {
        BtrVO beforeBtrInfo = null;

        if (!isSelect)
            ui.pmvMapView.removeAllMarkerItem();
        else {
            try {
                beforeBtrInfo = ((BtrVO) this.btrVO.clone());
            } catch (Exception e) {

            }
        }

        if (list == null || btrVO == null) {
            if(bottomSelectBinding!=null){
                bottomSelectBinding.lWhole.setVisibility(View.GONE);
            }
            return;
        }
        String btlrNm = "";
        String celphNo = "";
        String cnsltBdgYn = "";
        String custMgmtNo = "";
        String vin = "";
        if (this.btrVO != null) {
            btlrNm = StringUtil.isValidString(this.btrVO.getBtlrNm());
            celphNo = StringUtil.isValidString(this.btrVO.getCelphNo());
            cnsltBdgYn = StringUtil.isValidString(this.btrVO.getCnsltBdgYn());
            custMgmtNo = StringUtil.isValidString(this.btrVO.getCustMgmtNo());
            vin = StringUtil.isValidString(this.btrVO.getVin());
        }
        this.btrVO = btrVO;
        if (!TextUtils.isEmpty(btlrNm)) this.btrVO.setBtlrNm(btlrNm);
        if (!TextUtils.isEmpty(celphNo)) this.btrVO.setCelphNo(celphNo);
        if (!TextUtils.isEmpty(cnsltBdgYn)) this.btrVO.setCnsltBdgYn(cnsltBdgYn);
        if (!TextUtils.isEmpty(custMgmtNo)) this.btrVO.setCustMgmtNo(custMgmtNo);
        if (!TextUtils.isEmpty(vin)) this.btrVO.setVin(vin);


        if (bottomSelectBinding == null) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) findViewById(R.id.vs_map_overlay_bottom_box).getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            findViewById(R.id.vs_map_overlay_bottom_box).setLayoutParams(params);
            setViewStub(R.id.vs_map_overlay_bottom_box, R.layout.layout_map_overlay_ui_bottom_select_new, new ViewStub.OnInflateListener() {
                @Override
                public void onInflate(ViewStub viewStub, View inflated) {
                    bottomSelectBinding = DataBindingUtil.bind(inflated);
                    bottomSelectBinding.setActivity(ServiceNetworkActivity.this);
                    switch (pageType) {
                        case PAGE_TYPE_BTR:
                        case PAGE_TYPE_RENT:
                            bottomSelectBinding.btnLeftWhite.setVisibility(View.GONE);
                            bottomSelectBinding.btnRightBlack.setVisibility(View.VISIBLE);
                            bottomSelectBinding.btnRightBlack.setText(R.string.bt06_24);
                            break;
                        case PAGE_TYPE_REPAIR:
                            if (!StringUtil.isValidString(btrVO.getAcps1Cd()).equalsIgnoreCase("2")) {//????????? ????????? ?????? ??????
                                bottomSelectBinding.btnLeftWhite.setVisibility(View.VISIBLE);
                                bottomSelectBinding.btnLeftWhite.setText(R.string.bt06_25);
                            } else {
                                bottomSelectBinding.btnLeftWhite.setVisibility(View.GONE);
                            }
                            bottomSelectBinding.btnRightBlack.setVisibility(View.VISIBLE);
                            bottomSelectBinding.btnRightBlack.setText(R.string.bt06_26);
                            break;
                        case PAGE_TYPE_SERVICE:
                        default:
                            if (!StringUtil.isValidString(btrVO.getAcps1Cd()).equalsIgnoreCase("2")) {//????????? ????????? ?????? ??????
                                bottomSelectBinding.btnLeftWhite.setVisibility(View.VISIBLE);
                                bottomSelectBinding.btnLeftWhite.setText(R.string.bt06_25);
                            } else {
                                bottomSelectBinding.btnLeftWhite.setVisibility(View.GONE);
                            }

                            String custGbCd = lgnViewModel.getDbUserRepo().getUserVO().getCustGbCd();
                            if (!TextUtils.isEmpty(custGbCd) && custGbCd.equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_OV)) {//?????????????????????????????? ?????? ?????? ??????????????? ???????????? ??????
                                bottomSelectBinding.btnRightBlack.setVisibility(View.VISIBLE);
                                bottomSelectBinding.btnRightBlack.setText(R.string.bt06_26);
                            } else {
                                bottomSelectBinding.btnRightBlack.setVisibility(View.GONE);
                            }
                            break;
                    }
                    bottomSelectBinding.setData(btrVO);
                    setAuthView(btrVO);
                }
            });
        } else {
            bottomSelectBinding.lWhole.setVisibility(View.VISIBLE);
            switch (pageType) {
                case PAGE_TYPE_REPAIR:
                case PAGE_TYPE_SERVICE:
                    if (!StringUtil.isValidString(btrVO.getAcps1Cd()).equalsIgnoreCase("2")) {//????????? ????????? ?????? ??????
                        bottomSelectBinding.btnLeftWhite.setVisibility(View.VISIBLE);
                        bottomSelectBinding.btnLeftWhite.setText(R.string.bt06_25);
                    } else {
                        bottomSelectBinding.btnLeftWhite.setVisibility(View.GONE);
                    }
                    break;
                default:
                    bottomSelectBinding.btnLeftWhite.setVisibility(View.GONE);
                    break;
            }
            bottomSelectBinding.setData(btrVO);
            setAuthView(btrVO);
        }

        if (!isSelect) {
            for (int i = 0; i < list.size(); i++) {
                if (StringUtil.isValidString(btrVO.getAsnCd()).equalsIgnoreCase(list.get(i).getAsnCd())) {
                    drawMarkerItem(list.get(i), R.drawable.ic_pin_carcenter);
                } else {
                    drawMarkerItem(list.get(i), R.drawable.ic_pin);
                }
            }
        } else {
            if (beforeBtrInfo != null) modifyMarkerItem(beforeBtrInfo, R.drawable.ic_pin);
            if (btrVO != null) modifyMarkerItem(btrVO, R.drawable.ic_pin_carcenter);
        }
        ui.pmvMapView.setMapCenterPoint(new PlayMapPoint(Double.parseDouble(btrVO.getMapYcooNm()), Double.parseDouble(btrVO.getMapXcooNm())), 500);
    }

    private void setPositionChargeStation(List<ChargeEptInfoVO> list, ChargeEptInfoVO selectItemVo, boolean isSelect) {

        ChargeEptInfoVO beforeChargeEptInfoVO = null;

        if (!isSelect)
            ui.pmvMapView.removeAllMarkerItem();
        else {
            try {
                beforeChargeEptInfoVO = ((ChargeEptInfoVO) this.selectedChargeEptInfo.clone());
            } catch (Exception e) {

            }
        }

        if (list == null || list.size() == 0) {
            if(evBottomSelectBinding!=null){
                evBottomSelectBinding.lWhole.setVisibility(View.GONE);
            }
            // ????????? ????????? ???????????? ??????.
            return;
        }
        selectedChargeEptInfo = selectItemVo;


        if (evBottomSelectBinding == null) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) findViewById(R.id.vs_map_overlay_bottom_box).getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            findViewById(R.id.vs_map_overlay_bottom_box).setLayoutParams(params);
            setViewStub(R.id.vs_map_overlay_bottom_box, R.layout.layout_map_overlay_ui_bottom_ev_charge, (viewStub, inflated) -> {
                evBottomSelectBinding = DataBindingUtil.bind(inflated);
                evBottomSelectBinding.setActivity(ServiceNetworkActivity.this);
                setViewEVBottom(selectItemVo);
            });
        } else {
            setViewEVBottom(selectItemVo);
        }

        try {
            if(!isSelect) {
                for (ChargeEptInfoVO item : list) {

                    SpidCodes spidCodes = SpidCodes.findCode(StringUtil.isValidString(item.getSpid()));

                    drawMarkerItem(item.getCsid(),
                            Double.parseDouble(item.getLat()),
                            Double.parseDouble(item.getLot()),
                            StringUtil.isValidString(item.getCsid()).equalsIgnoreCase(StringUtil.isValidString(selectItemVo.getCsid())) ? spidCodes.getPinLarge() : spidCodes.getPinSmall());
                }
            }else{
                if (beforeChargeEptInfoVO != null) modifyMarkerItemCharge(beforeChargeEptInfoVO,  SpidCodes.findCode(StringUtil.isValidString(beforeChargeEptInfoVO.getSpid())).getPinSmall());
                if (selectItemVo != null) modifyMarkerItemCharge(selectItemVo, SpidCodes.findCode(StringUtil.isValidString(selectItemVo.getSpid())).getPinLarge());
            }
            ui.pmvMapView.setMapCenterPoint(new PlayMapPoint(Double.parseDouble(selectItemVo.getLat()), Double.parseDouble(selectItemVo.getLot())), 500);
        } catch (Exception e) {
            // ?????? ?????? ??????.
            e.printStackTrace();
        }
    }

    private void setPositionChargeStationStc(List<ReserveVo> list, ReserveVo selectItemVo, boolean isSelect) {

        ReserveVo beforeChargeEptInfoVO = null;

        if (!isSelect)
            ui.pmvMapView.removeAllMarkerItem();
        else {
            try {
                beforeChargeEptInfoVO = ((ReserveVo) this.selectChargeStcInfo.clone());
            } catch (Exception e) {

            }
        }

        if (list == null || list.size() == 0) {
            if(evBottomSelectBinding!=null){
                evBottomSelectBinding.lWhole.setVisibility(View.GONE);
            }
            // ????????? ????????? ???????????? ??????.
            return;
        }
        selectChargeStcInfo = selectItemVo;


        if (evBottomSelectBinding == null) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) findViewById(R.id.vs_map_overlay_bottom_box).getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            findViewById(R.id.vs_map_overlay_bottom_box).setLayoutParams(params);
            setViewStub(R.id.vs_map_overlay_bottom_box, R.layout.layout_map_overlay_ui_bottom_ev_charge, (viewStub, inflated) -> {
                evBottomSelectBinding = DataBindingUtil.bind(inflated);
                evBottomSelectBinding.setActivity(ServiceNetworkActivity.this);
                setViewEVBottom(selectItemVo);
            });
        } else {
            setViewEVBottom(selectItemVo);
        }

        try {
            if(!isSelect) {
                for (ReserveVo item : list) {
                    drawMarkerItem(item.getSid(),
                            Double.parseDouble(item.getLat()),
                            Double.parseDouble(item.getLot()),
                            StringUtil.isValidString(item.getSid()).equalsIgnoreCase(StringUtil.isValidString(selectItemVo.getSid())) ? SpidCodes.ST.getPinLarge() : SpidCodes.ST.getPinSmall());
                }
            }else{
                if (beforeChargeEptInfoVO != null) modifyMarkerItemCharge(beforeChargeEptInfoVO, SpidCodes.ST.getPinSmall());
                if (selectItemVo != null) modifyMarkerItemCharge(selectItemVo, SpidCodes.ST.getPinLarge());
            }
            ui.pmvMapView.setMapCenterPoint(new PlayMapPoint(Double.parseDouble(selectItemVo.getLat()), Double.parseDouble(selectItemVo.getLot())), 500);
        } catch (Exception e) {
            // ?????? ?????? ??????.
            e.printStackTrace();
        }
    }

    private void setViewEVBottom(ChargeEptInfoVO selectItemVo){
        if(evBottomSelectBinding!=null){
            evBottomSelectBinding.lWhole.setVisibility(View.VISIBLE);
            evBottomSelectBinding.tvBtnRouteDetail.setTag(R.id.item, selectItemVo);
            evBottomSelectBinding.tvChargeStationName.setText(selectItemVo.getCsnm());
            evBottomSelectBinding.tvMapSelectAddress.setText(selectItemVo.getDaddr());
            evBottomSelectBinding.tvDist.setText(selectItemVo.getDist() + "km");
            evBottomSelectBinding.tvTime.setText(selectItemVo.getUseTime());
            evBottomSelectBinding.tvChargeUnit.setText(Html.fromHtml(VariableType.getChargeStatus(this, new Gson().toJson(selectItemVo)), Html.FROM_HTML_MODE_COMPACT));
            if (selectItemVo.isReserve()) {
                // ?????? ????????? ??????.
                evBottomSelectBinding.tvBookStatus.setVisibility(View.VISIBLE);
                evBottomSelectBinding.tvBookStatus.setText(R.string.sm_evss01_30);
            } else {
                // ?????? ???????????? ??????.
                evBottomSelectBinding.tvBookStatus.setVisibility(View.GONE);
            }
        }
    }

    private void setViewEVBottom(ReserveVo selectItemVo){
        if(evBottomSelectBinding!=null){
            evBottomSelectBinding.lWhole.setVisibility(View.VISIBLE);
            evBottomSelectBinding.tvBtnRouteDetail.setTag(R.id.item, selectItemVo);
            evBottomSelectBinding.tvChargeStationName.setText(selectItemVo.getChgName());
            evBottomSelectBinding.tvMapSelectAddress.setText(selectItemVo.getDaddr());
            evBottomSelectBinding.tvDist.setText(selectItemVo.getDist() + "km");
            evBottomSelectBinding.tvTime.setText(selectItemVo.getUseStartTime() + "-" + selectItemVo.getUseEndTime());
            evBottomSelectBinding.tvChargeUnit.setText(Html.fromHtml(VariableType.getChargeStatus(this, new Gson().toJson(selectItemVo)), Html.FROM_HTML_MODE_COMPACT));
            if (selectItemVo.isReserve()) {
                // ?????? ????????? ??????.
                evBottomSelectBinding.tvBookStatus.setVisibility(View.VISIBLE);
                evBottomSelectBinding.tvBookStatus.setText(R.string.sm_evss01_30);
            } else {
                // ?????? ???????????? ??????.
                evBottomSelectBinding.tvBookStatus.setVisibility(View.GONE);
            }
        }
    }

    private void setAuthView(BtrVO btrVO) {

        bottomSelectBinding.tvAcps1CdC.setVisibility(!TextUtils.isEmpty(btrVO.getAcps1Cd()) && btrVO.getAcps1Cd().contains("C") ? View.VISIBLE : View.GONE);
        bottomSelectBinding.tvAcps1CdD.setVisibility(!TextUtils.isEmpty(btrVO.getAcps1Cd()) && btrVO.getAcps1Cd().contains("D") ? View.VISIBLE : View.GONE);

        if (btrVO == null || TextUtils.isEmpty(btrVO.getAcps1Cd()))
            return;

        TextView[] textViews = {bottomSelectBinding.tvAuth1, bottomSelectBinding.tvAuth2, bottomSelectBinding.tvAuth3, bottomSelectBinding.tvAuth4};
        Integer[] authNM;

        if (btrVO.getAcps1Cd().equalsIgnoreCase("2")) {
            //????????? ??????????????? ??????
            authNM = new Integer[]{
                    !TextUtils.isEmpty(btrVO.getGenLngYn()) && btrVO.getGenLngYn().equalsIgnoreCase(VariableType.COMMON_MEANS_YES) ? R.string.bt06_27 : 0
                    , !TextUtils.isEmpty(btrVO.getFmRronYn()) && btrVO.getFmRronYn().equalsIgnoreCase(VariableType.COMMON_MEANS_YES) ? R.string.bt06_28 : 0
                    , !TextUtils.isEmpty(btrVO.getHealZnYn()) && btrVO.getHealZnYn().equalsIgnoreCase(VariableType.COMMON_MEANS_YES) ? R.string.bt06_29 : 0
                    , !TextUtils.isEmpty(btrVO.getCsmrPcYn()) && btrVO.getCsmrPcYn().equalsIgnoreCase(VariableType.COMMON_MEANS_YES) ? R.string.bt06_30 : 0
            };

        } else {
            //?????? ???????????? ??????
            authNM = new Integer[]{
                    !TextUtils.isEmpty(btrVO.getEvYn()) && btrVO.getEvYn().equalsIgnoreCase(VariableType.COMMON_MEANS_YES) ? R.string.bt06_23_2 : 0
                    , !TextUtils.isEmpty(btrVO.getPntgXclYn()) && btrVO.getPntgXclYn().equalsIgnoreCase(VariableType.COMMON_MEANS_YES) ? R.string.bt06_17 : 0
                    , !TextUtils.isEmpty(btrVO.getPrimTechYn()) && btrVO.getPrimTechYn().equalsIgnoreCase(VariableType.COMMON_MEANS_YES) ? R.string.bt06_18 : 0
                    , !TextUtils.isEmpty(btrVO.getPrimCsYn()) && btrVO.getPrimCsYn().equalsIgnoreCase(VariableType.COMMON_MEANS_YES) ? R.string.bt06_23 : 0

            };
        }
        //?????? ??? ?????????
        for (TextView textView : textViews) {
            textView.setVisibility(View.GONE);
            textView.setText("");
        }
        //?????? ??? SET
        for (Integer auth : authNM) {
            if (auth != 0) {
                for (TextView textView : textViews) {
                    if (TextUtils.isEmpty(textView.getText().toString())) {
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(getString(auth));
                        textView.setTag(R.id.item, auth);
                        if (textView == bottomSelectBinding.tvAuth1) {
                            bottomSelectBinding.tvAuth2.setVisibility(View.INVISIBLE);
                        } else if (textView == bottomSelectBinding.tvAuth3) {
                            bottomSelectBinding.tvAuth4.setVisibility(View.INVISIBLE);
                        }
                        break;
                    }
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        List<SubFragment> fragments = getFragments();
        if (fragments != null && fragments.size() > 0) {
            hideFragment(fragments.get(0));
        } else {
            if(pageType==PAGE_TYPE_EVCHARGE||pageType==PAGE_TYPE_EVCHARGE_STC){
                evReturnData.putExtra(KeyNames.KEY_NAME_ADDR, initAddressVO);
                setResult(ResultCodes.REQ_CODE_CHARGE_FILTER_APPLY.getCode(), evReturnData);
            }
            super.onBackPressed();
        }
    }

    @Override
    public void onBackButton() {
        onBackPressed();
    }
}
