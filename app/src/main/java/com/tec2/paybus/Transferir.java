package com.tec2.paybus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class Transferir extends AppCompatActivity {
    int contador = 0;
    TextView txtVwTotal;
    JSONObject dataCode;
    ImageView imageViewQrCode;
    int saldo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir);

        txtVwTotal = findViewById(R.id.txtVwTotalTransferir);
        imageViewQrCode = findViewById(R.id.imgVwQrCodeTransferir);
        dataCode = new JSONObject();
        ImageButton imgPlus = findViewById(R.id.imgBtnMasTransferir);
        imgPlus.setOnClickListener(view -> {
            if (contador < 4) {
                actualizarQr(true);
            }
        });

        ImageButton imgRest = findViewById(R.id.imgBtnMenosTransferir);
        imgRest.setOnClickListener(view -> {
            if (contador >= 1) {
                actualizarQr(false);
            }
        });
        actualizarQr(true);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference saldoRef = firebaseDatabase.getReference("accounts").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("saldo");
        saldoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                saldo = Math.toIntExact((Long)dataSnapshot.getValue() / 9);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void actualizarQr(boolean b) {
        if (b) {
            if (saldo == contador+1){
                Snackbar.make(txtVwTotal, "No puedes transferir mas de tu propio saldo", Snackbar.LENGTH_LONG).show();
                return;
            }
            contador++;
        } else {
            contador--;
        }
        txtVwTotal.setText(getString(R.string.totalPrice, 9.00 + (9.00 * contador)));

        try {
            dataCode.put("id", "3");
            dataCode.put("tipo", 0);
            dataCode.put("monto", 9.00 + (9.00 * contador));
            dataCode.put("origen", FirebaseAuth.getInstance().getCurrentUser().getUid());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(dataCode.toString(), BarcodeFormat.AZTEC, 400, 400);
            imageViewQrCode.setImageBitmap(bitmap);
        } catch (Exception e) {

        }

    }
}
