package org.diiage.splittripwithyourfriends.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.ui.addparticipant.SelectableParticipant;

import java.util.ArrayList;
import java.util.List;

public class SelectableParticipantAdapter extends RecyclerView.Adapter implements SelectableParticipantViewHolder.OnItemSelectedListener {

    private List<SelectableParticipant> mValues;
    private boolean isMultiSelectionEnabled = false;
    private SelectableParticipantViewHolder.OnItemSelectedListener listener;

    public SelectableParticipantAdapter(SelectableParticipantViewHolder.OnItemSelectedListener listener, boolean isMultiSelectionEnabled) {
        this.listener = listener;
        this.isMultiSelectionEnabled = isMultiSelectionEnabled;
        this.mValues = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checked_item, parent, false);

        return new SelectableParticipantViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SelectableParticipantViewHolder holder = (SelectableParticipantViewHolder) viewHolder;
        SelectableParticipant selectableParticipant = mValues.get(position);
        String name = selectableParticipant.getName();
        holder.textView.setText(name);
        if (isMultiSelectionEnabled) {
            TypedValue value = new TypedValue();
            holder.textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, value, true);
            int checkMarkDrawableResId = value.resourceId;
            holder.textView.setCheckMarkDrawable(checkMarkDrawableResId);
        } else {
            TypedValue value = new TypedValue();
            holder.textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true);
            int checkMarkDrawableResId = value.resourceId;
            holder.textView.setCheckMarkDrawable(checkMarkDrawableResId);
        }

        holder.mItem = selectableParticipant;
        holder.setChecked(holder.mItem.isSelected());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void onItemSelected(SelectableParticipant item) {
        if (!isMultiSelectionEnabled) {

            for (SelectableParticipant selectableItem : mValues) {
                if (!selectableItem.equals(item)
                        && selectableItem.isSelected()) {
                    selectableItem.setSelected(false);
                } else if (selectableItem.equals(item)
                        && item.isSelected()) {
                    selectableItem.setSelected(true);
                }
            }
            notifyDataSetChanged();
        }
        listener.onItemSelected(item);
    }

    @Override
    public int getItemViewType(int position) {
        if(isMultiSelectionEnabled){
            return SelectableParticipantViewHolder.MULTI_SELECTION;
        }
        else{
            return SelectableParticipantViewHolder.SINGLE_SELECTION;
        }
    }

    public List<Participant> getSelectedParticipants() {

        List<Participant> selectedParticipants = new ArrayList<>();
        for (SelectableParticipant participant : mValues) {
            if (participant.isSelected()) {
                selectedParticipants.add(participant);
            }
        }
        return selectedParticipants;
    }

    public void setmParticipants(List<Participant> participants) {
        for(Participant participant : participants) {
            mValues.add(new SelectableParticipant(participant, false));
        }
    }
}
