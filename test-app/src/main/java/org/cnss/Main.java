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
        /*List<String> ns = Arrays.asList("Adam","Omar","Aymen","Sofia");
        List<String> square = ns.stream().map(el -> "Hello\t"+el).collect(Collectors.toList());
        System.out.println("enter the name >> ");
        String name = in.nextLine();
        System.out.println("enter the address >> ");
        String address = in.next();
        System.out.println("enter the age >> ");
        int age = in.nextInt();

        // create table in db
        String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (1, '"+ name +"', "+ age +", '"+ address +"', 20000.00 );";
        if(cn.execute(sql)){
            System.out.println("add success");
        }*/
        /*String sql = "SELECT * FROM patients";
        ResultSet res = cn.resultSet(sql);

        try {
            while (res.next()){
                System.out.println(res.getString("email"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }*/
//        }
    }

    public static void app(){
//      AgentsRepositoryImpl agentRepo = new AgentsRepositoryImpl();
        System.out.println("Please choose your session");
        s.menuSession();
        form.displayForm(s);
        if(auth(form, s)){
            switch (s.getLoggedIn()) {
                case "ADMIN" -> {
                    s.adminSession();
                    app();
                }
                case "AGENT" -> {
                    s.agentSession();
                    app();
                }
                case "PATIENT" -> {
                    s.patientSession();
                    app();
                }
            }
        }else System.out.println(RED+"Failed, your email or password incorrect."+RESET);

    }

    public static boolean auth(LoginForm f, Sessions s){
        return true;
    }

}