package org.cnss.helpers;

import org.cnss.controllers.AgentsRepositoryImpl;
import org.cnss.entities.Agents;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AgentForm {
    private final AgentsRepositoryImpl agentController = new AgentsRepositoryImpl();
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
        System.out.println("there's the list");
        ArrayList<Agents> agents = agentController.getAllAgents();
        System.out.println("|\t\t EMAIL \t\t|\t\t PASSWORD \t\t|\t\t ROLE \t\t|");
        for (Agents A: agents){
            System.out.print("| "+A.getEmail()+" |"+A.getPassword()+" |");
        }

    }

}
