package com.genesis.apps.ui.main.home;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.paris.Paris;
import com.bumptech.glide.Glide;
import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.developers.Agreements;
import com.genesis.apps.comm.model.api.developers.Dte;
import com.genesis.apps.comm.model.api.developers.EvStatus;
import com.genesis.apps.comm.model.api.developers.Odometer;
import com.genesis.apps.comm.model.api.developers.Odometers;
import com.genesis.apps.comm.model.api.gra.IST_1001;
import com.genesis.apps.comm.model.api.gra.LGN_0003;
import com.genesis.apps.comm.model.api.gra.LGN_0005;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.constants.WeatherCodes;
import com.genesis.apps.comm.model.vo.DownMenuVO;
import com.genesis.apps.comm.model.vo.MessageVO;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.model.vo.developers.OdometerVO;
import com.genesis.apps.comm.net.ga.LoginInfoDTO;
import com.genesis.apps.comm.util.DateUtil;
import com.genesis.apps.comm.util.DeviceUtil;
import com.genesis.apps.comm.util.RecordUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.comm.util.VibratorUtil;
import com.genesis.apps.comm.viewmodel.CMNViewModel;
import com.genesis.apps.comm.viewmodel.DevelopersViewModel;
import com.genesis.apps.comm.viewmodel.ISTViewModel;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.databinding.FragmentHome1Binding;
import com.genesis.apps.room.ResultCallback;
import com.genesis.apps.ui.common.activity.GAWebActivity;
import com.genesis.apps.ui.common.activity.GpsBaseActivity;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.dialog.middle.MiddleDialog;
import com.genesis.apps.ui.common.fragment.SubFragment;
import com.genesis.apps.ui.common.view.listener.OnSingleClickListener;
import com.genesis.apps.ui.main.MainActivity;
import com.genesis.apps.ui.main.home.view.HomeInsightHorizontalAdapter;
import com.genesis.apps.ui.myg.MyGHomeActivity;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import dagger.hilt.android.AndroidEntryPoint;

import static android.app.Activity.RESULT_OK;
import static com.airbnb.lottie.LottieDrawable.RESTART;
import static com.genesis.apps.comm.model.api.APPIAInfo.GM01_01;
import static com.genesis.apps.comm.model.api.APPIAInfo.GM_BTO1;
import static com.genesis.apps.comm.model.api.APPIAInfo.GM_BTO2;
import static com.genesis.apps.comm.model.api.APPIAInfo.GM_CARLST01;
import static com.genesis.apps.comm.model.constants.KeyNames.KEY_NAME_INTERNAL_LINK;
import static com.google.android.exoplayer2.Player.REPEAT_MODE_ALL;

@AndroidEntryPoint
public class FragmentHome1 extends SubFragment<FragmentHome1Binding> {

    @Inject
    public LoginInfoDTO loginInfoDTO;

    private SimpleExoPlayer player;
    private LGNViewModel lgnViewModel;
    private CMNViewModel cmnViewModel;
    private ISTViewModel istViewModel;
    private DevelopersViewModel developersViewModel;
    private HomeInsightHorizontalAdapter adapter = null;
    private RecordUtil recordUtil;
    private Timer timer = null;
    private boolean isRecord = false;

    private int preRawBackground = 0;
    private int rawBackground = 0;
    private int rawLottie = 0;
    private int dayCd = 1;
    private boolean batteryCharge;
    private float soc=-1;


    private String sigungu = "";
    private String t1h = "";



    private boolean isInit = true; //최초 로딩 완료의 기준은 LGN-0005. LGN-0005(날씨정보요청)에 의해서 기본적인 뷰 표시가 결정됨
    private WeatherCodes weatherCodes = WeatherCodes.SKY1;
    private PlaybackStateListener playbackStateListener;

    public enum SunTime {
        JANUARY(0, 739, 1729),
        FEBRUARY(1, 715, 1803),
        MARCH(2, 637, 1831),
        APRIL(3, 551, 1859),
        MAY(4, 515, 1927),
        JUNE(5, 502, 1948),
        JULY(6, 514, 1946),
        AUGUST(7, 539, 1918),
        SEPTEMBER(8, 606, 1834),
        OCTOBER(9, 632, 1748),
        NOVEMBER(10, 704, 1714),
        DECEMBER(11, 732, 1707);

        private int month;
        private int sunRise;
        private int sunSet;

        SunTime(int month, int sunRise, int sunSet) {
            this.month = month;
            this.sunRise = sunRise;
            this.sunSet = sunSet;
        }

        public static SunTime getSunTime(int month) {
            return Arrays.asList(SunTime.values()).stream().filter(data -> data.getMonth() == month).findAny().orElse(JUNE);
        }

        public int getMonth() {
            return month;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.setContentView(inflater, R.layout.fragment_home_1);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        initWeather();
        initView();
        recordUtil.regReceiver();
//        initPlayer();
//        setVideo();
    }

    private void initWeather() {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int HHmm = Integer.parseInt(DateUtil.getDate(calendar.getTime(), DateUtil.DATE_FORMAT_HHmm));
        SunTime sunTime = SunTime.getSunTime(calendar.get(Calendar.MONTH));
        dayCd = (HHmm < sunTime.sunRise || HHmm > sunTime.sunSet) ? VariableType.HOME_TIME_NIGHT : VariableType.HOME_TIME_DAY;
        rawBackground = WeatherCodes.getBackgroundResource(weatherCodes, dayCd);
    }

