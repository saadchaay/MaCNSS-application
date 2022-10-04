package org.cnss.entities;

public class Papiers {

    private int dossierID;
    private double price;

    public Papiers() {}

    public Papiers(int dossierID, double price) {
        this.dossierID = dossierID;
        this.price = price;
    }

    public int getDossierID() {
        return dossierID;
    }

    public void setDossierID(int dossierID) {
        this.dossierID = dossierID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
