package com.example.testapp.Resultados;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.testapp.R;
import com.example.testapp.databinding.DialogGraficaBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class DialogGrafica extends AlertDialog.Builder{
    private int[] datos;
    private String[] labels;
    private DialogGraficaBinding binding;

    public DialogGrafica(@NonNull Context context, int[] datos, @NonNull LayoutInflater inflater) {
        super(context);

        binding = DialogGraficaBinding.inflate(inflater);


//        GraficaBuilder builder = new GraficaBuilder(context);
//        builder.setView(binding.getRoot());

        this.datos = datos;

        //Labels para el grafico de pastel
        labels = new String[]{
                context.getString(R.string.nada_de_acuerdo),
                context.getString(R.string.poco_de_acuerdo),
                context.getString(R.string.neutral),
                context.getString(R.string.de_acuerdo),
                context.getString(R.string.muy_de_acuerdo)
        };

        initChart();
        setChart();

        this.setView(binding.getRoot());
    }

//    private static class GraficaBuilder extends AlertDialog.Builder{
//
//        public GraficaBuilder(Context context) {
//            super(context);
//        }
//    }

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

    private List<PieEntry> getData() {

        //Iteramos los datos extraidos y los asignamos a un List<PieEntry>
        List<PieEntry> data = new ArrayList<>();
        int tmpComparasion = -1;
        for (int i = 0; i < datos.length; i++) {

            if (tmpComparasion < datos[i]) { //Obtiene cual fue el resultado que mas se repitio
                tmpComparasion = datos[i];
            }
            data.add(new PieEntry(datos[i], labels[i]));
        }
        return data;
    }

    private PieDataSet setDataChart(){
        PieDataSet dataSet = new PieDataSet(getData(), "Grafica");
        dataSet.setSliceSpace(3f);
        dataSet.setValueTextColor(Color.BLACK);
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
}
