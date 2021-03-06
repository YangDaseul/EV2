package com.genesis.apps.ui.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.vo.BtrVO;
import com.genesis.apps.comm.util.DeviceUtil;
import com.genesis.apps.comm.util.RecyclerViewDecoration;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.databinding.ActivityBtrBluehandsHistBinding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.main.home.view.BtrBluehandsAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author hjpark
 * @brief 블루핸즈 목록 보기
 */
public class BtrBluehandsListActivity extends SubActivity<ActivityBtrBluehandsHistBinding> {
    private BtrBluehandsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btr_bluehands_hist);
        getDataFromIntent();
        setViewModel();
        setObserver();
    }

    private void initView(List<BtrVO> list) {
        adapter = new BtrBluehandsAdapter(onSingleClickListener);
        ui.rvBtr.setLayoutManager(new LinearLayoutManager(this));
        ui.rvBtr.setHasFixedSize(true);
        ui.rvBtr.addItemDecoration(new RecyclerViewDecoration((int)DeviceUtil.dip2Pixel(this,4.0f)));
        ui.rvBtr.setAdapter(adapter);

        if(list!=null&&list.size()>0){
            ui.tvEmpty.setVisibility(View.GONE);
            ui.tvCntValue.setText(list.size()+"");
            adapter.setRows(list);
            adapter.notifyDataSetChanged();
        }else{
            ui.tvEmpty.setVisibility(View.VISIBLE);
            ui.tvCntValue.setText("0");
        }
    }

    @Override
    public void onClickCommon(View v) {
        BtrVO btrVO;
        switch (v.getId()){
            case R.id.l_whole:
                btrVO = (BtrVO)v.getTag(R.id.btr);
                setResult(ResultCodes.REQ_CODE_BTR.getCode(), new Intent().putExtra(KeyNames.KEY_NAME_BTR,btrVO));
                finish();
                break;
            case R.id.tv_auth_1:
            case R.id.tv_auth_2:
            case R.id.tv_auth_3:
            case R.id.tv_auth_4:
                btrVO = (BtrVO)v.getTag(R.id.btr);
                if(btrVO==null||StringUtil.isValidString(btrVO.getAcps1Cd()).equalsIgnoreCase("2"))
                    return;
                int authId = Integer.parseInt(v.getTag(R.id.item).toString());
                String msg;
                switch (authId){
                    case R.string.bt06_17://차체도장
                        msg = btrVO.getPntgXclSvcSbc();
                        break;
                    case R.string.bt06_18://기술력우수
                        msg = btrVO.getPrimTechSvcSbc();
                        break;
                    case R.string.bt06_23://cs우수인증
                        msg = btrVO.getPrimCsSvcSbc();
                        break;
                    case R.string.bt06_23_2://전기차 전담
                        msg = btrVO.getEvSvcSbc();
                        break;
                    default:
                        return;
                }
                if(!TextUtils.isEmpty(msg)){
                    SnackBarUtil.show(this, msg);
                }

                break;
        }
    }

    @Override
    public void setViewModel() {
    }

    @Override
    public void setObserver() {

    }

    @Override
    public void getDataFromIntent() {
        List<BtrVO> list = null;
        int titleId=0;
        try {
            list = new Gson().fromJson(getIntent().getStringExtra(KeyNames.KEY_NAME_BTR), new TypeToken<List<BtrVO>>(){}.getType());
            titleId = getIntent().getIntExtra(KeyNames.KEY_NAME_MAP_SEARCH_TITLE_ID, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (list==null||list.size()<1) {
                exitPage("블루핸즈 정보가 존재하지 않습니다.", ResultCodes.REQ_CODE_EMPTY_INTENT.getCode());
            }else{
                initView(list);
                if(titleId!=0) ui.lTitle.setValue(getString(titleId));
            }
        }

    }
}
