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

    public Authentification(String email,String password,String grade){
        switch (grade){
            case "ADMIN":
                this.auth = agents.ifAdminExist(email,password);
                break;
            default:
                this.auth = agents.ifExist(email,password);
                break;
        }
    }

    public boolean getAuth(){
        return this.auth;
    }

}
