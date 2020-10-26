package com.genesis.apps.ui.main.home;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.ResultCodes;
import com.genesis.apps.comm.model.vo.map.ReverseGeocodingReqVO;
import com.genesis.apps.comm.net.NetUIResponse;
import com.genesis.apps.comm.viewmodel.MapViewModel;
import com.genesis.apps.databinding.ActivityMap2Binding;
import com.genesis.apps.databinding.LayoutMapOverlayUiBottomAddressBinding;
import com.genesis.apps.ui.common.activity.GpsBaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hmns.playmap.PlayMapPoint;
import com.hmns.playmap.extension.PlayMapGeoItem;
import com.hmns.playmap.shape.PlayMapMarker;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MyLocationActivity extends GpsBaseActivity<ActivityMap2Binding> {
    private MapViewModel mapViewModel;
    private List<String> vehiclePosition;
    private Double[] myPosition = new Double[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        getDataFromIntent();
        setViewModel();
        setObserver();
        reqMyLocation();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void getDataFromIntent() {
        try {
            vehiclePosition = new Gson().fromJson(getIntent().getStringExtra(KeyNames.KEY_NAME_VEHICLE_LOCATION), new TypeToken<List<String>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(vehiclePosition==null||vehiclePosition.size()!=2){
                exitPage("위치 정보가 존재하지 않습니다.\n잠시후 다시 시도해 주십시오.", ResultCodes.REQ_CODE_EMPTY_INTENT.getCode());
            }
        }
    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);

    }

    @Override
    public void setObserver() {

        mapViewModel.getPlayMapGeoItem().observe(this, result -> {

            switch (result.status){
                case LOADING:
                    showProgressDialog(true);


                    break;
                case SUCCESS:
                    showProgressDialog(false);

                    if(result.data!=null){
                        initView(result.data);
                    }

                    break;
                default:
                    showProgressDialog(false);

                    break;
            }


        });

    }

    private void initView(PlayMapGeoItem item) {
        setViewStub(R.id.vs_map_overlay_bottom_box, R.layout.layout_map_overlay_ui_bottom_address, (viewStub, inflated) -> {
            LayoutMapOverlayUiBottomAddressBinding binding = DataBindingUtil.bind(inflated);
            binding.tvMapAddressBtn.setVisibility(View.GONE);
            if(TextUtils.isEmpty(item.title)){
                binding.tvMapAddressTitle.setVisibility(View.GONE);
            }else {
                binding.tvMapAddressTitle.setText(item.title);
            }
            binding.tvMapAddressAddress.setText(item.addr);
        });
        ui.pmvMapView.initMap( Double.parseDouble(vehiclePosition.get(0)), Double.parseDouble(vehiclePosition.get(1)),17);
        drawMarkerItem(vehiclePosition.get(0),vehiclePosition.get(1));
        ui.btnMyPosition.setOnClickListener(onSingleClickListener);
        ui.lMapOverlayTitle.tvMapTitleText.setVisibility(View.GONE);
    }


    @Override
    public void onClickCommon(View v) {

        switch (v.getId()){
            case R.id.btn_my_position://선택
                ui.pmvMapView.initMap(myPosition[0], myPosition[1], 17);
                break;
        }

    }

    /**
     * drawMarkerItem 지도에 마커를 그린다.
     */
    public void drawMarkerItem(String mapXcooNm, String mapYcooNm) {
        PlayMapMarker markerItem = new PlayMapMarker();
//        PlayMapPoint point = mapView.getMapCenterPoint();
        PlayMapPoint point = new PlayMapPoint(Double.parseDouble(mapXcooNm), Double.parseDouble(mapYcooNm));
        markerItem.setMapPoint(point);
//        markerItem.setCalloutTitle("제목");
//        markerItem.setCalloutSubTitle("내용");
        markerItem.setCanShowCallout(false);
        markerItem.setAutoCalloutVisible(false);
        markerItem.setIcon(((BitmapDrawable)getResources().getDrawable(R.drawable.ic_pin_carcenter,null)).getBitmap());

        String strId = String.format("marker_%d", 0);
        ui.pmvMapView.addMarkerItem(strId, markerItem);
    }


    private void reqMyLocation() {
        showProgressDialog(true);
        findMyLocation(location -> {
            showProgressDialog(false);
            if (location == null) {
                exitPage("위치 정보를 불러올 수 없습니다. GPS 상태를 확인 후 다시 시도해 주세요.", ResultCodes.REQ_CODE_EMPTY_INTENT.getCode());
                return;
            }

            runOnUiThread(() -> {
                //현재 단말기 위치 정보 저장
                myPosition[0] = location.getLatitude();
                myPosition[1] = location.getLongitude();
                //차량 위치에 대한 주소 정보 요청
                mapViewModel.reqPlayMapGeoItem(new ReverseGeocodingReqVO(Double.parseDouble(vehiclePosition.get(0)),Double.parseDouble(vehiclePosition.get(1)),0));
            });

        }, 5000);
    }

}