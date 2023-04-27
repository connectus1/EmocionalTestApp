package com.example.testapp.BottomDialog;

import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import com.example.testapp.LoginActivity;
import com.example.testapp.LoginRegistro.RegistroVolley;
import com.example.testapp.LoginRegistro.Usuario;
import com.example.testapp.databinding.BottomSheetRegistroBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomSheetRegistro extends BottomSheetDialog {
    private BottomSheetRegistroBinding binding;
    private RegistroVolley registroVolley;
    private Activity activity;
    private static BottomSheetRegistro bottomSheetRegistro;
    public BottomSheetRegistro(@NonNull Activity activity) {
        super(activity);
        this.activity = activity;

        binding = BottomSheetRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setCancelable(true);

        initComponents();
        this.bottomSheetRegistro = this;
    }

    private void initComponents(){ //Inicializa los componentes
        binding.btnRegistro.setOnClickListener(clickRegistro);
        binding.chkTerminos.setOnCheckedChangeListener(changeTerminos);
        binding.editFechaNac.setOnClickListener(clickCalendar);

//        registroVolley = new RegistroVolley(activity, createUsuario());
    }

    private Usuario createUsuario(){ //Devuelve un objeto usuario con la informacion
        Usuario usuario = new Usuario();
        usuario.setNombre(binding.editNombre.getEditableText().toString());
        usuario.setApellido(binding.editApellido.getEditableText().toString());
        usuario.setCorreo(binding.editCorreo.getEditableText().toString());
        usuario.setContraseña(binding.editContra.getEditableText().toString());
        usuario.setFechaNac(binding.editFechaNac.getEditableText().toString());

        return usuario;
    }

    public void setCalendarInput(String fecha){
        binding.editFechaNac.setText(fecha);
    }

    //==============================
    // Listeners
    //==============================
    private View.OnClickListener clickRegistro = view -> {
//        createUsuario();
        registroVolley = new RegistroVolley(activity, createUsuario());
        registroVolley.registrar();
    };

    private CompoundButton.OnCheckedChangeListener changeTerminos = (compoundButton, b) -> {
        binding.btnRegistro.setEnabled(b);
    };

    private View.OnClickListener clickCalendar = view ->{
        ((LoginActivity)activity).showCalendar();
    };


    //=============================
    // Estados de la actividad
    //==============================
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (registroVolley != null)
            registroVolley.close();
    }

    public static void closeDialog(){
        bottomSheetRegistro.cancel();
    }
}
