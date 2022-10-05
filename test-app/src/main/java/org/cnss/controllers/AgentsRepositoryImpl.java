package org.cnss.controllers;

import org.cnss.entities.Agents;
import org.cnss.repositories.AgentRepository;

import java.util.ArrayList;

public class AgentsRepositoryImpl implements AgentRepository {

    private Agents agent ;

    @Override
    public Boolean saveAgent(Agents agent) {
        return agent.save();
    }

    @Override
    public Agents getAgentById(int id) {
        return agent.show(id);
    }

    @Override
    public ArrayList<Agents> getAllAgents() {
        return agent.all();
    }

    @Override
    public Boolean deleteAgent(int id) {
        return null;
    }
}
