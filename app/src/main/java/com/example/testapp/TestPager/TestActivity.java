package com.example.testapp.TestPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.testapp.R;
import com.example.testapp.databinding.ActivityTestBinding;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements Runnable, View.OnClickListener {

    private ActivityTestBinding binding;
    private ViewPager2 viewPager;
    private Handler handler;
    private List<ItemQuestion> itemQuestionList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initComponents();

        itemQuestionList = new ArrayList<>();
        itemQuestionList.add(new ItemQuestion("Prueba1"));
        itemQuestionList.add(new ItemQuestion("Prueba2"));
        itemQuestionList.add(new ItemQuestion("Prueba3"));

        TestPagerAdapter adapter = new TestPagerAdapter(getSupportFragmentManager(), getLifecycle(), itemQuestionList);
        viewPager.setAdapter(adapter);
        viewPager.setUserInputEnabled(false);
    }

    private void initComponents(){ //Inicializa los componentes
        binding.cardFeliz.setOnClickListener(this::onClick);
        binding.cardSemiFeliz.setOnClickListener(this::onClick);
        binding.cardNeutro.setOnClickListener(this::onClick);
        binding.cardSemiTriste.setOnClickListener(this::onClick);
        binding.cardTriste.setOnClickListener(this::onClick);

        handler = new Handler();
        viewPager = binding.viewPagerTest;
    }

    private void setFondoBlancoCards(){
        binding.cardFeliz.setCardBackgroundColor(getColor(R.color.white));
        binding.cardSemiFeliz.setCardBackgroundColor(getColor(R.color.white));
        binding.cardNeutro.setCardBackgroundColor(getColor(R.color.white));
        binding.cardSemiTriste.setCardBackgroundColor(getColor(R.color.white));
        binding.cardTriste.setCardBackgroundColor(getColor(R.color.white));
    }

    public void nextPage(){
        int index = viewPager.getCurrentItem();

        if (++index <= viewPager.getAdapter().getItemCount()-1 ){
            viewPager.setCurrentItem(index, true);
        }else{
            int suma = 0;
            for (ItemQuestion item: itemQuestionList) {
                suma += item.getPuntuacion();
            }

        }
    }

//  ============= Funciones Runnable =============
    @Override
    public void run() {
        nextPage();
        setFondoBlancoCards();
    }

    @SuppressLint("NewApi")
    private boolean isRunHandler(){
        return handler.hasCallbacks(this::run);
    }

//    =========== Funcion OnClick ==============
    @Override
    public void onClick(View view) {
        int id = view.getId();
        ((CardView)view).setCardBackgroundColor(getColor(R.color.verde));

        switch (id){
            case R.id.cardFeliz: itemQuestionList.get(viewPager.getCurrentItem()).setPuntuacion(4);
                break;
            case R.id.cardSemiFeliz: itemQuestionList.get(viewPager.getCurrentItem()).setPuntuacion(3);
                break;
            case R.id.cardNeutro: itemQuestionList.get(viewPager.getCurrentItem()).setPuntuacion(2);
                break;
            case R.id.cardSemiTriste: itemQuestionList.get(viewPager.getCurrentItem()).setPuntuacion(1);
                break;
            default: itemQuestionList.get(viewPager.getCurrentItem()).setPuntuacion(0);
        }

        if (!isRunHandler())
            handler.postDelayed(this::run, 300);

    }
}