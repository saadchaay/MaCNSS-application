package org.cnss.controllers;

import org.cnss.entities.Agents;
import org.cnss.entities.Patient;
import org.cnss.helpers.Database;

public class Authentification {
    private String email;
    private String password;
    private Database db;
    private boolean auth;
    private Agents agents = new Agents();
    private Patient patients = new Patient();

    public Authentification(String login,String password,String grade){
        switch (grade){
            case "ADMIN":
                this.auth = agents.ifAdminExist(login,password);
                break;
            case "AGENT":
                this.auth = agents.ifExist(login,password);
                break;
            default:

                this.auth = patients.ifPatient(login,password);
                break;
        }
    }

    public boolean getAuth(){
        return this.auth;
    }

}
