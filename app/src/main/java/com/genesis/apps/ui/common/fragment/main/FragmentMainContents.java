package com.genesis.apps.ui.common.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.main.contents.ContentsResVO;
import com.genesis.apps.comm.model.main.contents.ContentsViewModel;
import com.genesis.apps.databinding.FragmentMainContentsBinding;
import com.genesis.apps.ui.common.fragment.SubFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

public class FragmentMainContents extends SubFragment<FragmentMainContentsBinding> {

    private ContentsViewModel contentsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.setContentView(inflater, R.layout.fragment_main_contents);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        contentsViewModel = new ViewModelProvider(this).get(ContentsViewModel.class);
        me.setLifecycleOwner(getViewLifecycleOwner());
        contentsViewModel.getContentsList().observe(getViewLifecycleOwner(), arrayListNetUIResponse -> initViewPager(arrayListNetUIResponse.data));
        contentsViewModel.reqTestData();
    }

    private void initViewPager(ArrayList<ContentsResVO> arrayListNetUIResponse){
        me.vp.setAdapter(new ContentsViewpagerAdapter(getActivity(), arrayListNetUIResponse.size(),arrayListNetUIResponse));
        //ViewPager Setting
        me.vp.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        me.vp.setCurrentItem(0);
        me.vp.setOffscreenPageLimit(3);

        me.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    me.vp.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

        });

        final float pageMargin= getResources().getDimensionPixelOffset(R.dimen.vpMargin);

        me.vp.setPageTransformer((view, position) -> {
            float myOffset = position * -(pageMargin);
//                page.setScaleX(1 - (0.25f * abs(position)));
//                page.setTranslationY(pageTranslationY * position);
            final float tmp = 0.75f;

            if (position <= -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left/top page
                view.setAlpha(1);
                ViewCompat.setElevation(view, 1);
                // Counteract the default slide transition
                view.setTranslationY(view.getWidth() * -position); //위로 올리는 형태
                view.setTranslationY(view.getHeight() * -position); //덮는 형태
                view.setTranslationX(0);

                //set Y position to swipe in from top
                float scaleFactor = tmp + (1 - tmp) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
//                    view.setScaleY(1);

            } else if (position <= 1) { // [0,1]
                view.setAlpha(1);
                ViewCompat.setElevation(view, 2);

                // Counteract the default slide transition
                view.setTranslationX(0);
//                    view.setTranslationY(position * view.getHeight());
                view.setTranslationY(myOffset);

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(1);
                view.setScaleY(1);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        });

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClickCommon(View v) {

    }


    @Override
    public void onRefresh() {

    }





}
