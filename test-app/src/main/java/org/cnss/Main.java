package org.cnss;
import org.cnss.controllers.Authentification;
import org.cnss.entities.*;
import org.cnss.helpers.Database;
import org.cnss.helpers.Sessions;

import java.sql.ResultSet;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //test
        String role;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Your Email :");

        String email = scan.nextLine();
        System.out.println("Enter Your Password : ");

        String password = scan.nextLine();
        Agents agent = new Agents(email,password,false);
        agent.save();

        System.out.println("1) admin \n2) agents");
        int choice  = scan.nextInt();
        switch (choice){
            case 1:
                role = "admin";
                break;
            default:
                role = "agent";
                break;
        }
        Authentification auth = new Authentification(email,password,role);
        System.out.println(auth);
        if(auth.getAuth()){
            System.out.println("Hello You are :"+email+" / Your Role is : "+role);
        }else {
            System.out.println("there is No Agent Or Admin");
        }

//        Agents agent = new Agents();
//        ArrayList<Agents> listeAgent = agent.all();
//        for (Agents a:listeAgent
//             ) {
//            System.out.println(a.getEmail());
//        }


    }


}