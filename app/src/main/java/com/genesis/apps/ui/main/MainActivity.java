package com.genesis.apps.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.genesis.apps.R;
import com.genesis.apps.comm.hybrid.MyWebViewFrament;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.etc.AbnormalCheck;
import com.genesis.apps.comm.model.api.gra.BAR_1001;
import com.genesis.apps.comm.model.api.gra.CMN_0001;
import com.genesis.apps.comm.model.api.gra.NOT_0003;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.constants.StoreInfo;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.util.DeviceUtil;
import com.genesis.apps.comm.util.PackageUtil;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.comm.viewmodel.CMNViewModel;
import com.genesis.apps.comm.viewmodel.CMSViewModel;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.databinding.ActivityMainBinding;
import com.genesis.apps.databinding.ItemTabBinding;
import com.genesis.apps.databinding.ItemTabDayBinding;
import com.genesis.apps.ui.common.activity.GpsBaseActivity;
import com.genesis.apps.ui.common.dialog.middle.MiddleDialog;
import com.genesis.apps.ui.main.contents.ContentsSearchActivity;
import com.genesis.apps.ui.main.contents.FragmentContents;
import com.genesis.apps.ui.main.home.FragmentHome1;
import com.genesis.apps.ui.main.home.FragmentHome2;
import com.genesis.apps.ui.main.insight.FragmentInsight;
import com.genesis.apps.ui.main.service.FragmentService;
import com.genesis.apps.ui.main.store.FragmentStore;
import com.genesis.apps.ui.myg.MyGEntranceActivity;
import com.genesis.apps.ui.myg.MyGHomeActivity;
import com.genesis.apps.ui.myg.MyGMenuActivity;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends GpsBaseActivity<ActivityMainBinding> {
    public boolean isMoveHomeBottom() {
        return moveHomeBottom;
    }

    public void setMoveHomeBottom(boolean moveHomeBottom) {
        this.moveHomeBottom = moveHomeBottom;
    }

    public enum MainPageDiv {
        HOME,
        INSIGHT,
        SERVICE,
        STORE,
        CONTENTS
    }

    private final int pageNum = MainPageDiv.values().length;
    public FragmentStateAdapter pagerAdapter;
    private LGNViewModel lgnViewModel;
    private CMNViewModel cmnViewModel;
    private CMSViewModel cmsViewModel;
    //ios??? ????????? ????????? ????????? ?????? ??????
    //???????????? ?????? ???????????? onResume ?????? ??? ??? ???????????? ????????????
    private boolean moveHomeBottom=false;
    private VehicleVO vehicleVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ui.setActivity(this);
        getDataFromIntent();
        setViewModel();
        setObserver();
        initView();
//        initBarcode();
    }

