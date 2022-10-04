package org.cnss.entities;

import org.cnss.helpers.Database;

import java.sql.ResultSet;

public class Agents {
    private String email;
    private String password;
    private boolean verified;

    private Database db;

    //    *************************************************************** constructors *********************
    public Agents(){
        db = new Database();
    }
    public Agents(String email, String password, boolean verified) {
        db = new Database();
        this.email = email;
        this.password = password;
        this.verified = verified;
    }


    //    *************************************************************** getters *********************
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVerified() {
        return verified;
    }


    //    *************************************************************** setters *********************
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Boolean save(Agents agent){
        String sql = "INSERT INTO users (email, password, verified) VALUES " +
                "("+ agent.email +"', "+ agent.password +", true );";
        if(db.execute(sql))
            return true;
        else return false;
    }

//    public Agents show(int id){
//        String sql = "SELECT * FROM users WHERE id = "+id;
//        if(db.execute(sql)){
//            ResultSet res = db.resultSet(sql);
//        }
////        Agents newAgent = new Agents(res.get(0))
//    }
}
