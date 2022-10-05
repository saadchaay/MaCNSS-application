package org.cnss.entities;

import org.cnss.helpers.Database;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Agents {
    private String email;
    private String password;
    private String role;
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

    public Boolean save(){
        String sql = "INSERT INTO users (email, password, verified) VALUES " +
                "('"+ this.email +"', '"+ this.password +"', true );";
        return db.execute(sql);
    }

    public Agents show(int id){
        String sql = "SELECT * FROM users WHERE id = "+id;
        if(db.execute(sql)){
            ResultSet res = db.resultSet(sql);
            try {
                while ( res.next() ){
                    this.email = res.getString("email");
                    this.password = res.getString("password");
                    this.role = res.getString("role");
                    this.verified = res.getBoolean("verified");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return this;
    }

    public ArrayList<Agents> all(){
        ArrayList<Agents> agents = new ArrayList<>();

//        if(db.execute("SELECT * FROM users WHERE role !='admin'")){
            ResultSet res = db.resultSet("SELECT * FROM users WHERE role !='admin'");

            try{
                while ( res.next() ){
                    this.email = res.getString("email");
                    this.password = res.getString("password");
                    this.role = res.getString("role");
                    this.verified = res.getBoolean("verified");
                    System.out.println(email);
                    agents.add(this);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
//        }
        return agents;
    }

    //get specific Admin
    public ArrayList<Agents> allAdmin(){
        ArrayList<Agents> agents = new ArrayList<>();
//        if(db.execute("SELECT * FROM users WHERE role !='admin'")){
            ResultSet res = db.resultSet("SELECT * FROM users WHERE role ='admin'");
            System.out.println("test");
            try{
                System.out.println("inside while");
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
//        }
        return agents;
    }

    //get specific admin
    public boolean ifAdminExist(String email,String password){
        Boolean exist = false;
        ArrayList<Agents> listAdmin = allAdmin();
        for (Agents admin: listAdmin) {
            if(admin.getEmail().equals(email) && admin.getPassword().equals(password)){
                exist = true;
            }
        }
        return exist;
//        return (allAdmin().contains(email) && allAdmin().contains(password))? true : false;
    }

    //check if Agent exist
    public boolean ifExist(String email,String password){
        System.out.println("tttttttttttttttttttest");
//        boolean exist = false;
//        return (all().contains(email) && all().contains(password))? true : false;
        ArrayList<Agents> listAgent = all();

        for (Agents agent : listAgent) {
            System.out.println(agent.getEmail());
            if(agent.getEmail().equals(email) && agent.getPassword().equals(password)){
               return true;
            }
        }
        return false;
    }
}