//    private void initBarcode() {
//        String custGbCd = "";
//        try {
//            custGbCd = lgnViewModel.getUserInfoFromDB().getCustGbCd();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (!TextUtils.isEmpty(custGbCd) && !custGbCd.equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_0000)) {
//                cmnViewModel.reqBAR1001(new BAR_1001.Request(APPIAInfo.GM01.getId()));
//            }
//        }
//    }


    private void setBarcode() {
        String custGbCd = "";
        try {
            custGbCd = lgnViewModel.getUserInfoFromDB().getCustGbCd();
            vehicleVO = lgnViewModel.getMainVehicleFromDB();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!TextUtils.isEmpty(custGbCd) && !custGbCd.equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_0000)) {
                ui.lGnb.setIsEV(vehicleVO!=null&&vehicleVO.isEV());
                ui.lGnb.setUseBarcode(true);
            }
        }
    }


    private void initView() {
        pagerAdapter = new MainViewpagerAdapter(this, pageNum);
        ui.viewpager.setAdapter(pagerAdapter);
        ui.viewpager.setUserInputEnabled(false);
//        setTabView();

        //ViewPager Setting
        ui.viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        ui.viewpager.setCurrentItem(MainPageDiv.HOME.ordinal());
        ui.viewpager.setOffscreenPageLimit(4);

        ui.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    ui.viewpager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                initFragmentHome(position);
                initFragmentContents(position);
                setFirebaseAnalyticsLog(position);

//                ui.indicator.animatePageSelected(position%num_page);
            }

        });

        final float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);
        ui.viewpager.setPageTransformer((page, position) -> {
            float myOffset = position * -(2 * pageOffset + pageMargin);
            if (ui.viewpager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(ui.viewpager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.setTranslationX(-myOffset);
                } else {
                    page.setTranslationX(myOffset);
                }
            } else {
                page.setTranslationY(myOffset);
            }
        });

    }

    public void setFirebaseAnalyticsLog(int position){
        try {
            String menuId = "";
            if (position == MainPageDiv.HOME.ordinal()) {
                //HOME
                menuId = "GNB_TAB_HOME";
            } else if (position == MainPageDiv.INSIGHT.ordinal()) {
                menuId = "GNB_TAB_INSIGHT";
            } else if (position == MainPageDiv.SERVICE.ordinal()) {
                menuId = "GNB_TAB_SERVICE";
            } else if (position == MainPageDiv.STORE.ordinal()) {
                menuId = "GNB_TAB_STORE";
            } else if (position == MainPageDiv.CONTENTS.ordinal()) {
                menuId = "GNB_TAB_CONTENTS";
            } else {
                //HOME
                menuId = "GNB_TAB_HOME";
            }
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, menuId);
            FirebaseAnalytics.getInstance(this).logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle);
        }catch (Exception e){

        }
    }

    @Override
    public void onClickCommon(View v) {

        switch (v.getId()) {
            case R.id.btn_barcode:
                boolean isEv=false;
                try{
                    isEv = (Boolean)v.getTag();
                }catch (Exception ignore){

                }finally {
                    if(vehicleVO!=null&&vehicleVO.isEV()){
                        if(vehicleVO.isEVonlyOV()){
                            if(!hadTutorial(true, lgnViewModel.hadTutorial(VariableType.TUTORIAL_TYPE_DIGITAL_WALLET), VariableType.TUTORIAL_TYPE_DIGITAL_WALLET)){
                                startActivitySingleTop(new Intent(this, DigitalWalletActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_NONE);
                            }
                        }else{
                            SnackBarUtil.show(this, "?????? ?????? ??? ?????? ???????????????.");
                        }
                    }else{
                        //???????????? ????????? ?????? ???????????? ??????
                        startActivitySingleTop(new Intent(this, BarcodeActivity.class), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_NONE);
                    }
                }
                break;
            case R.id.btn_alarm:
                startActivitySingleTop(new Intent(this, AlarmCenterActivity.class), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            case R.id.btn_login:
                startActivitySingleTop(new Intent(this, MyGEntranceActivity.class), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            case R.id.btn_profile:
                startActivitySingleTop(new Intent(this, MyGHomeActivity.class), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            case R.id.btn_search:
                startActivitySingleTop(new Intent(this, MyGMenuActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            case R.id.btn_cart_list:
                try {
                    loginChk(StoreInfo.STORE_PURCHASE_URL, lgnViewModel.getUserInfoFromDB().getCustGbCd(), getCustInfo());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_store_cart:
                try {
                    loginChk(StoreInfo.STORE_CART_URL, lgnViewModel.getUserInfoFromDB().getCustGbCd(), getCustInfo());
                } catch (Exception ignore) {

                }
                break;
            case R.id.btn_store_search:
                try {
                    loginChk(StoreInfo.STORE_SEARCH_URL, lgnViewModel.getUserInfoFromDB().getCustGbCd(), getCustInfo());
                } catch (Exception e) {

                }

                break;
            case R.id.btn_contents_search:
                startActivitySingleTop(new Intent(this, ContentsSearchActivity.class), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);

                break;
        }

    }

    private String getCustInfo(){
        String mUserInfo;
        try{
            mUserInfo = cmsViewModel.getRES_CMS_1001().getValue().data.getCustInfo();
        }catch (Exception e){
            mUserInfo = "";
        }
        return mUserInfo;
    }

    @Override
    public void setViewModel() {
        lgnViewModel = new ViewModelProvider(this).get(LGNViewModel.class);
        cmnViewModel = new ViewModelProvider(this).get(CMNViewModel.class);
        cmsViewModel = new ViewModelProvider(this).get(CMSViewModel.class);
    }

    @Override
    public void setObserver() {
        cmnViewModel.getRES_ABNORMAL_CHECK().observe(this, result -> {
            switch (result.status){
                case LOADING:
                    break;
                case SUCCESS:
                    if(result.data!=null){
                        if(result.data.isAbnormal()){
                            MiddleDialog.dialogAbnormal(this, StringUtil.isValidString(result.data.getTitle()), StringUtil.isValidString(result.data.getMessage()), () -> exitApp());
                            break;
                        }
                    }
                default:
                    break;
            }
        });

        cmnViewModel.getRES_NOT_0003().observe(this, result -> {
            switch (result.status) {
                case SUCCESS:
                    if (result.data != null) {
                        setGnB();
                    }
                    break;
                default:
                    //???????????? ??????
                    break;
            }
        });

        cmnViewModel.getRES_BAR_1001().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    if (result.data != null && result.data.getCardList() != null && result.data.getCardList().size() > 0) {
                        ui.lGnb.setUseBarcode(true);
                        break;
                    }
                default:
                    ui.lGnb.setUseBarcode(false);
                    break;
            }
        });

    }

    @Override
    public void getDataFromIntent() {

    }

    @Override
    public void onResume() {
        super.onResume();
        cmnViewModel.reqAbnormalCheck(new AbnormalCheck.Request());
        Log.e("onResume", "onReusme Mainactivity");
        checkPushCode();
        checkDeepCode();
        reqNewNotiCnt();
        setBarcode();
    }

    public String getCustGbCd() {
        String custGbCd = "";
        try {
            custGbCd = lgnViewModel.getUserInfoFromDB().getCustGbCd();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return custGbCd;
        }
    }

    private void reqNewNotiCnt() {
        String custGbCd = "";
        try {
            custGbCd = lgnViewModel.getUserInfoFromDB().getCustGbCd();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //????????????????????? 0000(?????????)??? ????????? ?????? ?????? ?????? ??????
            if (!TextUtils.isEmpty(custGbCd) && !custGbCd.equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_0000)) {
                cmnViewModel.reqNOT0003(new NOT_0003.Request(APPIAInfo.GM03.getId()));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == RequestCodes.REQ_CODE_PERMISSIONS_MEDIAPROJECTION.getCode())
                || requestCode == RequestCodes.REQ_CODE_PLAY_VIDEO.getCode()
                || (requestCode == RequestCodes.REQ_CODE_GPS.getCode() && resultCode == RESULT_OK)
        ) {
            for (Fragment fragmentParent : getSupportFragmentManager().getFragments()) {
                if (fragmentParent instanceof FragmentHome) {
                    for (Fragment fragmentChild : fragmentParent.getChildFragmentManager().getFragments()) {
                        if (fragmentChild instanceof FragmentHome1) {
                            fragmentChild.onActivityResult(requestCode, resultCode, data);
                            return;
                        }
                    }
                }
            }
        } else if (resultCode == ResultCodes.REQ_CODE_SERVICE_RESERVE_AUTOCARE.getCode()
                || resultCode == ResultCodes.REQ_CODE_SERVICE_RESERVE_HOMETOHOME.getCode()
                || resultCode == ResultCodes.REQ_CODE_SERVICE_RESERVE_REPAIR.getCode()
                || resultCode == ResultCodes.REQ_CODE_SERVICE_RESERVE_REMOTE.getCode()
                || resultCode == ResultCodes.REQ_CODE_SERVICE_NETWORK_RESERVE.getCode()) {
//                || resultCode == ResultCodes.REQ_CODE_CHARGE_RESERVATION_FINISH.getCode()) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof FragmentService) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                    return;
                }
            }
        } else if (resultCode == ResultCodes.REQ_CODE_INSIGHT_EXPN_ADD.getCode()) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof FragmentInsight) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                    return;
                }
            }
        } else if (resultCode == ResultCodes.REQ_CODE_SERVICE_WASH_RESERVATION_FINISH.getCode()) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof FragmentService) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                    return;
                }
            }
        } else if (requestCode == RequestCodes.REQ_CODE_SERVICE_DRIVE_REQ.getCode()) {
            // ???????????? ?????? ??????
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof FragmentService) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                    return;
                }
            }
        } else if (requestCode == RequestCodes.REQ_CODE_CONTENTS_RELOAD.getCode()) {
            if(resultCode == ResultCodes.REQ_CODE_CONTENTS_RELOAD.getCode()) {
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if (fragment instanceof FragmentContents) {
                        fragment.onActivityResult(requestCode, resultCode, data);
                        return;
                    }
                }
            }
        } else if (resultCode == ResultCodes.REQ_CODE_SERVICE_CHARGE_BTR_RESERVATION_FINISH.getCode()) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof FragmentService) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                    return;
                }
            }
        } else if (resultCode == ResultCodes.REQ_CODE_TUTORIAL_DIGITAL_WALLET.getCode()) {
            startActivitySingleTop(new Intent(this, DigitalWalletActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_NONE);
            return;
//                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
//                    if (fragment instanceof FragmentContents) {
//                        fragment.onActivityResult(requestCode, resultCode, data);
//                        return;
//                    }
//                }
        }

    }

    public ViewPager2 getViewPager() {
        return ui.viewpager;
    }

    private final int[][] TAB_INFO = {
            {R.string.main_word_1, R.drawable.ic_tabbar_home_w},
            {R.string.main_word_2, R.drawable.ic_tabbar_insight_w},
            {R.string.main_word_3, R.drawable.ic_tabbar_service_w},
            {R.string.main_word_5, R.drawable.ic_tabbar_store_w},
            {R.string.main_word_4, R.drawable.ic_tabbar_contents_w}
    };

