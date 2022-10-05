package org.cnss;

import org.cnss.controllers.AgentsRepositoryImpl;

import org.cnss.entities.*;
import org.cnss.helpers.Database;
import org.cnss.helpers.LoginForm;
import org.cnss.helpers.Sessions;

import java.sql.ResultSet;
import java.util.*;

public class Main {
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    static Sessions s = new Sessions();
    static LoginForm form = new LoginForm();
    public static void main(String[] args) {

        System.out.println("\t ##  WELCOME MACNSS APP  ##");
        app();
//        Agents ag = new Agents();
//        ArrayList<Agents> allAgents = ag.all();
//        for (Agents e: allAgents){
//            System.out.println(e.getEmail());
//        }

    }

    public static void app(){
//      AgentsRepositoryImpl agentRepo = new AgentsRepositoryImpl();
        System.out.println("Please choose your session");
        s.menuSession();
        // display form login and make an instance for the user credentials
        form.displayForm(s);
        if(auth(form, s)){
            switch (s.getLoggedIn()) {
                case "ADMIN" -> s.adminSession();
                case "AGENT" -> s.agentSession();
                case "PATIENT" -> s.patientSession();
            }
            app();
        }else System.out.println(RED+"Failed, your email or password incorrect."+RESET);
    }

    public static boolean auth(LoginForm f, Sessions s){
        return true;
    }

}