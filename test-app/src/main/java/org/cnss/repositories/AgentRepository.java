package org.cnss.repositories;

import org.cnss.entities.Agents;

public interface AgentRepository {
    Agents getAgentById(int id);
    Agents saveAgent(Agents agent);
    Agents getAllAgents();
    Boolean deleteAgent(int id);
}
