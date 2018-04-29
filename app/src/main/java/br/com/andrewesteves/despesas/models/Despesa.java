package br.com.andrewesteves.despesas.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Despesa implements Parcelable {
    private int codigo;
    private String titulo;
    private Double total;

    public Despesa() {}

    public Despesa(int codigo, String titulo, Double total) {
        this.setCodigo(codigo);
        this.setTitulo(titulo);
        this.setTotal(total);
    }

    public Despesa(String titulo, Double total) {
        this.setTitulo(titulo);
        this.setTotal(total);
    }

    protected Despesa(Parcel in) {
        codigo = in.readInt();
        titulo = in.readString();
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readDouble();
        }
    }

    public static final Creator<Despesa> CREATOR = new Creator<Despesa>() {
        @Override
        public Despesa createFromParcel(Parcel in) {
            return new Despesa(in);
        }

        @Override
        public Despesa[] newArray(int size) {
            return new Despesa[size];
        }
    };

    public int getCodigo() { return codigo; }

    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * O Adapter do ListView utiliza o toString para apresentação
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.getTitulo() + " | R$ " + this.getTotal();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(codigo);
        parcel.writeString(titulo);
        if (total == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(total);
        }
    }
}
