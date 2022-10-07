package org.cnss.entities;

import org.cnss.helpers.EnumValues;

import java.sql.ResultSet;
import java.util.HashMap;

public class Documents extends Papiers {

    private String type;

    public Documents(String type) {
        this.type = type;
    }

    public Documents(int dossierID, double price, String type) {
        super(dossierID, price);
        this.type = type;
    }

    public Documents() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        EnumValues Type = () -> {
            return EnumValues.documentType.valueOf(type).toString();
        };
        this.type = Type.setValue();
    }


    public HashMap<String, Double> getAllRefundable(){
        HashMap<String, Double> refundableDocs = new HashMap<>();
        String sql = "SELECT * FROM refundabledocs";
        try {
            ResultSet res = db.resultSet(sql);
            while(res.next()){
                refundableDocs.put(res.getString("type"),res.getDouble("refpercentage"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return refundableDocs;
    }
}
