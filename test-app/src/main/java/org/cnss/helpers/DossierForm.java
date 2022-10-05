package org.cnss.helpers;

import org.cnss.controllers.DossierRepositoryImp;
import org.cnss.entities.*;

import java.util.Scanner;

import static org.cnss.Main.RED;
import static org.cnss.Main.RESET;

public class DossierForm {
    DossierRepositoryImp dossierImplementation;
    Consultations consultation;
    Medicaments medicament;
    Documents document;
    Patient patient = new Patient();

    private final Scanner in = new Scanner(System.in);

    public boolean addDossierForm(){
        Dossiers dossier = new Dossiers();
        System.out.print("\nPatient Matricule: ");
        int mat = in.nextInt();
        int newCodeDossier = generateCodeDossier();
        if(patient.getPatientByNumber(mat)){
            System.out.println(patient.toString());
            System.out.println("\n ##\t Dossier information: \t##");
            dossier.setCodeDossier(newCodeDossier);
            dossier.setMatriculePatient(mat);
            dossierImplementation = new DossierRepositoryImp(dossier);
//            if(dossierImplementation.saveDossier(dossier)){
            System.out.println("Consultation Type: 1- Generalist\t\t 2-Specialist");
            System.out.print("Which type >>\t");
            int consType = in.nextInt();
            switch(consType){
                case 1 -> {
//                    consultation = new Consultations(do)
                    addConsultation(dossier, 80.0);
                    dossier.setMontantRem(80.0);
                }
                case 2 -> {
                    addConsultation(dossier, 120.0);
                    dossier.setMontantRem(120.0);
                }
            }
            // medicament

//            }
        }else{
            System.out.println(RED+"Sorry there's no patient with this matricule!"+RESET);
            addDossierForm();
        }
        return true;
    }

    public int generateCodeDossier(){
        return (int)(Math.random()*(99999-9999+1)+9999);
    }
    public boolean addConsultation(Dossiers dossier,double price){
        int idDossier = dossier.getDossierByCode(dossier.getCodeDossier());
        System.out.println(idDossier);
        return true;
//        consultation = new Consultations()
    }


}
