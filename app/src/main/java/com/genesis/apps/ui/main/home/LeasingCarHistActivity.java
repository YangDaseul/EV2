package com.genesis.apps.ui.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.GNS_1007;
import com.genesis.apps.comm.model.vo.RentStatusVO;
import com.genesis.apps.comm.util.DeviceUtil;
import com.genesis.apps.comm.util.RecyclerViewDecoration;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.comm.viewmodel.GNSViewModel;
import com.genesis.apps.databinding.ActivityLeasingCarHistBinding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.view.listener.ViewPressEffectHelper;
import com.genesis.apps.ui.main.home.view.LeasingCarHistAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author hjpark
 * @brief 렌트리스 실운행자 내역
 */
public class LeasingCarHistActivity extends SubActivity<ActivityLeasingCarHistBinding> {
    private GNSViewModel gnsViewModel;
    private LeasingCarHistAdapter adapter;
    private boolean isApply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leasing_car_hist);
        getDataFromIntent();
        setViewModel();
        setObserver();
        initView();

        gnsViewModel.reqGNS1007(new GNS_1007.Request(APPIAInfo.GM_CARLST_02.getId()));
    }

    private void initView() {
        adapter = new LeasingCarHistAdapter(onSingleClickListener);
        ui.rv.setLayoutManager(new LinearLayoutManager(this));
        ui.rv.addItemDecoration(new RecyclerViewDecoration((int) DeviceUtil.dip2Pixel(this,4.0f)));
        ui.rv.setHasFixedSize(true);
        ui.rv.setAdapter(adapter);
        ViewPressEffectHelper.attach(ui.btnAdd);
    }

    @Override
    public void onClickCommon(View v) {

        switch (v.getId()){
            case R.id.btn_status:

                RentStatusVO rentStatusVO = (RentStatusVO)v.getTag(R.id.leasing_car_hist);

                if(rentStatusVO!=null)
                    startActivitySingleTop(new Intent(this, LeasingCarHistDetailActivity.class).putExtra(KeyNames.KEY_NAME_DATA_LEASINGCAR, rentStatusVO), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_VERTICAL_SLIDE);

                break;

            case R.id.btn_more:

                List<RentStatusVO> list = gnsViewModel.getRES_GNS_1007().getValue().data.getSuspList();
                if(adapter.getItemCount()>=list.size()){
                    adapter.setMore(false);
//                    adapter.notifyItemChanged(list.size()-1);
                }else{
                    if(list.size()-adapter.getItemCount()==1){
                        adapter.setMore(false);
                        adapter.addRow(list.get(list.size()-1));
                    }
//                    else if(list.size()-adapter.getItemCount()==2){
//                        adapter.setMore(false);
//                        adapter.addRow(list.get(list.size()-2));
//                        adapter.addRow(list.get(list.size()-1));
//                    }
                    else{
                        adapter.setMore(true);
                        adapter.addRows(list.subList(adapter.getItemCount(),adapter.getItemCount()+2));
                    }
                }

                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_add:
                startActivitySingleTop(new Intent(this, LeasingCarVinRegisterActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                finish();
                break;
        }

    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        ui.setActivity(this);
        gnsViewModel = new ViewModelProvider(this).get(GNSViewModel.class);
    }

    @Override
    public void setObserver() {

        gnsViewModel.getRES_GNS_1007().observe(this, result -> {

            switch (result.status){
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:

                    if(result.data!=null&&result.data.getRtCd().equalsIgnoreCase("0000")){
                        if(result.data.getSuspList()!=null&&result.data.getSuspList().size()>0) {
                            List<RentStatusVO> list = new ArrayList<>();
                            adapter.clear();
                            if (result.data.getSuspList().size() > 2) {
                                list = new ArrayList<>(result.data.getSuspList().subList(0, 3));
                                adapter.setMore(true);
                            } else {
                                list.addAll(result.data.getSuspList());
                                adapter.setMore(false);
                            }
                            adapter.setRows(list);
                            adapter.notifyDataSetChanged();
                            ui.tvEmpty.setVisibility(View.GONE);

                        }else{
                            ui.tvEmpty.setVisibility(View.VISIBLE);
                        }

                        if (isApply) {
                            isApply=false;
                            SnackBarUtil.show(this, getString(R.string.gm_carlist_01_01_snackbar_1));
                        }
                        showProgressDialog(false);
                        break;
                    }

                default:
                    ui.tvEmpty.setVisibility(View.VISIBLE);
                    showProgressDialog(false);
                    if(result.data!=null&& StringUtil.isValidString(result.data.getRtCd()).equalsIgnoreCase("9101")){
                        return; //신청정보가 없습니다.
                    }
                    String serverMsg="";
                    try {
                        serverMsg = result.data.getRtMsg();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        SnackBarUtil.show(this, serverMsg);
                    }
                    break;
            }
        });

    }

    @Override
    public void getDataFromIntent() {
        try {
            isApply = getIntent().getBooleanExtra(KeyNames.KEY_NAME_APPLY_LEASINGCAR, false);
        } catch (Exception e) {
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //재신청 시 내역 액티비티를 종료
         if (resultCode == ResultCodes.REQ_CODE_LEASING_CAR_RE_APPLY.getCode()) {
            finish();
        }else if(resultCode == ResultCodes.REQ_CODE_LEASING_CAR_CANCEL.getCode()){
        //신청 취소 시 스낵바 메시지 활성화 및 리스트를 다시 갱신
             String msg="";
             try {
                 if(data!=null)
                    msg = data.getStringExtra("msg");
             }catch (Exception e){
                 e.printStackTrace();
             }finally{
                 if(TextUtils.isEmpty(msg)){
                    msg = getString(R.string.gm_carlist_01_p05_snackbar_1);
                 }
                 SnackBarUtil.show(this, msg);
                 gnsViewModel.reqGNS1007(new GNS_1007.Request(APPIAInfo.GM_CARLST_02.getId()));
             }
         }
    }

}
