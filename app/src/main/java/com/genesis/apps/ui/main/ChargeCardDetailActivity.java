package com.genesis.apps.ui.main;

import android.os.Bundle;
import android.view.View;

import com.genesis.apps.R;
import com.genesis.apps.databinding.ActivityChargeCardDetailBinding;
import com.genesis.apps.ui.common.activity.SubActivity;

/**
 * 충전 카드 상세 Activity
 */
public class ChargeCardDetailActivity extends SubActivity<ActivityChargeCardDetailBinding> {

    /****************************************************************************************************
     * Override Method - LifeCycle
     ****************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_card_detail);
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
