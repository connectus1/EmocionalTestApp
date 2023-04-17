package com.example.testapp.BottomDialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.testapp.databinding.BottomSheetQrBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class BottomSheetQR extends BottomSheetDialog {
    private BottomSheetQrBinding binding;

    public BottomSheetQR(@NonNull Context context) {
        super(context);

        binding = BottomSheetQrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setCancelable(true);

        generateQR("1234");
    }

    private void generateQR(String codigo){

        //Generar el código QR
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(codigo, BarcodeFormat.QR_CODE, 512, 512);
            Bitmap bitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.RGB_565);
            for (int x = 0; x < 512; x++) {
                for (int y = 0; y < 512; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            binding.imgQR.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Toast.makeText(getContext(), "Ha Ocurrido un error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

}
