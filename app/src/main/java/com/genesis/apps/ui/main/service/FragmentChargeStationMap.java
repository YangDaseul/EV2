package com.genesis.apps.ui.main.service;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.AddressVO;
import com.genesis.apps.databinding.FragmentMapBinding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.fragment.SubFragment;
import com.genesis.apps.ui.main.home.MyLocationActivity;
import com.google.gson.Gson;
import com.hmns.playmap.PlayMapPoint;
import com.hmns.playmap.shape.PlayMapMarker;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Class Name : FragmentChargeStationMap
 *
 * @author Ki-man Kim
 * @since 2021-04-30
 */
public class FragmentChargeStationMap extends SubFragment<FragmentMapBinding> {
    private final int DEFAULT_ZOOM = 17;
    private double lat;
    private double lot;
    private String addr;
    private String chgName;

    public static FragmentChargeStationMap newInstance(double lat, double lot, String chgName, String addr) {
        Bundle args = new Bundle();
        args.putDouble(KeyNames.KEY_NAME_LAT, lat);
        args.putDouble(KeyNames.KEY_NAME_LOT, lot);
        args.putString(KeyNames.KEY_NAME_CHG_NAME, chgName);
        args.putString(KeyNames.KEY_NAME_CHG_ADDR, addr);

        FragmentChargeStationMap fragment = new FragmentChargeStationMap();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentChargeStationMap() {
    }


    /****************************************************************************************************
     * Override Method
     ****************************************************************************************************/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            lat = args.getDouble(KeyNames.KEY_NAME_LAT);
            lot = args.getDouble(KeyNames.KEY_NAME_LOT);
            chgName = args.getString(KeyNames.KEY_NAME_CHG_NAME);
            addr = args.getString(KeyNames.KEY_NAME_CHG_ADDR);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.setContentView(inflater, R.layout.fragment_map);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        me.setLifecycleOwner(getViewLifecycleOwner());
        initialize();
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
    }

    /****************************************************************************************************
     * Private Method
     ****************************************************************************************************/
    private void initialize() {
        me.pmvMapView.initMap(lat, lot, DEFAULT_ZOOM);
        me.btnMyPosition.setVisibility(View.GONE);
        me.btnPosRefresh.setVisibility(View.GONE);
        drawMarkerItem("station", lat, lot, R.drawable.ic_pin_carcenter);
        new Handler().postDelayed(() -> {
            me.pmvMapView.setClusterEnable(false);
            me.pmvMapView.setPanEnabled(false);
            me.pmvMapView.setNestedScrollingEnabled(false);
            me.pmvMapView.setZoomEnabled(false);
            me.pmvMapView.onMapTouchUpListener((motionEvent, arrayList) -> {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        try {
                            AddressVO addressVO = new AddressVO();
                            addressVO.setCenterLon(lot);
                            addressVO.setCenterLat(lat);
                            addressVO.setAddr(addr);
                            addressVO.setCname(chgName);

//                            List<Double> position = new ArrayList<>();
//                            position.add(lat);
//                            position.add(lot);
                            ((SubActivity)getActivity()).startActivitySingleTop(new Intent(getActivity(), MyLocationActivity.class)
                                    .putExtra(KeyNames.KEY_NAME_ADDR, addressVO)
                                    .putExtra(KeyNames.KEY_NAME_LOCATION_OTHERS, true)
                                    , RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                        }catch (Exception ignore){

                        }
                        break;
                    default:
                        break;
                }
            });
        },1000);
    }

    /**
     * ????????? ???????????? ??????.
     *
     * @param markerId ????????? ????????? ????????? ID
     * @param x        ????????? ?????? ??????
     * @param y        ????????? ?????? ??????
     * @param iconId   ????????? ?????? ????????? Resource ID
     */
    private void drawMarkerItem(String markerId, double x, double y, int iconId) {
        PlayMapMarker markerItem = new PlayMapMarker();
        PlayMapPoint point = new PlayMapPoint(x, y);
        markerItem.setMapPoint(point);
        markerItem.setCanShowCallout(false);
        markerItem.setAutoCalloutVisible(false);
        markerItem.setIcon(((BitmapDrawable) getResources().getDrawable(iconId, null)).getBitmap());
        me.pmvMapView.addMarkerItem(markerId, markerItem);
    }
} // end of class FragmentChargeStationMap
