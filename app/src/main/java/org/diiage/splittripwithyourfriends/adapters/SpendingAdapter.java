package org.diiage.splittripwithyourfriends.adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.MainActivity;
import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.entities.Spending;
import org.diiage.splittripwithyourfriends.ui.spending.SpendingDeleteDialogFragment;
import org.diiage.splittripwithyourfriends.ui.spending.SpendingSaveDialogFragment;

import java.util.List;

public class SpendingAdapter extends RecyclerView.Adapter {

    public static class SpendingViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView txtSpendingName;
        public final TextView txtPayerName;
        public final TextView txtSpendingMontant;

        public SpendingViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.txtSpendingName = itemView.findViewById(R.id.tvSpendingName);
            this.txtPayerName = itemView.findViewById(R.id.tvPayerName);
            this.txtSpendingMontant = itemView.findViewById(R.id.tvMontantSpending);
        }
    }

    private List<Spending> lstSpendings;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spending_item, parent, false);
        return new SpendingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SpendingViewHolder) {
            if ( this.lstSpendings.size()==0){
                ((SpendingViewHolder) holder).txtSpendingName.setText("Aucun participant ");
            }
            else{
                ((SpendingViewHolder) holder).txtSpendingName.setText(this.lstSpendings.get(position).getName());
                String s = "Payeur : "+this.lstSpendings.get(position).getPayerId();
                ((SpendingViewHolder) holder).txtPayerName.setText(s);
                ((SpendingViewHolder) holder).txtSpendingMontant.setText((this.lstSpendings.get(position).getTotal()).toString());
            }

            String spendingName= this.lstSpendings.get(position).getName();
            long spendingId= this.lstSpendings.get(position).getId();

            Button btnUpdate = holder.itemView.findViewById(R.id.btnUpdateSpendingDialog);
            btnUpdate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //Show the dialog
                    Context ctx = v.getContext();
                    FragmentManager fm = ((AppCompatActivity)ctx).getFragmentManager();
                    SpendingSaveDialogFragment dialogFragment;
                    dialogFragment = SpendingSaveDialogFragment.newInstance(spendingName, spendingId);
                    dialogFragment.show(fm, "dialog_spending_save");
                }
            });

            Button btnDelete = holder.itemView.findViewById(R.id.btnDeleteSpendingDialog);
            btnDelete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //Show the dialog
                    Context ctx = v.getContext();
                    FragmentManager fm = ((AppCompatActivity)ctx).getFragmentManager();
                    SpendingDeleteDialogFragment dialogFragment;
                    dialogFragment = SpendingDeleteDialogFragment.newInstance(spendingName, spendingId);
                    dialogFragment.show(fm, "dialog_spending_delete");
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    Bundle b = new Bundle();
                    b.putString("ParamParticipantName", spendingName);
                    b.putLong("ParamParticipantId", spendingId);
                    intent.putExtras(b);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(lstSpendings != null) {
            return this.lstSpendings.size();
        } else {
            return 0;
        }
    }

    public void setSpendingsList(List<Spending> spendingsList) {
        this.lstSpendings = spendingsList;
        notifyDataSetChanged();
    }
}
