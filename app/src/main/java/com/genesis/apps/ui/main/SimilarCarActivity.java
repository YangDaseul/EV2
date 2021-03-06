package com.genesis.apps.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.STO_1001;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.vo.SimilarVehicleVO;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.databinding.ActivitySimilarCarBinding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.dialog.middle.MiddleDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author hjpark
 * @brief 유사 재고 조회
 */
public class SimilarCarActivity extends SubActivity<ActivitySimilarCarBinding> {
    private LGNViewModel lgnViewModel;
    private SimilarCarAdapter adapter;
    private String ctrctNo;
    private SimilarVehicleVO similarVehicleVO = null;
    private String celphNo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_car);
        setViewModel();
        getDataFromIntent();
        setObserver();
        initView();
        lgnViewModel.reqSTO1001(new STO_1001.Request(APPIAInfo.GM02_INV01.getId(), ctrctNo));
    }

    private void initView() {
        adapter = new SimilarCarAdapter(view -> onClickCommon(view));
        ui.rv.setLayoutManager(new LinearLayoutManager(this));
        ui.rv.setHasFixedSize(false);
        ui.rv.setAdapter(adapter);
    }

    @Override
    public void onClickCommon(View v) {
        switch (v.getId()) {
            case R.id.l_whole:
                int pos = -1;
                try {
                    celphNo = ((SimilarVehicleVO)adapter.getItem(0)).getCelphNo(); //celphNo는 헤더인 계약차량에서
                    similarVehicleVO = (SimilarVehicleVO) v.getTag(R.id.item);
                    pos = Integer.parseInt(v.getTag(R.id.position).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    pos = -1;
                } finally {
                    if (similarVehicleVO != null && !TextUtils.isEmpty(celphNo) && pos > -1) {//카마스터 번호가 있으면
                        adapter.selectItem(pos, false);
                    } else {
                        //없을 때 초기화하는 로직
//                        adapter.selectItem(pos, true);
//                        SnackBarUtil.show(this, "딜러 정보가 존재하지 않습니다.");
                    }
                    ui.btnRequest.setVisibility(adapter.getSelectItem()==null ? View.GONE : View.VISIBLE);
                }
                break;
            case R.id.btn_request://문의하기
                MiddleDialog.dialogSimilarInfo(this, () -> {
                    celphNo = ((SimilarVehicleVO)adapter.getItem(0)).getCelphNo(); //celphNo는 헤더인 계약차량에서
                    similarVehicleVO = adapter.getSelectItem(); //선택된 유사차량 정보
                    if (similarVehicleVO != null && !TextUtils.isEmpty(celphNo)) {
                        String msg = String.format(Locale.getDefault(), getString(R.string.gm02_inv01_8), lgnViewModel.getDbUserRepo().getUserVO().getCustNm(), similarVehicleVO.getVhclCd());
                        sendSmsIntent(celphNo, msg);
                    }
                }, () -> {

                });
                break;
        }

    }

    public void sendSmsIntent(String number, String msg) {
        try {
            Uri smsUri = Uri.parse("sms:" + number);
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
            sendIntent.putExtra("sms_body", msg);
            startActivity(sendIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        ui.setListener(onSingleClickListener);
        lgnViewModel = new ViewModelProvider(this).get(LGNViewModel.class);
    }

    @Override
    public void setObserver() {

        lgnViewModel.getRES_STO_1001().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    if (result.data != null && result.data.getEstmVhclList() != null && result.data.getSmlrVhclList() != null) {
                        List<SimilarVehicleVO> list = new ArrayList<>();
                        list.addAll(result.data.getSmlrVhclList());
                        list.add(0, result.data.getEstmVhclList());
                        adapter.setRows(list);
                        adapter.notifyDataSetChanged();
                        showProgressDialog(false);
                        break;
                    }
                default:
                    showProgressDialog(false);
                    String serverMsg = "";
                    try {
                        serverMsg = result.data.getRtMsg();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        SnackBarUtil.show(this, (TextUtils.isEmpty(serverMsg)) ? getString(R.string.r_flaw06_p02_snackbar_1) : serverMsg);
                    }
                    break;
            }
        });
    }

    @Override
    public void getDataFromIntent() {
        VehicleVO vehicleVO=null;
        try{
            vehicleVO = (VehicleVO)getIntent().getSerializableExtra(KeyNames.KEY_NAME_VEHICLE);
        }catch (Exception e){

        }
        if(vehicleVO==null) vehicleVO = getMainVehicle();
        if(vehicleVO!=null) ctrctNo = StringUtil.isValidString(vehicleVO.getCtrctNo());
    }

    private VehicleVO getMainVehicle(){
        VehicleVO vehicleVO = null;
        try {
            vehicleVO = lgnViewModel.getMainVehicleFromDB();
        }catch (Exception e){

        }
        return vehicleVO;
    }
}