    private void initViewModel() {
        me.setLifecycleOwner(getViewLifecycleOwner());
        me.setFragment(this);
        lgnViewModel = new ViewModelProvider(getActivity()).get(LGNViewModel.class);
        cmnViewModel = new ViewModelProvider(getActivity()).get(CMNViewModel.class);
        istViewModel = new ViewModelProvider(getActivity()).get(ISTViewModel.class);
        developersViewModel = new ViewModelProvider(getActivity()).get(DevelopersViewModel.class);

        lgnViewModel.getRES_LGN_0003().observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case SUCCESS:
                    if (result.data != null) {
                        if (!TextUtils.isEmpty(result.data.getVirtRecptNo())) {
                            me.tvRepairStatus.setVisibility(View.VISIBLE);
                            Paris.style(me.tvRepairStatus).apply(R.style.MainHomeBadgeSOS);
                        } else {
                            me.tvRepairStatus.setVisibility(View.INVISIBLE);
                        }

//                        if (!TextUtils.isEmpty(result.data.getVirtRecptNo())) {
//                            me.tvRepairStatus.setVisibility(View.VISIBLE);
//                            Paris.style(me.tvRepairStatus).apply(R.style.MainHomeBadgeSOS);
//                        } else if (!TextUtils.isEmpty(result.data.getAsnStatsNm())) {
//                            me.tvRepairStatus.setVisibility(View.VISIBLE);
//                            Paris.style(me.tvRepairStatus).apply(R.style.MainHomeBadgeRpar);
//                            me.tvRepairStatus.setText(result.data.getAsnStatsNm());
//                        } else {
//                            me.tvRepairStatus.setVisibility(View.INVISIBLE);
//                        }
                    }
                default:
                    break;
            }
        });
        lgnViewModel.getRES_LGN_0005().observe(getViewLifecycleOwner(), result -> {

            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                default:
                    if (result.data != null&&StringUtil.isValidString(result.data.getRtCd()).equalsIgnoreCase("0000")) {
                        try {
                            dayCd = Integer.parseInt(StringUtil.isValidString(result.data.getDayCd()));
                            sigungu = StringUtil.isValidString(result.data.getSiGuGun());
                            t1h = StringUtil.isValidString(result.data.getT1h());
                            weatherCodes = WeatherCodes.decideCode(result.data.getLgt(), result.data.getPty(), result.data.getSky());
                        } catch (Exception e) {
                        }
                    }
                    //날씨 정보 요청 전문에서는 에러가 발생되어도 기본 값으로 표시
                    if (weatherCodes == null)
                        weatherCodes = WeatherCodes.SKY1;


                    try {
                        initViewBanner(false);
                        me.setActivity((MainActivity) getActivity());
                        me.setWeatherCode(weatherCodes);
                        me.setDayCd(dayCd);
                        rawBackground = WeatherCodes.getBackgroundResource(weatherCodes, dayCd);
                        rawLottie = WeatherCodes.getEffectResource(weatherCodes);

                        if (player != null && rawBackground == preRawBackground && player.getPlaybackState() == Player.STATE_READY) {
                            player.setPlayWhenReady(true);
                        } else {
                            initializePlayer();
                        }

                        if(((MainActivity) getActivity()).isFirstPage()) {
                            ((MainActivity) getActivity()).setTab(dayCd);
                            ((MainActivity) getActivity()).setGNB("", View.VISIBLE, false, dayCd == VariableType.HOME_TIME_DAY);
                            SubActivity.setStatusBarColor(getActivity(), dayCd == VariableType.HOME_TIME_DAY ? R.color.x_ffffff : R.color.x_000000);
                            resumeAndPauseLottie(true);
                            setViewCarImg();
                            setViewEvBattery(); //ev 충전 배터리를 낮밤에 따라 색상 변경하는 용도
                        }
                    } catch (Exception e) {

                    } finally {
                        istViewModel.reqIST1001(new IST_1001.Request(APPIAInfo.GM01.getId(), "HOME", "TOP"));
                    }

                    break;
            }
        });

        lgnViewModel.getPosition().observe(getViewLifecycleOwner(), doubles -> {
            lgnViewModel.reqLGN0005(new LGN_0005.Request(APPIAInfo.GM01.getId(), String.valueOf(doubles.get(1)), String.valueOf(doubles.get(0))));
        });

        //정보제공동의유무확인
        developersViewModel.getRES_CAR_AGREEMENTS().observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (result.data != null && result.data.getData() != null) {
                        try {
                            developersViewModel.updateCarConnectResult(result.data.getData().getResult() != 0, developersViewModel.getCarId(lgnViewModel.getMainVehicleSimplyFromDB().getVin()));
                            setViewDevelopers(false);
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                default:
                    setViewDevelopers(false);
//                    if(developersViewModel.needUpdateCarId()){
//                        ((MainActivity) getActivity()).showProgressDialog(true);
//                        developersViewModel.checkVehicleCarId(new ResultCallback() {
//                            @Override
//                            public void onSuccess(Object object) {
//                                ((MainActivity) getActivity()).showProgressDialog(false);
//                                setViewDevelopers();
//                            }
//
//                            @Override
//                            public void onError(Object e) {
//                                ((MainActivity) getActivity()).showProgressDialog(false);
//                                setViewDevelopers();
//                            }
//                        });
//                    }else{
//                        setViewDevelopers();
//                    }
                    break;
            }
        });

        //주행가능거리표기
        developersViewModel.getRES_DTE().observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (result.data != null) {
                        try {
                            me.tvDistancePossible.setText(StringUtil.getDigitGrouping((int) result.data.getValue()) + developersViewModel.getDistanceUnit((int) result.data.getUnit()));
                            break;
                        } catch (Exception e) {

                        }
                    }
                default:
//                    me.tvDistancePossible.setText("--km");


                    break;
            }
        });

        //총주행거리표기
        developersViewModel.getRES_ODOMETER().observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (result.data != null && result.data.getOdometers() != null && result.data.getOdometers().size() > 0) {
                        OdometerVO odometerVO = result.data.getOdometers().stream().max(Comparator.comparingInt(data -> Integer.parseInt(data.getDate()))).orElse(null);
                        if (odometerVO != null) {
                            me.tvDistanceTotal.setText(StringUtil.getDigitGrouping((int) odometerVO.getValue()) + developersViewModel.getDistanceUnit((int) odometerVO.getUnit()));
                            break;
                        }
                    }
                default:
                    try {
                        developersViewModel.needUpdateCarId(result.data.getErrCode(), new ResultCallback() {
                            @Override
                            public void onSuccess(Object object) {
                                setViewDevelopers(true);
                            }
                            @Override
                            public void onError(Object e) {
                                setViewDevelopers(true);
                            }
                        });
                    }catch (Exception e){

                    }
//                    me.tvDistanceTotal.setText("--km");
                    break;
            }
        });

        //일별 운행 거리
        developersViewModel.getRES_ODOMETERS().observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (result.data != null && result.data.getOdometers() != null && result.data.getOdometers().size() > 1) {
                        OdometerVO odometerMax = result.data.getOdometers().stream().max(Comparator.comparingInt(data -> Integer.parseInt(data.getDate()))).orElse(null);
                        OdometerVO odometerMin = result.data.getOdometers().stream().min(Comparator.comparingInt(data -> Integer.parseInt(data.getDate()))).orElse(null);

                        if (odometerMax != null && odometerMin != null) {
                            int distance = (int) (odometerMax.getValue() - odometerMin.getValue());
                            if (distance >= 0) {
                                me.tvDistanceRecently.setText(StringUtil.getDigitGrouping(distance) + developersViewModel.getDistanceUnit((int) odometerMax.getUnit()));
                                break;
                            }
                        }
                    }
                default:
