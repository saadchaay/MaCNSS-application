package org.cnss;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

class ConnectDB {
    private String database;
    private String username;
    private String password;
    Connection cnx = null;
    private Statement stmt = null;

    public ConnectDB(String database, String username, String password) {
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public String testConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            cnx = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+database, username, password);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return "Opened connexion successfully";
    }
}
