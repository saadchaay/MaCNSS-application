package org.cnss.controllers;

import org.cnss.entities.Agents;
import org.cnss.repositories.AgentRepository;

import java.util.ArrayList;

public abstract class AgentsRepositoryImpl implements AgentRepository {

    private Agents agent ;

    @Override
    public Agents saveAgent(Agents agent) {
        if(agent.save()){
            return agent;
        }else return null;
    }

    @Override
    public Agents getAgentById(int id) {
        return agent.show(id);
    }

    @Override
    public ArrayList<Agents> getAllAgents() {
        return agent.all();
    }
}
