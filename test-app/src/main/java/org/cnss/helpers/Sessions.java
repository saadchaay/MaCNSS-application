package org.cnss.helpers;

import java.util.HashMap;
import java.util.Scanner;

public class Sessions {

    private HashMap<Integer, String> current_session = new HashMap<>() ;

    public void menuSession(){
        Scanner in = new Scanner(System.in);
        System.out.println("1: Admin;");
        System.out.println("2: Agent;");
        System.out.println("3: Patient;");
        int choice;
        do {
            choice = in.nextInt();
            switch (choice){
                case 1:
                    this.current_session.put(1,"Admin");
                    break;
                case 2:
                    this.current_session.put(2,"Agent");
                    break;
                case 3:
                    this.current_session.put(3,"Patient");
                    break;
            }
        }while (choice < 1 || choice >3 );

    }
}
