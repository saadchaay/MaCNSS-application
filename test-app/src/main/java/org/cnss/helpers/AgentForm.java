package org.cnss.helpers;

import org.cnss.controllers.AgentsRepositoryImpl;
import org.cnss.entities.Agents;

import java.util.ArrayList;
import java.util.Scanner;

public class AgentForm {
    private final Agents agent = new Agents();
    private final AgentsRepositoryImpl agentController = new AgentsRepositoryImpl(agent);
    private final Scanner in = new Scanner(System.in);

    public Boolean addAgentForm(){
        System.out.println("\t*\t\tAGENT INFORMATION\t\t*");
        System.out.print("Email:\t");
        agent.setEmail(in.next());
        System.out.print("Password:\t");
        agent.setPassword(in.next());
        return agentController.saveAgent(agent);
    }

    public void listAllAgent(){
        ArrayList<Agents> agents = agentController.getAllAgents();
        System.out.println("|\t\t List of agents \t\t|");
        for (Agents A: agents){
            System.out.println(A.toString());
        }
    }

}
