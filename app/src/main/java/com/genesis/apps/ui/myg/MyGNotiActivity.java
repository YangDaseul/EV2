package com.genesis.apps.ui.myg;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.MYP_8005;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.util.SnackBarUtil;
import com.genesis.apps.comm.util.StringUtil;
import com.genesis.apps.comm.viewmodel.CMNViewModel;
import com.genesis.apps.comm.viewmodel.MYPViewModel;
import com.genesis.apps.comm.model.vo.NotiVO;
import com.genesis.apps.databinding.ActivityNotiListBinding;
import com.genesis.apps.ui.common.activity.SubActivity;
import com.genesis.apps.ui.myg.view.NotiAccodianRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hjpark
 * @brief 공지사항
 */
public class MyGNotiActivity extends SubActivity<ActivityNotiListBinding> {
    private final String TAG = MyGNotiActivity.class.getSimpleName();
    private static final int PAGE_SIZE = 20;
    private MYPViewModel mypViewModel;
    private CMNViewModel cmnViewModel;
    private NotiAccodianRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti_list);
        getDataFromIntent();
        setViewModel();
        setObserver();
        initView();
        reqMYP8005();
    }

    private void initView() {
        cmnViewModel.updateGlobalDataToDB(KeyNames.KEY_NAME_DB_GLOBAL_DATA_CHECKNOTIDT, StringUtil.isValidString(cmnViewModel.selectGlobalDataFromDB(KeyNames.KEY_NAME_DB_GLOBAL_DATA_NOTIDT)));
        adapter = new NotiAccodianRecyclerAdapter(onSingleClickListener);
//        ((SimpleItemAnimator) ui.rvNoti.getItemAnimator()).setSupportsChangeAnimations(true);
        ui.rvNoti.setLayoutManager(new LinearLayoutManager(this));
        ui.rvNoti.setHasFixedSize(true);
        ui.rvNoti.setAdapter(adapter);

        ui.rvNoti.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!ui.rvNoti.canScrollVertically(1)&&ui.rvNoti.getScrollState()==RecyclerView.SCROLL_STATE_IDLE) { //scroll end
                    if (adapter.getItemCount()>0&&adapter.getItemCount() >= adapter.getPageNo() * PAGE_SIZE)
                        reqMYP8005();
                }
            }
        });
    }

    private void reqMYP8005() {
        mypViewModel.reqMYP8005(new MYP_8005.Request(APPIAInfo.MG_NOTICE01.getId(), "" + (adapter.getPageNo() + 1), "" + PAGE_SIZE));
    }

    @Override
    public void onClickCommon(View v) {

        switch (v.getId()){
            case R.id.l_title:
                int position = Integer.parseInt(v.getTag(R.id.position).toString());
                adapter.eventAccordion(position);
                break;
        }

    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);
        mypViewModel = new ViewModelProvider(this).get(MYPViewModel.class);
        cmnViewModel = new ViewModelProvider(this).get(CMNViewModel.class);
    }

    @Override
    public void setObserver() {
        mypViewModel.getRES_MYP_8005().observe(this, result -> {
            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    //추가할 아이템이 있을 경우만 adaper 갱신
                    if (result.data != null && result.data.getNotiList() != null) {
                        int itemSizeBefore = adapter.getItemCount();
                        if (adapter.getPageNo() == 0) {
                            adapter.setRows(result.data.getNotiList());
//                            adapter.setRows(getListData());
                        } else {
                            adapter.addRows(result.data.getNotiList());
//                          adapter.addRows(getListData());
//                          Log.e(TAG, "itemSizeBefore:"+itemSizeBefore +"   currentSize:"+adapter.getItemCount());
                        }
                        adapter.setPageNo(adapter.getPageNo() + 1);
//                      adapter.notifyDataSetChanged();
                        adapter.notifyItemRangeInserted(itemSizeBefore, adapter.getItemCount());
                        setEmptyView();
                        break;
                    }else if(result.data!=null&&result.data.getRtCd().equalsIgnoreCase("2005")){
                        setEmptyView();
                        break;
                    }
                default:
                    setEmptyView();
                    showProgressDialog(false);
                    String serverMsg="";
                    try {
                        serverMsg = result.data.getRtMsg();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        SnackBarUtil.show(this, serverMsg);
                    }
                    break;
            }
        });
    }

    private void setEmptyView() {
        ui.tvEmpty.setVisibility(adapter==null||adapter.getItemCount() < 1 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void getDataFromIntent() {

    }

    private List<NotiVO> getListData() {
        List<NotiVO> list = new ArrayList<>();
        list.add(new NotiVO("", "", "공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다.", "공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다. 공지사항 상세 내용 입니다.", "20200902110522"));
        for (int i = 0; i < 10; i++) {
            list.add(new NotiVO("", i + "", "test" + i, "content" + i, "20200802180522"));
        }
        return list;
    }
}
