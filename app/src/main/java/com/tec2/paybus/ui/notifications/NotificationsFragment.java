package com.tec2.paybus.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tec2.paybus.PayProcessActivity;
import com.tec2.paybus.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
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

        return root;
    }

}