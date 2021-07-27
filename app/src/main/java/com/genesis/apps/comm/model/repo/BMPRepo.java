package com.genesis.apps.comm.model.repo;

import androidx.lifecycle.MutableLiveData;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APIInfo;
import com.genesis.apps.comm.model.api.gra.BMP_1001;
import com.genesis.apps.comm.model.api.gra.CHB_1002;
import com.genesis.apps.comm.net.NetCaller;
import com.genesis.apps.comm.net.NetResult;
import com.genesis.apps.comm.net.NetResultCallback;
import com.genesis.apps.comm.net.NetUIResponse;
import com.google.gson.Gson;

import javax.inject.Inject;

public class BMPRepo {

    NetCaller netCaller;

    public final MutableLiveData<NetUIResponse<BMP_1001.Response>> RES_BMP_1001 = new MutableLiveData<>();



    @Inject
    public BMPRepo(NetCaller netCaller) {
        this.netCaller = netCaller;
    }

    public MutableLiveData<NetUIResponse<BMP_1001.Response>> RES_BMP_1001(final BMP_1001.Request reqData) {
        RES_BMP_1001.setValue(NetUIResponse.loading(null));
        netCaller.reqDataToGRA(new NetResultCallback() {
            @Override
            public void onSuccess(String object) {
                RES_BMP_1001.setValue(NetUIResponse.success(new Gson().fromJson(object, BMP_1001.Response.class)));
            }

            @Override
            public void onFail(NetResult e) {
                RES_BMP_1001.setValue(NetUIResponse.error(e.getMseeage(), null));
            }

            @Override
            public void onError(NetResult e) {
                RES_BMP_1001.setValue(NetUIResponse.error(R.string.error_msg_4, null));
            }
        }, APIInfo.GRA_BMP_1001, reqData);

        return RES_BMP_1001;
    }
}
