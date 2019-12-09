package com.tec2.paybus;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

public class RecargaExitosaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga_exitosa);

        HashMap<String, Object> map = (HashMap) getIntent().getSerializableExtra("resultado");

        Object o = map.get("res");

        TextView txtTitulo = findViewById(R.id.txtTitulo);
        TextView txtDesc = findViewById(R.id.txtDesc);
        ImageView imgVw = findViewById(R.id.imgVwRes);


        if (o != null) {
            boolean res = (boolean) o;
            if (res) {
                //VERDE
                imgVw.setImageResource(R.drawable.ic_true);
                txtTitulo.setText("Recarga exitosa");
                txtDesc.setText("Ahora puedes disfrutar de tu recarga");

            } else {
                //ROJO
                imgVw.setImageResource(R.drawable.ic_close);
                txtTitulo.setText("Ocurrio un problema");
                txtTitulo.setTextColor(getColor(android.R.color.holo_red_dark));
                txtDesc.setText("Algo salio mal al intentar recargar tu cuenta, no te preocupes, puedes volver a intentarlo");
            }
        }

//        Iterator myVeryOwnIterator = map.keySet().iterator();
//        while(myVeryOwnIterator.hasNext()) {
//            String key=(String)myVeryOwnIterator.next();
//            String value=(String)meMap.get(key);
//            Toast.makeText(ctx, "Key: "+key+" Value: "+value, Toast.LENGTH_LONG).show();
//        }



    }

    public void cerrar(View v) {
        finish();
    }
}
