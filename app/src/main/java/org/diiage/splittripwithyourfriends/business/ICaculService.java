package org.diiage.splittripwithyourfriends.business;

import java.util.List;

public interface ICaculService {
    /**
     * Calculate the amount by withdrawing the share of the one who advances.
     * @param totalPayments Amount of the expense
     * @param nbParticipants Number of participant
     * @return Amount by withdrawing the share of the one who advances.
     */
    double splitSpending(double totalPayments, int nbParticipants);

    /**
     * Calculating the value of the refund due to a payer.
     * @param paiementsDue All payment amounts.
     * @param refundAmount Amount of the refund
     * @return Returns the list of payments updated with a refund.
     */
    List<Double> amountRefund(List<Double> paiementsDue, double refundAmount);
}
