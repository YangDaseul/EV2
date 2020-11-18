package com.genesis.apps.ui.main.service;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.BaseResponse;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.vo.VOCInfoVO;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.viewmodel.VOCViewModel;
import com.genesis.apps.databinding.ActivityServiceRelapseApply3Binding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.dialog.bottom.BottomListDialog;

public class ServiceRelapse3Activity extends SubActivity<ActivityServiceRelapseApply3Binding> {
    private static final String TAG = ServiceRelapse3Activity.class.getSimpleName();

    private static final int STATE_INIT = 0;
    private static final int STATE_OPEN_DEFECT_HISOTY = 1;
    private static final int STATE_2 = 2;
    private static final int STATE_3 = 3;
    private static final int STATE_4 = 4;

    private BottomListDialog defectListDialog;

    private VOCViewModel viewModel;
    private VOCInfoVO vocInfoVO;
    private ServiceRelapse3Adapter adapter;

    public boolean more4;
    public int dummyLineVisibility = View.GONE;

    private int currentState = STATE_INIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_relapse_apply_3);
        setResizeScreen();

        getDataFromIntent();
        setViewModel();
        setObserver();
        setAdapter();

        ui.setActivity(this);
    }

    @Override
    public void onClickCommon(View v) {
        Log.d(TAG, "onClickCommon: " + v.getId());

        switch (v.getId()) {
            //수리 횟수 추가
            case R.id.tv_relapse_3_repair_add_btn:
                onClickAddRepairHistory();
                break;

            //다음 버튼
            case R.id.tv_relapse_3_next_btn:
                onClickNextBtn();
                break;

            default:
                //do nothing
                break;
        }
    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(VOCViewModel.class);
    }

    @Override
    public void setObserver() {
        Log.d(TAG, "setObserver: ");

        viewModel.getRES_VOC_1002().observe(this, result -> {
            Log.d(TAG, "setObserver VOC 1002: " + result.status);

            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;

                case SUCCESS:
                    if (result.data != null && result.data.getRtCd() != null) {
                        if (result.data.getRtCd().equals(BaseResponse.RETURN_CODE_SUCC)) {
                            //TODO impl
                        }

                        showProgressDialog(false);
                        break;
                    }
                    //not break; 데이터 이상하면 default로 진입시킴

                default:
                    showProgressDialog(false);
                    SnackBarUtil.show(this, getString(result.message));
                    //todo : 구체적인 예외처리
                    break;
            }
        });
    }

    @Override
    public void getDataFromIntent() {
        //todo mainVehicle 데이터 획득(딴 데서 해도 되고...)
        try {
            vocInfoVO = (VOCInfoVO) getIntent().getSerializableExtra(KeyNames.KEY_NAME_SERVICE_VOC_INFO_VO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAdapter() {
        adapter = new ServiceRelapse3Adapter(this);
        ui.rvRelapse3RepairHistoryList.setLayoutManager(new LinearLayoutManager(this));
        ui.rvRelapse3RepairHistoryList.setHasFixedSize(true);
        ui.rvRelapse3RepairHistoryList.setAdapter(adapter);
    }

    //수리 내역 추가 버튼
    private void onClickAddRepairHistory() {
        //입력 창 추가
        adapter.addRow();
    }

    private void onClickNextBtn() {
        Log.d(TAG, "onClickNextBtn: ");

        switch (currentState) {
            case STATE_OPEN_DEFECT_HISOTY:
                if (adapter.validateInputData()) {
                    //todo 다음 단계 이름 짓고 뷰 갱신
                    currentState = STATE_2;
                }
                break;

            default:
                //do nothing
                break;
        }
    }


    public void changeStatusToDefectHistory() {
        if (currentState == STATE_INIT) {
            ui.tvRelapse3Desc.setText(R.string.relapse_3_msg_02);
            ui.tvRelapse3NextBtn.setVisibility(View.VISIBLE);

            //단계 변동을 저장
            currentState = STATE_OPEN_DEFECT_HISOTY;
        }
    }


    //TODO 1,2단계 입력정보 및 그 앞 단계인 회원정보 집어넣기
    // 특히 mainVehicle 아직 처리 안 한 상태라 null임
//    private VOCInfoVO makeVOCInfoVO(RepairData[] repairHistory) {
//        final int FIRST = 0;
//        final int SECOND = 1;
//        final int THIRD = 2;
//
//        for (RepairData data : repairHistory) {
//            if (data == null) {
//                //TODO 이거 dataList에 반영되는 거 맞는지 확인
//                data = new RepairData();//데이터 없으면 공백문자열로 채우기
//            }
//        }
//
//        //todo 중대한 하자인가?
//        // 클래스 필드로 두거나 해서 어쨌든 유효값 넣기.
//        boolean isCritical = true;
//        return null;
//
////        return new VOCInfoVO(
////                "고객 이름",
////                "고객 생년월일",
////                "이메일",
////                "도로명 우편번호",
////                "도로명주소",
////                "도로명 상세주소",
////                "연락처1",//010
////                "연락처2",//전번 앞자리
////                "연락처3",//전번  뒷자리
////
////                mainVehicle.getRecvYmd(),   //인도날짜(recvDt)인데 이거 맞나?
////                mainVehicle.getMdlNm(),     //차명(carNm)인데 이거 맞나?
////                mainVehicle.getMdlCd(),     //차종코드(crnVehlCd)인데 이거 맞나?
////                mainVehicle.getRecvYmd(),   //출고일자(whotYmd)는 인도날짜(이거 세 칸 앞에 거)랑 다른 건가?
////                "주행거리",
////                mainVehicle.getCarRgstNo(),
////                mainVehicle.getVin(),
////                "운행지역 시도",
////                "운행지역 시군구",
////                isCritical ? VOCInfoVO.DEFECT_LEVEL_HIGH : VOCInfoVO.DEFECT_LEVEL_LOW,
////
////                repairHistory[FIRST].getMechanic(),
////                repairHistory[FIRST].getReqDate(),
////                repairHistory[FIRST].getFinishDate(),
////                "주행거리 1회차",//이건 도대체 어떻게 알아내는 거여?
////                repairHistory[FIRST].getDefectDetail(),
////                repairHistory[FIRST].getRepairDetail(),
////
////                repairHistory[SECOND].getMechanic(),
////                repairHistory[SECOND].getReqDate(),
////                repairHistory[SECOND].getFinishDate(),
////                "주행거리 2회차",
////                repairHistory[SECOND].getDefectDetail(),
////                repairHistory[SECOND].getRepairDetail(),
////
////                repairHistory[THIRD].getMechanic(),
////                repairHistory[THIRD].getReqDate(),
////                repairHistory[THIRD].getFinishDate(),
////                "주행거리 3회차",
////                repairHistory[THIRD].getDefectDetail(),
////                repairHistory[THIRD].getRepairDetail(),
////
////                "",//수리시도회수4회이상여부 "Y" "N"
////                "누적수리기간",
////                "개인정보취급동의",//"Y" "N"
////                "",//미사용
////                "",//미사용
////                ""//미사용
////        );
//    }

}