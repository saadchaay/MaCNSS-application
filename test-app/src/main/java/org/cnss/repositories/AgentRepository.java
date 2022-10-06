package org.cnss.repositories;

import org.cnss.entities.Agents;

import java.util.ArrayList;

public interface AgentRepository {
    Agents getAgentById(int id);
    Boolean saveAgent(Agents agent);
    ArrayList<Agents> getAllAgents();
    Boolean deleteAgent(int id);

}
