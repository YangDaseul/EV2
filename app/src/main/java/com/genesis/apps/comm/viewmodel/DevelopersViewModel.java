package com.genesis.apps.comm.viewmodel;

import android.text.TextUtils;

import com.genesis.apps.comm.model.api.developers.Agreements;
import com.genesis.apps.comm.model.api.developers.CarCheck;
import com.genesis.apps.comm.model.api.developers.CarConnect;
import com.genesis.apps.comm.model.api.developers.CarId;
import com.genesis.apps.comm.model.api.developers.CarList;
import com.genesis.apps.comm.model.api.developers.CheckJoinCCS;
import com.genesis.apps.comm.model.api.developers.Detail;
import com.genesis.apps.comm.model.api.developers.Distance;
import com.genesis.apps.comm.model.api.developers.Dtc;
import com.genesis.apps.comm.model.api.developers.Dte;
import com.genesis.apps.comm.model.api.developers.EvStatus;
import com.genesis.apps.comm.model.api.developers.Odometer;
import com.genesis.apps.comm.model.api.developers.Odometers;
import com.genesis.apps.comm.model.api.developers.ParkLocation;
import com.genesis.apps.comm.model.api.developers.Replacements;
import com.genesis.apps.comm.model.api.developers.Target;
import com.genesis.apps.comm.model.constants.GAInfo;
import com.genesis.apps.comm.model.repo.DBVehicleRepository;
import com.genesis.apps.comm.model.repo.DevelopersRepo;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.model.vo.developers.CarConnectVO;
import com.genesis.apps.comm.model.vo.developers.CarVO;
import com.genesis.apps.comm.model.vo.developers.OdometerVO;
import com.genesis.apps.comm.net.NetUIResponse;
import com.genesis.apps.comm.net.ga.LoginInfoDTO;
import com.genesis.apps.comm.util.DateUtil;
import com.genesis.apps.comm.util.QueryString;
import com.genesis.apps.comm.util.excutor.ExecutorService;
import com.genesis.apps.room.ResultCallback;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import lombok.Data;

import static com.genesis.apps.comm.model.constants.VariableType.MAIN_VEHICLE_TYPE_OV;

public @Data
class DevelopersViewModel extends ViewModel {
    /**
     * Developers ??? My?????? GRA-GNS-1010 ???????????? ?????? ?????? ????????? ????????? ????????? ???????????? ?????? ?????? enum class.
     */
    public enum SEST {
        /**
         * ????????????/??????
         */
        ENGINE_OIL_FILTER(1, "11"),
        /**
         * ?????? ?????????
         */
        AIR_CLEANER(2, "0"),
        /**
         * ????????? ??????
         */
        AIR_CONDITIONING_FILTER(3, "13"),
        /**
         * ???????????? ??????
         */
        BREAK_OIL(9, "34");

        /**
         * Developers API ?????? ???????????? ????????? ??????.
         */
        public final int devCode;

        /**
         * My?????? API ??? GRA-GNS-1010??? ???????????? ???????????? ????????? ?????? ????????? ????????? ??????.
         * ?????? ????????? ?????? ?????? 0?????? ??????.
         */
        public final String graGns1010Code;

        SEST(int devCode, String graGns1010Code) {
            this.devCode = devCode;
            this.graGns1010Code = graGns1010Code;
        }
    } // end of enum class SEST

    private LoginInfoDTO loginInfoDTO;
    private final DevelopersRepo repository;
    private final SavedStateHandle savedStateHandle;

    private final DBVehicleRepository dbVehicleRepository;

