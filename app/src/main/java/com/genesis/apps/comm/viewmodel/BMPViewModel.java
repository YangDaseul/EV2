package com.genesis.apps.comm.viewmodel;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.genesis.apps.comm.model.api.gra.BMP_1001;
import com.genesis.apps.comm.model.repo.BMPRepo;
import com.genesis.apps.comm.model.repo.DBVehicleRepository;
import com.genesis.apps.comm.net.NetUIResponse;

import lombok.Data;

public @Data
class BMPViewModel extends ViewModel {

    private final BMPRepo repository;
    private final DBVehicleRepository dbVehicleRepository;
    private final SavedStateHandle savedStateHandle;

    private MutableLiveData<NetUIResponse<BMP_1001.Response>> RES_BMP_1001;

    @ViewModelInject
    BMPViewModel(
            BMPRepo repository,
            DBVehicleRepository dbVehicleRepository,
            @Assisted SavedStateHandle savedStateHandle) {
        this.repository = repository;
        this.dbVehicleRepository = dbVehicleRepository;
        this.savedStateHandle = savedStateHandle;

        RES_BMP_1001 = repository.RES_BMP_1001;
    }

    public void reqBMP1001(final BMP_1001.Request reqData) {
        repository.RES_BMP_1001(reqData);
    }
}