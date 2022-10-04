package org.cnss.helpers;

import java.sql.*;

public class Database {
    private String database = "cnss-app";
    private String username = "postgres";
    private String password = "admin";
    Connection cnx = null;
    private Statement stmt = null;

    public Database() {
        try {
            Class.forName("org.postgresql.Driver");
            cnx = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+database, username, password);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened connexion successfully");

    }

    public void query(String request){
        try {
            stmt = cnx.createStatement();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean execute(String rq) {
        this.query(rq);
        try {
            stmt.executeUpdate(rq);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ResultSet resultSet(String s){
        this.query(s);
        try {
            ResultSet result = stmt.executeQuery(s);
            return result;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void closeCnx(){
        try {
            stmt.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
