package com.genesis.apps.comm.viewmodel;

import android.text.TextUtils;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.genesis.apps.comm.model.api.gra.LGN_0001;
import com.genesis.apps.comm.model.api.gra.LGN_0002;
import com.genesis.apps.comm.model.api.gra.LGN_0003;
import com.genesis.apps.comm.model.api.gra.LGN_0004;
import com.genesis.apps.comm.model.api.gra.LGN_0005;
import com.genesis.apps.comm.model.api.gra.LGN_0006;
import com.genesis.apps.comm.model.api.gra.LGN_0007;
import com.genesis.apps.comm.model.api.gra.STO_1001;
import com.genesis.apps.comm.model.api.gra.STO_1002;
import com.genesis.apps.comm.model.api.gra.STO_1003;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.repo.DBGlobalDataRepository;
import com.genesis.apps.comm.model.repo.DBUserRepo;
import com.genesis.apps.comm.model.repo.DBVehicleRepository;
import com.genesis.apps.comm.model.repo.LGNRepo;
import com.genesis.apps.comm.model.repo.STORepo;
import com.genesis.apps.comm.model.vo.TopicVO;
import com.genesis.apps.comm.model.vo.UserVO;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.net.NetUIResponse;
import com.genesis.apps.comm.net.ga.LoginInfoDTO;
import com.genesis.apps.comm.util.excutor.ExecutorService;
import com.genesis.apps.room.ResultCallback;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lombok.Data;

import static com.genesis.apps.comm.model.constants.VariableType.COMMON_MEANS_YES;
import static com.genesis.apps.comm.model.constants.VariableType.MAIN_VEHICLE_TYPE_0000;
import static com.genesis.apps.comm.model.constants.VariableType.MAIN_VEHICLE_TYPE_CV;
import static com.genesis.apps.comm.model.constants.VariableType.MAIN_VEHICLE_TYPE_NV;
import static com.genesis.apps.comm.model.constants.VariableType.MAIN_VEHICLE_TYPE_OV;

public @Data
class LGNViewModel extends ViewModel {

    private LoginInfoDTO loginInfoDTO;
    private final LGNRepo repository;
    private final STORepo stoRepo;
    private final DBVehicleRepository dbVehicleRepository;
    private final DBGlobalDataRepository dbGlobalDataRepository;
    private final DBUserRepo dbUserRepo;
    private final SavedStateHandle savedStateHandle;

    private MutableLiveData<NetUIResponse<LGN_0001.Response>> RES_LGN_0001;
    private MutableLiveData<NetUIResponse<LGN_0002.Response>> RES_LGN_0002;
    private MutableLiveData<NetUIResponse<LGN_0003.Response>> RES_LGN_0003;
    private MutableLiveData<NetUIResponse<LGN_0004.Response>> RES_LGN_0004;
    private MutableLiveData<NetUIResponse<LGN_0005.Response>> RES_LGN_0005;

    private MutableLiveData<NetUIResponse<LGN_0006.Response>> RES_LGN_0006;
    private MutableLiveData<NetUIResponse<LGN_0007.Response>> RES_LGN_0007;

    public final LiveData<List<VehicleVO>> carVO = Transformations.map(RES_LGN_0001, input -> input.data.getOwnVhclList());


    private MutableLiveData<NetUIResponse<STO_1001.Response>> RES_STO_1001;
    private MutableLiveData<NetUIResponse<STO_1002.Response>> RES_STO_1002;
    private MutableLiveData<NetUIResponse<STO_1003.Response>> RES_STO_1003;

    //map?????? ???????????? ????????? ?????? (???????????? ?????? ????????? ?????? ???..)
    private MutableLiveData<List<Double>> position = new MutableLiveData<>();

    //???????????? ?????? ??????
    private List<Double> myPosition = new ArrayList<>();


//    public final LiveData<VehicleVO> carVO =
//            Transformations.switchMap(RES_LGN_0001, new Function<NetUIResponse<LGN_0001.Response>, LiveData<VehicleVO>>() {
//                @Override
//                public LiveData<VehicleVO> apply(NetUIResponse<LGN_0001.Response> input) {
//                    return input.data.getCarVO(); repo?????? getcarvo??? ??????????????????.. ????????? ?????? ??????
//                }
//            });

