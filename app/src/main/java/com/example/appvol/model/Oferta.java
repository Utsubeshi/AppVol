package com.example.appvol.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;

public class Oferta implements Parcelable {

    private String id;
    private String titulo;
    private String detalle;
    private String fecha;
    private String ubicacion;
    private String tipo;
    private String urlImagen;
    private String organizacion;
    private String tiempoMin;
    private boolean estado;
    private int cantidadMin;


    public Oferta(){}

    public Oferta( String titulo, String detalle, String fecha, String ubicacion, String tipo, String urlImagen, String organizacion, String tiempoMin, boolean estado, int cantidadMin) {

        this.titulo = titulo;
        this.detalle = detalle;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.urlImagen = urlImagen;
        this.organizacion = organizacion;
        this.tiempoMin = tiempoMin;
        this.estado = estado;
        this.cantidadMin = cantidadMin;
    }

    protected Oferta(Parcel in) {
        id = in.readString();
        titulo = in.readString();
        detalle = in.readString();
        fecha = in.readString();
        ubicacion = in.readString();
        tipo = in.readString();
        urlImagen = in.readString();
        organizacion = in.readString();
        tiempoMin = in.readString();
        estado = in.readByte() != 0;
        cantidadMin = in.readInt();
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

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getTiempoMin() {
        return tiempoMin;
    }

    public void setTiempoMin(String tiempoMin) {
        this.tiempoMin = tiempoMin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCantidadMin() {
        return cantidadMin;
    }

    public void setCantidadMin(int cantidadMin) {
        this.cantidadMin = cantidadMin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(titulo);
        dest.writeString(detalle);
        dest.writeString(fecha);
        dest.writeString(ubicacion);
        dest.writeString(tipo);
        dest.writeString(urlImagen);
        dest.writeString(organizacion);
        dest.writeString(tiempoMin);
        dest.writeByte((byte) (estado ? 1 : 0));
        dest.writeInt(cantidadMin);
    }
}
