package org.cnss.helpers;

import org.cnss.controllers.DossierRepositoryImp;
import org.cnss.entities.*;

import javax.print.Doc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.cnss.Main.RED;
import static org.cnss.Main.RESET;

public class DossierForm {
    DossierRepositoryImp dossierImplementation;
    Consultations consultation;
    ArrayList<Medicaments> medications = new ArrayList<>();
    Medicaments oneMedical = new Medicaments();
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
            addMedicals(dossier);
            // add all documents ...
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
        consultation = new Consultations(getIdDossier(dossier), price);
    }

    public void addMedicals(Dossiers dossier) {
        HashMap<String, Double> medicals = new HashMap<>();
        System.out.print("\nIs there any medicals ? 1- Yes\t 2-No >>\t");
        int res = in.nextInt();
        boolean status = true;
        if(res == 1){
            do {
                System.out.print("Enter the medical code: ");
                String codeBarre = in.next();
                System.out.print("Enter the price: ");
                double price = in.nextDouble();
                if(oneMedical.checkRemMedicament(codeBarre)){
                        medicals.put(codeBarre, price);
                }else{
                    System.out.println(RED+"This product is not Refundable"+RESET);
                }
                System.out.print("\n Still any medical ? 1- Yes\t 2-No >>");
                res = in.nextInt();
                if(res == 2)
                    status = false;
            }while (status);
        }
        for(Map.Entry<String, Double> med:medicals.entrySet()){
            Medicaments medic = new Medicaments(getIdDossier(dossier), med.getValue(), med.getKey());
            medications.add(medic);
        }
//        System.out.println(medicaments.get(0).getCodeBarre() +" \t"+medicaments.get(0).getPrice());
    }

    public void addDocuments(Dossiers dossier) {
        HashMap<String, ArrayList<Documents>> documents = new HashMap<>();
        String RADIO = EnumValues.documentType.RADIO.toString();
        String SCANNER = EnumValues.documentType.SCANNER.toString();
        String ANALYSE = EnumValues.documentType.ANALYSE.toString();
        System.out.print("\nIs there any documents ? 1- Yes\t 2-No >>\t");
        int res = in.nextInt();
        boolean status = true;
        if(res == 1){
            System.out.println("Which document you want to add?");
            System.out.println("1- "+RADIO);
            System.out.println("2- "+SCANNER);
            System.out.println("3- "+ANALYSE);
            int docTypeScan = in.nextInt();
            switch (docTypeScan){
                case 1 -> documents.put(RADIO, setRadios(dossier));
                case 2 -> System.out.println("yes");
                /*case 2 -> documents.put(SCANNER, setScanners());
                case 3 -> documents.put(ANALYSE, setAnalyses());*/
            }
//            do {
//                System.out.print("Enter the medical code: ");
//                String codeBarre = in.next();
//                System.out.print("Enter the price: ");
//                double price = in.nextDouble();
//                System.out.println(oneMedical.checkRemMedicament(codeBarre));
//                if(oneMedical.checkRemMedicament(codeBarre)){
//                    medicals.put(codeBarre, price);
//                }else{
//                    System.out.println(RED+"This product is not Refundable"+RESET);
//                }
//                System.out.print("\n Still any medical ? 1- Yes\t 2-No >>");
//                res = in.nextInt();
//                if(res == 2)
//                    status = false;
//            }while (status);
        }
        System.out.println(documents);
//        System.out.println(medicaments.get(0).getCodeBarre() +" \t"+medicaments.get(0).getPrice());
    }

    public ArrayList<Documents> setRadios(Dossiers dossier){
        ArrayList<Documents> radioDocs = new ArrayList<>();
        System.out.print("\n Radio documents: \n");
        int res = 1;
        boolean status = true;
        do {
            System.out.print("\nEnter the price: ");
            double price = in.nextDouble();
            Documents tmpDoc = new Documents(getIdDossier(dossier), price, "RADIO");
            radioDocs.add(tmpDoc);
            System.out.print("\n Still any document(Radio) ? 1- Yes\t 2-No >>");
            res = in.nextInt();
            System.out.println();
            if(res == 2)
                status = false;
        }while (status);
        return radioDocs;
//        System.out.println(medicaments.get(0).getCodeBarre() +" \t"+medicaments.get(0).getPrice());
    }

//    public ArrayList<Documents> setScanners(){
//        HashMap<String, Double> radios = new HashMap<>();
//        System.out.print("\nIs there any medicals ? 1- Yes\t 2-No >>\t");
//        int res = in.nextInt();
//        boolean status = true;
//        if(res == 1){
//            do {
//                System.out.print("Enter the medical code: ");
//                String codeBarre = in.next();
//                System.out.print("Enter the price: ");
//                double price = in.nextDouble();
//                System.out.println(oneMedical.checkRemMedicament(codeBarre));
//                if(oneMedical.checkRemMedicament(codeBarre)){
//                    medicals.put(codeBarre, price);
//                }else{
//                    System.out.println(RED+"This product is not Refundable"+RESET);
//                }
//                System.out.print("\n Still any medical ? 1- Yes\t 2-No >>");
//                res = in.nextInt();
//                if(res == 2)
//                    status = false;
//            }while (status);
//        }
//        for(Map.Entry<String, Double> med:medicals.entrySet()){
//            Medicaments medic = new Medicaments(getIdDossier(dossier), med.getValue(), med.getKey());
//            medicaments.add(medic);
//        }
////        System.out.println(medicaments.get(0).getCodeBarre() +" \t"+medicaments.get(0).getPrice());
//    }
//
//    public ArrayList<Documents> setAnalyses(){
//        HashMap<String, Double> radios = new HashMap<>();
//        System.out.print("\nIs there any medicals ? 1- Yes\t 2-No >>\t");
//        int res = in.nextInt();
//        boolean status = true;
//        if(res == 1){
//            do {
//                System.out.print("Enter the medical code: ");
//                String codeBarre = in.next();
//                System.out.print("Enter the price: ");
//                double price = in.nextDouble();
//                System.out.println(oneMedical.checkRemMedicament(codeBarre));
//                if(oneMedical.checkRemMedicament(codeBarre)){
//                    medicals.put(codeBarre, price);
//                }else{
//                    System.out.println(RED+"This product is not Refundable"+RESET);
//                }
//                System.out.print("\n Still any medical ? 1- Yes\t 2-No >>");
//                res = in.nextInt();
//                if(res == 2)
//                    status = false;
//            }while (status);
//        }
//        for(Map.Entry<String, Double> med:medicals.entrySet()){
//            Medicaments medic = new Medicaments(getIdDossier(dossier), med.getValue(), med.getKey());
//            medicaments.add(medic);
//        }
////        System.out.println(medicaments.get(0).getCodeBarre() +" \t"+medicaments.get(0).getPrice());
//    }
    public int getIdDossier(Dossiers dossier){
        return  dossier.getDossierByCode(dossier.getCodeDossier());
    }



}
