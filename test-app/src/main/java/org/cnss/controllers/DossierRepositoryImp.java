package org.cnss.controllers;

import org.cnss.entities.Dossiers;
import org.cnss.repositories.DossierRepository;

import java.util.ArrayList;

public class DossierRepositoryImp implements DossierRepository {
    Dossiers dossier ;

    public DossierRepositoryImp() {
        dossier = new Dossiers();
    }

    public DossierRepositoryImp(Dossiers dossier) {
        this.dossier = dossier;
    }

    @Override
    public Dossiers getDossierByCode(int DossierCode) {
        return null;
    }

    public int getMatricule(int id){
        return dossier.getMatriculeById(id);
    }

    public void setDossier(Dossiers dossier) {
        this.dossier = dossier;
    }

    @Override
    public void saveDossier(Dossiers dossier) {
        dossier.save();
    }

    public boolean updateDossier(int id, String status) {
        return dossier.update(id, status);
    }

    @Override
    public Boolean deleteDossier(int id) {
        return null;
    }

    @Override
    public Dossiers showDossier(int id) {
        return null;
    }

    @Override
    public ArrayList<Dossiers> allDossiers() {
        return  dossier.allDossier();
    }
}
