package org.cnss;

import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.cnss.controllers.AgentsRepositoryImpl;

import org.cnss.controllers.Authentification;
import org.cnss.entities.*;
import org.cnss.helpers.Database;
import org.cnss.helpers.LoginForm;
import org.cnss.helpers.SendMail;
import org.cnss.helpers.Sessions;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.*;

public class Main {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    static Sessions s = new Sessions();
    static LoginForm form = new LoginForm();
    public static void main(String[] args) {
        app();
    }

    public static void app(){
        SendMail senMail = new SendMail() ;
//      AgentsRepositoryImpl agentRepo = new AgentsRepositoryImpl();
        System.out.println("Please choose your session");
        s.menuSession();
        // display form login and make an instance for the user credentials
        s.agentSession("34773");
        form.displayForm(s);
       /* Authentification auth = new Authentification(form.getCredentials(),form.getPassword(),s.getLoggedIn());
        if(auth.getAuth()){
            switch (s.getLoggedIn()) {
                case "ADMIN" -> s.adminSession();
                case "AGENT" -> s.agentSession(senMail.sendMail(form.getCredentials()));
                case "PATIENT" -> s.patientSession();
            }

        }else System.out.println(RED+"Failed, your email or password incorrect."+RESET);
        */app();
    }

    public static boolean auth(LoginForm f, Sessions s){
        return true;
    }



}