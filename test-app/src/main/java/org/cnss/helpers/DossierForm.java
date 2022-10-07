package org.cnss.helpers;

import org.cnss.controllers.DossierRepositoryImp;
import org.cnss.entities.*;

import java.util.*;

import static org.cnss.Main.RED;
import static org.cnss.Main.RESET;

public class DossierForm {
    DossierRepositoryImp dossierImplementation = new DossierRepositoryImp();
    Consultations consultation;
    ArrayList<Medicaments> medications = new ArrayList<>();
    Medicaments oneMedical = new Medicaments();
    ArrayList<Documents> documents = new ArrayList<>();
    Patient patient = new Patient();

    private final Scanner in = new Scanner(System.in);
    String RADIO = EnumValues.documentType.RADIO.toString();
    String SCANNER = EnumValues.documentType.SCANNER.toString();
    String ANALYSE = EnumValues.documentType.ANALYSE.toString();

    public boolean addDossierForm(){
        Dossiers dossier = new Dossiers();
        System.out.print("\nPatient Matricule: ");
        int mat = in.nextInt();
        int newCodeDossier = generateCodeDossier();
        if(!Objects.equals(patient.getPatientByNumber(mat),null)){
            System.out.println(patient.toString());
            System.out.println("\n ##\t Dossier information: \t##");
            dossier.setCodeDossier(newCodeDossier);
            dossier.setMatriculePatient(mat);
            dossierImplementation = new DossierRepositoryImp(dossier);
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
            // add all medications ......
            addMedicals(dossier);
            // add all documents ...
            addDocuments(dossier);
            // calculate refund total price function;
            dossier.setMontantRem(dossier.getMontantRem()+calculateRemDocs()+calculateRemMed());
            dossierImplementation.saveDossier(dossier);
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
        for(Map.Entry<String, Double> med: medicals.entrySet()){
            Medicaments medic = new Medicaments(getIdDossier(dossier), med.getValue(), med.getKey());
            medications.add(medic);
        }
//        System.out.println(medicaments.get(0).getCodeBarre() +" \t"+medicaments.get(0).getPrice());
    }

    public void addDocuments(Dossiers dossier) {
        HashMap<String, ArrayList<Documents>> documents = new HashMap<>();
        ArrayList<String> menuDocs = new ArrayList<>();
        menuDocs.add(RADIO);
        menuDocs.add(SCANNER);
        menuDocs.add(ANALYSE);
        menuDocs.add("Save and Exit;");
        System.out.print("\nIs there any documents ? 1- Yes\t 2-No >>\t");
        int res = in.nextInt();
        boolean exit = false;
        if(res == 1){
            System.out.println("Which document you want to add?");
            do {
                for (String m: menuDocs){
                    System.out.println((menuDocs.indexOf(m)+1) +"- "+m);
                }
                int docTypeScan = in.nextInt();
//                switch (documents.size()){
//                    case 1 -> docTypeScan++;
//                    case 2 -> docTypeScan+=2;
//                    case 3 -> docTypeScan+=3;
//                }
                switch (docTypeScan){
                    case 1 -> {
                        documents.put(RADIO, setRadios(dossier));
//                        menuDocs.remove("RADIO");
                    }
                    case 2 -> {
                        documents.put(SCANNER, setScanners(dossier));
//                        menuDocs.remove("SCANNER");
                    }
                    case 3 -> {
                        documents.put(ANALYSE, setAnalyses(dossier));
//                        menuDocs.remove("ANALYSE");
                    }
                    case 4 -> {
                        for (Map.Entry<String, ArrayList<Documents>> doc: documents.entrySet()){
                            this.documents.addAll(doc.getValue());
                        }
                    }
                }
                if(docTypeScan == 4)
                    exit = true;
            }while(!exit);
        }
    }

    public ArrayList<Documents> setRadios(Dossiers dossier){
        ArrayList<Documents> radioDocs = new ArrayList<>();
        System.out.print("\n Radio documents: \n");
        int res = 1;
        int i = 0;
        boolean status = true;
        do {
            if(i==0)
                System.out.println((i+1) + "er Document:");
            else System.out.println((i+1) + "eme Document:");
            System.out.print("\tEnter the price: ");
            double price = in.nextDouble();
            Documents tmpDoc = new Documents(getIdDossier(dossier), price, "RADIO");
            radioDocs.add(tmpDoc);
            System.out.print("\nStill any document(Radio) ? 1- Yes\t 2-No >>  ");
            res = in.nextInt();
            System.out.print("\n");
            if(res == 2)
                status = false;
            else i++;
        }while (status);
        return radioDocs;
//        System.out.println(medicaments.get(0).getCodeBarre() +" \t"+medicaments.get(0).getPrice());
    }

    public ArrayList<Documents> setScanners(Dossiers dossier){
        ArrayList<Documents> scannerDocs = new ArrayList<>();
        System.out.print("\n Scanner documents: \n");
        int res = 1;
        int i = 0;
        boolean status = true;
        do {
            if(i==0)
                System.out.println((i+1) + "er Document:");
            else System.out.println((i+1) + "eme Document:");
            System.out.print("\nEnter the price: ");
            double price = in.nextDouble();
            Documents tmpDoc = new Documents(getIdDossier(dossier), price, "SCANNER");
            scannerDocs.add(tmpDoc);
            System.out.print("\n Still any document(SCANNER) ? 1- Yes\t 2-No >>  ");
            res = in.nextInt();
            System.out.print("\n");
            if(res == 2)
                status = false;
            else i++;
        }while (status);
        return scannerDocs;
//        System.out.println(medicaments.get(0).getCodeBarre() +" \t"+medicaments.get(0).getPrice());
    }

    public ArrayList<Documents> setAnalyses(Dossiers dossier){
        ArrayList<Documents> analyseDocs = new ArrayList<>();
        System.out.print("\n Analyse documents: \n");
        int res = 1;
        boolean status = true;
        do {
            System.out.print("\nEnter the price: ");
            double price = in.nextDouble();
            Documents tmpDoc = new Documents(getIdDossier(dossier), price, "ANALYSE");
            analyseDocs.add(tmpDoc);
            System.out.print("\n Still any document(ANALYSE) ? 1- Yes\t 2-No >>  ");
            res = in.nextInt();
            System.out.print("\n");
            if(res == 2)
                status = false;
        }while (status);
        return analyseDocs;
    }

    public int getIdDossier(Dossiers dossier){
        return  dossier.getDossierByCode(dossier.getCodeDossier());
    }

    public double calculateRemDocs(){
        Documents d = new Documents();
        double total = 0.00;
        HashMap<String, Double> refundableDocs = d.getAllRefundable();
        for(Documents doc: documents){
            switch (doc.getType()){
                case "RADIO" -> total+= doc.getPrice()*(refundableDocs.get(RADIO)/100);
                case "SCANNER" -> total+= doc.getPrice()*(refundableDocs.get(SCANNER)/100);
                case "ANALYSE" -> total+= doc.getPrice()*(refundableDocs.get(ANALYSE)/100);
            }
        }
        return total;
    }

    public double calculateRemMed(){
        Medicaments m = new Medicaments();
        double total = 0.00;
        HashMap<String, Double> refundableMeds = m.getAllRefundable();
        for(Medicaments med: medications){
            for (Map.Entry<String, Double> refMeds: refundableMeds.entrySet())
                if(Objects.equals(med.getCodeBarre(), refMeds.getKey()))
                    total+=refMeds.getValue();
        }
        return total;
    }

    public void manageDossier(){
        ArrayList<Dossiers> dossiers = dossierImplementation.allDossiers();
        System.out.println("\t\t####  MANAGE DOSSIERS  ####");
        System.out.println("All Pending dossier: ");
        for(Dossiers dos: dossiers){
            System.out.println(dos.toString());
        }
    }
}
