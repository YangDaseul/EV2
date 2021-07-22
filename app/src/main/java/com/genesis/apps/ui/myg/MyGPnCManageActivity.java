package com.genesis.apps.ui.myg;

import android.os.Bundle;
import android.view.View;

import com.genesis.apps.R;
import com.genesis.apps.databinding.ActivityMygPncManageBinding;
import com.genesis.apps.ui.common.activity.SubActivity;

/**
 * PnC 관리(E-PIT 간편결제) Activity
 */
public class MyGPnCManageActivity extends SubActivity<ActivityMygPncManageBinding> {

    /****************************************************************************************************
     * Override Method - LifeCycle
     ****************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myg_pnc_manage);
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
