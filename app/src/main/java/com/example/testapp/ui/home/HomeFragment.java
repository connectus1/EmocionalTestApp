package com.example.testapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testapp.databinding.FragmentHomeBinding;
import com.example.testapp.ui.home.Recycler.ItemTest;
import com.example.testapp.ui.home.Recycler.RvTest;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RvTest adapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initComponents();

        return root;
    }

    private void initComponents(){
        ItemTest itemTest =new ItemTest("Titulo de prueba","#2",1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(itemTest);

        adapter = new RvTest(arrayList, getContext());
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(linearLayout.VERTICAL);

        binding.rvTestCard.setLayoutManager(linearLayout);
        binding.rvTestCard.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}