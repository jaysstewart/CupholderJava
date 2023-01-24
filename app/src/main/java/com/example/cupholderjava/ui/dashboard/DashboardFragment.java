package com.example.cupholderjava.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cupholderjava.R;
import com.example.cupholderjava.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        setWaterProgress(80);
    }

    // Updates water progress bar and precentage
    public void setWaterProgress(int progress){
        // progress can not exceed 100%
        if (progress > 100) {
            progress = 100;
        }

        final TextView fillPercent = getView().findViewById(R.id.percent);
        final ProgressBar waterBar = getView().findViewById(R.id.waterBar);
        waterBar.setProgress(progress);
        CharSequence str = progress + "%";
        fillPercent.setText(str);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}