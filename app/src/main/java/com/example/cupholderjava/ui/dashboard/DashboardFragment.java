package com.example.cupholderjava.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cupholderjava.Connect;
import com.example.cupholderjava.MainActivity;
import com.example.cupholderjava.R;
import com.example.cupholderjava.databinding.FragmentDashboardBinding;
import com.example.cupholderjava.ui.settings.SettingsFragment;

public class DashboardFragment extends Fragment {

    private static int waterProgress = 0;
    private FragmentDashboardBinding binding;

    public static Connect connect = MainActivity.connect;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onStart() {
        super.onStart();


        connect.sendData("abcdefg");
//        TextView weight = getView().findViewById(R.id.weight);
//
//        System.out.println(connect.bluetoothGatt.readCharacteristic(connect.weightGattChar));

        setWaterProgress(waterProgress);
    }

    public void incProgress() {
        waterProgress += 10;
        setWaterProgress(waterProgress);
    }

    // Updates water progress bar and percentage
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