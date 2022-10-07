package org.cnss.entities;

import org.cnss.helpers.Database;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Patient {
    private int ID;
    private int matricule;
    private String firstName;
    private String lastName;
    private String email;

    private String password;

    Database db;


    //    *************************************************************** constructors *********************
    public Patient(){ db = new Database();}
    public Patient(int id, int matricule, String firstName, String lastName, String email) {
        this.ID = id;
        this.matricule = matricule;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.db = new Database();
    }

    public Patient(int matricule, String password) {

        this.matricule = matricule;
        this.password = password;
        this.db = new Database();
    }

    public Patient(int id, int matricule, String firstName, String lastName, String email, String password) {
        db = new Database();
        this.ID = ID;
        this.matricule = matricule;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.db = db;
    }


    //    *************************************************************** getters *********************
    public int getMatricule() {
        return this.matricule;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword(){return this.password;}


    //    *************************************************************** setters *********************
    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\n" +
                "ID: " + ID +
                ", Matricule: " + matricule +
                ", First Name: '" + firstName + '\'' +
                ", Last Name: '" + lastName + '\'' +
                ", Email: '" + email + '\'';
    }

    public String getPatientByNumber(int number){
        String sql = "SELECT * FROM patients WHERE matricule = "+number;
        ResultSet res = db.resultSet(sql);
//        System.out.println(res);
        try {
            while (res.next()){
                this.ID = res.getInt("id");
                this.matricule = res.getInt("matricule");
                this.email = res.getString("email");
                this.firstName = res.getString("firstname");
                this.lastName = res.getString("lastname");
                return email;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //get all Patients
    public ArrayList<Patient> all(){
        ArrayList<Patient> patients = new ArrayList<>();
        ResultSet res = db.resultSet("SELECT * FROM patients");
        try{
            while ( res.next() ){
                this.ID = res.getInt("id");
                this.matricule = res.getInt("matricule");
                this.firstName = res.getString("firstname");
                this.lastName = res.getString("lastname");
                this.password = res.getString("password");
                this.email = res.getString("email");
                Patient newPatient = new Patient(this.ID,this.matricule,this.firstName,this.lastName,this.email,this.password);
                patients.add(newPatient);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return patients;
    }

    public boolean ifPatient(String  matricule,String password){
      ArrayList<Patient> listPatient = all();
//        System.out.println(matricule);
//        System.out.println(password);
        for (Patient p:listPatient) {
//            System.out.println(p.toString());
//            System.out.println(String.valueOf(p.getMatricule()));
//            System.out.println(p.getPassword());
            if(String.valueOf(p.getMatricule()).equals(matricule) && p.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
