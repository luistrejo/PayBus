package com.tec2.paybus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

public class PayProcessActivity extends AppCompatActivity {
    ViewPager viewPager;
    HashMap<String, Object> operationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_process);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                cobroHecho(result.getContents());
                Toast.makeText(getApplicationContext(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Log.d("Scann", "Scanned: " + result.getContents());
            }
        }
    }

    public void cobroHecho(String data) {
        try {
            Timestamp fecha = new Timestamp(new Date());
            JSONObject obj = new JSONObject(data);
            operationData = new HashMap<>();
            operationData.put("id", obj.get("id"));
            operationData.put("tipo", 0);
            operationData.put("monto", obj.get("monto"));
            operationData.put("origen", obj.get("origen"));
            operationData.put("medio", 0);
            operationData.put("timestamp", fecha.getSeconds());
            operationData.put("receptor", FirebaseAuth.getInstance().getCurrentUser().getUid());

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference newCityRef = firebaseDatabase.getReference("transactions");
            newCityRef.push().setValue(operationData);

            DatabaseReference ref = firebaseDatabase.getReference("accounts").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("history");
            ref.push().setValue(operationData);

            operationData.put("tipo", 1);
            DatabaseReference ref2 = firebaseDatabase.getReference("accounts").child((String) obj.get("origen")).child("history");
            ref2.push().setValue(operationData)
                    .addOnSuccessListener(aVoid -> showResult(true))
                    .addOnFailureListener(e -> showResult(false));

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + data + "\"" + t.getMessage());
        }

    }

    public void showResult(boolean res) {
        operationData.put("res", res);
        Intent iRes = new Intent(this, RecargaExitosaActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable("resultado",operationData);
        iRes.putExtras(extras);
        startActivity(iRes);
        finish();
    }
}
