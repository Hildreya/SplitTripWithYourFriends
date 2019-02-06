package org.diiage.splittripwithyourfriends.ui.spending;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import org.diiage.splittripwithyourfriends.entities.Spending;
import org.diiage.splittripwithyourfriends.repositories.SpendingRepository;

import java.util.List;

public class SpendingsViewModel extends AndroidViewModel {
    private SpendingRepository spendingRepository;

    public SpendingsViewModel(Application application) {
        super(application);
        spendingRepository = new SpendingRepository(application);
    }

    public LiveData<List<Spending>> getAllSpendings() { return spendingRepository.getAllSpendings(); }
    public LiveData<List<Spending>> getAllSpendings(long id) { return spendingRepository.getSpendingByTrip(id); }
}
