package org.cnss.entities;

import org.cnss.helpers.EnumValues;

public class Documents extends Papiers {

    private String type;

    public Documents(String type) {
        this.type = type;
    }

    public Documents(int dossierID, double price, String type) {
        super(dossierID, price);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        EnumValues Type = () -> {
            return EnumValues.documentType.valueOf(type).toString();
        };
        this.type = Type.setValue();
    }
}
