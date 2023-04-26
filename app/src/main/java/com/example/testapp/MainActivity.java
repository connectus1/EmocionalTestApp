package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testapp.BottomDialog.BottomSheetQR;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Opciones del BottomNavigation
        binding.bottomNavigationView.setItemIconTintList(null);
        binding.bottomNavigationView.findViewById(R.id.nav_off).setOnClickListener(clickOff);

        binding.fabQR.setOnClickListener(clickQR);
    }

    private View.OnClickListener clickQR = view ->{
            BottomSheetQR qr = new BottomSheetQR(this);
            qr.show();
    };

    private View.OnClickListener clickOff = view -> {
          deleteAccount();
    };

    private void deleteAccount(){
        exitAccount();
    }

    private void exitAccount(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);

        finish();
    }
}