//                    me.tvDistanceRecently.setText("--km");
                    break;
            }
        });

        developersViewModel.getRES_EV_STATUS().observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case LOADING:
                    setEnableBattery(true);
                    break;
                case SUCCESS:
                    if (result.data != null) {
                        batteryCharge = result.data.isBatteryCharge();
                        soc = result.data.getSoc();
                        setViewEvBattery();
                        break;
                    }
                default:
                    batteryCharge = false;
                    soc = -1;
                    setViewEvBattery();
                    break;
            }
        });

        istViewModel.getRES_IST_1001().observe(getViewLifecycleOwner(), result -> {

            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (result.data != null) {
                        if (result.data.getAdmMsgList() != null && result.data.getAdmMsgList().size() > 0) {
                            for (MessageVO messageVO : result.data.getAdmMsgList()) {
                                messageVO.setBanner(false);
                                messageVO.setDayCd(dayCd);
                            }
                            adapter.addRows(result.data.getAdmMsgList());
//                            adapter.setRealItemCnt(adapter.getRealItemCnt() + result.data.getAdmMsgList().size());
                        }

                        if (result.data.getBnrMsgList() != null && result.data.getBnrMsgList().size() > 0) {
                            for (MessageVO messageVO : result.data.getBnrMsgList()) {
                                messageVO.setBanner(true);
                                messageVO.setDayCd(dayCd);
                            }

                            adapter.addRows(result.data.getBnrMsgList());
//                            adapter.setRealItemCnt(adapter.getRealItemCnt() + result.data.getBnrMsgList().size());
                        }
                        adapter.notifyDataSetChanged();
                        setViewBanner();
                        break;
                    }
                default:
                    adapter.notifyDataSetChanged();
                    break;
            }

        });

    }

    private void setViewEvBattery() {
        String time = developersViewModel.getBatteryChargeTime();
        int batteryRes = WeatherCodes.getEvBatteryResource(batteryCharge ? 1 : 0, dayCd, soc);
        me.ivEvBattery.setImageResource(batteryRes);
        me.tvEvBattery.setText(soc < 0 ? "- %" : (int)soc + "%");
        me.tvEvBattery.setTextColor(getContext().getColor((!batteryCharge&&soc>-1&&soc<=30 ? R.color.x_ce2d2d :  WeatherCodes.getTextColorResource(dayCd))));
        me.tvEvBatteryTime.setText((batteryCharge&&!TextUtils.isEmpty(time)) ? (String.format(Locale.getDefault(), getString(R.string.gm01_ev_1), time)) : "");
        setEnableBattery(false);
    }

    private void initViewBanner(boolean isUpdate){
        try {
            MessageVO weather = cmnViewModel.getHomeWeatherInsight(weatherCodes, dayCd, sigungu, t1h);
            if (weather != null) {
                //2021-02-15 요건 변경
                //기존 txtMsg에는 서버로 부터 전달받은 날씨 별 메시지를 랜덤으로 출력하고 있었으나
                //사용자 로그인 상태에 따라 인사 메시지로 변경
                //해당 코드에서 변경하기 전까지는 날씨 랜덤 메시지를 저장 중
                adapter.clear();
                weather.setTxtMsg(getGreetingMsg());
                adapter.addRow(weather);
                if(isUpdate){
                    adapter.notifyDataSetChanged();
                    setViewBanner();
                }
            }
        }catch (Exception e){

        }
    }


    private void setViewBanner() {
        pauseTimer();
        if(adapter!=null&&adapter.getRealItemCnt() > 1){
            me.vpInsight.setUserInputEnabled(true);
            startTimer();
        }else{
            me.vpInsight.setUserInputEnabled(false);
        }
    }

