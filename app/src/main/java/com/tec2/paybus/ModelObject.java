package com.tec2.paybus;

public enum ModelObject {

    RED(R.string.title_home, R.layout.fragment_opciones_pago),
    BLUE(R.string.title_home, R.layout.fragment_resumen),
    GREEN(R.string.title_home, R.layout.fragment_resultado);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}