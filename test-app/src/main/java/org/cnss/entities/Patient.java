package org.cnss.entities;

import org.cnss.helpers.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient {
    private int ID;
    private int matricule;
    private String firstName;
    private String lastName;
    private String email;

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


    //    *************************************************************** getters *********************
    public int getMatricule() {
        return matricule;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


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

    public boolean getPatientByNumber(int number){
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
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return email != null;
    }
}
