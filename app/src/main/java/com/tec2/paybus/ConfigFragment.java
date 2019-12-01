package com.tec2.paybus;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigFragment extends Fragment {


    public ConfigFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_config, container, false);

        Button btnLogout = v.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(view -> logout());

        return v;
    }

    private void logout(){
        AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(task -> {
                    // user is now signed out
                    //Toast.makeText(MainActivity.this, R.string.toast_sesion_cerrada, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(), Login.class));
                });
    }

}
