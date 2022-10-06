package org.cnss.entities;

import org.cnss.helpers.Database;

public class Papiers {

    private int dossierID;
    private double price;

    Database db;
    public Papiers() { db = new Database(); }

    public Papiers(int dossierID, double price) {
        db = new Database();
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

    @Override
    public String toString() {
        return "Papiers{" +
                "dossierID=" + dossierID +
                ", price=" + price +
                ", db=" + db +
                '}';
    }
}
