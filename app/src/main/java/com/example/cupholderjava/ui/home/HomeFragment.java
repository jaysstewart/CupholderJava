package com.example.cupholderjava.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cupholderjava.MainActivity;
import com.example.cupholderjava.R;
import com.example.cupholderjava.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button resetButton = getView().findViewById(R.id.resetButton);
        resetButton.setOnClickListener(v -> System.out.println("test"));
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Button resetButton = getView().findViewById(R.id.resetButton);
//        resetButton.setOnClickListener(v -> test() );
//    }

    public void test() {
        MainActivity mainActivity = new MainActivity();
        mainActivity.showNotification("Title", "Message test");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}