package org.cnss;

import org.cnss.controllers.Authentification;
import org.cnss.entities.*;
import org.cnss.helpers.LoginForm;
import org.cnss.helpers.SendMail;
import org.cnss.helpers.Sessions;

import java.time.LocalTime;
import java.util.*;

public class Main {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    static Sessions s = new Sessions();
    static LoginForm form = new LoginForm();
    public static void main(String[] args) {
        //Maij function
        System.out.println("***************************** MaCnss-Application *****************************");
    Dossiers d = new Dossiers();
//    d.ShowMyDossier();
        app();

    }

    public static void app(){
        Scanner sc = new Scanner(System.in);
        SendMail senMail = new SendMail() ;
        System.out.println("\nPlease choose your session");
        s.menuSession();
        form.displayForm(s);
        Authentification auth = new Authentification(form.getCredentials(),form.getPassword(),s.getLoggedIn());
        if(auth.getAuth()){
            switch (s.getLoggedIn()) {
                case "ADMIN" -> s.adminSession();
                case "AGENT" -> {
                    LocalTime sentCodeTiming = LocalTime.now();
                    String codeEntrer;
                    String codeSent = senMail.sendMail(form.getCredentials());
                    System.out.println("Code de Verification : ");
                    do {
                        codeEntrer = sc.nextLine();
                        if(!Objects.equals(codeSent, codeEntrer)) {
                            System.out.println(RED + "The code is incorrect" + RESET);
                            System.out.print("\nTry again (Enter '0' to cancel) >>  ");
                        }else {
                            if(checkFiveMinutes(sentCodeTiming)) {
                                System.out.println(RED + "The code is expired, Try to enter the new code >> " + RESET);
                                codeSent = senMail.sendMail(form.getCredentials());
                                sentCodeTiming = LocalTime.now();
                            }
                            else s.agentSession();
                        }
                    }while (!Objects.equals(codeSent, codeEntrer) && !Objects.equals("0", codeEntrer));
                }
                case "PATIENT" -> s.patientSession(form.getCredentials());

            }

        }else System.out.println(RED+"Failed, your email or password incorrect."+RESET);
    app();
    }

    public static boolean checkFiveMinutes(LocalTime time){
        LocalTime now = LocalTime.now();
        return now.compareTo(time.plusMinutes(5)) > 0;
    }



}