package com.tec2.paybus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;

public class OpcionesPagoFragment extends Fragment {
    private MaterialCardView cardEscanear;
    private MaterialCardView cardTarjetas;
    private MaterialCardView cardOxxo;
    Long saldo;

    static OpcionesPagoFragment newInstance() {
        OpcionesPagoFragment f = new OpcionesPagoFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_opciones_pago, container, false);
        cardEscanear = v.findViewById(R.id.cardEscanear);
        cardTarjetas = v.findViewById(R.id.cardTarjeta);
        cardOxxo = v.findViewById(R.id.cardOxxo);
        cardEscanear.setOnClickListener(view -> launchCodeScaner());
        cardTarjetas.setOnClickListener(view -> {
        });
        cardOxxo.setOnClickListener(view -> {
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference saldoRef = firebaseDatabase.getReference("accounts").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("saldo");
        saldoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                saldo = (Long) dataSnapshot.getValue() / 9;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }

    public void launchCodeScaner() {
        if (saldo > 0) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setCaptureActivity(CaptureActivity.class);
                integrator.setPrompt("Escanea el codigo");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
        } else {
            Snackbar.make(getView(), "No cuentas con saldo suficiente para transferir", Snackbar.LENGTH_LONG).show();
        }
    }
}
