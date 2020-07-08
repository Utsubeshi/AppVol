package com.example.appvol.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Voluntario implements Parcelable {

    private String nombres;
    private String apellidos;
    private String documento;
    private String telefono;
    private String intereses;
    private String ocupacion;
    private String experiencia;

    public Voluntario(){}


    public Voluntario(String nombres, String apellidos, String documento, String telefono, String intereses, String ocupacion, String experiencia) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.telefono = telefono;
        this.intereses = intereses;
        this.ocupacion = ocupacion;
        this.experiencia = experiencia;
    }

    protected Voluntario(Parcel in) {
        nombres = in.readString();
        apellidos = in.readString();
        documento = in.readString();
        telefono = in.readString();
        intereses = in.readString();
        ocupacion = in.readString();
        experiencia = in.readString();
    }

    public static final Creator<Voluntario> CREATOR = new Creator<Voluntario>() {
        @Override
        public Voluntario createFromParcel(Parcel in) {
            return new Voluntario(in);
        }

        @Override
        public Voluntario[] newArray(int size) {
            return new Voluntario[size];
        }
    };

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIntereses() {
        return intereses;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombres);
        dest.writeString(apellidos);
        dest.writeString(documento);
        dest.writeString(telefono);
        dest.writeString(intereses);
        dest.writeString(ocupacion);
        dest.writeString(experiencia);
    }
}