//    private void applyAlphaAnimation(TextView v){
//        Animation animation = new AlphaAnimation(0, 1);
//        animation.setDuration(1000);
//        v.setAnimation(animation);
//    }

    private void setViewCarImg() {

        VehicleVO vehicleVO = null;
        try {
            vehicleVO = lgnViewModel.getMainVehicleSimplyFromDB();
            if (vehicleVO != null) {
                Glide
                        .with(getContext())
                        .load(dayCd == 1 ? StringUtil.isValidString(vehicleVO.getMainImgUri()) : StringUtil.isValidString(vehicleVO.getMainNgtImgUri()))
                        .fitCenter()
                        .error(R.drawable.main_img_car)
//                        .placeholder(R.drawable.main_img_car)
                        .into(me.ivCar);
            }
        } catch (Exception e) {

        }
    }

    private String getGreetingMsg() {
        String greetingMsg = getString(R.string.gm01_4);
        if (loginInfoDTO != null && loginInfoDTO.getProfile() != null && !TextUtils.isEmpty(loginInfoDTO.getProfile().getName())) {
            greetingMsg = String.format(Locale.getDefault(), getString(R.string.gm01_5), loginInfoDTO.getProfile().getName());
        }
        return greetingMsg;
    }

    private void initView() {
        playbackStateListener = new PlaybackStateListener();
        setViewCarImg();
        me.setActivity((MainActivity) getActivity());
        me.setWeatherCode(weatherCodes);
        me.setDayCd(dayCd);

        adapter = new HomeInsightHorizontalAdapter(onSingleClickListener);
        me.vpInsight.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        me.vpInsight.setAdapter(adapter);

        recordUtil = new RecordUtil(this, visibility -> {
            ((MainActivity) getActivity()).ui.lGnb.lWhole.setVisibility(visibility);
            ((MainActivity) getActivity()).ui.tabs.setVisibility(visibility);
            me.flDim.setVisibility(visibility);
            me.lQuickMenu.setVisibility(visibility);
            me.lCarInfo.setVisibility(visibility);
            me.vpInsight.setVisibility(visibility);
            if (visibility == View.GONE) {
                me.tvRepairStatus.setVisibility(visibility);
                me.lFloating.setVisibility(visibility);
                me.lDistance.setVisibility(visibility);
                me.tvDeveloperAgreements.setVisibility(visibility);
                me.btnQuick.setVisibility(visibility);
            } else {
                setViewVehicle();
                ((MainActivity) getActivity()).setGNB("", View.VISIBLE);
                goneQuickMenu();
            }
        });
    }

    private void setIndicator(String custGbCd) {
        int indicatorCnt = 0;
        try {
            indicatorCnt = Integer.parseInt(lgnViewModel.selectGlobalDataFromDB(KeyNames.KEY_NAME_DB_GLOBAL_DATA_ISINDICATOR));
        } catch (Exception e) {
            indicatorCnt = 0;
        }

        if (StringUtil.isValidString(custGbCd).equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_OV)//소유차량이고
                && indicatorCnt < 10//하단 메뉴를 10회 미만 확인했을 경우
        ) {
            me.ivIndicator.setVisibility(View.VISIBLE);
            VibratorUtil.makeMeShakeY(me.ivIndicator, 2500, 15, 2);
            //최초로그인이 아니라는 상태 값은 그대로 유지
            lgnViewModel.updateGlobalDataToDB(KeyNames.KEY_NAME_DB_GLOBAL_DATA_ISFIRSTLOGIN, VariableType.COMMON_MEANS_NO);
        } else {
            me.ivIndicator.setVisibility(View.INVISIBLE);
            me.ivIndicator.clearAnimation();
        }
    }

    private void setViewWeather() {

        if (!((MainActivity) getActivity()).isGpsEnable()) {
            MiddleDialog.dialogGPS(getActivity(), () -> ((MainActivity) getActivity()).turnGPSOn(isGPSEnable -> {
            }), () -> {
            });

            //현대양재사옥위치
            lgnViewModel.setPosition(37.463936, 127.042953);
        } else {
            reqMyLocation();
        }

    }

    private void reqMyLocation() {
//        ((MainActivity) getActivity()).showProgressDialog(true);
        ((MainActivity) getActivity()).findMyLocation(location -> {
            try {
//                ((MainActivity) getActivity()).showProgressDialog(false);
                if (location == null) {
                    lgnViewModel.setPosition(37.463936, 127.042953);
                    return;
                }

                getActivity().runOnUiThread(() -> {
                    lgnViewModel.setPosition(location.getLatitude(), location.getLongitude());
                });
            } catch (Exception e) {
                lgnViewModel.setPosition(37.463936, 127.042953);
            }
        }, 5000, GpsBaseActivity.GpsRetType.GPS_RETURN_FIRST, false);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClickCommon(View v) {
        String userCustGbCd = "";
        VehicleVO vehicleVO = null;
        switch (v.getId()) {
            case R.id.iv_indicator:
                ((MainActivity) getActivity()).movePage(1);
                break;
            case R.id.l_whole:
                MessageVO messageVO = null;
                try {
                    messageVO = ((MessageVO) v.getTag(R.id.item));
                    userCustGbCd = lgnViewModel.getDbUserRepo().getUserVO().getCustGbCd();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (messageVO != null) {
                        if (messageVO.getWeatherCodes() != null && !StringUtil.isValidString(userCustGbCd).equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_0000)) {
                            //날씨 인사이트이고 로그인이 되어있는 상태면 마이페이지로 이동
                            ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getContext(), MyGHomeActivity.class), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                        } else if (!((MainActivity) getActivity()).moveToMainTab(StringUtil.isValidString(messageVO.getLnkUri()))) {
                            ((MainActivity) getActivity()).moveToPage(StringUtil.isValidString(messageVO.getLnkUri()), StringUtil.isValidString(messageVO.getLnkTypCd()), false);
                        }
                    }
                }
                break;
            case R.id.btn_location:
                moveToNativePage(KEY_NAME_INTERNAL_LINK + GM01_01.getId());
                break;
            case R.id.iv_car:
                //2021-03-11요건 변경으로 임시 제거
                break;
            case R.id.btn_my_car:
                try {
                    userCustGbCd = lgnViewModel.getDbUserRepo().getUserVO().getCustGbCd();
                    if (StringUtil.isValidString(userCustGbCd).equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_OV) || StringUtil.isValidString(userCustGbCd).equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_CV)) {
                        moveToNativePage(KEY_NAME_INTERNAL_LINK + GM_CARLST01.getId());
                    }
                } catch (Exception e) {

                }
                break;
//            case R.id.btn_share:
//                recordUtil.checkRecordPermission();
//                break;
            case R.id.btn_quick:
                toggleQuickMenu();
                break;
            case R.id.fl_dim:
                goneQuickMenu();
                break;
            case R.id.tv_developer_agreements:
                try {
                    vehicleVO = lgnViewModel.getMainVehicleSimplyFromDB();
                    if (vehicleVO != null && !TextUtils.isEmpty(vehicleVO.getVin())) {
                        String carId = developersViewModel.getCarId(vehicleVO.getVin());
                        ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), GAWebActivity.class)
                                        .putExtra(KeyNames.KEY_NAME_URL, developersViewModel.getDataMilesAgreementsUrl(loginInfoDTO.getAccessToken(), loginInfoDTO.getProfile().getId(), carId))
                                , RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                    }
                } catch (Exception e) {

                }
                break;
            case R.id.btn_ev_battery:
            case R.id.iv_ev_battery:
            case R.id.tv_ev_battery:
            case R.id.tv_ev_battery_time:
                try {
                    setEnableBattery(true);
                    stopProgressBattery();
                    vehicleVO = lgnViewModel.getMainVehicleSimplyFromDB();
                    if (vehicleVO != null && !TextUtils.isEmpty(vehicleVO.getVin())) {
                        startProgressBattery();
                        String carId = developersViewModel.getCarId(vehicleVO.getVin());
                        requestEvStatus(vehicleVO, carId);
                    }else{
                        setEnableBattery(false);
                    }
                } catch (Exception e) {
                    setEnableBattery(false);
                }
                break;
        }
    }

