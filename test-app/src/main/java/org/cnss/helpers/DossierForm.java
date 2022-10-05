package org.cnss.helpers;

import org.cnss.controllers.DossierRepositoryImp;
import org.cnss.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
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
                    addConsultation(dossier, 120.0);
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
    public void addConsultation(Dossiers dossier,double price){
        int idDossier = dossier.getDossierByCode(dossier.getCodeDossier());
        consultation = new Consultations(idDossier, price);
    }

    public void addMedicals(Dossiers dossier){
        HashMap<Integer, Double> medicals = new HashMap<>();
        System.out.print("\nIs there any medicals ? 1- Yes\t 2-No >>\t");
        int res = in.nextInt();
        boolean status = true;
        if(res == 1){
            do {
                System.out.print("Enter the medical code: ");
                int codeBarre = in.nextInt();
                System.out.print("Enter the price: ");
                double price = in.nextDouble();
                medicals.put(codeBarre, price);
                System.out.print("\n Still any medical ? 1- Yes\t 2-No >>");
                res = in.nextInt();
                if(res == 2)
                    status = false;
            }while (status);
        }
        ArrayList<Medicaments> allMedicals = new ArrayList<>();

        System.out.println(medicals);
    }


}
