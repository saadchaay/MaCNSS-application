package org.cnss.entities;

import org.cnss.helpers.Database;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Agents {
    private int ID;
    private String email;
    private String password;
    private String role;
    private boolean verified;

    Database db;

    //    *************************************************************** constructors *********************
    public Agents(){
        db = new Database();
    }

    public Agents(int id,String email, String password, String role, boolean verified) {
        db = new Database();
        this.ID = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.verified = verified;
    }


    //    *************************************************************** getters *********************
    public int getID() {
        return ID;
    }

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

    @Override
    public String toString() {
        return "" +
                "ID=" + ID +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", verified=" + verified ;
    }

    public Boolean save(){
        String sql = "INSERT INTO users (email, password, verified) VALUES " +
                "('"+ this.email +"', '"+ this.password +"', true );";
        return db.execute(sql);
    }

    public Agents show(int id){
        String sql = "SELECT * FROM users WHERE id = "+id;
        ResultSet res = db.resultSet(sql);
        try {
            while ( res.next() ){
                this.ID = res.getInt("id");
                this.email = res.getString("email");
                this.password = res.getString("password");
                this.role = res.getString("role");
                this.verified = res.getBoolean("verified");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return this;
    }

    public ArrayList<Agents> all(){
        ArrayList<Agents> agents = new ArrayList<>();
        ResultSet res = db.resultSet("SELECT * FROM users WHERE role !='admin'");
        try{
            while ( res.next() ){
                this.ID = res.getInt("id");
                this.email = res.getString("email");
                this.password = res.getString("password");
                this.role = res.getString("role");
                this.verified = res.getBoolean("verified");
                Agents newAgent = new Agents(this.ID, this.email, this.password, this.role, this.verified);
                agents.add(newAgent);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return agents;
    }

    //get specific Admin
    public ArrayList<Agents> allAdmin(){
        ArrayList<Agents> agents = new ArrayList<>();

            ResultSet res = db.resultSet("SELECT * FROM users WHERE role ='admin'");
            try{
                while ( res.next() ){
                    this.email = res.getString("email");
                    this.password = res.getString("password");
                    this.role = res.getString("role");
                    this.verified = res.getBoolean("verified");
                    agents.add(this);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        return agents;
    }

    //get specific admin
    public boolean ifAdminExist(String email,String password){

        boolean exist = false;
        ArrayList<Agents> listAdmin = allAdmin();
        for (Agents admin: listAdmin) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                exist = true;
                break;
            }
        }
        return exist;

    }

    //check if Agent exist
    public boolean ifExist(String email,String password){


        ArrayList<Agents> listAgent = all();
        for (Agents agent : listAgent) {

            if(agent.getEmail().equals(email) && agent.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