    private MutableLiveData<NetUIResponse<Dtc.Response>> RES_DTC;
    private MutableLiveData<NetUIResponse<Replacements.Response>> RES_REPLACEMENTS;
    private MutableLiveData<NetUIResponse<Target.Response>> RES_TARGET;
    private MutableLiveData<NetUIResponse<Detail.Response>> RES_DETAIL;
    private MutableLiveData<NetUIResponse<CarList.Response>> RES_CARLIST;
    private MutableLiveData<NetUIResponse<Dte.Response>> RES_DTE;
    private MutableLiveData<NetUIResponse<Odometer.Response>> RES_ODOMETER;
    private MutableLiveData<NetUIResponse<Odometers.Response>> RES_ODOMETERS;
    private MutableLiveData<NetUIResponse<ParkLocation.Response>> RES_PARKLOCATION;
    private MutableLiveData<NetUIResponse<Distance.Response>> RES_DISTANCE;
    private MutableLiveData<NetUIResponse<CarCheck.Response>> RES_CAR_CHECK;
    private MutableLiveData<NetUIResponse<CarId.Response>> RES_CAR_ID;
    private MutableLiveData<NetUIResponse<CarConnect.Response>> RES_CAR_CONNECT;
    private MutableLiveData<NetUIResponse<Agreements.Response>> RES_CAR_AGREEMENTS;
    private MutableLiveData<NetUIResponse<EvStatus.Response>> RES_EV_STATUS;


    @ViewModelInject
    DevelopersViewModel(
            DevelopersRepo repository,
            DBVehicleRepository dbVehicleRepository,
            LoginInfoDTO loginInfoDTO,
            @Assisted SavedStateHandle savedStateHandle) {
        this.repository = repository;
        this.savedStateHandle = savedStateHandle;
        this.dbVehicleRepository = dbVehicleRepository;
        this.loginInfoDTO = loginInfoDTO;
        RES_DTC = repository.RES_DTC;
        RES_REPLACEMENTS = repository.RES_REPLACEMENTS;
        RES_TARGET = repository.RES_TARGET;
        RES_DETAIL = repository.RES_DETAIL;
        RES_CARLIST = repository.RES_CARLIST;
        RES_DTE = repository.RES_DTE;
        RES_ODOMETER = repository.RES_ODOMETER;
        RES_ODOMETERS = repository.RES_ODOMETERS;
        RES_PARKLOCATION = repository.RES_PARKLOCATION;
        RES_DISTANCE = repository.RES_DISTANCE;
        RES_CAR_CHECK = repository.RES_CAR_CHECK;
        RES_CAR_ID = repository.RES_CAR_ID;
        RES_CAR_CONNECT = repository.RES_CAR_CONNECT;
        RES_CAR_AGREEMENTS = repository.RES_CAR_AGREEMENTS;
        RES_EV_STATUS = repository.RES_EV_STATUS;
    }

    public void reqDtc(final Dtc.Request reqData) {
        repository.REQ_DTC(reqData);
    }

    public void reqReplacements(final Replacements.Request reqData) {
        repository.REQ_REPLACEMENTS(reqData);
    }

    public void reqTarget(final Target.Request reqData) {
        repository.REQ_TARGET(reqData);
    }

    public void reqDetail(final Detail.Request reqData) {
        repository.REQ_DETAIL(reqData);
    }

    public void reqCarList(final CarList.Request reqData) {
        repository.REQ_CARLIST(reqData);
    }

    public void reqDte(final Dte.Request reqData) {
        repository.REQ_DTE(reqData);
    }

    public void reqOdometer(final Odometer.Request reqData) {
        repository.REQ_ODOMETER(reqData);
    }
    public void reqOdometers(final Odometers.Request reqData) {
        repository.REQ_ODOMETERS(reqData);
    }
    public void reqParkLocation(final ParkLocation.Request reqData) {
        repository.REQ_PARKLOCATION(reqData);
    }

    public void reqEvStatus(final EvStatus.Request reqData) {
        repository.REQ_EV_STATUS(reqData);
    }

    public void reqDistance(final Distance.Request reqData) {
        repository.REQ_DISTANCE(reqData);
    }

    public void reqCarCheck(final CarCheck.Request reqData) {
        repository.REQ_CAR_CHECK(reqData);
    }

    public void reqCarId(final CarId.Request reqData) {
        repository.REQ_CAR_ID(reqData);
    }

    public void reqCarConnect(final CarConnect.Request reqData) {
        repository.REQ_CAR_CONNECT(reqData);
    }

    public void reqAgreementsAsync(final Agreements.Request reqData) {
        repository.REQ_AGREEMENTS_ASYNC(reqData);
    }

