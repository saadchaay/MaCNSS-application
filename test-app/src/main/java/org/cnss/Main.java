package org.cnss;

public class Main {
    public static void main(String[] args) {
        ConnectDB cn = new ConnectDB("cnss-app", "postgres", "admin");
        System.out.println(cn.testConnection());

    }
}