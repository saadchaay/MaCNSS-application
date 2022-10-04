package org.cnss.repositories;

import org.cnss.entities.Agents;

import java.util.ArrayList;

public interface AgentRepository {
    Agents getAgentById(int id);
    Agents saveAgent(Agents agent);
    ArrayList<Agents> getAllAgents();
    Boolean deleteAgent(int id);
}