//    private void setTabView() {
//        new TabLayoutMediator(ui.tabs, ui.viewpager, (tab, position) -> {
//
//        }).attach();
//
//        for (int i = 0; i < pageNum; i++) {
//            final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            final ItemTabBinding itemTabBinding = DataBindingUtil.inflate(inflater, R.layout.item_tab, null, false);
//            final View view = itemTabBinding.getRoot();
//            itemTabBinding.tvTab.setText(TAB_INFO[i][0]);
//            itemTabBinding.ivTab.setImageResource(TAB_INFO[i][1]);
//            ui.tabs.getTabAt(i).setCustomView(view);
//        }
//    }

    /**
     * @param dayCd ??? : 1 , ??? : 2
     */
    public void setTab(int dayCd) {
        ui.tabs.setBackgroundResource(dayCd == 1 ? R.drawable.bg_ffffff_topline_f8f8f8 : R.drawable.bg_000000_topline_262626);
        new Handler().postDelayed(() -> {
            if(ui.tabs.getTabCount()>0) ui.tabs.removeAllTabs();
            new TabLayoutMediator(ui.tabs, ui.viewpager, (tab, position) -> {

            }).attach();

            for (int i = 0; i < pageNum; i++) {
                final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = null;
                if (dayCd == 1) {
                    final ItemTabDayBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_tab_day, null, false);

                    view = binding.getRoot();
                    binding.tvTab.setText(TAB_INFO[i][0]);
                    binding.ivTab.setImageResource(TAB_INFO[i][1]);
//                setPaddingTab(binding.lTab, i);
                } else {
                    final ItemTabBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_tab, null, false);

                    view = binding.getRoot();
                    binding.tvTab.setText(TAB_INFO[i][0]);
                    binding.ivTab.setImageResource(TAB_INFO[i][1]);
//                setPaddingTab(binding.lTab, i);
                }

                ui.tabs.getTabAt(i).setCustomView(view);
            }
        },500);
    }

    private void setPaddingTab(ConstraintLayout lTab, int pos) {
        if(pos==0||pos==1) {
            lTab.setPadding((int) DeviceUtil.dip2Pixel(this, 10), 0, 0, 0);
        }else if(pos==pageNum-1||pos==pageNum-2) {
            lTab.setPadding(0, 0, (int) DeviceUtil.dip2Pixel(this, 10), 0);
        }
    }

    public void setGNB(String menu, int isVisibility) {
        setGNB(menu, isVisibility, false, false);
    }

    public void setGNB(String menu, int isVisibility, boolean isStore, boolean isBgWhite) {
        try {
            ui.lGnb.setMenu(menu);
            ui.lGnb.setIsAlarm(isNewAlarm());
            ui.lGnb.setCustGbCd(lgnViewModel.getUserInfoFromDB().getCustGbCd());
            ui.lGnb.lWhole.setVisibility(isVisibility);
            ui.lGnb.setIsStore(isStore);
            ui.lGnb.setIsBgWhite(isBgWhite);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setGnB() {
        try {
            ui.lGnb.setIsAlarm(isNewAlarm());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean isNewAlarm() {
        String newNotiCnt = "";
        int newNotiCnt_i = 0;
        try {
            newNotiCnt = cmnViewModel.getRES_NOT_0003().getValue().data.getNewNotiCnt();
            newNotiCnt_i = Integer.parseInt(newNotiCnt);
        } catch (Exception e) {
            newNotiCnt_i = 0;
        }
        return newNotiCnt_i > 0;

    }


    private long backKeyPressedTime = 0;

    @Override
    public void onBackPressed() {
        if(isFragmentBack()){
            //do nothing
        }else if(!isHome()) {
            ui.viewpager.setCurrentItem(MainPageDiv.HOME.ordinal(), true);
        }else if (System.currentTimeMillis() > backKeyPressedTime + (2 * 1000)) {
            backKeyPressedTime = System.currentTimeMillis();
            SnackBarUtil.show(this, getString(R.string.comm_msg_1));
        } else {
            super.onBackPressed();
        }
    }

    private boolean isFragmentBack() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (getViewPager().getCurrentItem() == MainPageDiv.STORE.ordinal() && fragment instanceof FragmentStore) {
                FragmentStore fragmentStore = (FragmentStore) fragment;
                MyWebViewFrament webFragment = (MyWebViewFrament) fragment.getChildFragmentManager().findFragmentById(R.id.fm_holder);

                if (base.lProgress.lProgress.getVisibility() == View.VISIBLE) {
                    showProgressDialog(false);

                    return true;
                }

                if (fragmentStore.isDlp.equals("YES")) {
                    webFragment.loadUrl("javascript:bwcAppClose();");
                } else {
                    if (!TextUtils.isEmpty(fragmentStore.fn)) {
                        if (webFragment.openWindows.size() > 0) {
                            webFragment.openWindows.get(0).loadUrl("javascript:" + fragmentStore.fn);
                        } else {
                            webFragment.loadUrl("javascript:" + fragmentStore.fn);
                        }
                    } else {
                        if (webFragment!=null&&webFragment.canGoBack()) {
                            webFragment.goBack();
                            return true;
                        }
                    }
                }
            } else if (getViewPager().getCurrentItem() == MainPageDiv.HOME.ordinal() && fragment instanceof FragmentHome) {
                if (((FragmentHome) fragment).isBottom()) {
                    initFragmentHome(1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isHome() {
        return getViewPager().getCurrentItem() == MainPageDiv.HOME.ordinal();
    }

    public boolean moveToMainTab(String lnkUri) {
        Uri uri = null;
        String id = "";
        try {
            uri = Uri.parse(lnkUri);
            id = uri.getQueryParameter(KeyNames.KEY_NAME_URI_PARSER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (TextUtils.isEmpty(id))
                id = "";
        }

        if (!TextUtils.isEmpty(id)) {

            switch (APPIAInfo.findCode(id)) {
                case GM01:
                case GM02:
                case GM03:
                case GM04:
                    //home
                    ui.viewpager.setCurrentItem(MainPageDiv.HOME.ordinal(), true);
                    return true;
                case TM01:
                case TM02:
                case TM03:
                case TM04:
                    //insight
                    ui.viewpager.setCurrentItem(MainPageDiv.INSIGHT.ordinal(), true);
                    return true;
                case SM01:
                case SM02:
                case SM03:
                case SM04:
                    //service
                    ui.viewpager.setCurrentItem(MainPageDiv.SERVICE.ordinal(), true);
                    return true;
                case RM01:
                case RM02:
                    //stroe
                    ui.viewpager.setCurrentItem(MainPageDiv.STORE.ordinal(), true);
                    return true;
                case CM01:
                case CM02:
                case CM03:
                case CM04:
                    ui.viewpager.setCurrentItem(MainPageDiv.CONTENTS.ordinal(), true);
                    //contents
                    return true;
                default:
                    break;
            }
        }
        return false;
    }

    public void initFragmentHome(int currentPosition) {
        try {
            if (currentPosition > 0) {
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if (fragment instanceof FragmentHome) {
                        //??????????????? HOME??? ???????????? ??????
                        ((FragmentHome) fragment).moveToFirstPage();
                        for (Fragment fragmentChild : fragment.getChildFragmentManager().getFragments()) {
                            if (fragmentChild instanceof FragmentHome2) {
                                //???????????????2 ?????? ?????????????????? ????????? ????????? ?????????
                                ((FragmentHome2) fragmentChild).initScrollPosition();
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    public void initFragmentContents(int position) {
        try {
            if(position==MainPageDiv.CONTENTS.ordinal()) {
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if (fragment instanceof FragmentContents) {
                        //??????????????? HOME??? ???????????? ??????
                        ((FragmentContents) fragment).initPage();
                        break;
                    }
                }
            }
        } catch (Exception e) {

        }
    }


    public void movePage(int page) {
        try {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof FragmentHome) {
                    ((FragmentHome) fragment).movePage(page);
                    return;
                }
            }
        } catch (Exception e) {

        }
    }

    public boolean isFirstPage(){
        try {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof FragmentHome) {
                    return ((FragmentHome) fragment).isFirstPage()&&isHome();
                }
            }
        } catch (Exception e) {

        }
        return false;
    }
}
