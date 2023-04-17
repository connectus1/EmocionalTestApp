package com.example.testapp.BottomDialog;

import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import com.example.testapp.LoginRegistro.RegistroVolley;
import com.example.testapp.LoginRegistro.Usuario;
import com.example.testapp.databinding.BottomSheetRegistroBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomSheetRegistro extends BottomSheetDialog {
    private BottomSheetRegistroBinding binding;
    private RegistroVolley registroVolley;
    private Activity activity;

    public BottomSheetRegistro(@NonNull Activity activity) {
        super(activity);
        this.activity = activity;

        binding = BottomSheetRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setCancelable(true);

        initComponents();
    }

    private void initComponents(){ //Inicializa los componentes
        binding.btnRegistro.setOnClickListener(clickRegistro);
        binding.chkTerminos.setOnCheckedChangeListener(changeTerminos);

        registroVolley = new RegistroVolley(activity, createUsuario());
    }

    private Usuario createUsuario(){ //Devuelve un objeto usuario con la informacion
        Usuario usuario = new Usuario();
        usuario.setNombre(binding.editNombre.getEditableText().toString());
        usuario.setApellido(binding.editApellido.getEditableText().toString());
        usuario.setCorreo(binding.editCorreo.getEditableText().toString());
        usuario.setContraseña(binding.editContra.getEditableText().toString());

        return usuario;
    }

    //==============================
    // Listeners
    //==============================
    private View.OnClickListener clickRegistro = view -> {
        registroVolley.registrar();
    };

    private CompoundButton.OnCheckedChangeListener changeTerminos = (compoundButton, b) -> {
        binding.btnRegistro.setEnabled(b);
    };

    //=============================
    // Estados de la actividad
    //==============================
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        registroVolley.close();
    }
}
