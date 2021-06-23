package com.genesis.apps.ui.myg;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.MYP_1013;
import com.genesis.apps.comm.model.api.gra.MYP_2006;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.viewmodel.MYPViewModel;
import com.genesis.apps.databinding.ActivityMygCouponBinding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.myg.view.ExtncPlanPontAdapter;
import com.genesis.apps.ui.myg.view.HswCouponAdapter;

/**
 * @author hjpark
 * @brief 혜택/쿠폰
 */
public class MyGCouponActivity extends SubActivity<ActivityMygCouponBinding> {

    private MYPViewModel mypViewModel;
    private HswCouponAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myg_coupon);
        getDataFromIntent();
        setViewModel();
        setObserver();
        initView();
        mypViewModel.reqMYP1013(new MYP_1013.Request(APPIAInfo.MG_BF01.getId()));
    }

    private void initView() {
        adapter = new HswCouponAdapter();
        ui.rv.setLayoutManager(new LinearLayoutManager(this));
        ui.rv.setHasFixedSize(true);
        ui.rv.setAdapter(adapter);
    }

    @Override
    public void onClickCommon(View v) {
    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        mypViewModel = new ViewModelProvider(this).get(MYPViewModel.class);
    }

    @Override
    public void setObserver() {
        mypViewModel.getRES_MYP_1013().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    return;
                case SUCCESS:
                    showProgressDialog(false);
                    //추가할 아이템이 있을 경우만 adaper 갱신
                    if (result.data != null && result.data.getCpnList() != null) {
                        adapter.setRows(result.data.getCpnList());
                    }
                    adapter.notifyDataSetChanged();
                    setEmptyView(adapter.getItemCount());
                    break;
                default:
                    showProgressDialog(false);
                    String serverMsg="";
                    try {
                        serverMsg = result.data.getRtMsg();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        if(TextUtils.isEmpty(serverMsg)) serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                        SnackBarUtil.show(this, serverMsg);
                        setEmptyView(0);
                    }
                    break;
            }

        });
    }

    @Override
    public void getDataFromIntent() {

    }

    private void setEmptyView(int itemCount){
        if(itemCount>0){
            ui.rv.setVisibility(View.VISIBLE);
            ui.tvEmpty.setText("");
        }else{
            ui.rv.setVisibility(View.GONE);
            ui.tvEmpty.setText(R.string.mg_bf01_02_2);
        }
    }
}
