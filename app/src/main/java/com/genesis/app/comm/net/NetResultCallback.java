package com.genesis.app.comm.net;

public interface NetResultCallback {
    void onSuccess(String object);
    void onFail(NetResult e);
    void onError(NetResult e);
}
