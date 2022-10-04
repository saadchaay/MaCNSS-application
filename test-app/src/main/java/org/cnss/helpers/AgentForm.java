package org.cnss.helpers;

import org.cnss.controllers.AgentsRepositoryImpl;
import org.cnss.entities.Agents;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AgentForm {
    private AgentsRepositoryImpl agentController = new AgentsRepositoryImpl();
    private final Agents agent = new Agents();
    private
    Scanner in = new Scanner(System.in);

    public Boolean addAgentForm(){
        System.out.println("\t*\t\tAGENT INFORMATION\t\t*");
        System.out.print("Email:\t");
        agent.setEmail(in.next());
        System.out.print("Password:\t");
        agent.setPassword(in.next());
        return agentController.saveAgent(agent);
    }

    public void listAllAgent(){
        for (int i=0; i<25; i++)
            System.out.print("_");
        ArrayList<Agents> agents = agentController.getAllAgents();
        for (Agents A: agents){
            System.out.print("\t");
        }
    }

}
