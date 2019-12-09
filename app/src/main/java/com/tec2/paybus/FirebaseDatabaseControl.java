package com.tec2.paybus;


import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by LuisTrejo on 29/09/2016.
 */

public class FirebaseDatabaseControl {

    private static Query mQuery;
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static MyAdapter mMyAdapter;
    private static ArrayList<Transaccion> mAdapterItems;
    private static ArrayList<String> mAdapterKeys;
    private static MyAdapterPayments mMyAdapterPayments;
    private static ArrayList<Transaccion> mAdapterItemsPayments;
    private static ArrayList<String> mAdapterKeysPayments;

    static ArrayList<String> chars = new ArrayList<>();

    static int contador = 0;

    public static DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public static void setUpDataBase() {
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            //firebaseDatabase.setPersistenceEnabled(true);
            databaseReference = firebaseDatabase.getReference();
        }
    }

    public static FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public static Query getQuery() {
        mQuery = databaseReference.child("accounts").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("history");
        Log.d("Firebase Database", "Get Query");
        return mQuery;
    }

    public static MyAdapter Adapter() {
        if (mMyAdapter == null) {
            mMyAdapter = new MyAdapter(getQuery(), Transaccion.class, mAdapterItems, mAdapterKeys);
            return mMyAdapter;
        } else {
            return mMyAdapter;
        }
    }

    public static Query getQueryPayments() {
        mQuery = databaseReference.child("accounts").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("payments");
        Log.d("Firebase Database", "Get Query");
        return mQuery;
    }

    public static MyAdapterPayments AdapterPayments() {
        if (mMyAdapterPayments == null) {
            mMyAdapterPayments = new MyAdapterPayments(getQueryPayments(), Transaccion.class, mAdapterItemsPayments, mAdapterKeysPayments);
            return mMyAdapterPayments;
        } else {
            return mMyAdapterPayments;
        }
    }
}

