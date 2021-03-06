package com.genesis.apps.ui.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.AddressVO;
import com.genesis.apps.comm.util.SoftKeyboardUtil;
import com.genesis.apps.databinding.ActivityBaseBinding;
import com.genesis.apps.ui.common.dialog.middle.MiddleDialog;
import com.genesis.apps.ui.common.fragment.SubFragment;
import com.genesis.apps.ui.common.view.listener.OnSingleClickListener;
import com.genesis.apps.ui.intro.IntroActivity;
import com.genesis.apps.ui.intro.PermissionsActivity;
import com.genesis.apps.ui.main.TutorialActivity;
import com.genesis.apps.ui.main.service.CarWashFindSonaxBranchFragment;
import com.genesis.apps.ui.main.store.StoreWebActivity;
import com.genesis.apps.ui.myg.MyGEntranceActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public abstract class SubActivity<T extends ViewDataBinding> extends BaseActivity {

    public ActivityBaseBinding base;
    public T ui;
    public OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View v) {
            onClickCommon(v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (base == null) base = (ActivityBaseBinding) inflate(R.layout.activity_base);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setFirebaseAnalyticsLog();
        if (isTargetPermissionCheck() &&
                (!isPermissions() || deviceDTO == null || TextUtils.isEmpty(deviceDTO.getDeviceId()))) {
            //????????? ?????? ???????????? ??? ??? ????????????
            //????????? ???????????? ????????? (os ???????????? ?????? ????????? ?????? ??????)
            //????????? ?????? ?????? ?????? ????????? ??????????????? ???????????? ??? ??????????????? ?????? ????????? ???????????? singleton ???????????? ????????? ?????? (???????????? ???????????? ???????????? ???)
            //??? ?????????
            restart();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("permission test", "permission test:activity killed:" + this.getClass().getSimpleName());
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getColor(color));
            if (color == R.color.x_000000 || color == R.color.x_05141f) {
                window.getDecorView().setSystemUiVisibility(0);
            } else {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    @Override
    public void setContentView(int layoutResId) {
        ui = inflate(layoutResId);
        if (base == null) {
            base = (ActivityBaseBinding) inflate(R.layout.activity_base);
        }
        base.lContents.addView(ui.getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        super.setContentView(base.getRoot());

        try {
            if (findViewById(R.id.back) != null) {
                findViewById(R.id.back).setOnClickListener(new OnSingleClickListener() {
                    @Override
                    public void onSingleClick(View v) {
                        onBackButton();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T extends ViewDataBinding> T inflate(int layoutResId) {
        return DataBindingUtil.inflate(getLayoutInflater(), layoutResId, null, false);
    }

    public final void showProgressDialog(final boolean show) {
        Log.v("test", "test show:" + show);
        try {
            if (base.lProgress != null) {
                runOnUiThread(() -> {
                    base.lProgress.ivProgress.setImageResource(R.drawable.ic_loading_circle);
                    base.lProgress.tvProgress.setVisibility(View.GONE);
                    base.lProgress.lProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                    AnimationDrawable animationDrawable = (AnimationDrawable) base.lProgress.ivProgress.getDrawable();
                    if (show) {
                        if (!animationDrawable.isRunning()) animationDrawable.start();
                    } else {
                        animationDrawable.stop();
                    }
                });
            }
//                runOnUiThread(() -> base.lProgress.lProgress.setVisibility(show ? View.VISIBLE : View.GONE));
        } catch (Exception e) {

        }
    }


    public static final int PROGRESS_TYPE_NORMAL = 0;
    public static final int PROGRESS_TYPE_INSIGHT = 1;
    public static final int PROGRESS_TYPE_LOCATION = 2;

    public final void showProgressDialog(final boolean show, int type) {
        Log.v("test", "test show:" + show);
        try {
            if (base.lProgress != null) {
                runOnUiThread(() -> {
                    switch (type) {
                        case PROGRESS_TYPE_INSIGHT:
                            base.lProgress.tvProgress.setVisibility(View.VISIBLE);
                            base.lProgress.tvProgress.setText(R.string.dialog_msg_2);
                            base.lProgress.ivProgress.setImageResource(R.drawable.loading_insight);
                            break;
                        case PROGRESS_TYPE_LOCATION:
                            base.lProgress.tvProgress.setVisibility(View.VISIBLE);
                            base.lProgress.tvProgress.setText(R.string.dialog_msg_3);
                            base.lProgress.ivProgress.setImageResource(R.drawable.loading_location);
                            break;
                        case PROGRESS_TYPE_NORMAL:
                        default:
                            base.lProgress.tvProgress.setVisibility(View.GONE);
                            base.lProgress.ivProgress.setImageResource(R.drawable.ic_loading_circle);
                            break;
                    }
                    base.lProgress.lProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                    AnimationDrawable animationDrawable = (AnimationDrawable) base.lProgress.ivProgress.getDrawable();
                    if (show) {
                        if (!animationDrawable.isRunning()) animationDrawable.start();
                    } else {
                        animationDrawable.stop();
                    }
                });
            }
//                runOnUiThread(() -> base.lProgress.lProgress.setVisibility(show ? View.VISIBLE : View.GONE));
        } catch (Exception e) {

        }
    }

    public void onBackButton() {
        clearKeyPad();
        List<SubFragment> fragments = getFragments();
        if (fragments != null && fragments.size() > 0 && fragments.get(0) instanceof CarWashFindSonaxBranchFragment) {
            hideFragment(fragments.get(0));
        } else {
            finish();
            closeTransition();
        }
    }

    public abstract void onClickCommon(View v);

    public abstract void setViewModel();

    public abstract void setObserver();

    public abstract void getDataFromIntent();

    public void setResizeScreen() {
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);


//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.flags &= ~WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//        getWindow().setAttributes(lp);

    }


    public <T extends SubFragment> T getFragment(int id) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(id);
        return (T) fragment;
    }

    public <T extends SubFragment> List<T> getFragments() {
        final List<T> fragmentList = new ArrayList<>();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof SubFragment) {
                fragmentList.add((T) fragment);
            }
        }
        return fragmentList;
    }

    public <T extends SubFragment> boolean isVisible(T subFragment) {
        for (Fragment fragment : getFragments()) {
            if (fragment.isVisible() && fragment.equals(subFragment)) {
                return true;
            }
        }
        return false;
    }

    public <T extends SubFragment> T getVisibleFragment() {
        Fragment visibleFragment = null;
        for (Fragment fragment : getFragments()) {
            if (fragment.isVisible()) {
                visibleFragment = fragment;
                break;
            }
        }
        return (T) visibleFragment;
    }

    public <T extends SubFragment> T getBackFragment(T visibleFragment) {
        final List<T> fragmentList = getFragments();
        final int index = fragmentList.indexOf(visibleFragment);
        if (index > 0) {
            return fragmentList.get(index - 1);
        }
        return null;
    }

    public <T extends SubFragment> void showFragment(T fragment) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_stay);
        transaction.replace(R.id.l_fragment, fragment);
        transaction.commitAllowingStateLoss();
    }

    public <T extends SubFragment> void showFragment(T fragment, Bundle bundle) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_stay);
        if (bundle != null) fragment.setArguments(bundle);
        transaction.replace(R.id.l_fragment, fragment);
        transaction.commitAllowingStateLoss();
    }

    public <T extends SubFragment> void hideFragment(T fragment) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(0, R.anim.fragment_exit_toright);
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }

    public void clearFragmentStack() {
        try {
            int backStackEntry = getSupportFragmentManager().getBackStackEntryCount();
            if (backStackEntry > 0) {
                for (int i = 0; i < backStackEntry; i++) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
            }
            if (getSupportFragmentManager().getFragments().size() > 0) {
                for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
                    Fragment fragment = getSupportFragmentManager().getFragments().get(i);
                    if (fragment != null) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param addressVO
     * @return 0 : ?????? 1 : ????????????
     * @brief ?????? ???????????? ??????????????? ?????? ???????????? ????????? ??????
     * (????????? ????????? ????????????)
     */
    public static String[] getAddress(AddressVO addressVO) {
        String[] address = new String[2];

        try {
            if (!TextUtils.isEmpty(addressVO.getAddrRoad())) {
                //???????????????
                address[0] = addressVO.getAddrRoad();
                //???????????? ???
                address[1] = (TextUtils.isEmpty(addressVO.getTitle()) ? "" : addressVO.getTitle()) + (TextUtils.isEmpty(addressVO.getCname()) ? "" : " " + addressVO.getCname());
            } else {
                //????????????
                address[0] = addressVO.getAddr() + (TextUtils.isEmpty(addressVO.getTitle()) ? "" : " " + addressVO.getTitle());
                //????????????
                address[1] = (TextUtils.isEmpty(addressVO.getCname()) ? "" : addressVO.getCname());
            }
        } catch (Exception e) {
            address[0] = "";
            address[1] = "";
            e.printStackTrace();
        }

        return address;
    }

    public boolean loginChk(String custGbCd) {
        if (!TextUtils.isEmpty(custGbCd) && !custGbCd.equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_0000)) {
            return true;
        } else {
            return false;
        }
    }

    public void loginChk(String url, String custGbCd, String mCustInfo) {
        if (!TextUtils.isEmpty(custGbCd) && !custGbCd.equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_0000)) {
            startActivitySingleTop(new Intent(this, StoreWebActivity.class)
                    .putExtra(KeyNames.KEY_NAME_URL, url)
                    .putExtra(KeyNames.KEY_NAME_CUST_INFO, mCustInfo), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
        } else {
            MiddleDialog.dialogLogin(this, () -> {
                startActivitySingleTop(new Intent(this, MyGEntranceActivity.class), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
            }, () -> {

            });
        }
    }

    public void setFirebaseAnalyticsLog() {
        Class classNm = this.getClass();
        APPIAInfo appiaInfo = APPIAInfo.findCode(classNm);
        if (appiaInfo != null && appiaInfo != APPIAInfo.DEFAULT) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, appiaInfo.getId());
//            bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, classNm.getSimpleName());
            FirebaseAnalytics.getInstance(this).logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
        }

//        if(appiaInfo!=null&&appiaInfo!=APPIAInfo.DEFAULT) {
//            Bundle bundle = new Bundle();
//            bundle.putString("menu_id", appiaInfo.getId());
//            bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, appiaInfo.getId());
//            bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, classNm.getSimpleName());
//            FirebaseAnalytics.getInstance(this).logEvent("app_menu_id", bundle);
//        }


//        if(appiaInfo!=null&&appiaInfo!=APPIAInfo.DEFAULT) {
////            Bundle bundle = new Bundle();
////            bundle.putString("menu_id", appiaInfo.getId());
////            FirebaseAnalytics.getInstance(this).logEvent("app_menu_id", bundle);
//            FirebaseAnalytics.getInstance(this).setCurrentScreen(this, appiaInfo.getId(), classNm.getSimpleName());
//        }
    }

    /**
     * @return
     * @brief onResume?????? ?????? ?????? ?????? ?????????????????? ??????
     */
    private boolean isTargetPermissionCheck() {
        return this.getClass() != IntroActivity.class && this.getClass() != PermissionsActivity.class;
    }

    public void clearKeyPad(){
        try {
            SoftKeyboardUtil.hideKeyboard(this, getWindow().getDecorView().getWindowToken());
        }catch (Exception e){

        }
    }

    public boolean hadTutorial(boolean isEvOnlyOV, boolean hadTutorial, int type){
        if(!hadTutorial&&isEvOnlyOV){
            startActivitySingleTop(new Intent(this, TutorialActivity.class)
                    .putExtra(KeyNames.KEY_NAME_TUTORIAL_TYPE, type), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_VERTICAL_SLIDE);
        }
        return !hadTutorial&&isEvOnlyOV;
    }

}
