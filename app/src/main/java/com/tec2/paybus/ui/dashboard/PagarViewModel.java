package com.tec2.paybus.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tec2.paybus.R;

public class PagarViewModel extends ViewModel {

    private MutableLiveData<Integer> iContador;

    public PagarViewModel() {
        iContador = new MutableLiveData<>();
        iContador.setValue(1);
    }

    public MutableLiveData<Integer> getContador() {
        return iContador;
    }
}