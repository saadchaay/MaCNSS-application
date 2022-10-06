package org.cnss.entities;

import org.cnss.helpers.*;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Dossiers{
    private int codeDossier;
    private int matriculePatient;
    private LocalDate appliedDate;
//    private enum Status {PENDING, REFUSED, VALIDATE};
    private String status;
    private Double montantRem;

    Database db ;
    public Dossiers() {
        db = new Database();
        EnumValues s1 = EnumValues.status.PENDING::toString;
        this.status = s1.setValue();
        this.montantRem = 0.00;
    }

    public Dossiers(int codeDossier, int matriculePatient) {
        this.codeDossier = codeDossier;
        this.matriculePatient = matriculePatient;
        this.appliedDate = LocalDate.now();
        EnumValues s1 = EnumValues.status.PENDING::toString;
        this.status = s1.setValue();
        this.montantRem = 0.00;
    }

    public int getCodeDossier() {
        return codeDossier;
    }

    public void setCodeDossier(int codeDossier) {
        this.codeDossier = codeDossier;
    }

    public int getMatriculePatient() {
        return matriculePatient;
    }

    public void setMatriculePatient(int matriculePatient) {
        this.matriculePatient = matriculePatient;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public Double getMontantRem() {
        return montantRem;
    }

    public void setMontantRem(Double montantRem) {
        this.montantRem = montantRem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        EnumValues s1 = () -> {
            return EnumValues.status.valueOf(status.toUpperCase()).toString();
        };
        this.status = s1.setValue();
    }

    public boolean save(){
        String sql = "INSERT INTO dossiers (codeDossier, matricule) VALUES " +
                "("+ this.codeDossier +", "+ this.matriculePatient +");";
        return db.execute(sql);
    }

    public int getDossierByCode(int code){
        Dossiers newDossier;
        String sql = "SELECT * FROM dossiers WHERE codedossier = "+code;
        ResultSet res = db.resultSet(sql);
        int id = 0;
        try {
            while (res.next()){
                this.codeDossier = code;
                this.status = res.getString("status");
                this.montantRem = res.getDouble("montantrem");
                id = res.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

//    Show Dossier function
    public void ShowMyDossier(String matricule){
        ArrayList<Dossiers> listDossier = new ArrayList<>();
        String sql = "SELECT * FROM dossiers WHERE matricule = "+matricule;
        ResultSet res = db.resultSet(sql);
        try {
           while (res.next()){
               System.out.println("********-_ Dossier "+res.getInt("codeDossier")+" _-********");
               System.out.println("Statut : "+res.getString("status"));
               System.out.println("Price  : "+res.getInt("montantRem"));
           }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
//        return listDossier;

    }


}
