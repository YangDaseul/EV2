package com.genesis.apps.ui.myg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.MYP_2001;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.RequestCodes;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.model.vo.CardVO;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.viewmodel.MYPViewModel;
import com.genesis.apps.databinding.ActivityMygMembershipBinding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.common.view.listener.ViewPressEffectHelper;
import com.genesis.apps.ui.myg.view.CardHorizontalAdapter;

import java.util.ArrayList;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

public class MyGMembershipActivity extends SubActivity<ActivityMygMembershipBinding> {

    private MYPViewModel mypViewModel;
    private CardHorizontalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myg_membership);
        getDataFromIntent();
        setViewModel();
        setObserver();
        initView();
        mypViewModel.reqMYP2001(new MYP_2001.Request(APPIAInfo.MG_MEMBER01.getId()));
    }

    private void initView() {
        adapter = new CardHorizontalAdapter(onSingleClickListener);
        ui.viewpager.setAdapter(adapter);
        ui.viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
//        ui.viewpager.setCurrentItem(0);

        final float pageMargin = getResources().getDimensionPixelOffset(R.dimen.offset2);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset2);
        ui.viewpager.setPageTransformer((page, position) -> {
            float myOffset = position * -(2 * pageOffset + pageMargin);
            if (position < -1) {
                page.setTranslationX(-myOffset);

            } else if (position <= 1) {
                float scaleFactor = Math.max(1f, 1 - Math.abs(position - 0.14285715f));
                page.setTranslationX(myOffset);
                page.setScaleY(scaleFactor);
                page.setScaleX(scaleFactor);
                page.setAlpha(scaleFactor);
            } else {
                page.setAlpha(0f);
                page.setTranslationX(myOffset);
            }

        });

        ViewPressEffectHelper.attach(ui.btnPassword);
    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        ui.setActivity(this);
        mypViewModel = new ViewModelProvider(this).get(MYPViewModel.class);
    }

    @Override
    public void setObserver() {
        mypViewModel.getRES_MYP_2001().observe(this, result -> {
            switch (result.status){
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if(result.data!=null){
                        ui.setData(result.data);
                        mypViewModel.reqNewCardList(result.data.getBlueMbrCrdList()==null ? new ArrayList<>() : result.data.getBlueMbrCrdList());
                        break;
                    }
                default:
                    showProgressDialog(false);
                    String serverMsg="";
                    try {
                        serverMsg = result.data.getRtMsg();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        if(TextUtils.isEmpty(serverMsg)) serverMsg = getString(R.string.r_flaw06_p02_snackbar_1);
                        SnackBarUtil.show(this, serverMsg);

                        setEmptyCardView();
                    }
                    break;
            }
        });

        mypViewModel.getCardVoList().observe(this, result -> {
            if(result.data==null||result.data.size()<1){
                setEmptyCardView();
            }else{
                ui.btnPassword.setVisibility(View.VISIBLE);
                ui.btnQuestion.setVisibility(View.VISIBLE);
                ui.tvEmptyCard.setVisibility(View.GONE);
                ui.viewpager.setOffscreenPageLimit(result.data.size());
                adapter.setRows(result.data);
                adapter.notifyDataSetChanged();
                ui.tvStar.setVisibility(adapter.getItemCount()<2 ? View.GONE : View.VISIBLE);
                //??????????????? ????????? ???????????????????????? ??????????????? ????????? ????????? ???????????? ???????????? 100ms ??? ?????? ??????
                new Handler().postDelayed(() -> ui.viewpager.setCurrentItem(0, true), 100);
            }

        });
    }

    @Override
    public void getDataFromIntent() {

    }

    private void setEmptyCardView(){
        ui.tvEmptyCard.setVisibility(View.VISIBLE);
        ui.btnPassword.setVisibility(View.GONE);
        ui.btnQuestion.setVisibility(View.GONE);
    }

    @Override
    public void onClickCommon(View v) {

        int pos = -1;
        try {
            pos = Integer.parseInt(v.getTag(R.id.item_position).toString());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }

        switch (v.getId()) {
            case R.id.l_whole:

                if(pos>-1){
                    CardVO cardVO = ((CardVO) adapter.getItem(pos));
                    if(cardVO.getCardStusNm().equalsIgnoreCase(CardVO.CARD_STATUS_99)){
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(WebView.SCHEME_TEL + getString(R.string.word_membership_4))));
                    }else{
                        //????????????
                   }
                }


                break;
            case R.id.iv_favorite:
                if (!((CardVO) adapter.getItem(pos)).isFavorite() && !TextUtils.isEmpty(((CardVO) adapter.getItem(pos)).getCardNo())) {
                    mypViewModel.reqChangeFavoriteCard(((CardVO) adapter.getItem(pos)).getCardNo(), adapter.getItems());
                }

                break;
            case R.id.btn_use_list:
                startActivitySingleTop(new Intent(this, MyGMembershipUseListActivity.class).putExtra(KeyNames.KEY_NAME_MEMBERSHIP_MBR_MGMT_NO, ""), RequestCodes.REQ_CODE_ACTIVITY.getCode(), VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            case R.id.btn_password:
                startActivitySingleTop(new Intent(this, MyGMembershipCardPasswordActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(),VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
            case R.id.tv_extnc_point:
                startActivitySingleTop(new Intent(this, MyGMembershipExtncActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(),VariableType.ACTIVITY_TRANSITION_ANIMATION_VERTICAL_SLIDE);
                break;
            case R.id.btn_question:
                startActivitySingleTop(new Intent(this, MyGMembershipInfoActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(),VariableType.ACTIVITY_TRANSITION_ANIMATION_VERTICAL_SLIDE);
                break;
            case R.id.btn_use_case_info://????????? ??????
                startActivitySingleTop(new Intent(this, MyGMembershipUseCaseActivity.class), RequestCodes.REQ_CODE_ACTIVITY.getCode(),VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                break;
        }

    }

}
