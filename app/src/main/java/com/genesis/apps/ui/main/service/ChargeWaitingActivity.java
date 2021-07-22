package com.genesis.apps.ui.main.service;

import android.os.Bundle;
import android.view.View;

import com.genesis.apps.R;
import com.genesis.apps.databinding.ActivityChargeWaitingBinding;
import com.genesis.apps.ui.common.activity.SubActivity;

/**
 * 대기표 발급, 상태 Activity
 */
public class ChargeWaitingActivity extends SubActivity<ActivityChargeWaitingBinding> {

    /****************************************************************************************************
     * Override Method - LifeCycle
     ****************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_waiting);
        getDataFromIntent();
        setViewModel();
        setObserver();
    }

    /****************************************************************************************************
     * Override Method - Event
     ****************************************************************************************************/
    @Override
    public void onClickCommon(View v) {

    }

    /****************************************************************************************************
     * Override Method
     ****************************************************************************************************/
    @Override
    public void setViewModel() {

    }

    @Override
    public void setObserver() {

    }

    @Override
    public void getDataFromIntent() {

    }
} // end of class ChargeCardManageActivity
