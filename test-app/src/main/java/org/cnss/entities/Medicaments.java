package org.cnss.entities;

import java.sql.ResultSet;
import java.util.HashMap;

public class Medicaments extends Papiers{

    private String codeBarre;

    public Medicaments(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public Medicaments(int dossierID, double price, String codeBarre) {
        super(dossierID, price);
        this.codeBarre = codeBarre;
    }

    public Medicaments() {}

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public boolean checkRemMedicament(String codeBarre) {
        String sql = "SELECT * FROM refundablemedicals WHERE codebarre = '"+codeBarre+"';" ;
        try {
            ResultSet res = db.resultSet(sql);
            while (res.next()){
                return res.getString("codeBarre") != null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public HashMap<String, Double> getAllRefundable(){
        HashMap<String, Double> refundableMeds = new HashMap<>();
        String sql = "SELECT * FROM refundablemedicals";
        try {
            ResultSet res = db.resultSet(sql);
            while(res.next()){
                refundableMeds.put(res.getString("codebarre"),res.getDouble("refprice"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return refundableMeds;
    }
}
