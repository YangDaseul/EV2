package com.genesis.apps.comm.viewmodel;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.genesis.apps.comm.model.gra.api.REQ_1001;
import com.genesis.apps.comm.model.gra.api.REQ_1002;
import com.genesis.apps.comm.model.gra.api.REQ_1003;
import com.genesis.apps.comm.model.gra.api.REQ_1004;
import com.genesis.apps.comm.model.gra.api.REQ_1005;
import com.genesis.apps.comm.model.gra.api.REQ_1007;
import com.genesis.apps.comm.model.gra.api.REQ_1008;
import com.genesis.apps.comm.model.gra.api.REQ_1009;
import com.genesis.apps.comm.model.gra.api.REQ_1010;
import com.genesis.apps.comm.model.gra.api.REQ_1011;
import com.genesis.apps.comm.model.gra.api.REQ_1012;
import com.genesis.apps.comm.model.gra.api.REQ_1013;
import com.genesis.apps.comm.model.gra.api.REQ_1014;
import com.genesis.apps.comm.model.gra.api.REQ_1015;
import com.genesis.apps.comm.model.repo.DBVehicleRepository;
import com.genesis.apps.comm.model.repo.REQRepo;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.net.NetUIResponse;
import com.genesis.apps.comm.util.excutor.ExecutorService;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lombok.Data;

public @Data
class REQViewModel extends ViewModel {

    private final REQRepo repository;
    private final DBVehicleRepository dbVehicleRepository;
    private final SavedStateHandle savedStateHandle;

    private MutableLiveData<NetUIResponse<REQ_1001.Response>> RES_REQ_1001;
    private MutableLiveData<NetUIResponse<REQ_1002.Response>> RES_REQ_1002;
    private MutableLiveData<NetUIResponse<REQ_1003.Response>> RES_REQ_1003;
    private MutableLiveData<NetUIResponse<REQ_1004.Response>> RES_REQ_1004;
    private MutableLiveData<NetUIResponse<REQ_1005.Response>> RES_REQ_1005;
    private MutableLiveData<NetUIResponse<REQ_1007.Response>> RES_REQ_1007;
    private MutableLiveData<NetUIResponse<REQ_1008.Response>> RES_REQ_1008;
    private MutableLiveData<NetUIResponse<REQ_1009.Response>> RES_REQ_1009;
    private MutableLiveData<NetUIResponse<REQ_1010.Response>> RES_REQ_1010;
    private MutableLiveData<NetUIResponse<REQ_1011.Response>> RES_REQ_1011;
    private MutableLiveData<NetUIResponse<REQ_1012.Response>> RES_REQ_1012;
    private MutableLiveData<NetUIResponse<REQ_1013.Response>> RES_REQ_1013;
    private MutableLiveData<NetUIResponse<REQ_1014.Response>> RES_REQ_1014;
    private MutableLiveData<NetUIResponse<REQ_1015.Response>> RES_REQ_1015;


    private MutableLiveData<List<VehicleVO>> vehicleList;

    @ViewModelInject
    REQViewModel(
            REQRepo repository,
            DBVehicleRepository dbVehicleRepository,
            @Assisted SavedStateHandle savedStateHandle) {
        this.repository = repository;
        this.dbVehicleRepository = dbVehicleRepository;
        this.savedStateHandle = savedStateHandle;

        RES_REQ_1001 = repository.RES_REQ_1001;
        RES_REQ_1002 = repository.RES_REQ_1002;
        RES_REQ_1003 = repository.RES_REQ_1003;
        RES_REQ_1004 = repository.RES_REQ_1004;
        RES_REQ_1005 = repository.RES_REQ_1005;
        RES_REQ_1007 = repository.RES_REQ_1007;
        RES_REQ_1008 = repository.RES_REQ_1008;
        RES_REQ_1009 = repository.RES_REQ_1009;
        RES_REQ_1010 = repository.RES_REQ_1010;
        RES_REQ_1011 = repository.RES_REQ_1011;
        RES_REQ_1012 = repository.RES_REQ_1012;
        RES_REQ_1013 = repository.RES_REQ_1013;
        RES_REQ_1014 = repository.RES_REQ_1014;
        RES_REQ_1015 = repository.RES_REQ_1015;

        vehicleList = new MutableLiveData<>();
    }

    public void reqREQ1001(final REQ_1001.Request reqData) {
        repository.REQ_REQ_1001(reqData);
    }

    public void reqREQ1002(final REQ_1002.Request reqData) {
        repository.REQ_REQ_1002(reqData);
    }

    public void reqREQ1003(final REQ_1003.Request reqData) {
        repository.REQ_REQ_1003(reqData);
    }

    public void reqREQ1004(final REQ_1004.Request reqData) {
        repository.REQ_REQ_1004(reqData);
    }

    public void reqREQ1005(final REQ_1005.Request reqData) {
        repository.REQ_REQ_1005(reqData);
    }

    public void reqREQ1007(final REQ_1007.Request reqData) {
        repository.REQ_REQ_1007(reqData);
    }

    public void reqREQ1008(final REQ_1008.Request reqData) {
        repository.REQ_REQ_1008(reqData);
    }

    public void reqREQ1009(final REQ_1009.Request reqData) {
        repository.REQ_REQ_1009(reqData);
    }

    public void reqREQ1010(final REQ_1010.Request reqData) {
        repository.REQ_REQ_1010(reqData);
    }

    public void reqREQ1011(final REQ_1011.Request reqData) {
        repository.REQ_REQ_1011(reqData);
    }

    public void reqREQ1012(final REQ_1012.Request reqData) {
        repository.REQ_REQ_1012(reqData);
    }

    public void reqREQ1013(final REQ_1013.Request reqData) {
        repository.REQ_REQ_1013(reqData);
    }

    public void reqREQ1014(final REQ_1014.Request reqData) {
        repository.REQ_REQ_1014(reqData);
    }

    public void reqREQ1015(final REQ_1015.Request reqData) {
        repository.REQ_REQ_1015(reqData);
    }


    public VehicleVO getMainVehicle() throws ExecutionException, InterruptedException {
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
            es.shutDownExcutor();
        }
    }


}