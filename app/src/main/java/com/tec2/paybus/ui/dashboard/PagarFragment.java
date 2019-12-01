package com.tec2.paybus.ui.dashboard;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tec2.paybus.R;

import net.glxn.qrgen.android.QRCode;

public class PagarFragment extends Fragment {

    private PagarViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(PagarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pagar, container, false);
        final TextView txtVwPersonas = root.findViewById(R.id.txtVwNumPersonas);
        final TextView txtVwTotal = root.findViewById(R.id.txtVwTotal);

        dashboardViewModel.getContador().observe(this, s -> {
            txtVwPersonas.setText(getString(R.string.pagar, s));
            txtVwTotal.setText(String.valueOf(9.00 + (9.00 * s)));
        });

        ImageButton imgPlus = root.findViewById(R.id.imgBtnMas);
        imgPlus.setOnClickListener(view -> {
            int contador = dashboardViewModel.getContador().getValue();
            if (contador < 4) {
                dashboardViewModel.getContador().setValue(contador + 1);
            }
        });

        ImageButton imgRest = root.findViewById(R.id.imgBtnMenos);
        imgRest.setOnClickListener(view -> {
            int contador = dashboardViewModel.getContador().getValue();
            if (contador > 1) {
                dashboardViewModel.getContador().setValue(contador - 1);
            }
        });

        Bitmap myBitmap = QRCode.from("www.buscuu.com").withSize(400, 400).bitmap();
        ImageView myImage = root.findViewById(R.id.imgVwQrCode);
        myImage.setImageBitmap(myBitmap);

        return root;
    }
}