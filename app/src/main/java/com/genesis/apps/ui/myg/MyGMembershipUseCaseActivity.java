package com.genesis.apps.ui.myg;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.genesis.apps.comm.model.vo.TermVO;
import com.genesis.apps.comm.viewmodel.MYPViewModel;
import com.genesis.apps.ui.common.activity.TermActivity;

public class MyGMembershipUseCaseActivity extends TermActivity {
    private MYPViewModel mypViewModel;
    private final String URL = "https://www.genesis.com/content/genesis/kr/ko/genesis-membership/life-service.html"; //사용처 안내 하드코딩 URL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataFromIntent();
        setViewModel();
        setObserver();
        loadTerms(URL);
        ui.lTitle.lTitleBar.setVisibility(View.GONE);
//        mypViewModel.reqMYP2003(new MYP_2003.Request(APPIAInfo.MG_MEMBER02.getId()));
    }

    @Override
    public void getDataFromIntent() {
    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        mypViewModel= new ViewModelProvider(this).get(MYPViewModel.class);
    }

    @Override
    public void setObserver() {
        mypViewModel.getRES_MYP_2003().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    if (result.data != null&&!TextUtils.isEmpty(result.data.getCont())) {
                        loadTerms(new TermVO("","","",result.data.getCont(),""));

                        if(!TextUtils.isEmpty(result.data.getTtl())){
                            ui.lTitle.setValue(result.data.getTtl());
                        }

                        showProgressDialog(false);
                        break;
                    }
                default:
                    showProgressDialog(false);
                    break;
            }
        });
    }
}