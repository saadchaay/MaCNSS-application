package org.cnss.entities;

import org.cnss.helpers.Database;
import org.cnss.helpers.EnumValues;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Dossiers{
    private int ID;
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

    public Dossiers(int id, int codeDossier, int matriculePatient) {
        this.ID = id;
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

    @Override
    public String toString() {
        return "" +
                "ID: " + ID +
                ", code Dossier:  " + codeDossier +
                ", matricule de Patient:  " + matriculePatient +
                ", status:  ' " + status + '\'' +
                ", Amount:  " + montantRem ;
    }

    public boolean save(){
        String sql = "INSERT INTO dossiers (codeDossier, matricule, montantrem) VALUES " +
                "("+ this.codeDossier +", "+ this.matriculePatient +","+ this.getMontantRem()+");";
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

    public int getMatriculeById(int id){
        Dossiers newDossier;
        String sql = "SELECT * FROM dossiers WHERE id = "+id;
        ResultSet res = db.resultSet(sql);
        try {
            while (res.next()){
                return res.getInt("matricule");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void getDossier(int id){
        Dossiers newDossier;
        String sql = "SELECT * FROM dossiers WHERE id = "+id;
        ResultSet res = db.resultSet(sql);
        try {
            while (res.next()){
                this.ID = id;
                setCodeDossier(res.getInt("codedossier"));
                setStatus(res.getString("status"));
                setMatriculePatient(res.getInt("matricule"));
                setMontantRem(res.getDouble("montantrem"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
               if(Objects.equals(res.getString("status"), "PENDING"))
                System.out.println("Price  : en cours de traitement");
               else
                System.out.println("Price  : "+res.getInt("montantRem"));
           }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
//        return listDossier;
    }

    public boolean update(int dossierID, String status){
        String sql = "UPDATE dossiers SET status = '"+status+"' WHERE id ="+dossierID+";";
        try {
            return db.execute(sql);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ArrayList<Dossiers> allDossier(){
        ArrayList<Dossiers> dossiers = new ArrayList<>();
        ResultSet res = db.resultSet("SELECT * FROM dossiers");
        try{
            while ( res.next() ){
                this.ID = res.getInt("id");
                this.codeDossier = res.getInt("codedossier");
                this.matriculePatient = res.getInt("matricule");
                Dossiers newDossier = new Dossiers(this.ID, this.codeDossier, this.matriculePatient);
                newDossier.setMontantRem(res.getDouble("montantrem"));
                newDossier.setStatus(res.getString("status"));
//                newDossier.setAppliedDate(res.getDate("applieddate"));
                if(Objects.equals(res.getString("status"), "PENDING"))
                    dossiers.add(newDossier);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return dossiers;
//        try {
//            ResultSet res = db.resultSet(sql);
//            while (res.next()){
//                if(Objects.equals(res.getString("status"), "PENDING"))
//                    System.out.println("ID=: "+ res.getInt("id")+", " +
//                        "Code dossier: "+res.getInt("codedossier")+
//                        "Patient Matricule:"+res.getInt("matricule")+
//                        "Applied Date:"+res.getDate("appliedDate"));
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }


}
