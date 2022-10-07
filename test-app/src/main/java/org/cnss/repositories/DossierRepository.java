package org.cnss.repositories;

import org.cnss.entities.Dossiers;

import java.util.ArrayList;

public interface DossierRepository {

    Dossiers getDossierByCode(int DossierCode);
    void saveDossier(Dossiers dossier);
    boolean updateDossier(int id, String status);
    int getMatricule(int id);
    Boolean deleteDossier(int id);
    Dossiers showDossier(int id);
    ArrayList<Dossiers> allDossiers();
}
