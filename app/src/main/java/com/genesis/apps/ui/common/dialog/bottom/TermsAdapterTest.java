package com.genesis.apps.ui.common.dialog.bottom;

import android.text.Html;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import com.genesis.apps.R;
import com.genesis.apps.comm.model.vo.TermVO;
import com.genesis.apps.databinding.ItemTermBinding;
import com.genesis.apps.ui.common.view.listener.OnSingleClickListener;
import com.genesis.apps.ui.common.view.listview.BaseRecyclerViewAdapter2;
import com.genesis.apps.ui.common.view.viewholder.BaseViewHolder;

import java.util.Objects;

import androidx.annotation.NonNull;

public class TermsAdapterTest extends BaseRecyclerViewAdapter2<TermVO> {
    private static final String TAG = TermsAdapterTest.class.getSimpleName();
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private BottomDialogAskAgreeTerm BottomDialogAskAgreeTerm;
    public OnSingleClickListener onSingleClickListener;
    private boolean isTermEsnAgmtYn = true;

    //설마 그럴 일은 없다고 생각하지만 add/remove 호출하면 안됨
    public TermsAdapterTest(BottomDialogAskAgreeTerm dialog, OnSingleClickListener listener) {
        BottomDialogAskAgreeTerm = dialog;
        onSingleClickListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;//다 똑같음
    }

    public void setTermEsnAgmtYn(boolean isTermEsnAgmtYn){
        this.isTermEsnAgmtYn = isTermEsnAgmtYn;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TermViewHolder(getView(parent, R.layout.item_term), isTermEsnAgmtYn);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Log.d(TAG, "TermsAdapter onBindViewHolder position : " + position);
        holder.onBindView(getItem(position), position, selectedItems);
    }

    public void setAllCheck(boolean checked) {
        for (int i = 0; i < getItemCount(); i++) {
            selectedItems.put(i, checked);
        }
        notifyDataSetChanged();
    }

    public boolean isAll() {
        for (int i = 0; i < getItemCount(); i++) {
            if (!selectedItems.get(i)) {
                return false;
            }
        }
        return true;
    }


    public class TermViewHolder extends BaseViewHolder<TermVO, ItemTermBinding> {

        private boolean isTermEsnAgmtYn = true;

        public TermViewHolder(View itemView, boolean isTermEsnAgmtYn) {
            super(itemView);
            this.isTermEsnAgmtYn = isTermEsnAgmtYn;
            Log.d(TAG, "TermViewHolder: ");
        }

        @Override
        public void onBindView(TermVO item) {
            //do nothing
        }

        @Override
        public void onBindView(TermVO item, int pos) {
            //do nothing
        }

        @Override
        public void onBindView(TermVO item, int pos, SparseBooleanArray selectedItems) {
            Log.d(TAG, "TermViewHolder onBindView(): ");

            String termName = item.getTermNm();
            if (isTermEsnAgmtYn&&Objects.equals(item.getTermEsnAgmtYn(), "Y")) {
                termName += getContext().getString(R.string.terms_essential);
            }
            getBinding().cb.setText(Html.fromHtml(termName, Html.FROM_HTML_MODE_COMPACT));
            getBinding().cb.setChecked(selectedItems.get(pos));
            getBinding().lWhole.setBackgroundResource(R.drawable.ripple_bg_ffffff);
            getBinding().ivLine.setVisibility(View.GONE);
            getBinding().cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    selectedItems.put(pos, true);
                } else {
                    selectedItems.delete(pos);
                }

                BottomDialogAskAgreeTerm.validateCheck(selectedItems);
                BottomDialogAskAgreeTerm.setAllAgree(isAll());
            });

//            String target = getContext().getString(R.string.int03_3); //(필수)
//            if(termName.contains(target)){
//                int start = termName.indexOf(target.charAt(0));
//                int end = start + target.length();
//                Spannable span = (Spannable)getBinding().cb.getText();
//                span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.x_996449)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
            getBinding().ivArrow.setTag(R.id.tag_term_vo, item);
            getBinding().setListener(onSingleClickListener);
        }
    }
}
