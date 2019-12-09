package com.tec2.paybus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecargarTransferirFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);


        Button btnCamera = root.findViewById(R.id.btnLaunchCamera);
        btnCamera.setOnClickListener(view -> {
//            IntentIntegrator integrator = new IntentIntegrator(getActivity());
//            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//            integrator.setPrompt("Scan a barcode");
//            integrator.setCameraId(0);  // Use a specific camera of the device
//            integrator.setBeepEnabled(false);
//            integrator.setBarcodeImageEnabled(true);
//            integrator.initiateScan();

            startActivity(new Intent(getActivity(), PayProcessActivity.class));
        });

        Button btnTransferir = root.findViewById(R.id.btnTransferir);
        btnTransferir.setOnClickListener(view -> startActivity(new Intent(getActivity(), Transferir.class)));

        final RecyclerView recyclerView = root.findViewById(R.id.rvHistorialTransacciones);
        FirebaseDatabaseControl.setUpDataBase();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setAdapter(FirebaseDatabaseControl.Adapter());


        return root;
    }

}