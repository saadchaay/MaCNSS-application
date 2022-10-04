package org.cnss.entities;

public class Patient {
    private int matricule;
    private String firstName;
    private String lastName;
    private String email;


//    *************************************************************** constructors *********************
    public Patient(){}
    public Patient(int matricule, String firstName, String lastName, String email) {
        this.matricule = matricule;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
}
