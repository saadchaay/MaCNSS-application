package org.cnss.entities;

import org.cnss.helpers.Database;

public class Consultations extends Papiers{
    Database db;
    public Consultations() { db = new Database();}

    public Consultations(int dossierID, double price) {
        super(dossierID, price);
        db = new Database();
    }

    public boolean save(){
        String sql = "INSERT INTO consultatuins (dossierid, price) VALUES " +
                "("+ this.getDossierID() +", "+ this.getPrice() +");";
        return db.execute(sql);
    }

}
