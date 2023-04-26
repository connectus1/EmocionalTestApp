package com.example.testapp.TestPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testapp.databinding.TabItemTestBinding;

public class FragmentTestTarget extends Fragment {
    private TabItemTestBinding binding;
    private ItemQuestion question;

    public FragmentTestTarget(ItemQuestion question) {
        this.question = question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TabItemTestBinding.inflate(inflater,container,false);
        binding.txtCuerpoTest.setText(question.getTexto());

        return binding.getRoot();
    }


}
