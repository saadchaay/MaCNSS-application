package org.cnss.repositories;

import org.cnss.entities.Dossiers;

public interface DossierRepository {

    Dossiers getDossierByCode(int DossierCode);
    Dossiers saveDossier(Dossiers dossier);
    Dossiers updateDossier(Dossiers dossier, int id);
    Boolean deleteDossier(int id);
    Dossiers showDossier(int id);
}
