package com.example.appvol.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Oferta implements Parcelable {

    private String titulo;
    private String detalle;
    private String fecha;
    private String ubicacion;
    private String tipo;
    private String urlImagen;
    private boolean estado;

    public Oferta(String titulo, String detalle, String fecha, String ubicacion, String tipo, String urlImagen, boolean estado) {
        this.titulo = titulo;
        this.detalle = detalle;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.urlImagen = urlImagen;
        this.estado = estado;
    }

    protected Oferta(Parcel in) {
        titulo = in.readString();
        detalle = in.readString();
        fecha = in.readString();
        ubicacion = in.readString();
        tipo = in.readString();
        urlImagen = in.readString();
        estado = in.readByte() != 0;
    }

    public static final Creator<Oferta> CREATOR = new Creator<Oferta>() {
        @Override
        public Oferta createFromParcel(Parcel in) {
            return new Oferta(in);
        }

        @Override
        public Oferta[] newArray(int size) {
            return new Oferta[size];
        }
    };

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(detalle);
        dest.writeString(fecha);
        dest.writeString(ubicacion);
        dest.writeString(tipo);
        dest.writeString(urlImagen);
        dest.writeByte((byte) (estado ? 1 : 0));
    }
}
