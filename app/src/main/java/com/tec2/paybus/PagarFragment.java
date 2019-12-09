package com.tec2.paybus;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class PagarFragment extends Fragment {

    int contador = 0;
    JSONObject dataCode = new JSONObject();
    ImageView imageViewQrCode;
    RadioGroup radioGroup;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pagar, container, false);
        final TextView txtVwPersonas = root.findViewById(R.id.txtVwNumPersonas);
        final TextView txtVwTotal = root.findViewById(R.id.txtVwTotal);
        ImageButton imgPlus = root.findViewById(R.id.imgBtnMas);
        ImageButton imgRest = root.findViewById(R.id.imgBtnMenos);
        radioGroup = root.findViewById(R.id.toggle);

        imgPlus.setVisibility(View.INVISIBLE);
        imgRest.setVisibility(View.INVISIBLE);
        txtVwPersonas.setVisibility(View.INVISIBLE);
        txtVwTotal.setText(getString(R.string.totalPrice, 9.00));

        contador = 1;
        updateQr();


        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            switch (i) {
                case R.id.radioInd:
                    imgPlus.setVisibility(View.INVISIBLE);
                    imgRest.setVisibility(View.INVISIBLE);
                    txtVwPersonas.setVisibility(View.INVISIBLE);
                    txtVwTotal.setText(getString(R.string.totalPrice, 9.00));
                    contador = 1;
                    updateQr();
                    break;
                case R.id.radioMul:
                    contador = 2;
                    imgPlus.setVisibility(View.VISIBLE);
                    imgRest.setVisibility(View.VISIBLE);
                    txtVwPersonas.setVisibility(View.VISIBLE);
                    txtVwPersonas.setText((contador == 2 ? getString(R.string.pagaruna, contador - 1) : getString(R.string.pagar, contador - 1)));
                    txtVwTotal.setText(getString(R.string.totalPrice, (9.00 + (9.00 * (contador - 1)))));
                    updateQr();
                    break;
            }
        });

        imgPlus.setOnClickListener(view -> {
            if (contador < 5) {
                contador++;
                txtVwPersonas.setText(getString(R.string.pagar, contador - 1));
                txtVwTotal.setText(getString(R.string.totalPrice, (9.00 + (9.00 * (contador - 1)))));
                updateQr();
            }
        });

        imgRest.setOnClickListener(view -> {
            if (contador > 2) {
                contador--;
                txtVwPersonas.setText((contador == 2 ? getString(R.string.pagaruna, contador - 1) : getString(R.string.pagar, contador - 1)));
                txtVwTotal.setText(getString(R.string.totalPrice, (9.00 + (9.00 * (contador - 1)))));
                updateQr();
            }
        });
        imageViewQrCode = root.findViewById(R.id.imgVwQrCode);
        updateQr();
        return root;
    }

    public void updateQr() {
        try {
            dataCode.put("id", "3");
            dataCode.put("tipo", 0);
            dataCode.put("monto", 9.00 * contador);
            dataCode.put("origen", FirebaseAuth.getInstance().getCurrentUser().getUid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(dataCode.toString(), BarcodeFormat.AZTEC, 380, 380);
            imageViewQrCode.setImageBitmap(bitmap);
        } catch (Exception e) {

        }
    }
}