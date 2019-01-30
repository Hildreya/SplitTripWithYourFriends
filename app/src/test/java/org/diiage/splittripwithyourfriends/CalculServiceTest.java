package org.diiage.splittripwithyourfriends;

import org.diiage.splittripwithyourfriends.business.CalculService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculServiceTest {

    @Test
    public void amountRefound_isCorrect() {

        CalculService calculService = new CalculService();

        List<Double> refounds = new ArrayList<Double>();
        refounds.add(10.0);
        refounds.add(20.0);
        refounds.add(40.0);



        Assert.assertEquals(new ArrayList<Double>(), calculService.amountRefund(refounds, 70.0));
    }
}
