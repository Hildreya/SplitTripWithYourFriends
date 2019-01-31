package org.diiage.splittripwithyourfriends.ui.hometrip;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class HomeTripViewModel extends ViewModel {
    private MutableLiveData<String> tripName;

    public LiveData<String> getTripName() {
        if(this.tripName == null) {
            this.tripName = new MutableLiveData<String>();
        }
        return this.tripName;
    }

    private void loadTripName() {
        // Do an asynchronous operation to fetch trip
    }
}
