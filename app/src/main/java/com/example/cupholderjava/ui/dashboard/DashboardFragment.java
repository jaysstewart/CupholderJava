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
        final ProgressBar waterBar = getView().findViewById(R.id.waterBar);
        waterBar.setProgress(30);

        final TextView fillPercent = getView().findViewById(R.id.percent);
        CharSequence str = "30%";
        fillPercent.setText(str);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}