package org.cnss.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.cnss.Main.*;


public class Sessions {
    private final HashMap<Integer, String> allUsers = new HashMap<>() ;
    private String loggedIn;
    // Forms
    AgentForm form = new AgentForm();
    DossierForm dossierForm = new DossierForm();
    Scanner in = new Scanner(System.in);

    public Sessions() {
        EnumValues admin = EnumValues.users.ADMIN::toString;
        EnumValues agent = EnumValues.users.AGENT::toString;
        EnumValues patient = EnumValues.users.PATIENT::toString;
        allUsers.put(1, admin.setValue());
        allUsers.put(2, agent.setValue());
        allUsers.put(3, patient.setValue());
    }

    public HashMap<Integer, String> getAllUsers() {
        return allUsers;
    }

    public String getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(String loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void menuSession(){
        for(Map.Entry e: this.allUsers.entrySet()){
            System.out.println(e.getKey()+": "+e.getValue());
        }
        int choice;
        do {
            choice = in.nextInt();
            switch (choice) {
                case 1 -> loggedIn = allUsers.get(1);
                case 2 -> loggedIn = allUsers.get(2);
                case 3 -> loggedIn = allUsers.get(3);
            }
            if(choice < 1 || choice > 3){
                System.out.print(RED+"\nFailed, enter the num between 1 and 3 >>  "+RESET);
            }
        }while (choice < 1 || choice >3);
    }

    public void adminSession(){
        System.out.println("\n\t\t ADMIN DASHBOARD: ");
        System.out.println("1: Add new agent;");
        System.out.println("2: Update an agent;");
        System.out.println("3: Delete an agent;");
        System.out.println("4: List all agents;");
        System.out.println("5: LogOut;");
        int choice;
        do {
            choice = in.nextInt();
            switch (choice){
                case 1:
                    if(form.addAgentForm())
                        System.out.println(GREEN+"\n\tAdd an agent successfully."+RESET);
                    else System.out.println(RED+"\n\tFailed to add an agent"+RESET);
                    break;
                case 2:
                    // function to update an agent
                    break;
                case 3:
                    // function to delete an agent
                    break;
                case 4:
                    form.listAllAgent();
                    break;
                case 5:
                    break;
            }

            if(choice < 1 || choice > 5){
                System.out.print("\nFailed, enter the num between 1 and 5 >>  ");
            }
        }while (choice < 1 || choice >5);
        if(choice != 5) {
            adminSession();
        }else{
            if (loggedOut())
                System.out.println("Logged Out");
            else System.out.println("still logged in");
        }
    }

    public void agentSession(String codeVerif){
        Scanner sc = new Scanner(System.in);
        System.out.println("Code de Verification : ");
        String codeEntrer = sc.nextLine();
        if(codeVerif.equals(codeEntrer)){
            System.out.println("\n\t Agent Dashboard: ");
            System.out.println("1: Add new Dossier for a patient;");
            System.out.println("2: Manage Dossiers;");
            System.out.println("3: LogOut;");
            int choice;
            do {
                choice = in.nextInt();
                switch (choice){
                    case 1:
                        if(dossierForm.addDossierForm()){
                            System.out.println(GREEN+"\n\tAdd dossier successfully."+RESET);
                        }
                        break;
                    case 2:
                        // function to update an agent
                        break;
                    case 3:
                        break;
                }
                if(choice < 1 || choice > 3){
                    System.out.print("\nFailed, enter the num between 1 and 3 >>  ");
                }
            }while (choice < 1 || choice > 3);
        }
        else{
            System.out.println(RED+"The code in  incorrect"+RESET);
        }
    }

    public void patientSession(){
        System.out.println("\t Admin Dashboard: ");
        System.out.println("1: Check all dossiers;");
        System.out.println("2: LogOut;");
        int choice;
        do {
            choice = in.nextInt();
            switch (choice){
                case 1:
                    // function to add new agent
                    break;
                case 2:
                    // function to update an agent
                    break;
                case 3:
                    // function to delete an agent
                    break;
                case 4:
                    // list all agents
            }
            if(choice < 1 || choice > 4){
                System.out.print("\nFailed, enter the num between 1 and 4 >>  ");
            }
        }while (choice < 1 || choice >4);
    }

    public boolean loggedOut(){
        this.setLoggedIn(null);
        return true;
    }
}
