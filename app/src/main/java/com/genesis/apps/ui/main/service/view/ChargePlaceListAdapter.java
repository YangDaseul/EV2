package com.genesis.apps.ui.main.service.view;

import android.text.Html;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.constants.ChargePlaceStatus;
import com.genesis.apps.comm.model.vo.ChargeEptInfoVO;
import com.genesis.apps.databinding.ItemChargePlaceBinding;
import com.genesis.apps.ui.common.view.listview.BaseRecyclerViewAdapter2;
import com.genesis.apps.ui.common.view.viewholder.BaseViewHolder;

/**
 * Class Name : ChargePlaceListAdapter
 *
 * @author Ki-man Kim
 * @since 2021-03-22
 */
public class ChargePlaceListAdapter extends BaseRecyclerViewAdapter2<ChargeEptInfoVO> {
    public ChargePlaceListAdapter() {

    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ChargePlaceViewHolder(layoutInflater.inflate(R.layout.item_charge_place, parent, false));
    }

    private static class ChargePlaceViewHolder extends BaseViewHolder<ChargeEptInfoVO, ItemChargePlaceBinding> {
        public ChargePlaceViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(ChargeEptInfoVO item) {
            ItemChargePlaceBinding binding = getBinding();
            binding.tvChargeName.setText(item.getChgName() + " " + item.getDist());

//            switch (item.status) {
//                case FINISH_BOOK:
//                case ABLE_BOOK: {
//                    binding.tvChargeName.setText(Html.fromHtml(binding.tvChargeName.getText() + " | <font color=#996449>" + item.status.getName() + "</font>"));
//                    break;
//                }
//                default:
//                case CHECKING: {
//                    break;
//                }
//            }

            if (!TextUtils.isEmpty(item.getChgStusCd())) {
                // 충전소 상태 표시.
                ChargePlaceStatus status = ChargePlaceStatus.valueOf(item.getChgStusCd());
                binding.tvChargeStatus.setText(status.getTitle());
            }
        }

        @Override
        public void onBindView(ChargeEptInfoVO item, int pos) {

        }

        @Override
        public void onBindView(ChargeEptInfoVO item, int pos, SparseBooleanArray selectedItems) {

        }
    } // end of class ChargePlaceViewHolder
} // end of class ChargePlaceListAdapter
