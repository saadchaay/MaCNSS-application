package org.cnss.entities;

import org.cnss.helpers.*;
import java.time.LocalDate;

public class Dossiers{
    private int codeDossier;
    private int matriculePatient;
    private LocalDate appliedDate;
//    private enum Status {PENDING, REFUSED, VALIDATE};
    private String status;
    private Double montantRem;

    public Dossiers() {
        EnumValues s1 = () -> {
            return EnumValues.status.PENDING.toString();
        };
        this.status = s1.setValue();
        this.montantRem = 0.00;
    }

    public Dossiers(int codeDossier, int matriculePatient, LocalDate appliedDate) {
        this.codeDossier = codeDossier;
        this.matriculePatient = matriculePatient;
        this.appliedDate = appliedDate;
        EnumValues s1 = () -> {
            return EnumValues.status.PENDING.toString();
        };
        this.status = s1.setValue();
        this.montantRem = 0.00;
    }

    public int getCodeDossier() {
        return codeDossier;
    }

    public void setCodeDossier(int codeDossier) {
        this.codeDossier = codeDossier;
    }

    public int getMatriculePatient() {
        return matriculePatient;
    }

    public void setMatriculePatient(int matriculePatient) {
        this.matriculePatient = matriculePatient;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public Double getMontantRem() {
        return montantRem;
    }

    public void setMontantRem(Double montantRem) {
        this.montantRem = montantRem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        EnumValues s1 = () -> {
            return EnumValues.status.valueOf(status.toUpperCase()).toString();
        };
        this.status = s1.setValue();
    }


}
