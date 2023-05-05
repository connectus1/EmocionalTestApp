package com.example.testapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.testapp.TestPager.ItemQuestion;
import com.example.testapp.TestPager.TestPagerAdapter;
import com.example.testapp.TestPager.VolleyTest;
import com.example.testapp.databinding.ActivityTestBinding;

import java.util.List;

public class TestActivity extends AppCompatActivity implements Runnable, View.OnClickListener {

    private ActivityTestBinding binding;
    private ViewPager2 viewPager;
    private Handler handler;
    private VolleyTest volleyTest;
    private List<ItemQuestion> itemQuestionList;
    private int[] seleccion;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initComponents();

        volleyTest = new VolleyTest(this, "1");
        volleyTest.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        volleyTest.close();

    }

    private void initComponents(){ //Inicializa los componentes
        binding.cardFeliz.setOnClickListener(this::onClick);
        binding.cardSemiFeliz.setOnClickListener(this::onClick);
        binding.cardNeutro.setOnClickListener(this::onClick);
        binding.cardSemiTriste.setOnClickListener(this::onClick);
        binding.cardTriste.setOnClickListener(this::onClick);

        handler = new Handler();

        viewPager = binding.viewPagerTest;
        viewPager.setUserInputEnabled(false);

        seleccion = new int[5];
    }

    private void setFondoBlancoCards(){
        binding.cardFeliz.setCardBackgroundColor(getColor(R.color.white));
        binding.cardSemiFeliz.setCardBackgroundColor(getColor(R.color.white));
        binding.cardNeutro.setCardBackgroundColor(getColor(R.color.white));
        binding.cardSemiTriste.setCardBackgroundColor(getColor(R.color.white));
        binding.cardTriste.setCardBackgroundColor(getColor(R.color.white));
    }

    //Recibe Un List<ItemQuestion> con la informacion de las preguntas,
    //se inicializa el TestPagerAdapter para ser asignado al ViewPager2
    public void initPager(List<ItemQuestion> questionList){
        this.itemQuestionList = questionList;
        TestPagerAdapter adapter = new TestPagerAdapter(getSupportFragmentManager(),
                getLifecycle(),
                questionList);

        binding.txtIndiceTest.setText(1 + "/" + adapter.getItemCount());
        viewPager.setAdapter(adapter);
    }

    public void nextPage(){
        int index = viewPager.getCurrentItem();

        if (++index <= viewPager.getAdapter().getItemCount()-1 ){
            viewPager.setCurrentItem(index, true);
            binding.txtIndiceTest.setText((index+1) + "/" + viewPager.getAdapter().getItemCount());
        }
        else {

            // Una vez terminado las preguntas abre la activity de resultados,
            // se envia por Intent el arreglo con los datos
            Intent i = new Intent(this, ResultadosTest.class);
            i.putExtra("resultados", this.seleccion);
            startActivity(i);

            this.finish();
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
            case R.id.cardFeliz: this.seleccion[4]++;
                break;
            case R.id.cardSemiFeliz: this.seleccion[3]++;
                break;
            case R.id.cardNeutro: this.seleccion[2]++;
                break;
            case R.id.cardSemiTriste:this.seleccion[1]++;
                break;
            default: this.seleccion[0]++;
        }

        if (!isRunHandler())
            handler.postDelayed(this::run, 300);

    }
}