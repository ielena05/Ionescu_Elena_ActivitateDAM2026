package com.example.laborator4_5_6;

import java.io.Serializable;

public class Televizor implements Serializable {
    private String numeClient;
    private Firma firma;
    private boolean vreiSmart;
    private Dimensiune dimensiune;

    public Televizor(String numeClient, Firma firma, boolean vreiSmart, Dimensiune dimensiune) {
        this.numeClient = numeClient;
        this.firma = firma;
        this.vreiSmart = vreiSmart;
        this.dimensiune = dimensiune;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public boolean isVreiSmart() {
        return vreiSmart;
    }

    public void setVreiSmart(boolean vreiSmart) {
        this.vreiSmart = vreiSmart;
    }

    public Dimensiune getDimensiune() {
        return dimensiune;
    }

    public void setDimensiune(Dimensiune dimensiune) {
        this.dimensiune = dimensiune;
    }

    @Override
    public String toString() {
        return
                "NumeClient:  " + numeClient  +
                "\n firma: " + firma +
                "\n vreiSmart: " + vreiSmart +
                "\n dimensiune: " + dimensiune;
    }
}
