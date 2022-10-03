package org.cnss;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Database cn = new Database();
        Scanner in = new Scanner(System.in);

        /*List<String> ns = Arrays.asList("Adam","Omar","Aymen","Sofia");
        List<String> square = ns.stream().map(el -> "Hello\t"+el).collect(Collectors.toList());
        System.out.println("enter the name >> ");
        String name = in.nextLine();
        System.out.println("enter the address >> ");
        String address = in.next();
        System.out.println("enter the age >> ");
        int age = in.nextInt();

        // create table in db
        String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (1, '"+ name +"', "+ age +", '"+ address +"', 20000.00 );";
        if(cn.execute(sql)){
            System.out.println("add success");
        }*/

        Dossiers d1 = new Dossiers();
        System.out.println(d1.getStatus());
        d1.setStatus("refused");
        System.out.println(d1.getStatus());

    }
}