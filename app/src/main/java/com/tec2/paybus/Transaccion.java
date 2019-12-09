package com.tec2.paybus;

import android.os.Parcel;
import android.os.Parcelable;

class Transaccion implements Parcelable {

    private Long monto;
    private String origen;
    private String receptor;
    private Long tipo;
    private Long medio;
    private Long timestamp;
    private String ruta;
    private Long numbus;

    public Transaccion() {
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public Long getMedio() {
        return medio;
    }

    public void setMedio(Long medio) {
        this.medio = medio;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Long getNumbus() {
        return numbus;
    }

    public void setNumbus(Long numbus) {
        this.numbus = numbus;
    }

    protected Transaccion(Parcel in) {
        if (in.readByte() == 0) {
            monto = null;
        } else {
            monto = in.readLong();
        }
        origen = in.readString();
        receptor = in.readString();
        if (in.readByte() == 0) {
            tipo = null;
        } else {
            tipo = in.readLong();
        }
        if (in.readByte() == 0) {
            medio = null;
        } else {
            medio = in.readLong();
        }
        if (in.readByte() == 0) {
            timestamp = null;
        } else {
            timestamp = in.readLong();
        }
        ruta = in.readString();
        if (in.readByte() == 0) {
            numbus = null;
        } else {
            numbus = in.readLong();
        }
    }

    public static final Creator<Transaccion> CREATOR = new Creator<Transaccion>() {
        @Override
        public Transaccion createFromParcel(Parcel in) {
            return new Transaccion(in);
        }

        @Override
        public Transaccion[] newArray(int size) {
            return new Transaccion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (monto == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(monto);
        }
        parcel.writeString(origen);
        parcel.writeString(receptor);
        if (tipo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(tipo);
        }
        if (medio == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(medio);
        }
        if (timestamp == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(timestamp);
        }
        parcel.writeString(ruta);
        if (numbus == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(numbus);
        }
    }
}
