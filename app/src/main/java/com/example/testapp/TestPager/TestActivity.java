package com.example.testapp.TestPager;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.testapp.R;
import com.example.testapp.databinding.ActivityTestBinding;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initComponents();

        List<ItemQuestion> questions = new ArrayList<>();
        questions.add(new ItemQuestion("Prueba1"));
        questions.add(new ItemQuestion("Prueba2"));
        questions.add(new ItemQuestion("Prueba3"));

        TestPagerAdapter adapter = new TestPagerAdapter(getSupportFragmentManager(), getLifecycle(),questions);
        binding.viewPagerTest.setAdapter(adapter);

    }

    private void initComponents(){ //Inicializa los componentes
        binding.cardFeliz.setOnClickListener(clickCard);
        binding.cardSemiFeliz.setOnClickListener(clickCard);
        binding.cardNeutro.setOnClickListener(clickCard);
        binding.cardSemiTriste.setOnClickListener(clickCard);
        binding.cardTriste.setOnClickListener(clickCard);
    }


    //Limpia los background del cardView y pinta el que este seleccionado
    private View.OnClickListener clickCard = view ->{
        if(((CardView)view).getCardBackgroundColor() == getColorStateList(R.color.verde)){
            ((CardView)view).setCardBackgroundColor(getColor(R.color.white));
        }else{
            setFondoBlancoCards();
            ((CardView)view).setCardBackgroundColor(getColor(R.color.verde));
        }
    };

    private void setFondoBlancoCards(){
        binding.cardFeliz.setCardBackgroundColor(getColor(R.color.white));
        binding.cardSemiFeliz.setCardBackgroundColor(getColor(R.color.white));
        binding.cardNeutro.setCardBackgroundColor(getColor(R.color.white));
        binding.cardSemiTriste.setCardBackgroundColor(getColor(R.color.white));
        binding.cardTriste.setCardBackgroundColor(getColor(R.color.white));
    }

}