package com.genesis.apps.ui.common.activity;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.DeviceDTO;
import com.genesis.apps.comm.model.vo.TopicVO;
import com.genesis.apps.comm.util.PackageUtil;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.comm.util.excutor.ExecutorService;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.fcm.PushVO;
import com.genesis.apps.ui.intro.IntroActivity;
import com.genesis.apps.ui.intro.PermissionsActivity;
import com.genesis.apps.ui.main.AlarmCenterActivity;
import com.genesis.apps.ui.main.service.ServiceReviewActivity;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

import static com.genesis.apps.comm.model.constants.KeyNames.DEEP_VO;
import static com.genesis.apps.comm.model.constants.KeyNames.NOTIFICATION_ID;
import static com.genesis.apps.comm.model.constants.KeyNames.PUSH_VO;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {

    @Inject
    public DeviceDTO deviceDTO;

    @Inject
    public ExecutorService executorService;

    //About PUSH
    public PushVO pushVO;
    public int notificationId;
    public boolean isForeground=false;
    public Intent intent = null;

    //About DEEP
    public String deep;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutDownExcutor();
    }


    public void startActivitySingleTop(Intent intent, int flag, int animationFlag) {

        // intent??? Intent.FLAG_ACTIVITY_FORWARD_RESULT ??? ???????????? ?????? ????????? flag??? 0?????? ???????????????

        if(intent.getFlags()==Intent.FLAG_ACTIVITY_FORWARD_RESULT)
            flag=0;

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if(flag==0)
            startActivity(intent);
        else
            startActivityForResult(intent, flag);

        switch (animationFlag){
            case VariableType.ACTIVITY_TRANSITION_ANIMATION_ZOON:
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;
            case VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE:
                overridePendingTransition(R.anim.fragment_enter, R.anim.fragment_stay);
                break;
            case VariableType.ACTIVITY_TRANSITION_ANIMATION_VERTICAL_SLIDE:
                overridePendingTransition(R.anim.slide_up, R.anim.fragment_stay);
                break;
            case VariableType.ACTIVITY_TRANSITION_ANIMATION_NONE:
            default:
                break;


        }


    }

    public void alert(String title, String msg, DialogInterface.OnClickListener okListener) {
        if(TextUtils.isEmpty(title)) title = getString(R.string.comm_word_3);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.comm_word_1, okListener);
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    public Intent getPushIntent(Class className){
        intent = new Intent(this, className).putExtra(PUSH_VO, pushVO).putExtra(NOTIFICATION_ID, notificationId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }


    public boolean isPushData() {
        boolean isPush=false;
        try {
            pushVO = (PushVO) getIntent().getSerializableExtra(PUSH_VO);
            notificationId = getIntent().getIntExtra(NOTIFICATION_ID, 0);
            if(pushVO!=null) isPush=true;
        }catch (Exception e){

        }
        return isPush;
    }

    public Intent getDeepIntent(Class className){
        intent = new Intent(this, className).putExtra(DEEP_VO, deep);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }

    public boolean isDeepData() {
        boolean isDeep=false;
        try {
            deep = getIntent().getDataString();
            if(TextUtils.isEmpty(deep)){
                deep = getIntent().getStringExtra(DEEP_VO);
            }
            if(!TextUtils.isEmpty(deep)) isDeep=true;
        }catch (Exception e){

        }
        return isDeep;
    }

    public void checkDeepCode() {
        if (isDeepData()) {
            String url="";
            try {
                if(!TextUtils.isEmpty(deep)) {
                    url = deep;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(url))
                url = "";

            moveToNativePage(url, false);

//            //????????? ????????? ????????? ?????? ?????? ????????? ?????? ??? ????????? ?????? ?????? ???????????? ??????.
//            if(TextUtils.isEmpty(body)&&!TextUtils.isEmpty(url)){
//                moveToPage(url, pushVO.getData().getMsgLnkCd(),false);
//            }else{
//                //??? ????????? ??????????????? ??????
//                //????????? ???????????? ??????
//                moveToNativePage(url, true);
//            }

            this.getIntent().removeExtra(DEEP_VO);
        }
    }


    public void checkPushCode() {
        if (isPushData()) {
            String url="";
            String body="";
            try {
                if(pushVO!=null&&pushVO.getData()!=null) {
                    url = !TextUtils.isEmpty(pushVO.getData().getMsgLnkUri()) ? pushVO.getData().getMsgLnkUri() : pushVO.getData().getDtlLnkUri();
                    body = StringUtil.isValidString(pushVO.getData().getBody());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(url))
                url = "";

            //????????? ????????? ????????? ?????? ?????? ????????? ?????? ??? ????????? ?????? ?????? ???????????? ??????.
            if(TextUtils.isEmpty(body)&&!TextUtils.isEmpty(url)){
                moveToPage(url, pushVO.getData().getMsgLnkCd(),false);
            }else{
                //??? ????????? ??????????????? ??????
                //????????? ???????????? ??????
                moveToNativePage(url, true);
            }

            this.getIntent().removeExtra(PUSH_VO);
            this.getIntent().removeExtra(NOTIFICATION_ID);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).cancel(notificationId);
        }
    }

    public void moveToPage(String lnkUri, String lnkTypCd, boolean isPush){
        if(!TextUtils.isEmpty(lnkUri) && !TextUtils.isEmpty(lnkTypCd)){
            if(lnkTypCd.equalsIgnoreCase("I")||lnkTypCd.equalsIgnoreCase("IM")){
                if(lnkUri.startsWith(KeyNames.KEY_NAME_INTERNAL_LINK)||lnkUri.startsWith(KeyNames.KEY_NAME_INTERNAL_LINK2)){
                    //??????????????? ??????
                    moveToNativePage(lnkUri, isPush);
                }else if(lnkUri.startsWith("http")){
                    //????????? ??????
                    moveToExternalPage(lnkUri, VariableType.COMMON_MEANS_YES);
                }
            }else if(lnkTypCd.equalsIgnoreCase("O")||lnkTypCd.equalsIgnoreCase("OW")){
                //?????? ??????????????? ??????
                moveToExternalPage(lnkUri, VariableType.COMMON_MEANS_NO);
            }
        }
    }


    public void moveToNativePage(String lnkUri, boolean isPush) {
        Uri uri = null;
        String id = "";
        String PI = "";
        try {
            uri = Uri.parse(lnkUri);
            id = uri.getQueryParameter(KeyNames.KEY_NAME_URI_PARSER_ID);
            PI = uri.getQueryParameter(KeyNames.KEY_NAME_URI_PARSER_PI);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(TextUtils.isEmpty(id))
                id="";
        }

        if (TextUtils.isEmpty(id)&&!isPush)
            return;


        switch (APPIAInfo.findCode(id)) {
            case SM_REVIEW01_P01:
            case SM_REVIEW01_P03:
            case SM_REVIEW01_P04:
                if (!TextUtils.isEmpty(PI)) {
                    startActivitySingleTop(new Intent(this, ServiceReviewActivity.class)
                            .putExtra(KeyNames.KEY_NAME_REVIEW_PI, PI)
                            .putExtra(KeyNames.KEY_NAME_REVIEW_ID, id), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                }else{
                    SnackBarUtil.show(this, "??????????????? ???????????? ????????????.");
                }
                break;
            default:
                if(!isPush) {
                    APPIAInfo appiaInfo = APPIAInfo.findCode(id);
                    if (appiaInfo != null && appiaInfo.getActivity() != null) {
                        startActivitySingleTop(new Intent(this, appiaInfo.getActivity()), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                    }else{
                        SnackBarUtil.show(this, "?????? ID??? ???????????? ????????????.");
                    }
                }else{
                    startActivitySingleTop(new Intent(this, AlarmCenterActivity.class).putExtra(PUSH_VO, pushVO), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                }
                break;
        }
    }

    public void moveToExternalPage(String lnkUri, String wvYn){
        if(!lnkUri.startsWith("http"))
            return;

        if (TextUtils.isEmpty(wvYn) || wvYn.equalsIgnoreCase(VariableType.COMMON_MEANS_YES)) {
            startActivitySingleTop(new Intent(this, GAWebActivity.class).putExtra(KeyNames.KEY_NAME_URL, lnkUri), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(lnkUri));
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RequestCodes.REQ_CODE_ACTIVITY.getCode()&&
                (resultCode==ResultCodes.RES_CODE_NETWORK.getCode()||
                        resultCode==ResultCodes.REQ_CODE_EMPTY_INTENT.getCode()||
                        resultCode==ResultCodes.REQ_CODE_NORMAL.getCode())||
                        resultCode==ResultCodes.REQ_CODE_TS_AUTH.getCode()){

            String errorMsg="";
            try {
                if(data!=null) errorMsg = data.getStringExtra("msg");
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                if(TextUtils.isEmpty(errorMsg)){
                    if(requestCode==ResultCodes.RES_CODE_NETWORK.getCode()){
                        errorMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                    }else{
                        errorMsg = "????????? ??????????????????.\n??? ????????? ??? ?????? ????????? ?????????. \nCODE:1";
                    }
                }
                SnackBarUtil.show(this, errorMsg);
            }
        }
    }

    public void exitPage(String msg, int resultCode){
        setResult(resultCode, new Intent().putExtra("msg",msg));
        finish();
        closeTransition();
    }

    public void exitPage(Intent intent, int resultCode){
        setResult(resultCode, intent);
        finish();
        closeTransition();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeTransition();
    }

    public void closeTransition(){
        if(findViewById(R.id.l_title_bar_popup)!=null){
            //????????? ??????????????? ??????
        }else{
            //????????? ??????????????? ??????
            overridePendingTransition(0, R.anim.fragment_exit_toright);
        }
    }


    /**
     * ??? ?????????
     */
    public void restart() {
        final Intent intent = new Intent(this, IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        startActivity(intent);
        finishAffinity();
    }
    public void exitApp(){
//        moveTaskToBack(true);//???????????? ?????????????????? ??????
//        finishAndRemoveTask();// ???????????? ?????? + ????????? ??????????????? ?????????
//        System.exit(0);//??????????????????
        finishAffinity();
        System.runFinalization();
        System.exit(0);
    }

    public boolean isPermissions() {
        // ????????? ???????????? ?????? ???????????? ??????
        // ?????? ?????? ????????? ?????????.
        // ?????? ????????? ?????? ?????? ?????? ?????? ?????? ???????????? ????????????.
        for (String p : PermissionsActivity.requiredPermissions) {
            boolean check = PackageUtil.checkPermission(this, p);
            for (String permission : PermissionsActivity.permissions) {
                if (permission.equals(p)) {
                    check = true;
                }
            }
            if (!check) {
                // ??????????????? ?????? ??????
                return false;
            }
        }

        return true;
    }




}