    @ViewModelInject
    LGNViewModel(
            LGNRepo repository,
            STORepo stoRepo,
            DBVehicleRepository dbVehicleRepository,
            DBGlobalDataRepository dbGlobalDataRepository,
            DBUserRepo dbUserRepo,
            LoginInfoDTO loginInfoDTO,
            @Assisted SavedStateHandle savedStateHandle)
    {
        this.loginInfoDTO = loginInfoDTO;
        this.repository = repository;
        this.stoRepo = stoRepo;
        this.dbVehicleRepository = dbVehicleRepository;
        this.dbGlobalDataRepository = dbGlobalDataRepository;
        this.dbUserRepo = dbUserRepo;
        this.savedStateHandle = savedStateHandle;

        RES_LGN_0001 = repository.RES_LGN_0001;
        RES_LGN_0002 = repository.RES_LGN_0002;
        RES_LGN_0003 = repository.RES_LGN_0003;
        RES_LGN_0004 = repository.RES_LGN_0004;
        RES_LGN_0005 = repository.RES_LGN_0005;

        RES_LGN_0006 = repository.RES_LGN_0006;
        RES_LGN_0007 = repository.RES_LGN_0007;

        RES_STO_1001 = stoRepo.RES_STO_1001;
        RES_STO_1002 = stoRepo.RES_STO_1002;
        RES_STO_1003 = stoRepo.RES_STO_1003;
    }

    public void reqLGN0001(final LGN_0001.Request reqData){
        repository.REQ_LGN_0001(reqData);
    }

    public void reqLGN0002(final LGN_0002.Request reqData){
        repository.REQ_LGN_0002(reqData);
    }

    public void reqLGN0003(final LGN_0003.Request reqData){
        repository.REQ_LGN_0003(reqData);
    }

    public void reqLGN0004(final LGN_0004.Request reqData){
        repository.REQ_LGN_0004(reqData);
    }

    public void reqLGN0005(final LGN_0005.Request reqData){
        repository.REQ_LGN_0005(reqData);
    }

    public void reqLGN0006(final LGN_0006.Request reqData){
        repository.REQ_LGN_0006(reqData);
    }

    public void reqLGN0007(final LGN_0007.Request reqData){
        repository.REQ_LGN_0007(reqData);
    }

    public void reqSTO1001(final STO_1001.Request reqData){
        stoRepo.REQ_STO_1001(reqData);
    }

    public void reqSTO1002(final STO_1002.Request reqData){
        stoRepo.REQ_STO_1002(reqData);
    }

    public void reqSTO1003(final STO_1003.Request reqData){
        stoRepo.REQ_STO_1003(reqData);
    }


