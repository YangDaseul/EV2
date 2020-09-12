package com.genesis.apps.comm.module;

import android.app.Activity;

import com.genesis.apps.R;
import com.genesis.apps.chat.SocketIOHelper;
import com.genesis.apps.ui.common.activity.BaseActivity;
import com.genesis.apps.ui.common.dialog.TwoButtonDialog;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class ActivityModule {
    @Provides
    public SocketIOHelper getChatSocket() {
        return new SocketIOHelper();
    }

    @Provides
    public BaseActivity getBaseActivity(Activity activity){
        return (BaseActivity)activity;
    }

//    @Provides
//    public ScreenCaptureUtil getScreenCaptureUtil(Activity activity){
//        return new ScreenCaptureUtil(activity);
//    }

    @Provides
    public TwoButtonDialog getTwoButtonDialog(Activity activity){
        return new TwoButtonDialog(activity, R.style.BottomSheetDialogTheme);
    }
}
