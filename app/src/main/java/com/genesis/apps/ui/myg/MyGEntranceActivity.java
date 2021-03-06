package com.genesis.apps.ui.myg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.LGN_0001;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.UserVO;
import com.genesis.apps.comm.net.ga.GA;
import com.genesis.apps.comm.util.PackageUtil;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.databinding.ActivityMygEntranceBinding;
import com.genesis.apps.room.ResultCallback;
import com.genesis.apps.ui.common.activity.LoginActivity;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.view.listener.ViewPressEffectHelper;
import com.genesis.apps.ui.main.ServiceJoinActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyGEntranceActivity extends SubActivity<ActivityMygEntranceBinding> {
    @Inject
    public GA ga;
    private LGNViewModel lgnViewModel;

    private String tokenCode;
    private String authUuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myg_entrance);
        getDataFromIntent();
        setViewModel();
        setObserver();
        initView();
    }

    private void initView() {
        ViewPressEffectHelper.attach(ui.btnFind);
    }

    @Override
    public void onClickCommon(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                moveToLogin();
                break;
            case R.id.btn_join:
                moveToJoin();
                break;
            case R.id.btn_find:
                moveToFind();
                break;
        }
    }

    @Override
    public void setViewModel() {
        ui.setActivity(this);
        lgnViewModel = new ViewModelProvider(this).get(LGNViewModel.class);
    }

    @Override
    public void setObserver() {
//        lgnViewModel.getRES_LGN_0007().observe(this, result -> {
//            switch (result.status){
//                case LOADING:
//
//                    break;
//                case SUCCESS:
//                    if(result.data!=null&&result.data.getTopicList()!=null){
//                        subscribeTopic(lgnViewModel, result.data.getTopicList());
//                    }
//                    restart();
//                    break;
//                default:
//                    restart();
//                    break;
//            }
//        });


        lgnViewModel.getRES_LGN_0001().observe(this, result -> {

            switch (result.status){
                case LOADING:
                    showProgressDialog(true);
                    break;

                case SUCCESS:
                    if(result.data!=null
                            &&(TextUtils.isEmpty(result.data.getCustGbCd())||result.data.getCustGbCd().equalsIgnoreCase("0000"))
//                            &&(result.data.getRtCd().equalsIgnoreCase("2002")||result.data.getRtCd().equalsIgnoreCase("2001"))){
                            &&(result.data.getRtCd().equalsIgnoreCase("0000"))){
                        showProgressDialog(false);
                        startActivitySingleTop(new Intent(this, ServiceJoinActivity.class).putExtra(VariableType.KEY_NAME_LOGIN_TOKEN_CODE, tokenCode).putExtra(VariableType.KEY_NAME_LOGIN_AUTH_UUID, authUuid), RequestCodes.REQ_CODE_JOIN_SERVICE.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                        break;
                    }else if(result.data!=null
                            &&result.data.getRtCd().equalsIgnoreCase("0000")
                            &&!result.data.getCustGbCd().equalsIgnoreCase("0000")){

                        lgnViewModel.setLGN0001ToDB(result.data, new ResultCallback() {
                            @Override
                            public void onSuccess(Object retv) {
                                if (((Boolean) retv)) {
                                    restart();
                                } else {
                                    ga.clearLoginInfo();
                                    SnackBarUtil.show(MyGEntranceActivity.this, "???????????? ???????????? ???????????????.\n?????? ??? ?????? ????????? ?????????.");
                                }
                                showProgressDialog(false);
                            }
                            @Override
                            public void onError(Object e) {
                                showProgressDialog(false);
                                ga.clearLoginInfo();
                                SnackBarUtil.show(MyGEntranceActivity.this, "???????????? ???????????? ???????????????.\n?????? ??? ?????? ????????? ?????????.\nErrCode:2");
                            }
                        }, true);

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
                        ga.clearLoginInfo();
                        SnackBarUtil.show(this, serverMsg);
                        showProgressDialog(false);
                    }
                    break;
            }

        });

    }

    @Override
    public void getDataFromIntent() {

    }

    public void moveToLogin(){
        startActivitySingleTop(new Intent(this, LoginActivity.class)
                        .putExtra(KeyNames.KEY_NAME_URL,ga.getLoginUrl())
                        .putExtra(KeyNames.KEY_ANME_CCSP_TYPE, LoginActivity.TYPE_LOGIN)
                , RequestCodes.REQ_CODE_LOGIN.getCode()
                , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
    }
    public void moveToJoin(){
        startActivitySingleTop(new Intent(this, LoginActivity.class)
                        .putExtra(KeyNames.KEY_NAME_URL,ga.getEnrollUrl())
                        .putExtra(KeyNames.KEY_ANME_CCSP_TYPE, LoginActivity.TYPE_JOIN)
                , RequestCodes.REQ_CODE_JOIN.getCode()
                , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
    }
    public void moveToFind(){
        startActivitySingleTop(new Intent(this, LoginActivity.class)
                        .putExtra(KeyNames.KEY_NAME_URL,ga.getFindUrl())
                        .putExtra(KeyNames.KEY_ANME_CCSP_TYPE, LoginActivity.TYPE_FIND)
                , RequestCodes.REQ_CODE_JOIN.getCode()
                , VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK){
            if((requestCode==RequestCodes.REQ_CODE_LOGIN.getCode()||requestCode==RequestCodes.REQ_CODE_JOIN.getCode())&&data!=null){
                try {
                    tokenCode = data.getStringExtra(VariableType.KEY_NAME_LOGIN_TOKEN_CODE);
                    authUuid = data.getStringExtra(VariableType.KEY_NAME_LOGIN_AUTH_UUID);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(!TextUtils.isEmpty(tokenCode)) lgnViewModel.reqLGN0001(new LGN_0001.Request(APPIAInfo.INT01.getId(), PackageUtil.getApplicationVersionName(MyGEntranceActivity.this, getPackageName()),""));
                }
            }else if(requestCode==RequestCodes.REQ_CODE_JOIN_SERVICE.getCode()){
                //????????? ?????? ?????? ???
                lgnViewModel.reqLGN0001(new LGN_0001.Request(APPIAInfo.INT01.getId(), PackageUtil.getApplicationVersionName(MyGEntranceActivity.this, getPackageName()),""));
            }
        }else{
            UserVO userVO = null;
            try{
                userVO = lgnViewModel.getUserInfoFromDB();
            }catch (Exception e){
                userVO = null;
            }
            if(userVO==null)
                ga.clearLoginInfo();
        }
    }
}
