package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Toast;

import com.example.testapp.databinding.ActivityResultadosTestBinding;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResultadosTest extends AppCompatActivity {
    private ActivityResultadosTestBinding binding;
    private int[] datos = new int[]{0, 0, 0, 0, 0};
    private String[] labels;
    private int[] _recomendaciones;
    private int indexMayor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultadosTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Labels para el grafico de pastel
        labels = new String[]{getString(R.string.muy_de_acuerdo),
                getString(R.string.de_acuerdo),
                getString(R.string.neutral),
                getString(R.string.poco_de_acuerdo),
                getString(R.string.nada_de_acuerdo)};

        //Identificadores de recursos string para las recomendaciones de cada puntuacion
        _recomendaciones = new int[]{R.string.depresion_muy_severa,
                R.string.depresion_severa,
                R.string.depresion_moderada,
                R.string.depresion_leve,
                R.string.no_depresion,
            };

        initChart();
        setChart();
        setRecomendacion();

        binding.btnFinish.setOnClickListener(View ->{this.finish();});
    }

    //Inicializa las propiedades del BarChart
    private void initChart() {
        binding.chart.setUsePercentValues(true);
        binding.chart.getDescription().setEnabled(false);
        binding.chart.setExtraOffsets(5, 10, 5, 5);
        binding.chart.setDragDecelerationFrictionCoef(0.95f);
        binding.chart.setDrawHoleEnabled(true);
        binding.chart.setHoleColor(Color.WHITE);
        binding.chart.setTransparentCircleRadius(61f);
        binding.chart.setEntryLabelTextSize(18f);
    }

    //Extrae el arreglo de datos int pasado por Intent a la activity
    private List<PieEntry> getData() {
        Bundle bundle = getIntent().getExtras();

        //Por si no se pasaron datos en el Bundle
        if (bundle == null) {
            Toast.makeText(this, "Error de resultados", Toast.LENGTH_SHORT).show();
            return null;
        }

        //Extrae los datos del Bundle
        this.datos = bundle.getIntArray("resultados");

        //Iteramos los datos extraidos y los asignamos a un List<PieEntry>
        List<PieEntry> data = new ArrayList<>();
        int tmpComparasion = -1;
        for (int i = 0; i < datos.length; i++) {

            if (tmpComparasion < datos[i]) { //Obtiene cual fue el resultado que mas se repitio
                tmpComparasion = datos[i];
                indexMayor = i;
            }

            data.add(new PieEntry(datos[i], labels[i]));
        }

        return data;
    }

    private PieDataSet setDataChart(){
        PieDataSet dataSet = new PieDataSet(getData(), "Resultados");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        return dataSet;
    }

    private void setChart(){
        PieData pieData = new PieData(setDataChart());
        pieData.setValueTextSize(18f);
        pieData.setValueTextColor(Color.BLACK);

        binding.chart.setData(pieData);
        binding.chart.invalidate();
    }

    private void setRecomendacion(){
        binding.txtRecomendacion.setMovementMethod(new ScrollingMovementMethod());
        binding.txtRecomendacion.setText(_recomendaciones[indexMayor]);
    }

}