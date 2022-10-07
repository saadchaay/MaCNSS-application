package org.cnss.helpers;

import org.cnss.entities.Dossiers;
import org.cnss.entities.Patient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static org.cnss.Main.*;


public class Sessions {
    private final HashMap<Integer, String> allUsers = new HashMap<>() ;
    private String loggedIn;
    // Forms
    AgentForm form = new AgentForm();
    DossierForm dossierForm = new DossierForm();
    Scanner in = new Scanner(System.in);
    String ACCEPTED = EnumValues.status.ACCEPTED.toString();
    String REFUSED = EnumValues.status.REFUSED.toString();
    String PENDING = EnumValues.status.PENDING.toString();

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

    public void agentSession(){
        SendMail mailMessage = new SendMail();
        Patient patient = new Patient();
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
                        System.out.println(GREEN+"\nAdd dossier successfully."+RESET);
                    }
                    agentSession();
                    break;
                case 2:
                    dossierForm.manageDossier();
                    System.out.print("\nChoose the dossier that you want to manage >> ");
                    int idDossier = in.nextInt();
                    int matricule = dossierForm.dossierImplementation.getMatricule(idDossier);
                    String email = patient.getPatientByNumber(matricule);
                    System.out.print("Manage this dossier:\t\t1-"+ACCEPTED+ "\t\t2-"+REFUSED);
                    int choiceStatus;
                    do {
                        System.out.print("\nEnter your choice >>\t");
                        choiceStatus = in.nextInt();
                    }while (choiceStatus != 1 && choiceStatus != 2);
                    String status = PENDING;
                    switch (choiceStatus){
                        case 1 -> status = ACCEPTED;
                        case 2 -> status = REFUSED;
                    }
                    Dossiers dos = new Dossiers();
                    if(dossierForm.dossierImplementation.updateDossier(idDossier, status)) {
                        dos.getDossier(idDossier);
                        Scanner st = new Scanner(System.in);
                        System.out.print("\nAdd Description >>>\t");
                        String desc = st.nextLine();
                        String msg = "Dossier Number: \t\t"+dos.getCodeDossier()+"\nStatus: \t"+dos.getStatus()+".\n";
                        if(Objects.equals(dos.getStatus(), "ACCEPTED"))
                            msg += "\nAmount: "+dos.getMontantRem()+"\n";
                        msg+= "\nDescription: "+desc+".";
                        if(mailMessage.sendMessage(email,msg))
                            System.out.println(GREEN+"Message hase been sent to the patient."+RESET);
                    }
                    agentSession();
                    break;
                case 3:
                    break;
            }
            if(choice < 1 || choice > 3){
                System.out.print("\nFailed, enter the num between 1 and 3 >>  ");
            }
        }while (choice < 1 || choice > 3);
    }

    public void patientSession(String matricule){
        System.out.println("\t Patient Dashboard: ");
        System.out.println("1: Check all dossiers;");
        System.out.println("2: LogOut;");
        int choice;
        do {
            choice = in.nextInt();
            switch (choice) {
                case 1 -> {
                    Dossiers d = new Dossiers();
                    d.ShowMyDossier(matricule);
                    patientSession(matricule);
                }
                case 2 -> loggedOut();
                default -> {
                }
            }
            if(choice < 1 || choice > 2){
                System.out.print("\nFailed, enter the num between 1 and 2 >>  ");
            }
        }while (choice < 1 || choice >2);

    }

    public boolean loggedOut(){
        this.setLoggedIn(null);
        return true;
    }
}
