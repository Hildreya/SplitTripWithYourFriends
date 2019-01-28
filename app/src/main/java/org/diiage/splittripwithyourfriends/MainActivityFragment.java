package org.diiage.splittripwithyourfriends;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.diiage.splittripwithyourfriends.databinding.FragmentMainBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        FragmentMainBinding binding = DataBindingUtil.setContentView(this.getActivity(), R.layout.fragment_main);
        binding.setStrHelloWorld("Test binding");

        return view;
    }
}
