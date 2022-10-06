package org.cnss.controllers;

import org.cnss.entities.Agents;
import org.cnss.repositories.AgentRepository;

import java.util.ArrayList;

public class AgentsRepositoryImpl implements AgentRepository {

    private final Agents agent ;

    public AgentsRepositoryImpl(Agents agent) {
        this.agent = agent;
    }

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
        return this.agent.all();
    }

    @Override
    public Boolean deleteAgent(int id) {
        return null;
    }
}
