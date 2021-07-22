package com.genesis.apps.ui.main;

import android.os.Bundle;
import android.view.View;

import com.genesis.apps.R;
import com.genesis.apps.databinding.ActivityChargeCardManageBinding;
import com.genesis.apps.ui.common.activity.SubActivity;

/**
 * 충전 카드 관리 Activity
 */
public class ChargeCardManageActivity extends SubActivity<ActivityChargeCardManageBinding> {

    /****************************************************************************************************
     * Override Method - LifeCycle
     ****************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_card_manage);
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
