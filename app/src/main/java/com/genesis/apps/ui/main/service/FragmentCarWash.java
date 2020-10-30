package com.genesis.apps.ui.main.service;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.gra.APPIAInfo;
import com.genesis.apps.comm.model.gra.api.WSH_1001;
import com.genesis.apps.comm.model.vo.WashGoodsVO;
import com.genesis.apps.comm.viewmodel.WSHViewModel;
import com.genesis.apps.databinding.FragmentServiceCarWashBinding;
import com.genesis.apps.ui.common.activity.BaseActivity;
import com.genesis.apps.ui.common.fragment.SubFragment;
import com.genesis.apps.ui.main.MainActivity;

import java.util.List;

public class FragmentCarWash extends SubFragment<FragmentServiceCarWashBinding> {
    private static final String TAG = FragmentCarWash.class.getSimpleName();

    private WSHViewModel viewModel;
    private FragmentCarWashAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        return super.setContentView(inflater, R.layout.fragment_service_car_wash);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);

        setOnSingleClickListener();
        setViewModel();
        setAdapter();
        setObserver();

        viewModel.reqWSH1001(new WSH_1001.Request(APPIAInfo.SM_CW01_A01.getId(), "SONAX"));
    }

    //TODO : 이중 클릭 방지 클릭 리스너 붙이기. 세차 서비스 예약내역 버튼인데
    // 이 버튼이 리사이클러 뷰에 들어가서 어댑터가 이걸 처리할지, 현행 그대로 둘지 고려
    private void setOnSingleClickListener() {
        me.lServiceCarWashHistoryBtn.lServiceCarWash.setOnClickListener(onSingleClickListener);
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(getActivity()).get(WSHViewModel.class);
        me.setLifecycleOwner(getViewLifecycleOwner());
    }

    private void setObserver() {
        viewModel.getRES_WSH_1001().observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case LOADING:
                    ((MainActivity) getActivity()).showProgressDialog(true);
                    break;

                case SUCCESS:
                    if (result.data != null && result.data.getGodsList() != null) {
                        List<WashGoodsVO> list = result.data.getGodsList();

                        adapter.setRows(list);
                        adapter.notifyDataSetChanged();

                        //성공 후 데이터 로딩까지 다 되면 로딩 치우고 break;
                        ((MainActivity) getActivity()).showProgressDialog(false);
                        break;
                    }
                    //not break; 데이터 이상하면 default로 진입시킴

                default:
                    ((MainActivity) getActivity()).showProgressDialog(false);
                    break;
            }

        });
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClickCommon(View v) {
        int id = v.getId();
        Log.d(TAG, "onClickCommon: view id :" + id);

        switch (id) {
            //세차 서비스 예약내역 버튼
            case R.id.l_service_car_wash_history_btn:
                ((BaseActivity) getActivity()).startActivitySingleTop(new Intent(getActivity(), CarWashHistoryActivity.class), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;

            default:
                //do nothing
                break;
        }
    }

    private void setAdapter() {
        //세차 아이템 어댑터 (인스턴스 타입 맞나 확인)
        adapter = new FragmentCarWashAdapter(onSingleClickListener);
        me.rvServiceCarWashItemList.setLayoutManager(new LinearLayoutManager(getContext()));
        me.rvServiceCarWashItemList.setHasFixedSize(true);
        me.rvServiceCarWashItemList.setAdapter(adapter);
    }
}
