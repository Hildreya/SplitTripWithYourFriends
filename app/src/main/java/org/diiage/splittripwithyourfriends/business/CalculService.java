package org.diiage.splittripwithyourfriends.business;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

public class CalculService implements ICaculService {

    @Override
    public double splitSpending(double totalPayments, int nbParticipant) {
        double calculatePayments = totalPayments - (totalPayments / nbParticipant);
        return calculatePayments / (nbParticipant - 1);
    }

    @Override
    public List<Double> amountRefund(List<Double> paiementsDue, double refundAmount) {

        ArrayList<Double> removeValuesCollection = new ArrayList<Double>();
        removeValuesCollection.add(0.0);

        for (int i = 0; i < paiementsDue.size(); i++) {
            if(refundAmount == 0.0) {
                break;
            } else if(paiementsDue.get(i).doubleValue() <= refundAmount) {
                refundAmount -= paiementsDue.get(i).doubleValue();
                paiementsDue.set(i, 0.0);
            } else if(paiementsDue.get(i).doubleValue() > refundAmount) {
                Double lastValue = paiementsDue.get(i).doubleValue() - refundAmount;
                paiementsDue.set(i, lastValue);
            }
        }

        paiementsDue.removeAll(removeValuesCollection);

        return paiementsDue;
    }
}
