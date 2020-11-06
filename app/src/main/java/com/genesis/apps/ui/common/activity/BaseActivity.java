package com.genesis.apps.ui.common.activity;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.excutor.ExecutorService;
import com.genesis.apps.fcm.PushCode;
import com.genesis.apps.ui.intro.IntroActivity;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

import static com.genesis.apps.comm.model.constants.KeyNames.NOTIFICATION_ID;
import static com.genesis.apps.comm.model.constants.KeyNames.PUSH_CODE;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {

    @Inject
    ExecutorService executorService;

    //About PUSH
    public PushCode pushCode;
    public int notificationId;
    public boolean isForeground=false;
    public Intent intent = null;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutDownExcutor();
    }


    public void startActivitySingleTop(Intent intent, int flag, int animationFlag) {

        // intent에 Intent.FLAG_ACTIVITY_FORWARD_RESULT 가 추가됬을 경우 반드시 flag를 0으로 사용해야함

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

    public Intent moveToPush(Class className){
        intent = new Intent(this, className).putExtra(PUSH_CODE, pushCode).putExtra(NOTIFICATION_ID, notificationId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }


    public boolean isPushData() {
        boolean isPush=false;
        try {
            pushCode = (PushCode) getIntent().getSerializableExtra(PUSH_CODE);
            notificationId = getIntent().getIntExtra(NOTIFICATION_ID, 0);
            if(pushCode!=null&&pushCode!=PushCode.CAT_DEFAULT) isPush=true;
        }catch (Exception e){
            pushCode = PushCode.CAT_DEFAULT;
        }
        return isPush;
    }

    public void checkPushCode() {
        if(isPushData()) {
            switch (pushCode) {
                case CAT_50:
                    //TODO EXCUTE ACTIVITY
                    break;
                case CAT_G1:
                    //TODO EXCUTE ACTIVITY
                    break;
                case CAT_40:
                case CAT_41:
                case CAT_42:
                case CAT_43:
                    //TODO EXCUTE ACTIVITY
                    break;
                default:
                    //TODO EXCUTE ACTIVITY
                    break;
            }

            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
            this.getIntent().removeExtra(PUSH_CODE);
            this.getIntent().removeExtra(NOTIFICATION_ID);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).cancel(notificationId);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RequestCodes.REQ_CODE_ACTIVITY.getCode()&&
                (resultCode==ResultCodes.RES_CODE_NETWORK.getCode()||
                        resultCode==ResultCodes.REQ_CODE_EMPTY_INTENT.getCode()||
                        resultCode==ResultCodes.REQ_CODE_NORMAL.getCode())){

            String errorMsg="";
            try {
                errorMsg = data.getStringExtra("msg");
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                if(TextUtils.isEmpty(errorMsg)){
                    if(requestCode==ResultCodes.RES_CODE_NETWORK.getCode()){
                        errorMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                    }else{
                        errorMsg = "오류가 발생했습니다.\n앱 재실행 후 다시 시도해 주세요. \nCODE:1";
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
            //팝업형 액티비티인 경우
        }else{
            //스택형 액티비티인 경우
            overridePendingTransition(0, R.anim.fragment_exit_toright);
        }
    }


    /**
     * 앱 재시작
     */
    public void restart() {
        final Intent intent = new Intent(this, IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        startActivity(intent);
        finish();
    }

}