    public void setLGN0001ToDB(LGN_0001.Response data, ResultCallback callback, boolean isFirstLogin){
        ExecutorService es = new ExecutorService("");
        Futures.addCallback(es.getListeningExecutorService().submit(() -> {

            boolean isSuccess;
            try {
                String userType=MAIN_VEHICLE_TYPE_0000;
                UserVO userVO = new Gson().fromJson(new Gson().toJson(data),UserVO.class);
                if(userVO.getCustGbCd().equalsIgnoreCase(MAIN_VEHICLE_TYPE_NV)){
                    userType=MAIN_VEHICLE_TYPE_NV;
                }
                isSuccess = dbVehicleRepository.deleteAllVehicle()
                        &&dbVehicleRepository.setVehicleList(data.getOwnVhclList(), MAIN_VEHICLE_TYPE_OV)
                        &&dbVehicleRepository.setVehicleList(data.getCtrctVhclList(), MAIN_VEHICLE_TYPE_CV)
                        &&dbVehicleRepository.setVehicle(data.getDftVhclInfo(), userType)
                        &&dbUserRepo.setUserVO(userVO)
                        &&updateUserInfo((userVO!=null ? userVO.getCustNm() : "") , (userVO!=null ? userVO.getCelphNo() : ""))
                        &&(!isFirstLogin || updateGlobalDataToDB(KeyNames.KEY_NAME_DB_GLOBAL_DATA_ISFIRSTLOGIN, VariableType.COMMON_MEANS_YES));
            } catch (Exception e1) {
                e1.printStackTrace();
                isSuccess=false;
            }

            return isSuccess;
        }), new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@NullableDecl Boolean isSuccess) {
                callback.onSuccess(isSuccess);
                es.shutDownExcutor();
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError(t);
                es.shutDownExcutor();
            }
        }, es.getUiThreadExecutor());
    }


    public VehicleVO getMainVehicleFromDB() throws ExecutionException, InterruptedException {
        ExecutorService es = new ExecutorService("");
        Future<VehicleVO> future = es.getListeningExecutorService().submit(()->{
            VehicleVO vehicleVO = null;
            try {
                vehicleVO = dbVehicleRepository.getMainVehicleFromDB();
            } catch (Exception ignore) {
                ignore.printStackTrace();
                vehicleVO = null;
            }
            return vehicleVO;
        });

        try {
            return future.get();
        }finally {
            //todo ????????? ??????
            es.shutDownExcutor();
        }
    }

    public VehicleVO getMainVehicleSimplyFromDB() throws ExecutionException, InterruptedException {
        ExecutorService es = new ExecutorService("");
        Future<VehicleVO> future = es.getListeningExecutorService().submit(()->{
            VehicleVO vehicleVO = null;
            try {
                vehicleVO = dbVehicleRepository.getMainVehicleSimplyFromDB();
            } catch (Exception ignore) {
                ignore.printStackTrace();
                vehicleVO = null;
            }
            return vehicleVO;
        });

        try {
            return future.get();
        }finally {
            //todo ????????? ??????
            es.shutDownExcutor();
        }
    }

    public UserVO getUserInfoFromDB() throws ExecutionException, InterruptedException {
        ExecutorService es = new ExecutorService("");
        Future<UserVO> future = es.getListeningExecutorService().submit(()->{
            UserVO userVO = null;
            try {
                userVO = dbUserRepo.getUserVO();
            } catch (Exception ignore) {
                ignore.printStackTrace();
                userVO = null;
            }
            return userVO;
        });

        try {
            return future.get();
        }finally {
            //todo ????????? ??????
            es.shutDownExcutor();
        }
    }

    public void setPosition(double x, double y){

        List<Double> positions = new ArrayList<>();
//        positions.add(37.463936);
//        positions.add(127.042953);
        positions.add(x);
        positions.add(y);
        position.setValue(positions);

    }

    public void setMyPosition(double x, double y){
        myPosition = new ArrayList<>();
        myPosition.add(x);
        myPosition.add(y);
    }

    public List<Double> getPositionValue(){
        return position.getValue();
    }

    public List<TopicVO> getTopicList(){
        return dbUserRepo.getTopicList();
    }

    public void insertTopicList(List<String> oriList){
        dbUserRepo.insertTopicList(oriList);
    }

    public void unSubscribeTopic(){
        try {
            //????????? db??? ????????? ?????? ?????? ??? ?????? ??????
            List<TopicVO> topicList = new ArrayList<>();
            topicList.addAll(getTopicList());
            for (TopicVO oriTopic : topicList) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(oriTopic.getTopicNm());
            }
        }catch (Exception e){

        }
    }

    public void updateTopic(List<String> receiveTopicList){
        unSubscribeTopic();
        subscribeTopic(receiveTopicList);
    }

    public void subscribeTopic(List<String> receiveTopicList){
        try {
            if(receiveTopicList==null) receiveTopicList = new ArrayList<>();

            //?????? TOPIC ?????? (?????? ??????)
            receiveTopicList.add(VariableType.TOPIC_ALL);
            //db??? ?????? ?????? ??????
            insertTopicList(receiveTopicList);
            //db??? ?????? ????????? ????????? ??????
            List<TopicVO> newTopicList = new ArrayList<>();
            newTopicList.addAll(getTopicList());
            for (TopicVO newTopic : newTopicList) {
                FirebaseMessaging.getInstance().subscribeToTopic(newTopic.getTopicNm());
            }
        }catch (Exception e){

        }
    }

    public boolean updateGlobalDataToDB(String keyName, String value) {
        boolean isUpdate=false;
        try {
            isUpdate = dbGlobalDataRepository.update(keyName, value);
        }catch (Exception e){
            isUpdate=false;
        }
        return isUpdate;
    }

    public boolean hadTutorial(int type){
        String isWatch="";
        try{
            isWatch = selectGlobalDataFromDB(KeyNames.KEY_NAME_TUTORIAL_TYPE+type);
        }catch (Exception ignore){
            isWatch = "";
        }
        return VariableType.COMMON_MEANS_YES.equalsIgnoreCase(isWatch);
    }

    public String selectGlobalDataFromDB(String keyName){
        return dbGlobalDataRepository.select(keyName);
    }

    public void removeDBTable(){
        unSubscribeTopic();
        dbUserRepo.clearUserInfo();
    }

    public boolean updateUserInfo(String name, String phone){
        try{
            if(loginInfoDTO!=null
                    &&loginInfoDTO.getProfile()!=null
                    &&!TextUtils.isEmpty(name)
                    &&!TextUtils.isEmpty(phone))
            {
                loginInfoDTO.getProfile().setName(name);
                loginInfoDTO.getProfile().setMobileNum(phone);
                loginInfoDTO.updateLoginInfo(loginInfoDTO);
            }
        }catch (Exception ignore){

        }

        return true;
    }

    public boolean checkContractInfoPopUp() {
        return !VariableType.COMMON_MEANS_YES.equalsIgnoreCase(getDbGlobalDataRepository().select(KeyNames.KEY_NAME_SIMILAR_CAR_CONTRACT_INFO_POPUP));
    }

    public void setContractInfo(Boolean isCheck){
        if(isCheck){
            updateGlobalDataToDB(KeyNames.KEY_NAME_SIMILAR_CAR_CONTRACT_INFO_POPUP, COMMON_MEANS_YES);
        }
    }

}