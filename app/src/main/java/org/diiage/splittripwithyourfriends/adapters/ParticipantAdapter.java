package org.diiage.splittripwithyourfriends.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.HomeTripActivity;
import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.entities.Participant;

import java.util.List;

public class ParticipantAdapter extends RecyclerView.Adapter {

    public static class ParticipantViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView txtParticipantName;

        public ParticipantViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.txtParticipantName = itemView.findViewById(R.id.txtParticipantName);
        }
    }
    private List<Participant> lstParticipants;

    public void setParticipantsList(List<Participant> movieList) {
        this.lstParticipants = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.participant_item, parent, false);
        return new ParticipantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ParticipantViewHolder) {
            ((ParticipantViewHolder) holder).txtParticipantName.setText(this.lstParticipants.get(position).getName());

            String participantName= this.lstParticipants.get(position).getName();
            long participantId= this.lstParticipants.get(position).getId();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), HomeTripActivity.class);
                    Bundle b = new Bundle();
                    b.putString("ParamParticipantName", participantName);
                    b.putLong("ParamParticipantId", participantId);
                    intent.putExtras(b);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (lstParticipants != null) {
            return lstParticipants.size();

        }
        return 0;
    }
}
