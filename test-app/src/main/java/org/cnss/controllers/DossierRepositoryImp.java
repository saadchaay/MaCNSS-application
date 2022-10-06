package org.cnss.controllers;

import org.cnss.entities.Dossiers;
import org.cnss.repositories.DossierRepository;

public class DossierRepositoryImp implements DossierRepository {
    Dossiers dossier ;

    public DossierRepositoryImp(Dossiers dossier) {
        this.dossier = dossier;
    }

    @Override
    public Dossiers getDossierByCode(int DossierCode) {
        return null;
    }

    @Override
    public Boolean saveDossier(Dossiers dossier) {
        return dossier.save();
    }

    @Override
    public Dossiers updateDossier(Dossiers dossier, int id) {
        return null;
    }

    @Override
    public Boolean deleteDossier(int id) {
        return null;
    }

    @Override
    public Dossiers showDossier(int id) {
        return null;
    }
}
