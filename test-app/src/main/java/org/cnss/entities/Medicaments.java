package org.cnss.entities;

public class Medicaments extends Papiers{

    private int codeBarre;

    public Medicaments(int codeBarre) {
        this.codeBarre = codeBarre;
    }

    public Medicaments(int dossierID, double price, int codeBarre) {
        super(dossierID, price);
        this.codeBarre = codeBarre;
    }

    public int getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(int codeBarre) {
        this.codeBarre = codeBarre;
    }
}