    public Boolean reqAgreements(Agreements.Request reqData, boolean isUpdate) throws ExecutionException, InterruptedException {
        ExecutorService es = new ExecutorService("");
        Future<Boolean> future = es.getListeningExecutorService().submit(() -> {
            Boolean result = false;
            Agreements.Response response = repository.REQ_AGREEMENTS(reqData);
            if (response != null) {
                try {
                    result = response.getData().getResult() != 0;
                    if (isUpdate) {
                        updateCarConnectResult(result, reqData.getCarId());
                    }
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
            }
            return result;
        });

        try {
            return future.get();
        } finally {
            es.shutDownExcutor();
        }
    }

    //?????????????????? get
    public int getOdometerValue(){
        int value=0;
        try {
            if(getRES_ODOMETER().getValue()!=null&&getRES_ODOMETER().getValue().data!=null&&getRES_ODOMETER().getValue().data.getOdometers()!=null&&getRES_ODOMETER().getValue().data.getOdometers().size()>0){
                OdometerVO odometerVO = getRES_ODOMETER().getValue().data.getOdometers().stream().max(Comparator.comparingInt(data -> Integer.parseInt(data.getDate()))).orElse(null);
                if(odometerVO!=null){
                    value = (int)odometerVO.getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            value=0;
        }
        return value;
    }

    public boolean updateCarConnectResult(boolean result, String carId){
        try {
            dbVehicleRepository.updateCarConnect(result, carId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * @brief Developers??? ?????? ????????? ?????? carId ?????? ??????
     */
    public void checkVehicleCarId(ResultCallback callback) {
        ExecutorService es = new ExecutorService("");
        Futures.addCallback(es.getListeningExecutorService().submit(() -> {
            try {
                String userId="";
                String accessToken="";
                try{
                    userId = loginInfoDTO.getProfile().getId();
                    accessToken = loginInfoDTO.getAccessToken();
                }catch (Exception e){
                    e.printStackTrace();
                }

                if (!TextUtils.isEmpty(accessToken)&&!TextUtils.isEmpty(userId)) {
                    List<CarConnectVO> targetList = checkJoinCCS(userId);
                    if(targetList!=null&&targetList.size()>0){
                        checkCarId(userId, accessToken, targetList);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return true;
        }), new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@NullableDecl Boolean isSuccess) {
                callback.onSuccess(isSuccess);
                es.shutDownExcutor();
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onSuccess(true);
                es.shutDownExcutor();
            }
        }, es.getUiThreadExecutor());
    }


    public List<CarConnectVO> checkJoinCCS(String userId) throws ExecutionException, InterruptedException {
        ExecutorService es = new ExecutorService("");
        Future<List<CarConnectVO>> future = es.getListeningExecutorService().submit(() -> {
            List<VehicleVO> list = new ArrayList<>();
            try {
                list.addAll(dbVehicleRepository.getVehicleList(MAIN_VEHICLE_TYPE_OV));
            }catch (Exception e){

            }
            List<CarConnectVO> targetList = new ArrayList<>();
            if(list!=null&&list.size()>0) {
                for(VehicleVO vehicleVO : list) {
                    String carId = "";
                    try {
                        carId = dbVehicleRepository.getCarConnect(vehicleVO.getVin()).getCarId();
                    }catch (Exception e){
                        carId = null;
                    }
                    if(TextUtils.isEmpty(carId)) {
                        //carId??? ?????? ???????????? developers ?????? ?????? ??????
                        CheckJoinCCS.Response response = repository.REQ_CHECK_JOIN_CCS(new CheckJoinCCS.Request(userId, vehicleVO.getVin()));
                        if (response != null) {
                            try {
                                if (!TextUtils.isEmpty(response.getCarId()) && response.isMaster()) {
                                    targetList.add(new CarConnectVO(vehicleVO.getVin(), "", response.getCarId(), 2, "genesis"));
                                }
                            } catch (Exception ignore) {
                                ignore.printStackTrace();
                            }
                        }
                    }
                }
            }
            return targetList;
        });

        try {
            return future.get();
        } finally {
            es.shutDownExcutor();
        }
    }


    public List<CarConnectVO> checkCarId(String userId, String accessToken, List<CarConnectVO> targetList) throws ExecutionException, InterruptedException {
        ExecutorService es = new ExecutorService("");
        Future<List<CarConnectVO>> future = es.getListeningExecutorService().submit(() -> {
                try {
                    //???????????? ?????? ????????? 1????????? ?????????
                    if (targetList != null && targetList.size() > 0) {
                        //???????????? ??? CARID??? ????????????????????? ???????????? ???????????? ?????? ????????? ?????? ??????
                        registerCarIdToDevelopers(targetList, userId);
                        //???????????? ??? CARID??? ????????????????????? ?????? ???????????? car id??? DB??? ??????
                        updateCarIdToLocal(targetList, userId, accessToken);
                    }
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
            return targetList;
        });

        try {
            return future.get();
        } finally {
            es.shutDownExcutor();
        }
    }

    private void updateCarIdToLocal(List<CarConnectVO> targetList, String userId, String accessToken) {
        if (targetList != null && targetList.size() > 0 && !TextUtils.isEmpty(userId)) {
            CarId.Response carIdResLast = repository.REQ_SYNC_CAR_ID(new CarId.Request(userId));

            if(carIdResLast != null) { //???????????? ?????? ?????? ????????? carid??? ???????????? ??????
                if (carIdResLast.getCars() != null && carIdResLast.getCars().size() > 0) {
                    for (int i = 0; i < targetList.size(); i++) {
                        for (CarVO carVO : carIdResLast.getCars()) {
                            if (targetList.get(i).getVin().equalsIgnoreCase(carVO.getVin())) {
                                targetList.get(i).setCarId(carVO.getCarId());
                                try {
                                    targetList.get(i).setResult(reqAgreements(new Agreements.Request(userId, carVO.getCarId(), accessToken), false));
                                } catch (Exception ignore) {

                                }
                                break;
                            }
                        }
                    }
                }
                dbVehicleRepository.insertOrUpdateCarConnect(targetList);
            }
        }
    }


//    /**
//     * @biref Developers CardID ?????? ?????? ?????? ??????
//     * DB??? ???????????? ?????????????????? CARID??? ?????? ????????? ???????????? ??????
//     */
//    private List<CarConnectVO> getTargetList() {
//        List<CarConnectVO> targetList = new ArrayList<>();
//        List<VehicleVO> list = new ArrayList<>();
//        try {
//            //?????? db??? ?????? ???????????? ??????
//            list.addAll(dbVehicleRepository.getVehicleList(MAIN_VEHICLE_TYPE_OV));
//            for (VehicleVO vehicleVO : list) {
//                try {
//                    CarConnectVO carConnectVO = dbVehicleRepository.getCarConnect(vehicleVO.getVin());//?????? db??? vin??? ???????????? ?????? ??????????????? ??????????????? ???????????? ?????? ??????????????? ??????
//                    if ((carConnectVO == null || TextUtils.isEmpty(carConnectVO.getCarId()))
//                            && !TextUtils.isEmpty(vehicleVO.getVin())) {//?????? db??? ????????? ??????????????? ???????????? carId??? ????????? ??????
//
//                        //carId??? ?????? ????????? ???????????? ??????
//                        targetList.add(new CarConnectVO(vehicleVO.getVin(), "", "", 2, ""));
//                    }else if(carConnectVO != null&&!TextUtils.isEmpty(carConnectVO.getCarId())&& !TextUtils.isEmpty(vehicleVO.getVin())){
//                        //??????????????? ?????? ??????????????? ?????? vin??? ????????? (?????? carid??? ????????? carid?????? ?????? ??????
//                        targetList.add(new CarConnectVO(vehicleVO.getVin(), "", carConnectVO.getCarId(), 2, ""));
//                    }
//                } catch (Exception ignore) {
//
//                }
//            }
//
//        } catch (Exception ignore) {
//
//        }
//        return targetList;
//    }


//    private List<CarConnectVO> getDeveloperCarInfo(List<CarConnectVO> targetList) {
//
//        List<CarConnectVO> newTargetList = new ArrayList<>();
//
//        if (targetList != null && targetList.size() > 0) {
//            //GCS??? ????????? ?????? ????????? ??????
//            CarCheck.Response carCheckRes = repository.REQ_SYNC_CAR_CHECK(new CarCheck.Request());
//            //GCS??? ????????? ????????? ?????????
//            if (carCheckRes != null && carCheckRes.getCars() != null && carCheckRes.getCars().size() > 0) {
//                //GCS?????? ????????? TARGETLIST??? SET ??????
//                for (CarVO carVO : carCheckRes.getCars()) {
//                    for (int i = 0; i < targetList.size(); i++) {
//                        if (carVO.getVin().equalsIgnoreCase(targetList.get(i).getVin())) {
//                            if(!TextUtils.isEmpty(targetList.get(i).getMasterCarId())&&(targetList.get(i).getMasterCarId().equalsIgnoreCase(carVO.getCarId()))){
//                                //DB??? ?????? ??????????????? ??????????????? ?????? ??? ???????????? ?????????
//
//                            }else{
//                                //???????????? ????????? (?????? ?????? ????????? ??????)
//                                targetList.get(i).setMasterCarId(TextUtils.isEmpty(carVO.getCarId()) ? "" : carVO.getCarId());
//                                targetList.get(i).setCarName(TextUtils.isEmpty(carVO.getCarName()) ? "" : carVO.getCarName());
//                                newTargetList.add(targetList.get(i));
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return newTargetList;
//    }

    private void registerCarIdToDevelopers(List<CarConnectVO> targetList, String userId) {
        if (targetList != null && targetList.size() > 0 && !TextUtils.isEmpty(userId)) {
            //???????????? ??? CARID??? ????????????????????? ???????????? ???????????? ?????? ????????? ?????? ??????
            CarId.Response carIdRes = repository.REQ_SYNC_CAR_ID(new CarId.Request(userId));
            List<CarConnectVO> reqConnectList = new ArrayList<>();

            for(CarConnectVO target : targetList){
                CarVO carVO;
                try{
                    carVO = carIdRes.getCars().stream().filter(data -> data.getVin().equalsIgnoreCase(target.getVin())).findAny().orElse(null);
                }catch (Exception e){
                    carVO = null;
                }
                if(carVO==null){
                    CarConnectVO carConnectVO = new CarConnectVO("", target.getMasterCarId(), "", 2, "genesis");
                    reqConnectList.add(carConnectVO);
                }
            }

            if(reqConnectList.size() > 0){
                CarConnect.Response carConnectRes = repository.REQ_SYNC_CAR_CONNECT(new CarConnect.Request(reqConnectList, userId));
            }

//            for (int i = 0; i < targetList.size(); i++) {
//                CarConnectVO target = targetList.get(i);
//                if(carIdRes==null||carIdRes.getCars()==null||carIdRes.getCars().size()<1){
//                    CarConnectVO carConnectVO = new CarConnectVO("", target.getMasterCarId(), "", 2, target.getCarName());
//                    reqConnectList.add(carConnectVO);
//                }else{
//                    //????????? ??????????????? ?????? ???????????? ????????? ?????? ????????? ??????????????? ???????????? ????????? ????????? ?????? (????????? ccs ?????? ????????? ??????)
//                    CarVO carVO = carIdRes.getCars().stream().filter(data -> data.getVin().equalsIgnoreCase(target.getVin())).findAny().orElse(null);
//                    if(carVO==null){
//                        CarConnectVO carConnectVO = new CarConnectVO("", target.getMasterCarId(), "", 2, target.getCarName());
//                        reqConnectList.add(carConnectVO);
//                    }else if(TextUtils.isEmpty(carVO.getCarId())){
//                        //????????? ???????????? ??? ???????????? ???????????? ??????. ????????? ?????? ??????
//                        CarConnectVO carConnectVO = new CarConnectVO("", target.getMasterCarId(), "", 2, target.getCarName());
//                        reqConnectList.add(carConnectVO);
//                    }
//
//
//
////                    for(CarVO carVO : carIdRes.getCars()) {
////                        if (targetList.get(i).getVin().equalsIgnoreCase(carVO.getVin())) {
////                            if (TextUtils.isEmpty(carVO.getCarId())) {
////                                CarConnectVO carConnectVO = new CarConnectVO("", targetList.get(i).getMasterCarId(), "", 2, targetList.get(i).getCarName());
////                                list.add(carConnectVO);
////                            }
////                        }
////                    }
//                }
//            }
        }
    }



    /**
     * @param value
     * @return
     * @brief ?????? ?????? ??????
     */
    public String getDistanceUnit(int value) {
        String unit = "km";
        switch (value) {
            case 0:
                unit = " feet";
                break;
            case 2:
                unit = " meter";
                break;
            case 3:
                unit = " miles";
                break;
            case 1:
            default:
                unit = " km";
                break;
        }
        return unit;
    }

    public String getCarId(String vin) {
        CarConnectVO carConnectVO = null;
        String carId = "";
        try {
            if (!TextUtils.isEmpty(vin))
                carConnectVO = dbVehicleRepository.getCarConnect(vin);

            if (carConnectVO != null)
                carId = carConnectVO.getCarId();

        } catch (Exception e) {

        }

        return carId;
    }

    public CarConnectVO getCarConnectVO(String vin) {
        CarConnectVO carConnectVO = null;
        try {
            if (!TextUtils.isEmpty(vin))
                carConnectVO = dbVehicleRepository.getCarConnect(vin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carConnectVO;
    }


    public String getDateYyyyMMdd(int day) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        if (day != 0) calendar.add(Calendar.DATE, day);

        String date = "";

        try {
            date = DateUtil.getDate(calendar.getTime(), DateUtil.DATE_FORMAT_yyyyMMdd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    public VehicleVO getMainVehicleSimplyFromDB() throws ExecutionException, InterruptedException {
        ExecutorService es = new ExecutorService("");
        Future<VehicleVO> future = es.getListeningExecutorService().submit(() -> {
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
        } finally {
            es.shutDownExcutor();
        }
    }

    /**
     * ???????????? ?????? ?????? Foramt String ?????? ??????.
     *
     * @param distance ??????
     * @param unit     Developers ?????? ????????? ?????? ?????? ID
     * @return ????????? ?????? ?????? ?????? String.
     */
    public static String getDistanceFormatByUnit(int distance, int unit) {
        String format;
        switch (unit) {
            case 0:
                format = "%,3d feet";
                break;
            case 2:
                format = "%,3d meter";
                break;
            case 3:
                format = "%,3d miles";
                break;
            case 1:
            default:
                format = "%,3d km";
                break;
        }
        return String.format(format, distance);
    }

    /**
     * Developer API??? ????????? ????????? ???????????? ?????? GRA-GNS-1010 ????????? ????????? ????????? ??????????????? ??????.
     *
     * @param sestCode Developer API ????????? ??????.
     * @return GRA-GNS-1010 ????????? ????????? ??????. ??????????????? ????????? 0?????? ??????.
     */
    public static String getServiceCodeBySestCode(int sestCode) {
        String result = "0";
        for (SEST item : SEST.values()) {
            if (item.devCode == sestCode) {
                result = item.graGns1010Code;
                break;
            }
        }
        return result;
    }

    /**
     * @param token  CCSP ACCESS TOKEN
     * @param userId CCSP USER ID
     * @param carId  CCS CAR ID
     * @return ????????? ????????? ?????? URL
     * @biref ?????????????????? ?????? ????????? URL ??????
     */
    public String getDataMilesDetailUrl(String token, String userId, String carId) {
        return GAInfo.GA_DATAMILES_DETAIL_URL + getParams(token, userId, carId).getQuery();
    }

    /**
     * @param token  CCSP ACCESS TOKEN
     * @param userId CCSP USER ID
     * @param carId  CCS CAR ID
     * @return ????????? ????????? ?????? ?????? URL
     * @biref ?????????????????? ???????????? ????????? URL ??????
     */
    public String getDataMilesAgreementsUrl(String token, String userId, String carId) {
        return GAInfo.GA_DATAMILES_AGREEMENTS_URL + getParams(token, userId, carId).getQuery();
    }

    public QueryString getParams(String token, String userId, String carId) {
        QueryString q = new QueryString();
        q.add(GAInfo.GA_DATAMILES_KEY_TOKEN, token);
        q.add(GAInfo.GA_DATAMILES_KEY_USER_ID, userId);
        q.add(GAInfo.GA_DATAMILES_KEY_CAR_ID, carId);
        return q;
    }

    public enum CCSSTAT {
        STAT_AGREEMENT,
        STAT_DISAGREEMENT,
        STAT_DISABLE
    }

    public CCSSTAT checkCarInfoToDevelopers(String vin, String userId) {
        CarConnectVO carConnectVO = getCarConnectVO(vin);
        //ccs ????????? ????????? ??????
        if (carConnectVO != null && !TextUtils.isEmpty(carConnectVO.getCarId())) {
            if (carConnectVO.isResult()) {
                return CCSSTAT.STAT_AGREEMENT;
            } else {
                return CCSSTAT.STAT_DISAGREEMENT;
            }
        } else {
            //ccs ??? ?????? ????????? ??????
            return CCSSTAT.STAT_DISABLE;
        }
    }

    private final String TIME_FORMAT = "%d?????? %d???";
    public String getBatteryChargeTime() {
        String time = "";
        if (RES_EV_STATUS!=null&&RES_EV_STATUS.getValue()!=null&&RES_EV_STATUS.getValue().data!=null&&RES_EV_STATUS.getValue().data.getRemainTime()!=null&&RES_EV_STATUS.getValue().data.getRemainTime().getUnit() < 4) {
            int value = (int) RES_EV_STATUS.getValue().data.getRemainTime().getValue();
            final int unit = (int) RES_EV_STATUS.getValue().data.getRemainTime().getUnit();
            int hour;
            int min;
            switch (unit) {
                case 0:
                    //hour
                    time = String.format(Locale.getDefault(), TIME_FORMAT, value, 0);
                    break;
                case 1:
                    //min
                    hour = value / 60;
                    min = value % 60;
                    time = String.format(Locale.getDefault(), TIME_FORMAT, hour, min);
                    break;
                case 2:
                    //mesce
                    hour = value / 3600000;
                    min = value % 3600000 / 60 / 1000;
                    time = String.format(Locale.getDefault(), TIME_FORMAT, hour, min);
                    break;
                case 3:
                    //sec
                    hour = value / 3600;
                    min = value % 3600 / 60;
                    time = String.format(Locale.getDefault(), TIME_FORMAT, hour, min);
                    break;
                default:
                    //none
                    break;
            }

        }
        return time;
    }


//    public CCSSTAT checkCarInfoToDevelopers(String vin, String userId) {
//        CarConnectVO carConnectVO = getCarConnectVO(vin);
//        //ccs ????????? ????????? ??????
//        if (carConnectVO != null && !TextUtils.isEmpty(carConnectVO.getCarId())) {
//            if (carConnectVO.isResult()) {
//                return CCSSTAT.STAT_AGREEMENT;
//            } else {
//                //???????????? ??????????????? ??? ??????
//                boolean result = false;
//
//                try {
//                    //?????? ????????? ????????? ??????
//                    result = reqAgreements(new Agreements.Request(userId, carConnectVO.getCarId()), true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                if (result) {
//                    //????????? ??? ??????
//                    return CCSSTAT.STAT_AGREEMENT;
//                } else {
//                    //???????????? ?????? ??????
//                    return CCSSTAT.STAT_DISAGREEMENT;
//                }
//            }
//        } else {
//            //ccs ??? ?????? ????????? ??????
//            return CCSSTAT.STAT_DISABLE;
//        }
//    }

    public boolean needUpdateCarId(String errCode, ResultCallback callback){
        try{
            if("4043".equalsIgnoreCase(errCode)||"4101".equalsIgnoreCase(errCode)){
                try {
                    //4043??? ?????? ?????? ??? ?????? ????????? carId??? ????????????
                    dbVehicleRepository.updateCarConnectCarId("", getMainVehicleSimplyFromDB().getVin());
                    checkVehicleCarId(callback);
                    return true;
                }catch (Exception e){

                }
            }
        }catch (Exception ignore){

        }
        return false;
    }

}