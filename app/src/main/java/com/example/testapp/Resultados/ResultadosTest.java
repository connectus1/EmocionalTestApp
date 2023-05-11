package com.example.testapp.Resultados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Toast;

import com.example.testapp.R;
import com.example.testapp.databinding.ActivityResultadosTestBinding;

public class ResultadosTest extends AppCompatActivity {
    private ActivityResultadosTestBinding binding;
    private int[] datos = new int[]{0, 0, 0, 0, 0};
    private int[] _recomendaciones;
    private int indexMayor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultadosTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Identificadores de recursos string para las recomendaciones de cada puntuacion
        _recomendaciones = new int[]{R.string.depresion_muy_severa,
                R.string.depresion_severa,
                R.string.depresion_moderada,
                R.string.depresion_leve,
                R.string.no_depresion,
        };

        getData();
        setRecomendacion();

        binding.btnFinish.setOnClickListener(View ->{this.finish();});
        binding.imgGrafica.setOnClickListener(clickGrafica);
        binding.txtCatalogo.setOnClickListener(clickCatalogo);
    }



    //Extrae el arreglo de datos int pasado por Intent a la activity
    private void getData() {
        Bundle bundle = getIntent().getExtras();

        //Por si no se pasaron datos en el Bundle
        if (bundle == null) {
            Toast.makeText(this, "Error de resultados", Toast.LENGTH_SHORT).show();
            return;
        }

        //Extrae los datos del Bundle
        this.datos = bundle.getIntArray("resultados");

        int tmpComparasion = -1;
        for (int i = 0; i < datos.length; i++) {

            if (tmpComparasion < datos[i]) { //Obtiene cual fue el resultado que mas se repitio
                tmpComparasion = datos[i];
                indexMayor = i;
            }
        }
    }

    //Establece en el TextView una recomendacion segun el indice del puntuaje mas alto objeto
    private void setRecomendacion(){
        binding.txtRecomendacion.setMovementMethod(new ScrollingMovementMethod());
        binding.txtRecomendacion.setText(_recomendaciones[indexMayor]);
    }

    //  ######### OnClickListeners ###########
    private View.OnClickListener clickGrafica = view -> {
        DialogGrafica dialogGrafica = new DialogGrafica(this, datos, getLayoutInflater());
        dialogGrafica.create();
        dialogGrafica.show();
    };

    private View.OnClickListener clickCatalogo = view ->{
        Uri enlace = Uri.parse("https://www.doctoralia.com.mx/buscar?q=Psic%C3%B3logo");
        Intent intent = new Intent(Intent.ACTION_VIEW, enlace);
        startActivity(intent);
    };


}