//    private void setProgressBattery(boolean isShow){
//        AnimationDrawable animationDrawable = (AnimationDrawable) me.btnEvBattery.getDrawable();
//        if (isShow) {
//            if (!animationDrawable.isRunning()) animationDrawable.start();
//        } else {
//            animationDrawable.stop();
//            me.btnEvBattery.setBackground(null);
//            me.btnEvBattery.setImageResource(dayCd==VariableType.HOME_TIME_DAY ? R.drawable.loading_battery_b : R.drawable.loading_battery_w);
//        }
//        me.btnEvBattery.setEnabled(!isShow);
//        me.tvEvBattery.setEnabled(!isShow);
//        me.ivEvBattery.setEnabled(!isShow);
//    }

    private void stopProgressBattery(){
        AnimationDrawable animationDrawable = (AnimationDrawable) me.btnEvBattery.getDrawable();
        animationDrawable.stop();
        me.btnEvBattery.setBackground(null);
        me.btnEvBattery.setImageResource(dayCd == VariableType.HOME_TIME_DAY ? R.drawable.loading_battery_b : R.drawable.loading_battery_w);
    }

    private void startProgressBattery(){
        new Handler().postDelayed(() -> {
            AnimationDrawable animationDrawable = (AnimationDrawable) me.btnEvBattery.getDrawable();
            animationDrawable.setOneShot(true);
            animationDrawable.start();
        }, 100);
    }

    private void setEnableBattery(boolean isShow){
        me.btnEvBattery.setEnabled(!isShow);
        me.tvEvBattery.setEnabled(!isShow);
        me.ivEvBattery.setEnabled(!isShow);
    }


    private void requestEvStatus(VehicleVO vehicleVO, String carId){
        try {
            if (vehicleVO != null && !TextUtils.isEmpty(vehicleVO.getVin()) && !TextUtils.isEmpty(carId) && vehicleVO.isEV()) {
                setViewEvBatteryVisibility(View.VISIBLE);
                developersViewModel.reqEvStatus(new EvStatus.Request(carId));
            }else{
                setViewEvBatteryVisibility(View.GONE);
            }
        } catch (Exception e) {

        }
    }

    private void toggleQuickMenu() {
        if (me.lQuickMenu.getVisibility() == View.VISIBLE) {
            goneQuickMenu();
        } else {
            visibleQuickMenu();
        }
    }

    private void visibleQuickMenu() {
        me.lQuickMenu.setVisibility(View.VISIBLE);
        me.flDim.setVisibility(View.VISIBLE);
    }

    private void goneQuickMenu() {
        me.lQuickMenu.setVisibility(View.GONE);
        me.flDim.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        if (isRecord)
            return;

        try {
            if (isInit) {
                isInit = false;
                initViewBanner(true);
                initializePlayer();
                ((MainActivity) getActivity()).setTab(dayCd);
                ((SubActivity) getActivity()).hadTutorial(lgnViewModel.getMainVehicleFromDB().isEVonlyOV(), lgnViewModel.hadTutorial(VariableType.TUTORIAL_TYPE_HOME), VariableType.TUTORIAL_TYPE_HOME);
            }
            setViewVehicle();
            //날씨 효과가 있을 경우 중복이지만 onResume에서 한번더 호출 진행 (0005받기 전까지 대기 화면이 이질적으로 보임)
            resumeAndPauseLottie(true);
            //LGN-0005와 중복되지만 하->상 이동 시 바로 변경하기 위한 처리
            SubActivity.setStatusBarColor(getActivity(), dayCd == VariableType.HOME_TIME_DAY ? R.color.x_ffffff : R.color.x_000000);
            setViewWeather();
            setIndicator(lgnViewModel.getMainVehicleSimplyFromDB().getCustGbCd());
            goneQuickMenu();
            ((MainActivity) getActivity()).setGNB("", View.VISIBLE, false, dayCd == VariableType.HOME_TIME_DAY);
            clearKeyPad();
        } catch (Exception e) {

        }
    }

    private void startTimer() {

        if (timer == null)
            timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        try {
                            if (adapter.getRealItemCnt() > 1) {
                                me.vpInsight.setCurrentItem(me.vpInsight.getCurrentItem() + 1, true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }, 6000, 8000);

    }

    private void pauseTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void setViewCarInfo(VehicleVO vehicle) {

        VehicleVO vehicleVO;
        try {
            vehicleVO = (vehicle == null) ? lgnViewModel.getMainVehicleFromDB() : vehicle;
            if (vehicle == null
                    || TextUtils.isEmpty(vehicle.getCustGbCd())
                    || vehicle.getCustGbCd().equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_0000)
                    || vehicle.getCustGbCd().equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_NV)) {
                me.tvCarCode.setText("GENESIS");
                me.tvCarCode.setTextSize(TypedValue.COMPLEX_UNIT_DIP,56);
                me.tvGenesis.setVisibility(View.INVISIBLE);
                me.tvCarVrn.setVisibility(View.GONE);
            } else {
                me.tvCarCode.setTextSize(TypedValue.COMPLEX_UNIT_DIP,70);
                me.tvGenesis.setVisibility(View.VISIBLE);
                if (vehicleVO != null) {
                    me.tvCarCode.setText(StringUtil.isValidString(vehicleVO.getMdlNm()));
                    //2021-02-19 요건으로 제거됨
//                  me.tvCarModel.setText(StringUtil.isValidString(vehicleVO.getSaleMdlNm()).replace(StringUtil.isValidString(vehicleVO.getMdlNm()), ""));

                    if (TextUtils.isEmpty(vehicleVO.getCarRgstNo())) {
                        me.tvCarVrn.setVisibility(View.GONE);
                    } else {
                        me.tvCarVrn.setVisibility(View.VISIBLE);
                        me.tvCarVrn.setText(vehicleVO.getCarRgstNo());
                    }
                }
            }
        } catch (Exception e) {

        }
    }


    private void setViewVehicle() {
        VehicleVO vehicleVO = null;
        String userCustGbCd = "";
        try {
            userCustGbCd = lgnViewModel.getDbUserRepo().getUserVO().getCustGbCd();
            vehicleVO = lgnViewModel.getMainVehicleFromDB();
            if (vehicleVO != null) {
                setViewCarInfo(vehicleVO);

                me.lFloating.setVisibility(View.GONE);
                switch (vehicleVO.getCustGbCd()) {
                    case VariableType.MAIN_VEHICLE_TYPE_OV:
                        String carId = developersViewModel.getCarId(vehicleVO.getVin());
                        String userId = loginInfoDTO.getProfile().getId();
                        String accessToken = loginInfoDTO.getAccessToken();
                        lgnViewModel.reqLGN0003(new LGN_0003.Request(APPIAInfo.GM01.getId(), vehicleVO.getVin()));
                        if(TextUtils.isEmpty(carId)){
                            setViewDevelopers(false);
                        }else {
                            developersViewModel.reqAgreementsAsync(new Agreements.Request(userId, carId, accessToken));
                        }
                        makeQuickMenu(vehicleVO.getCustGbCd(), vehicleVO, userCustGbCd);
                        break;
                    case VariableType.MAIN_VEHICLE_TYPE_CV:
                        makeDownMenu(vehicleVO.getCustGbCd());
                        makeQuickMenu(vehicleVO.getCustGbCd(), vehicleVO, userCustGbCd);
                        break;
                    case VariableType.MAIN_VEHICLE_TYPE_NV:
                    case VariableType.MAIN_VEHICLE_TYPE_0000:
                    default:
                        //차량 소유자의 차량이 삭제 예정 상태가되고 디폴트 차량이 보여질 경우 로그인/미소유(NV) 상태로 보여줌)
                        makeDownMenu(userCustGbCd.equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_OV) ? VariableType.MAIN_VEHICLE_TYPE_NV : vehicleVO.getCustGbCd());
                        makeQuickMenu(vehicleVO.getCustGbCd(), vehicleVO, userCustGbCd);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setViewDevelopers(boolean isInit) {
        me.tvDeveloperAgreements.setVisibility(View.GONE);
        me.lDistance.setVisibility(View.GONE);
        me.btnLocation.lWhole.setVisibility(View.GONE);
        VehicleVO vehicleVO;
        try {
            vehicleVO = lgnViewModel.getMainVehicleSimplyFromDB();
            String carId = developersViewModel.getCarId(vehicleVO.getVin());
            String userId = loginInfoDTO.getProfile().getId();
            //정보제공동의유무확인
            switch (developersViewModel.checkCarInfoToDevelopers(vehicleVO.getVin(), userId)) {
                case STAT_AGREEMENT:
                    //동의한경우
                    if(!isInit) {
                        reqCarInfoToDevelopers(carId);
                        requestEvStatus(vehicleVO, carId);
                        break;
                    }
                case STAT_DISAGREEMENT:
                    //동의되지 않은 경우
                    me.btnLocation.lWhole.setVisibility(View.GONE);
                    me.lDistance.setVisibility(View.GONE);
                    me.tvDeveloperAgreements.setVisibility(View.VISIBLE);
                    setViewEvBatteryVisibility(View.GONE);
                    break;
                case STAT_DISABLE:
                default:
                    //ccs 사용불가상태
                    me.btnLocation.lWhole.setVisibility(View.GONE);
                    me.lDistance.setVisibility(View.GONE);
                    me.tvDeveloperAgreements.setVisibility(View.GONE);
                    setViewEvBatteryVisibility(View.GONE);
                    break;
            }
        } catch (Exception e) {

        }

    }

    private void setViewEvBatteryVisibility(int visibility){
        me.lBattery.setVisibility(visibility);
    }

    private void reqCarInfoToDevelopers(String carId) {
        me.lDistance.setVisibility(View.VISIBLE);
        me.tvDeveloperAgreements.setVisibility(View.GONE);
        me.btnLocation.lWhole.setVisibility(View.VISIBLE);
        developersViewModel.reqDte(new Dte.Request(carId));
        developersViewModel.reqOdometer(new Odometer.Request(carId));
        developersViewModel.reqOdometers(new Odometers.Request(carId, developersViewModel.getDateYyyyMMdd(-1), developersViewModel.getDateYyyyMMdd(0)));
//        developersViewModel.reqDistance(new Distance.Request(carId, developersViewModel.getDateYyyyMMdd(-7), developersViewModel.getDateYyyyMMdd(0)));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isRecord)
            return;

        pausePlayer();
        pauseTimer();
        resumeAndPauseLottie(false);
    }

    private void pausePlayer(){
        if (player != null)
            player.setPlayWhenReady(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        pausePlayer();
    }

    private void makeQuickMenu(String custGbCd, VehicleVO vehicleVO, String userCustGbCd) {
        //차량 상세 노출 처리
        if (StringUtil.isValidString(userCustGbCd).equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_OV) || StringUtil.isValidString(userCustGbCd).equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_CV)) {
            me.btnMyCar.lWhole.setVisibility(View.VISIBLE);
        } else {//차량 미보유 일 경우 미노출
            me.btnMyCar.lWhole.setVisibility(View.GONE);
        }

//        //내 차 위치 노출 처리
//        CarConnectVO carConnectVO = developersViewModel.getCarConnectVO(vehicleVO.getVin());
//        if (carConnectVO != null && !TextUtils.isEmpty(carConnectVO.getCarId()) && carConnectVO.isResult()) {//GCS 미가입 차 일 경우 미노출
//            me.btnLocation.lWhole.setVisibility(View.VISIBLE);
//        } else {
//            me.btnLocation.lWhole.setVisibility(View.GONE);
//        }
    }


    //2021-03-09 요건 변경으로 퀵메뉴 제거
//    private void makeQuickMenu(String custGbCd, VehicleVO vehicleVO, String userCustGbCd) {
//        final TextView[] quickBtn = {me.btnQuick1, me.btnQuick2, me.btnQuick3, me.btnQuick4, me.btnQuick5, me.btnQuick6};
//        List<QuickMenuVO> list = cmnViewModel.getQuickMenuList(custGbCd);
//        quickBtn[0].setVisibility(View.GONE);
//        quickBtn[1].setVisibility(View.GONE);
//        quickBtn[2].setVisibility(View.GONE);
//        quickBtn[3].setVisibility(View.GONE);
//        quickBtn[4].setVisibility(View.GONE);
//        quickBtn[5].setVisibility(View.GONE);
//
//        list.add(0, new QuickMenuVO("GM_CARLST01", "I", "차량 상세", "1", "genesisapp://menu?id=GM_CARLST01", "NV"));
//        list.add(1, new QuickMenuVO("GM01_01", "I", "내 차 위치 찾기", "2", "genesisapp://menu?id=GM01_01", "OV"));
//        //2021-02-18 해당 기능 제거로 요건 확정 됨
////        list.add(2, new QuickMenuVO("GM01_03", "I", "SNS 공유하기", "3", "genesisapp://menu?id=GM01_03", "OV"));
//
//        int menuSize = list.size() > quickBtn.length ? quickBtn.length : list.size();
//
//        int visibleCnt = 0; //버튼이 활성화 된 갯수
//
//        for (int i = 0; i < menuSize; i++) {
//
//            APPIAInfo appiaInfo = APPIAInfo.findCode(StringUtil.isValidString(list.get(i).getMenuId()));
//
//            switch (appiaInfo) {
//                case GM_CARLST01: //MY 차고
//                case GM01_03: //SNS 공유하기
//                    if (StringUtil.isValidString(userCustGbCd).equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_OV) || StringUtil.isValidString(userCustGbCd).equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_CV)) {
//                        quickBtn[i].setVisibility(View.VISIBLE);
//                        visibleCnt++;
//                    } else {//차량 미보유 일 경우 미노출
//                        quickBtn[i].setVisibility(View.GONE);
//                    }
//                    break;
//                case GM01_01: //주차위치확인
//                    CarConnectVO carConnectVO = developersViewModel.getCarConnectVO(vehicleVO.getVin());
//                    if (carConnectVO!=null&&!TextUtils.isEmpty(carConnectVO.getCarId())&&carConnectVO.isResult()) {//GCS 미가입 차 일 경우 미노출
//                        quickBtn[i].setVisibility(View.VISIBLE);
//                        visibleCnt++;
//                    } else {
//                        quickBtn[i].setVisibility(View.GONE);
//                    }
//                    break;
//                default:
//                    quickBtn[i].setVisibility(View.VISIBLE);
//                    visibleCnt++;
//                    break;
//            }
//
//            quickBtn[i].setText(list.get(i).getMenuNm());
//            quickBtn[i].setTag(R.id.menu_id, list.get(i));
//            quickBtn[i].setOnClickListener(new OnSingleClickListener() {
//                @Override
//                public void onSingleClick(View v) {
//                    QuickMenuVO quickMenuVO = (QuickMenuVO) v.getTag(R.id.menu_id);
//                    if (quickMenuVO != null) {
//                        String msgLnkCd = quickMenuVO.getMsgLnkCd();
//                        String lnkUri = quickMenuVO.getLnkUri();
//                        if (!TextUtils.isEmpty(msgLnkCd) && !TextUtils.isEmpty(lnkUri)) {
//                            if (msgLnkCd.equalsIgnoreCase("I") || msgLnkCd.equalsIgnoreCase("IM")) {
//                                if (lnkUri.startsWith(KeyNames.KEY_NAME_INTERNAL_LINK) || lnkUri.startsWith(KeyNames.KEY_NAME_INTERNAL_LINK2)) {
//                                    //내부링크로 이동
//                                    moveToNativePage(lnkUri);
//                                } else if (lnkUri.startsWith("http")) {
//                                    //웹뷰로 이동
//                                    ((MainActivity) getActivity()).moveToExternalPage(lnkUri, VariableType.COMMON_MEANS_YES);
//                                }
//                            } else if (msgLnkCd.equalsIgnoreCase("O") || msgLnkCd.equalsIgnoreCase("OW")) {
//                                //외부 브라우저로 이동
//                                ((MainActivity) getActivity()).moveToExternalPage(lnkUri, VariableType.COMMON_MEANS_NO);
//                            }
//                        }
//                    }
//                }
//            });
//        }
//
//        me.btnQuick.setVisibility(visibleCnt == 0 ? View.INVISIBLE : View.VISIBLE);
//    }

    //todo baseactivity의 함수로 대체가능한지 확인 필요. (주차위치확인)
    private void moveToNativePage(String lnkUri) {
        lnkUri = lnkUri.replace(KEY_NAME_INTERNAL_LINK, "");
        switch (APPIAInfo.findCode(lnkUri)) {
            case GM01_01://주차위치 확인
                if (!((MainActivity) getActivity()).isGpsEnable()) {
                    MiddleDialog.dialogGPS(getActivity(), () -> ((MainActivity) getActivity()).turnGPSOn(isGPSEnable -> {
                    }), () -> {
                        //TODO 확인 클릭
                    });
                } else {
                    ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), MyLocationActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                }
                break;
            case GM01_03://sns 공유하기
                isRecord = true;
                recordUtil.checkRecordPermission();
                break;
            case GM_BTO1://BTO
                ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), GAWebActivity.class)
                        .putExtra(KeyNames.KEY_NAME_APP_IA_INFO, GM_BTO1)
                        .putExtra(KeyNames.KEY_NAME_MAP_SEARCH_TITLE_ID, R.string.gm03_3), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
//                lgnViewModel.reqSTO1002(new STO_1002.Request(APPIAInfo.GM01.getId()));
                break;
            case GM_BTO2://견적내기
                ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), GAWebActivity.class)
                        .putExtra(KeyNames.KEY_NAME_APP_IA_INFO, GM_BTO2)
                        .putExtra(KeyNames.KEY_NAME_MAP_SEARCH_TITLE_ID, R.string.gm03_3), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            case GM02_CTR01://계약서 조회
                if(lgnViewModel.checkContractInfoPopUp()){
                    MiddleDialog.dialogSimilarCarContractInfo(getActivity(), new OnSingleClickListener() {
                        @Override
                        public void onSingleClick(View v) {
                            lgnViewModel.setContractInfo((Boolean)v.getTag(R.id.item));
                            moveToContractInfo();
                        }
                    });
                }else{
                    moveToContractInfo();
                }
                break;
            case GM02_INV01://유사 재고 조회
                ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), APPIAInfo.GM02_INV01.getActivity()), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            case GM_CARLST_01://렌트/리스 실운행자 등록
                ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), APPIAInfo.GM_CARLST_01.getActivity()), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            case GM_CARLST_03://내 차 등록
                ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), APPIAInfo.GM_CARLST_03.getActivity()), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            case GM_CARLST01://MY 차고
                ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), GM_CARLST01.getActivity()), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
        }
    }

    private void moveToContractInfo(){
        VehicleVO vehicleVO = null;
        try {
            vehicleVO = lgnViewModel.getMainVehicleFromDB();
        } catch (Exception e) {

        } finally {
            if (vehicleVO != null) {
                ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), APPIAInfo.GM02_CTR01.getActivity())
                                .putExtra(KeyNames.KEY_NAME_CTRCT_NO, vehicleVO.getCtrctNo())
                        , RequestCodes.REQ_CODE_ACTIVITY.getCode()
                        , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
            }
        }
    }

    private void makeDownMenu(String custGbCd) {
        me.lDistance.setVisibility(View.GONE);
        me.tvDeveloperAgreements.setVisibility(View.GONE);
        final TextView[] floatingBtns = {me.btnFloating1, me.btnFloating2, me.btnFloating3};
        List<DownMenuVO> list = cmnViewModel.getDownMenuList(custGbCd);
        if (list == null || list.size() == 0) {
            me.lFloating.setVisibility(View.GONE);
        } else {
            me.lFloating.setVisibility(View.VISIBLE);
            me.btnFloating1.setVisibility(View.GONE);
            me.btnFloating2.setVisibility(View.GONE);
            me.btnFloating3.setVisibility(View.GONE);
            int menuSize = Math.min(list.size(), 3);

            //차량이 없는 고객인 경우 흰색배경의 검은글씨 활성화
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) me.lFloating.getLayoutParams();
            int margin = (int) DeviceUtil.dip2Pixel(getContext(), 20);
            int marginHorizontal = (int) DeviceUtil.dip2Pixel(getContext(), 35);
            int marginBottom = (int) DeviceUtil.dip2Pixel(getContext(), 105);
            if (menuSize == 1) {
                //메뉴가 하나일 때 (보통 미로그인, 미소유 사용자)
                params.setMargins(marginHorizontal, 0, marginHorizontal, marginBottom);
            } else {
                //메뉴가 하나 이상일 때
                params.setMargins(margin, 0, margin, marginBottom);
            }
            me.setMenuSize(menuSize);
            me.lFloating.setLayoutParams(params);


            me.lFloating.setBackgroundResource(menuSize == 1 ? R.drawable.bg_ffffff_stroke_e5e5e5 : 0);
            me.btnFloating1.setTextColor(menuSize == 1 ? getContext().getColor(R.color.x_000000) : (dayCd == VariableType.HOME_TIME_DAY ? getContext().getColor(R.color.x_000000) : getContext().getColor(R.color.x_ffffff)));

            me.btnFloating1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (menuSize == 1 ? 16 : 14));
            me.btnFloating1.setTypeface(menuSize == 1 ? ResourcesCompat.getFont(getActivity(), R.font.regular_genesissanstextglobal) : ResourcesCompat.getFont(getActivity(), R.font.light_genesissansheadglobal));

            for (int i = 0; i < menuSize; i++) {
                floatingBtns[i].setVisibility(View.VISIBLE);


                floatingBtns[i].setText(list.get(i).getMenuNm().replace("\\n", "\n"));
                floatingBtns[i].setTag(R.id.menu_id, list.get(i));
                floatingBtns[i].setOnClickListener(new OnSingleClickListener() {
                    @Override
                    public void onSingleClick(View v) {
                        DownMenuVO downMenuVO = (DownMenuVO) v.getTag(R.id.menu_id);
                        if (downMenuVO != null) {
                            String qckMenuDivCd = downMenuVO.getMsgLnkCd();
                            String lnkUri = downMenuVO.getLnkUri();
                            String wvYn = downMenuVO.getWvYn();
                            if (!TextUtils.isEmpty(qckMenuDivCd) && !TextUtils.isEmpty(lnkUri)) {
                                if (qckMenuDivCd.equalsIgnoreCase("I") || qckMenuDivCd.equalsIgnoreCase("IM")) {

                                    if (lnkUri.startsWith(KEY_NAME_INTERNAL_LINK)) {
                                        if (!TextUtils.isEmpty(lnkUri)) {
                                            moveToNativePage(lnkUri);
                                        }
                                    }

                                    //네이티브 링크로 이동
                                    //TODO 네이티브로 이동하는 부분은 처리 필요
                                } else {
                                    ((MainActivity) getActivity()).moveToExternalPage(lnkUri, wvYn);
//                                    if (TextUtils.isEmpty(wvYn) || wvYn.equalsIgnoreCase(VariableType.COMMON_MEANS_YES)) {
//                                        ((MainActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), WebviewActivity.class).putExtra(KeyNames.KEY_NAME_URL, lnkUri), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
//                                    } else {
//                                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                                        intent.setData(Uri.parse(lnkUri));
//                                        startActivity(intent); //TODO 테스트 필요 0002
//                                    }
                                    //외부 링크로 이동
                                }
                            }
                        }
                    }
                });
            }

            if (list.size() < 2) {
                me.ivFloatingMark1.setVisibility(View.GONE);
                me.ivFloatingMark2.setVisibility(View.GONE);
            } else if (list.size() == 2) {
                me.ivFloatingMark1.setVisibility(View.VISIBLE);
                me.ivFloatingMark2.setVisibility(View.GONE);
            } else {
                me.ivFloatingMark1.setVisibility(View.VISIBLE);
                me.ivFloatingMark2.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseVideo();
        recordUtil.unRegReceiver();
    }

    private int currentWindow = 0;
    private long playbackPosition = 0;

    private void releaseVideo() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    private void initPlayer() {
        Log.v("videoPlayerStatus", "init");
        releaseVideo();
        player = new SimpleExoPlayer.Builder(getContext()).build();
        player.setVolume(0);
        player.setRepeatMode(REPEAT_MODE_ALL);
        player.setSeekParameters(null);
        player.addListener(playbackStateListener);
        me.exoPlayerView.requestFocus();
        me.exoPlayerView.setPlayer(player);
        me.exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        me.exoPlayerView.setUseController(false);
    }

    private class PlaybackStateListener implements Player.EventListener {

        @Override
        public void onPlaybackStateChanged(int playbackState) {
            String stateString;
            switch (playbackState) {
                case ExoPlayer.STATE_IDLE:
                    stateString = "ExoPlayer.STATE_IDLE      -";
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    stateString = "ExoPlayer.STATE_BUFFERING -";
                    break;
                case ExoPlayer.STATE_READY:
                    stateString = "ExoPlayer.STATE_READY     -";
                    break;
                case ExoPlayer.STATE_ENDED:
                    stateString = "ExoPlayer.STATE_ENDED     -";
                    break;
                default:
                    stateString = "UNKNOWN_STATE             -";
                    break;
            }
            Log.v("videoPlayerStatus", "event state:" + stateString);
        }
    }


    private void setVideo() {
        try {
            MediaItem mediaItem = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(rawBackground));
            if (player.getMediaItemCount() > 0) {
                player.clearMediaItems();
            }
            player.setMediaItem(mediaItem);
            player.setPlayWhenReady(true);
            if (preRawBackground == rawBackground) {
                player.seekTo(currentWindow, playbackPosition);
            }
            preRawBackground = rawBackground;
            player.prepare();
        } catch (Exception e) {

        }
    }

    private void initializePlayer() {
        if (rawBackground != 0) {
            Log.v("videoPlayerStatus", "isResume:true");
            initPlayer();
            setVideo(); //다시 초기화 진행
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RequestCodes.REQ_CODE_GPS.getCode() && resultCode == RESULT_OK) {
            reqMyLocation();
        } else if (requestCode == RequestCodes.REQ_CODE_PERMISSIONS_MEDIAPROJECTION.getCode()) {
            Log.v("testRecord", "testRecord:resultOk");
            if (resultCode == RESULT_OK) {
                isRecord = true;
                recordUtil.doRecordService(me.vClickReject, resultCode, data);
            } else {
                isRecord = false;
            }
        } else if (requestCode == RequestCodes.REQ_CODE_PLAY_VIDEO.getCode()) {
            isRecord = false;
            recordUtil.requestShare();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void resumeAndPauseLottie(boolean isResume) {
        if (rawLottie != 0 && isResume) {
            me.lottieView.setSpeed(0.6f);
            me.lottieView.setAnimation(rawLottie);
            me.lottieView.setRepeatMode(RESTART);
//            me.lottieView.setMaxFrame(40);
            me.lottieView.playAnimation();
        } else {
            me.lottieView.pauseAnimation();
        }
    }
}
