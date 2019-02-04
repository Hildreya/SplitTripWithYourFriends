package org.diiage.splittripwithyourfriends.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.ui.addparticipant.SelectableParticipant;

public class SelectableParticipantViewHolder extends RecyclerView.ViewHolder {
    public static final int MULTI_SELECTION = 2;
    public static final int SINGLE_SELECTION = 1;
    CheckedTextView textView;
    SelectableParticipant mItem;
    OnItemSelectedListener itemSelectedListener;


    public SelectableParticipantViewHolder(View view, OnItemSelectedListener listener) {
        super(view);
        itemSelectedListener = listener;
        textView = view.findViewById(R.id.checked_text_item);
        textView.setOnClickListener(view1 -> {
            if (mItem.isSelected() && getItemViewType() == MULTI_SELECTION) {
                setChecked(false);
            } else {
                setChecked(true);
            }
            itemSelectedListener.onItemSelected(mItem);
        });
    }

    public void setChecked(boolean value) {
        if (value) {
            textView.setBackgroundColor(Color.LTGRAY);
        } else {
            textView.setBackground(null);
        }
        mItem.setSelected(value);
        textView.setChecked(value);
    }

    public interface OnItemSelectedListener {

        void onItemSelected(SelectableParticipant item);
    }
}
